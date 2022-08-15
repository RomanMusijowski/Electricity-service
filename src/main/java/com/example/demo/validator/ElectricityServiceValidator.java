package com.example.demo.validator;

import com.example.demo.entity.ElectricityService;
import com.example.demo.exception.ElectricityServiceValidationException;
import com.example.demo.repository.ElectricityServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectricityServiceValidator {

    private final ElectricityServiceRepository electricityServiceRepository;
    public void checkIfUserCanAddOrUpdateElectricityService(ElectricityService service) {
        Optional<String> exceptionMsg = checkAmountOfKW(service.getKw(), service.getAddress().getZipCode());
        if (exceptionMsg.isPresent()) {
            throw new ElectricityServiceValidationException(exceptionMsg.get());
        }
    }

    private Optional<String> checkAmountOfKW(Integer orderKwByUser, String zipCode) {
        Integer usedEnergy = countAvailableEnergy(zipCode);

        if (zipCode.equals("30-120")) {
            return verifyEnergyUpdate(1500, usedEnergy, orderKwByUser);
        } else if (zipCode.startsWith("20")) {
            return verifyEnergyUpdate(750, usedEnergy, orderKwByUser);
        } else {
            return verifyEnergyUpdate(1000, usedEnergy, orderKwByUser);
        }
    }

    private Optional<String> verifyEnergyUpdate(Integer energyLimit, Integer usedEnergy, Integer orderKwByUser) {
        int availableKw = energyLimit - usedEnergy;
        int userCanUseKw = availableKw - orderKwByUser;
        if (userCanUseKw < 0) {
            return Optional.of("Your current electricity limit allows only for : " + (orderKwByUser - Math.abs(userCanUseKw)) + " kw.");
        }
        return Optional.empty();
    }

    private Integer countAvailableEnergy(String zipCode) {
        if (zipCode == null || zipCode.isEmpty()) {
            return electricityServiceRepository.countKwWithoutZipCode()
                    .orElse(0);
        }
        return electricityServiceRepository.countKwForZipCode(zipCode)
                .orElse(0);
    }
}
