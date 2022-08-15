package com.example.demo.record;

import com.example.demo.entity.ElectricityService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record ElectricityServiceRecord(Long serviceId,
                                       @NotNull Long accountId,
                                       @NotNull Long addressId,
                                       @Min(value = 1, message = "Service kw can not be lower than 1") Integer kw,
                                       String note,
                                       Long count) {

    public static ElectricityServiceRecord convertToRecord(ElectricityService service) {
        return new ElectricityServiceRecord(
                service.getId(),
                service.getAccount().getId(),
                service.getAddress().getId(),
                service.getKw(),
                service.getNote(),
                null);
    }
}
