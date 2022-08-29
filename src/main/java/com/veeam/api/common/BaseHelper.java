package com.veeam.api.common;

import com.github.javafaker.Faker;
import com.veeam.api.config.PropertiesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
abstract public class BaseHelper {

    @Autowired
    protected Faker faker;

    @Autowired
    private PropertiesProvider propertiesProvider;

    /**
     * Returns random valid Long
     *
     * @return Returns random valid Long
     */
    protected Long getLong() {
        return faker.number().numberBetween(0l, Long.MAX_VALUE);
    }

    /**
     * Returns random valid integer
     *
     * @return Returns integer
     */
    protected Integer getInteger() {
        return faker.number().numberBetween(0, Integer.MAX_VALUE);
    }

    /**
     * Returns date
     *
     * @return Returns date
     */
    protected LocalDateTime getDate() {
        return LocalDateTime.now().withNano((int) 0l);
    }

    /**
     * Returns string date
     *
     * @return Returns string date
     */
    protected String getStringDate() {
        String res = getDate().toString();
        res += propertiesProvider.getAcceptType().contains("json")
                ?".000+0000"
                :"Z";
        return res;
    }
}
