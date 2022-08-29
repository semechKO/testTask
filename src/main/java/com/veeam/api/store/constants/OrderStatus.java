package com.veeam.api.store.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderStatus {
    @JsonProperty("placed")
    PLACED,
    @JsonProperty("approved")
    APPROVED,
    @JsonProperty("delivered")
    DELIVERED;
}
