package com.example.demo.controller;

import com.example.demo.record.AccountCriteriaRecord;
import com.example.demo.record.AccountRecord;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public AccountRecord createAccount(@RequestBody AccountRecord accountRecord) {
        return accountService.createAccount(accountRecord);
    }

    @GetMapping("/filter")
    public List<AccountRecord> getByFilter(@RequestBody AccountCriteriaRecord criteria,
                                                  @PageableDefault(size = 15)
                                                              @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return accountService.getAccountByFilter(criteria, pageable);
    }

    @PutMapping("/{id}")
    public AccountRecord updateAccount(@PathVariable(name = "id") Long id, @RequestBody AccountRecord accountRecord) {
        return accountService.update(id, accountRecord);
    }
}
