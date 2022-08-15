package com.example.demo.record;

import com.example.demo.entity.Address;

import javax.validation.constraints.*;

public record AddressRecord(Long id,
                            @NotBlank @Size(min = 3, max = 255) String country,
                            @NotBlank @Size(min = 3, max = 255) String voivodeship,
                            @NotBlank @Size(min = 3, max = 255) String city,
                            @NotNull String zipCode,
                            @NotBlank @Size(min = 3, max = 255) String street,
                            @NotNull @Min(1) Integer houseNumber,
                            Integer flatNumber) {
    public static AddressRecord convertToRecord(Address address) {
        return new AddressRecord(
                address.getId(),
                address.getCountry(),
                address.getVoivodeship(),
                address.getCity(),
                address.getZipCode(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getFlatNumber());
    }
}