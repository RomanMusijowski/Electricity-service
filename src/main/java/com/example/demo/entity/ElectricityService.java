package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "electricity_service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ElectricityService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @OneToOne
    private Address address;
    private Integer kw;
    private String note;
}
