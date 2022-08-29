package com.veeam.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class DataTransferObject {

    @Override
    public String toString() {
        return Serializer.getJson(this, JsonInclude.Include.NON_NULL);
    }
}
