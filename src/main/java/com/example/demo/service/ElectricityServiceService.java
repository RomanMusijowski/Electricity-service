package com.example.demo.service;

import com.example.demo.record.ElectricityServiceRecord;
import com.example.demo.entity.Address;
import com.example.demo.entity.Account;
import com.example.demo.entity.ElectricityService;
import com.example.demo.repository.ElectricityServiceRepository;
import com.example.demo.validator.ElectricityServiceValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ElectricityServiceService {

    private final ElectricityServiceRepository electricityServiceRepository;
    private final AddressService addressService;
    private final AccountService accountService;
    private final ElectricityServiceValidator electricityServiceValidator;
    private final AccountAuthenticationService accountAuthenticationService;

    public ElectricityServiceRecord createService(ElectricityServiceRecord record) {
        Address address = addressService.getAddress(record.addressId());
        Account account = accountService.getAccount(record.accountId());
        ElectricityService electricityService = buildService(record, address, account);
        electricityServiceValidator.checkIfUserCanAddOrUpdateElectricityService(electricityService);
        ElectricityService service = electricityServiceRepository.save(electricityService);
        return ElectricityServiceRecord.convertToRecord(service);
    }

    private static ElectricityService buildService(ElectricityServiceRecord record, Address address, Account account) {
        return ElectricityService.builder()
                .address(address)
                .account(account)
                .kw(record.kw())
                .note(record.note())
                .build();
    }

    public List<ElectricityServiceRecord> getServicesForClient() {
        Account account = accountService.findByName(accountAuthenticationService.getAccountFromContext().getName());
        List<ElectricityService> allByAccount = electricityServiceRepository.findAllByAccount(account);
        return allByAccount.stream()
                .map(ElectricityServiceRecord::convertToRecord)
                .collect(Collectors.toList());
    }
}
