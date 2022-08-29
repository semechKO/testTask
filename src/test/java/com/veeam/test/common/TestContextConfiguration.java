package com.veeam.test.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {
        "com.veeam.api",
        "com.veeam.test"
})
@PropertySource("classpath:/application.properties")
public class TestContextConfiguration {

}
