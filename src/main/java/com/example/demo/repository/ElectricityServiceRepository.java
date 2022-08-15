package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.ElectricityService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElectricityServiceRepository extends JpaRepository<ElectricityService, Long> {

    @Query("select sum(e.kw) " +
            "from electricity_service e " +
            "join e.address a " +
            "where a.zipCode = :zipCode")
    public Optional<Integer> countKwForZipCode(String zipCode);

    @Query("select sum(e.kw) " +
            "from electricity_service e " +
            "join e.address a " +
            "where a.zipCode = '' or a.zipCode is null")
    public Optional<Integer> countKwWithoutZipCode();

    public List<ElectricityService> findAllByAccount(Account account);
}