package com.express.employeeservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    @Value("${addressservice.base.url}")
    private String BASE_URL;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

