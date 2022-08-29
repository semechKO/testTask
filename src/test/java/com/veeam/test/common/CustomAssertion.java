package com.veeam.test.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Class contains methods for HTTP ResponseEntity assertions for status codes 404, 400, 200
 */
public final class CustomAssertion {

    /**
     * 404 - Not Found
     */
    public static <T> void assertNotFoundResponse(ResponseEntity<T> responseEntity) {
        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND),
                () -> assertThat(responseEntity.getBody()).isNotNull()
        );
    }

    public static void assertNotFoundStringResponse(ResponseEntity<String> responseEntity) {
        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND),
                () -> assertThat(responseEntity.getBody()).isNotEmpty()
        );
    }

    /**
     * 400 - Bad Request
     */
    public static <T> void assertBadRequestResponse(ResponseEntity<T> responseEntity) {
        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST),
                () -> assertThat(responseEntity.getBody()).isNotNull()
        );
    }

    public static <T> void assertBadRequestStringResponse(ResponseEntity<T> responseEntity, String expectedError) {
        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST),
                () -> assertThat(responseEntity.getBody().equals(expectedError))
        );
    }

//    @SneakyThrows
//    public static void assertResponseWithErrorMessages(ResponseEntity<String> responseEntity, Map<String, String> errorMap) {
//        Map<String, String> map = new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<Map<String, String>>() {});
//
//        assertAll(
//                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST),
//                () -> assertThat(map).isEqualTo(errorMap)
//        );
//    }

    /**
     * 200 - OK
     */
    public static <T> void assertOkResponse(ResponseEntity<T> responseEntity, Object model) {
        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(responseEntity.getBody()).isEqualTo(model)
        );
    }
}