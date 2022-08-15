package com.example.demo.controller;

import com.example.demo.record.AddressRecord;
import com.example.demo.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public AddressRecord createAddress(@RequestBody @Valid AddressRecord record) {
        return addressService.createAddress(record);
    }
}
