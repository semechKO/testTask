package com.veeam.test.store;

import com.veeam.api.store.StoreServices;
import com.veeam.api.store.constants.OrderStatus;
import com.veeam.api.store.dto.OrderDTO;
import com.veeam.api.store.helpers.StoreHelper;
import com.veeam.test.common.AbstractTestDefinition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.veeam.test.common.CustomAssertion.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestStoreApi extends AbstractTestDefinition {

    @Autowired
    private StoreServices storeServices;

    @Autowired
    private StoreHelper storeHelper;


    @BeforeEach
    public void init(TestInfo testInfo) {
        logger.info("\n\n-------------Running test is: " + testInfo.getDisplayName() + "-------------\n");
    }

    @AfterEach
    public void end(TestInfo testInfo) {
        logger.info("\n\n-------------End of the test: " + testInfo.getDisplayName() + "-------------\n");
    }

    @Test
    @DisplayName("Get order by id")
    @Tag("Positive")
    @Tag("Json")
    @Tag("Xml")
    public void getOrderById() {
        var orderDTO = storeHelper.sendRandomOrderDTO();
        var responseEntity = storeServices.get(orderDTO.getId());
        assertOkResponse(responseEntity, orderDTO);
    }

    @ParameterizedTest
    @MethodSource("providerForStorePostOrderPositiveTest")
    @DisplayName("Positive check for post order with different statuses")
    @Tag("Positive")
    @Tag("Json")
    @Tag("Xml")
    public void testOrderPostWithDifferentStatus(OrderDTO orderDTO) {
        var responseEntity = storeServices.post(orderDTO);
        assertOkResponse(responseEntity, orderDTO);
    }

    @Test
    @DisplayName("Delete order by id")
    @Tag("Json")
    @Tag("Xml")
    public void testOrderDeleteById() {
        var orderDTO = storeHelper.sendRandomOrderDTO();
        var deleteOrderResponseDTO = storeHelper.getOrderDeleteDTO();
        var responseEntity = storeServices.delete(orderDTO.getId());
        assertOkResponse(responseEntity, deleteOrderResponseDTO.setMessage(orderDTO.getId().toString()));
        var responseEntityForGet = storeServices.get(orderDTO.getId());
        assertNotFoundResponse(responseEntityForGet);
    }

    @Test
    @DisplayName("Get inventory")
    @Tag("Json")
    public void testGetInventory() {
        var responseEntity = storeServices.getInventory();
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    @DisplayName("Get order by not-existing id")
    @Tag("Negative")
    @Tag("Json")
    @Tag("Xml")
    public void testGetNonExistingOrderById() {
        var responseEntity = storeServices.get(2l);
        assertNotFoundResponse(responseEntity);
    }

    @Test
    @DisplayName("Get order by invalid id")
    @Tag("Negative")
    @Tag("Json")
    @Tag("Xml")
    public void testGetNonExistingOrderByInvalidId() {
        var responseEntity = storeServices.get(-5l);
        assertBadRequestStringResponse(responseEntity, "Invalid ID supplied");
    }

    //TODO: more negative tests

    /**
     * Providers
     **/

    private Stream<OrderDTO> providerForStorePostOrderPositiveTest() {
        return Stream.of(
                storeHelper.randomOrderDTOWithStatus(OrderStatus.APPROVED).setComplete(true),
                storeHelper.randomOrderDTOWithStatus(OrderStatus.PLACED).setComplete(false),
                storeHelper.randomOrderDTOWithStatus(OrderStatus.DELIVERED).setComplete(false)
        );
    }

    /**
     * After methods
     */

    //Some methods for deleting test entities =)
}
