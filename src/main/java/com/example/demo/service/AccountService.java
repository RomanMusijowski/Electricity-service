package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.record.AccountCriteriaRecord;
import com.example.demo.record.AccountRecord;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final RoleRepository roleRepository;

    public Account findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("User with name: " + name + " not found."));
    }

    public AccountRecord createAccount(AccountRecord record) {
        Account save = repository.save(
                Account.builder()
                        .name(record.name())
                        .surname(record.surname())
                        .roles(Set.of(getUserRole()))
                        .build());
        return AccountRecord.convertToRecord(save);
    }

    private Role getUserRole() {
        return roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("Role with name: " + RoleName.ROLE_USER + " wasn't found."));
    }

    public AccountRecord update(Long id, AccountRecord record) {
        Account account = getAccount(id);
        account.setName(record.name());
        account.setSurname(record.surname());
        return AccountRecord.convertToRecord(repository.save(account));
    }

    public Account getAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("account with id: " + id + " not found."));
    }

    public List<AccountRecord> getAccountByFilter(AccountCriteriaRecord criteria, Pageable pageable) {
        List<AccountRecord> allByFilter = repository.findAllByCriteria(criteria, pageable);
        return allByFilter;
    }
}