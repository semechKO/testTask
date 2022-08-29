package com.veeam.api.store.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.veeam.api.common.DataTransferObject;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "apiResponse")
public class DeleteOrderResponseDTO extends DataTransferObject {
    private Integer code;
    private String type;
    private String message;
}
