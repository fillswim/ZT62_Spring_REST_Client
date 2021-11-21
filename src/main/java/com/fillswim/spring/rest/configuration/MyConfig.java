package com.fillswim.spring.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.fillswim.spring.rest")
public class MyConfig {

    // Необходим для осуществления HTTP-Requests
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
