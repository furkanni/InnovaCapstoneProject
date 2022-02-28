package com.innova.customer.service;

import com.innova.customer.dto.CustomerDto;
import com.innova.customer.entity.CustomerEntity;
import com.innova.customer.exception.ResourceNotFoundException;
import com.innova.customer.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service
 */
@Log4j2
@Service
@AllArgsConstructor
public class CustomerService {


    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @return List of all customers
     */
    public List<CustomerDto> getAll() {
        try {
            List<CustomerEntity> list = customerRepository.findAll();
            List<CustomerDto> dtoList = list.stream().map(customerEntity -> modelMapper.map(customerEntity, CustomerDto.class)).collect(Collectors.toList());
            return dtoList;
        } catch (Exception e) {
            throw new ResourceNotFoundException("There is no Customer!");
        }
    }

    /**
     * @param dto DTO
     * @return Creted DTO
     */
    public CustomerDto save(CustomerDto dto) {
        try {
            CustomerEntity entity = modelMapper.map(dto, CustomerEntity.class);
            return modelMapper.map(customerRepository.save(entity), CustomerDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Could Not Saved!");
        }

    }

    /**
     * @param identityNumber Unique customer Identity information
     * @return CustomerEntity Information
     */
    public CustomerDto getByIdentityNumber(Long identityNumber) {
        try {
            return modelMapper.map(customerRepository.findByIdentityNumber(identityNumber), CustomerDto.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Customer Could Not Found with this identity number: " + identityNumber);
        }
    }

    /**
     * @param id Unique Id of customer
     */
    public boolean deleteCustomer(Long id) {
        try {
            CustomerEntity customerEntity = customerRepository.findById(id).get();
            customerRepository.delete(customerEntity);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage() + " Customer Could Not Deleted.");
            return false;
        }
    }

    /**
     * @param id Unique Id of customer
     * @return CustomerEntity information
     * @throws Exception e
     */
    public CustomerDto getCustomerById(Long id) {
        try {
            CustomerEntity entity = customerRepository.findById(id).get();
            CustomerDto customer = modelMapper.map(entity, CustomerDto.class);
            return customer;

        } catch (Exception e) {
            throw new ResourceNotFoundException("Customer did not found with this id: " + id);
        }


    }

    /**
     * @param id          Unique Id of customer
     * @param customerDto dto
     * @return
     */
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {

        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);

        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Does Not Exist with this id: " + id));

        customer.setName(customerEntity.getName());
        customer.setSurname(customerEntity.getSurname());
        customer.setIncome(customerEntity.getIncome());
        customer.setPhoneNumber(customerEntity.getPhoneNumber());

        CustomerEntity updatedCustomerEntity = customerRepository.save(customer);
        CustomerDto updatedCustomerDto = modelMapper.map(updatedCustomerEntity, CustomerDto.class);
        return updatedCustomerDto;


    }
}
