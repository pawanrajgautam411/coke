package com.coke.problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderProcessorTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderProcessor orderProcessor;

    @Test
    void processOrder_givenNonDuplicateOrder_returnsOrderId() {
        OrderInput orderInput = createOrderInput();
        when(orderRepository.findLastTenActiveOrdersByUserEmail(orderInput.getUserDetail().getEmail()))
                .thenReturn(Collections.emptyList());

        String orderId = orderProcessor.processOrder(orderInput);

        assertNotNull(orderId);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void processOrder_givenDuplicateOrder_throwsDuplicateOrderException() {
        OrderInput orderInput = createOrderInput();
        Order existingOrder = createOrder(orderInput);
        when(orderRepository.findLastTenActiveOrdersByUserEmail(orderInput.getUserDetail().getEmail()))
                .thenReturn(Collections.singletonList(existingOrder));

        assertThrows(DuplicateOrderException.class, () -> orderProcessor.processOrder(orderInput));
        verify(orderRepository, never()).save(any(Order.class));
    }

    private OrderInput createOrderInput() {
        UserDetail userDetail = UserDetail.builder()
                .email("john.doe@example.com")
                .build();

        Item item1 = Item.builder()
                .product("product1")
                .desc("Product 1")
                .build();

        Item item2 = Item.builder()
                .product("product2")
                .desc("Product 2")
                .build();

        ServerMetaData serverMetaData = ServerMetaData.builder()
                .dateTime(LocalDateTime.now())
                .build();

        return OrderInput.builder()
                .userDetail(userDetail)
                .outletId("outlet1")
                .item(Arrays.asList(item1, item2))
                .serverMetaData(serverMetaData)
                .build();
    }

    private Order createOrder(OrderInput orderInput) {
        return Order.builder()
                .orderId("existingOrderId")
                .orderStatus(OrderStatus.PLACED)
                .orderInput(orderInput)
                .build();
    }

}