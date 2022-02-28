package com.innova.customer.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created modelMapper method to use anywhere needed in project
 */
@Configuration
public class ModelMapperBean {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
