package com.veeam.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public final class PropertiesProvider {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${accept.type}")
    private String acceptType;
}
