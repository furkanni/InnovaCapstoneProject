package com.innova.customer.service;

import com.innova.customer.dto.CustomerDto;
import com.innova.customer.entity.CustomerEntity;
import com.innova.customer.repository.ICustomerRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Spy
    private ModelMapper modelMapper;


    @Test
    @Order(1)
    void getAll() {
        List<CustomerDto> expectedCustomerInformations = Arrays.asList(
                new CustomerDto(1L, "Test-Name-1", "Test-Surname-1", 25986458911L, "05305303030", 4000D),
                new CustomerDto(1L, "Test-Name-2", "Test-Surname-2", 25986458912L, "05305303031", 5000D),
                new CustomerDto(1L, "Test-Name-3", "Test-Surname-3", 25986458913L, "05305303032", 6000D)
        );

        when(customerService.getAll()).thenReturn(expectedCustomerInformations);


        assertEquals(customerService.getAll(), expectedCustomerInformations);
        assertEquals(customerService.getAll().size(), expectedCustomerInformations.size());
    }

    @Test
    @Order(2)
    void getCustomer_successful() {
        CustomerEntity expectedCustomer = new CustomerEntity(1L, "Test-Name-1", "Test-Surname-1", 25986458911L, "05305303030", 4000D);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(expectedCustomer));

        CustomerEntity a = modelMapper.map(customerService.getCustomerById(1L), CustomerEntity.class);
        assertEquals(a, expectedCustomer);
        verify(customerRepository).findById(1L);

    }


    @Test
    @Order(3)
    void addCustomerTest() {

        CustomerEntity expectedCustomer = new CustomerEntity(1L, "Test-Name-1", "Test-Surname-1", 25986458911L, "05305303030", 4000D);

        when(customerRepository.save(expectedCustomer)).thenReturn(expectedCustomer);

        customerService.save(modelMapper.map(customerRepository.save(expectedCustomer), CustomerDto.class));

        verify(customerRepository, times(1)).save(expectedCustomer);

    }

    @Test
    @Order(5)
    void deleteCustomerTest() {

        customerRepository.deleteById(1L);
        verify(customerRepository, times(1)).deleteById(1L);

    }

    @Test
    @Order(6)
    void getByIdentity() {

        CustomerEntity customer = new CustomerEntity(1L, "Test-Name-1", "Test-Surname-1", 123456789L, "05305303030", 4000D);

        when(customerRepository.findByIdentityNumber(123456789L)).thenReturn(customer);

        assertEquals(customerService.getByIdentityNumber(123456789L), modelMapper.map(customer,CustomerDto.class));
    }
}
