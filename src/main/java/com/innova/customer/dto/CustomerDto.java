package com.innova.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private Long identityNumber;
    private String phoneNumber;
    private Double income;
}
