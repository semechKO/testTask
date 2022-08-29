package com.veeam.api;

import com.github.javafaker.Faker;
import com.veeam.api.config.PropertiesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Autowired
    private PropertiesProvider propertiesProvider;

    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public HttpHeaders httpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", propertiesProvider.getAcceptType());
        headers.set("Content-Type", "application/json");
        return headers;
    }

}
