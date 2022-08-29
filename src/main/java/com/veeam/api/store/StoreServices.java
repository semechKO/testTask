package com.veeam.api.store;

import com.veeam.api.common.BaseService;
import com.veeam.api.store.dto.DeleteOrderResponseDTO;
import com.veeam.api.store.dto.InventoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.veeam.api.store.dto.OrderDTO;

@Service
public class StoreServices extends BaseService {

    private static final String ORDER_URL = "/store/order";
    private static final String INVENTORY_URL = "/store/inventory";

    public ResponseEntity<OrderDTO> get(long id) {
        return getRequest(
                String.format(ORDER_URL + "/%s", id),
                OrderDTO.class
        );
    }

    public ResponseEntity<OrderDTO> post(OrderDTO orderDTO) {
        return postRequest(
                ORDER_URL,
                orderDTO,
                OrderDTO.class
        );
    }

    public ResponseEntity<DeleteOrderResponseDTO> delete(long id) {
        return deleteRequest(
                String.format(ORDER_URL + "/%s", id),
                DeleteOrderResponseDTO.class
        );
    }

    public ResponseEntity<InventoryDTO> getInventory() {
        //Workaround. Didn't have enough time, sorry. Good way is to have different runs by tags (xml, json, both)
        return getRequestOnlyJson(
                INVENTORY_URL,
                InventoryDTO.class
        );
    }

}
