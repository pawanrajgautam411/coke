package com.coke.problem3;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderProcessor {
    private final OrderRepository orderRepository;

    public OrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * @param orderInput input data for new order from UI or service
     * @return the new order id
     */
    public String processOrder(OrderInput orderInput) {
        boolean isDuplicateOrder = this.checkForDuplicateOrder(orderInput);
        if (isDuplicateOrder) {
            throw new DuplicateOrderException("duplicate order detected for user ("
                    + orderInput.getUserDetail().getEmail() + ")");
        }

        String orderId = UUID.randomUUID().toString();
        this.saveOrder(orderId, orderInput);

        return orderId;
    }

    /**
     * @param orderInput input data for new order from UI or service
     * @return return true if the order is duplicate for a given user
     */
    private boolean checkForDuplicateOrder(OrderInput orderInput) {
        OrderHashKey currentOrderHashKey = createOrderHashKey(orderInput);

        List<Order> lastTenOrders = orderRepository
                .findLastTenActiveOrdersByUserEmail(orderInput.getUserDetail().getEmail());

        int newOrderHash = currentOrderHashKey.hashCode();

        for (Order existingOrder : lastTenOrders) {
            OrderHashKey orderHashKey = this.createOrderHashKey(existingOrder.getOrderInput());

            if (newOrderHash == orderHashKey.hashCode()
                    && currentOrderHashKey.equals(orderHashKey)) {
                return true; // duplicate order detected
            }
        }

        return false;
    }

    /**
     * @param orderInput input data for new order from UI or service
     */
    private OrderHashKey createOrderHashKey(OrderInput orderInput) {
        Set<String> setOfItems = orderInput.getItem().stream()
                .map(Item::getProduct)
                .collect(Collectors.toSet());

        return OrderHashKey
                .builder()
                .userEmail(orderInput.getUserDetail().getEmail())
                .outletId(orderInput.getOutletId())
                .setOfItems(setOfItems)
                .build();
    }

    /**
     * @param orderId    newly created order id
     * @param orderInput input data for new order from UI or service
     */
    private void saveOrder(String orderId, OrderInput orderInput) {
        Order order = Order.builder()
                .orderId(orderId)
                .orderStatus(OrderStatus.PLACED)
                .orderInput(orderInput)
                .build();

        orderRepository.save(order);
    }
}

