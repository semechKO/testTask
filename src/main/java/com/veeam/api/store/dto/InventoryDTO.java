package com.veeam.api.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.veeam.api.common.DataTransferObject;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class InventoryDTO extends DataTransferObject {
    private Integer sold;
    private Integer unavailable;
    private Integer pending;
}
