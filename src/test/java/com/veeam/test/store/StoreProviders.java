package com.veeam.test.store;

import com.veeam.api.store.constants.OrderStatus;
import com.veeam.api.store.dto.OrderDTO;
import com.veeam.api.store.helpers.StoreHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

public class StoreProviders {

    @Autowired
    private StoreHelper storeHelper;

    public Stream<OrderDTO> providerForStorePostOrderPositiveTest() {
        return Stream.of(
                storeHelper.randomOrderDTOWithStatus(OrderStatus.APPROVED).setComplete(true),
                storeHelper.randomOrderDTOWithStatus(OrderStatus.PLACED).setComplete(false),
                storeHelper.randomOrderDTOWithStatus(OrderStatus.DELIVERED)
        );
}}
