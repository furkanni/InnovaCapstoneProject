package com.innova.customer.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity
 */
@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "identity_number", nullable = false, unique = true)
    private Long identityNumber;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "income", nullable = false)
    private Double income;

    public CustomerEntity(Long id, String name, String surname, Long identityNumber, String phoneNumber, Double income) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.identityNumber = identityNumber;
        this.phoneNumber = phoneNumber;
        this.income = income;
    }
}
