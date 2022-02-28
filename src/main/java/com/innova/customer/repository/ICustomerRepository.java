package com.innova.customer.repository;

import com.innova.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository service for connection of Databases
 * Also includes a unique find method for Customer Service
 */
@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByIdentityNumber(Long identityNumber);
}
