package com.veeam.api.store.helpers;

import com.veeam.api.common.BaseHelper;
import com.veeam.api.store.StoreServices;
import com.veeam.api.store.constants.OrderStatus;
import com.veeam.api.store.dto.DeleteOrderResponseDTO;
import com.veeam.api.store.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreHelper extends BaseHelper {

    @Autowired
    private StoreServices storeServices;

    public OrderDTO randomOrderDTOWithStatus(OrderStatus status) {
        return new OrderDTO()
                .setId(getLong())
                .setPetId(getLong())
                .setQuantity(getInteger())
                .setShipDate(getStringDate())
                .setStatus(status);
    }

    public OrderDTO sendRandomOrderDTO() {
        OrderDTO orderDTO = randomOrderDTOWithStatus(OrderStatus.APPROVED).setComplete(false);
        storeServices.post(orderDTO);
        return orderDTO;
    }

    public DeleteOrderResponseDTO getOrderDeleteDTO() {
        return new DeleteOrderResponseDTO()
                .setCode(200)
                .setMessage("Not found")
                .setType("unknown");
    }
}
