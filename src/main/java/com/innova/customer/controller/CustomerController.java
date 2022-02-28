package com.innova.customer.controller;

import com.innova.customer.dto.CustomerDto;
import com.innova.customer.exception.ResourceNotFoundException;
import com.innova.customer.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class has been created for API's CRUD operations of CustomerEntity
 */
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    /**
     * @param identityNumber Identity number of the customer
     * @return CustomerEntity list or a single customer by identityNumber value
     */
    @GetMapping("/customer")
    public ResponseEntity<?> getAllCustomers(@RequestParam(required = false) Long identityNumber) {
        if (identityNumber == null) {
            try {
                log.info("All Customers List Gotten");
                List<CustomerDto> customerDto = customerService.getAll();
                return ResponseEntity.ok(customerDto);

            } catch (ResourceNotFoundException e) {
                Map error = new HashMap();
                error.put("error", e.getMessage());
                log.error(e.getMessage());
                return ResponseEntity.badRequest().body(error);
            }
        } else {
            try {
                CustomerDto customerDto = customerService.getByIdentityNumber(identityNumber);
                return ResponseEntity.ok(customerDto);
            } catch (ResourceNotFoundException e) {
                Map error = new HashMap();
                error.put("error", e.getMessage());
                log.error(" Customer with this identity number did not found " + identityNumber);
                return ResponseEntity.badRequest().body(error);
            }
        }

    }

    /**
     * @param customerDto dto
     * @return creted dto
     */
    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            return ResponseEntity.ok(customerService.save(customerDto));
        } catch (RuntimeException e) {
            Map error = new HashMap();
            error.put("error", e.getMessage());
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    /**
     * @param id unique customer ID
     * @return information about customer by given ID
     */
    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerService.getCustomerById(id));
        } catch (ResourceNotFoundException e) {
            Map error = new HashMap();
            error.put("error", e.getMessage());
            log.error("Customer with this id number did not found " + id);
            return ResponseEntity.badRequest().body(error);
        }


    }


    /**
     * @param id unique customer ID
     * @return boolean value as result for deleting operation
     */
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable Long id) {
        return ResponseEntity.ok().body(customerService.deleteCustomer(id));
    }


    /**
     * @param id          unique customer ID
     * @param customerDto dto
     * @return
     */
    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {

        try {
            customerService.updateCustomer(id, customerDto);
            log.info("Customer Updated");
            return ResponseEntity.ok(customerDto);
        } catch (ResourceNotFoundException e) {
            Map error = new HashMap();
            error.put("error", e.getMessage());
            log.error("Customer Did Not Updated, id: " + id);
            return ResponseEntity.badRequest().body(error);
        }
    }

}
