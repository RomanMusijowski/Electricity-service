package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.record.AccountCriteriaRecord;
import com.example.demo.record.AccountRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByIdIn(List<Long> ids, Pageable pageable);

    Optional<Account> findByName(String name);

    @Query("select new com.example.demo.record.AccountRecord(c.id, c.name, c.surname, count(s)) " +
            "from account c  " +
            "left join c.services s " +
            "where (:#{#criteria.accountIds} is null or c.id in :#{#criteria.accountIds}) " +
            "and (:#{#criteria.name} is null or lower(c.name) like concat('%', lower(cast(:#{#criteria.name} as text)), '%')) " +
            "and (:#{#criteria.surname} is null or lower(c.surname) like concat('%', lower(cast(:#{#criteria.surname} as text)), '%')) " +
            "group by c.id, c.name, c.surname "  +
            "having count(s) > :#{#criteria.moreServicesThan} OR :#{#criteria.moreServicesThan} = null")
    List<AccountRecord> findAllByCriteria(AccountCriteriaRecord criteria, Pageable pageable);
}
