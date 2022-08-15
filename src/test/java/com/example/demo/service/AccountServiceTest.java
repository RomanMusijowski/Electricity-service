package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.generator.AccountGenerator;
import com.example.demo.generator.RoleGenerator;
import com.example.demo.record.AccountRecord;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldCreateUser() {
        Account account = AccountGenerator.generateAccount();
        Role role = RoleGenerator.generateUserRole();
        AccountRecord record = new AccountRecord(null, account.getName(), account.getSurname(), null);
        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(role));
        when(accountRepository.save(any())).thenReturn(account);

        AccountRecord createdAccount = accountService.createAccount(record);

        assertThat(createdAccount.name()).isEqualTo(record.name());
        assertThat(createdAccount.surname()).isEqualTo(record.surname());
    }

    @Test
    void shouldThrowOnAccountUpdate() {
        Account account = AccountGenerator.generateAccount();
        AccountRecord record = new AccountRecord(null, account.getName(), account.getSurname(), null);
        assertThrows(EntityNotFoundException.class, () -> accountService.update(1L, record));
    }
}