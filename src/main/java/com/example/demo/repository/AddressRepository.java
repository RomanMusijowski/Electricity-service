package com.example.demo.repository;

import com.example.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select (count(a) > 0) from address a " +
            "where (lower(a.country) like lower(:country) ) " +
            "and (lower(a.voivodeship) like lower(:voivodeship)) " +
            "and (lower(a.city) like lower(:city)) " +
            "and (lower(a.street) like lower(:street)) " +
            "and (a.houseNumber = :houseNumber) " +
            "and (a.flatNumber = :flatNumber) ")
    boolean existsByProperties(String country, String voivodeship, String city, String street, Integer houseNumber, Integer flatNumber);

//    @Query("""
//            select (count(a) > 0) from address a
//            where upper(a.country) like upper(?1)
//            and upper(a.voivodeship) like upper(?2)
//            and upper(a.city) like upper(?3)
//            and upper(a.zipCode) like upper(?4)
//            and upper(a.street) like upper(?5)
//            and a.houseNumber = ?6
//            and a.flatNumber = ?7""")
//    boolean existsByProperties(String country, String voivodeship, String city, String zipCode, String street, Integer houseNumber, Integer flatNumber);
}
