package com.example.demo.controller;

import com.example.demo.record.ElectricityServiceRecord;
import com.example.demo.service.ElectricityServiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@AllArgsConstructor
public class ServiceController {

    private final ElectricityServiceService service;

    @PostMapping
    public ElectricityServiceRecord createService(@RequestBody @Valid ElectricityServiceRecord record) {
        return service.createService(record);
    }

    @GetMapping("/client")
    public List<ElectricityServiceRecord> gerServicesForClient() {
        return service.getServicesForClient();
    }
}
