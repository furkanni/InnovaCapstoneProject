package com.innova.customer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.innova.customer.dto.CustomerDto;
import com.innova.customer.entity.CustomerEntity;
import com.innova.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();

    }

    @Test
    @Order(1)
    void createCustomerTest() throws Exception {

        List<CustomerDto> expectedCustomers = getTestCustomers();

        CustomerDto expectedCustomer = new CustomerDto();
        expectedCustomer.setName("Test-Name-5");
        expectedCustomers.add(expectedCustomer);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedCustomerJsonStr = ow.writeValueAsString(expectedCustomer);

        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedCustomerJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Mockito.verify(customerService, Mockito.times(1)).save(any());
    }

    @Test
    @Order(2)
    void getALLTest() throws Exception {

        List<CustomerDto> expectedCustomers = getTestCustomers();

        Mockito.when(customerService.getAll()).thenReturn(expectedCustomers);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<CustomerEntity> actualCustomers = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<CustomerEntity>>() {
        });
        assertEquals(expectedCustomers.size(), actualCustomers.size());

    }


    @Test
    @Order(3)
    public void deleteCustomerTest() throws Exception {
        Mockito.when(customerService.deleteCustomer(any())).thenReturn(true);

        MockHttpServletResponse response = mockMvc.perform(delete("/api/v1/customer/5")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    private List<CustomerDto> getTestCustomers() {
        List<CustomerDto> customerEntities = new ArrayList<>();
        customerEntities.add(new CustomerDto(1L, "Test-Name-1", "Test-Surname-1", 25986458911L, "05305303031", 4000D));
        customerEntities.add(new CustomerDto(1L, "Test-Name-2", "Test-Surname-2", 25986458912L, "05305303032", 5000D));
        customerEntities.add(new CustomerDto(1L, "Test-Name-3", "Test-Surname-3", 25986458913L, "05305303033", 6000D));
        customerEntities.add(new CustomerDto(1L, "Test-Name-4", "Test-Surname-4", 25986458914L, "05305303034", 7000D));
        return customerEntities;
    }


}