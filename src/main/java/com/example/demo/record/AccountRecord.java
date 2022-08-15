package com.example.demo.record;

import com.example.demo.entity.Account;

public record AccountRecord(Long id, String name, String surname, Long amountOfServices) {

    public static AccountRecord convertToRecord(Account account) {
        return new AccountRecord(account.getId(), account.getName(), account.getSurname(), null);
    }
}
