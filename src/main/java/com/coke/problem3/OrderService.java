package com.coke.problem3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

public class OrderService {
    private final OrderProcessor orderProcessor;

    public OrderService(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    /**
     * @param payload the json string for placing the order
     * @return the orderId wrapper
     */
    public OrderOutput processOrder(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            OrderInput orderInput = objectMapper.readValue(payload, OrderInput.class);
            orderInput.setServerMetaData(new ServerMetaData(LocalDateTime.now()));


            orderInput.setServerMetaData(new ServerMetaData(LocalDateTime.now()));
            orderInput.setUserDetail(LoggedInUser.getThreadLocal().get());

            String orderId = orderProcessor.processOrder(orderInput);
            return new OrderOutput(orderId);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("invalid payload", e);
        }
    }
}
