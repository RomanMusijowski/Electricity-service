package com.example.demo.service;

import com.example.demo.record.AddressRecord;
import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address getAddress(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address with id: " + id + " not found."));
    }

    public AddressRecord createAddress(AddressRecord record) {
        checkIfAddressAlreadyExists(record);
        Address address = addressRepository.save(
                Address.builder()
                        .country(record.country())
                        .voivodeship(record.voivodeship())
                        .city(record.city())
                        .zipCode(record.zipCode())
                        .street(record.street())
                        .houseNumber(record.houseNumber())
                        .flatNumber(record.flatNumber())
                        .build());
        return AddressRecord.convertToRecord(address);
    }

    private void checkIfAddressAlreadyExists(AddressRecord record) {
        if (addressRepository.existsByProperties(record.country(),
                record.voivodeship(), record.city(), record.street(), record.houseNumber(), record.flatNumber())) {
            throw new EntityExistsException("This address already exists.");
        }
    }
}
