package com.veeam.api.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.veeam.api.config.PropertiesProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class BaseService {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PropertiesProvider propertiesProvider;

    @Autowired
    private HttpHeaders httpHeaders;

    /**
     * Post requests
     */
    protected <T> ResponseEntity<T> postRequest(String url, DataTransferObject model, Class<T> responseType) {
        url = propertiesProvider.getBaseUrl() + url;
        return addReportDescription(
                "POST",
                url,
                model,
                testRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(model, httpHeaders), responseType)
        );
    }

    /**
     * Get requests
     */
    protected <T> ResponseEntity<T> getRequest(String url, Class<T> responseType) {
        url = propertiesProvider.getBaseUrl() + url;
        return addReportDescription(
                "GET",
                url,
                null,
                testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), responseType)
        );
    }

    //TODO: Refactor

    /**
     * Get request with accept json headers
     * Workaroun for getting inventory
     */
    protected <T> ResponseEntity<T> getRequestOnlyJson(String url, Class<T> responseType) {
        url = propertiesProvider.getBaseUrl() + url;
        httpHeaders.set("accept", "application/json");
        return addReportDescription(
                "GET",
                url,
                null,
                testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), responseType)
        );
    }

    /**
     * Delete requests
     */
    protected <T> ResponseEntity<T> deleteRequest(String url, Class<T> responseType) {
        url = propertiesProvider.getBaseUrl() + url;
        return addReportDescription(
                "DELETE",
                url,
                null,
                testRestTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), responseType)
        );
    }

    /**
     * Method for logging responses. Also can be used for, for example, Allure style attaches
     *
     * @param restType       Request type
     * @param url            URL
     * @param requestBody    body
     * @param responseEntity responseBody
     * @param <T>            type of response body
     * @return responseEntity
     */
    @SneakyThrows
    private <T> ResponseEntity<T> addReportDescription(String restType, String url, Object requestBody, ResponseEntity<T> responseEntity) {
        ObjectMapper mapper = new ObjectMapper();

        String res = "\n\nRequest url: " + restType + "  " + url;
        res += "\nHeaders: " + responseEntity.getHeaders().toString();
        res += "\nResponse code: " + responseEntity.getStatusCode().toString();
        res += responseEntity.hasBody()
                ? "\nResponse body: \n" + Serializer.formatJson(mapper.writeValueAsString(responseEntity.getBody()))
                : "";

        log.info(res);
        return responseEntity;
    }
}