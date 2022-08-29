package com.veeam.api.store.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.veeam.api.common.DataTransferObject;
import lombok.Data;
import com.veeam.api.store.constants.OrderStatus;

@Data
@JacksonXmlRootElement(localName = "Order")
public final class OrderDTO extends DataTransferObject {
    private Long id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private OrderStatus status;
    private Boolean complete;
}
