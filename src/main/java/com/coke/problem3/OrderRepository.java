package com.coke.problem3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class OrderRepository {
    private final ConcurrentHashMap<String, Order> orderMap;
    private static final int LAST_ORDERS_LIMIT = 10;

    public OrderRepository() {
        orderMap = new ConcurrentHashMap<>();
    }

    public void save(Order order) {
        orderMap.put(order.getOrderId(), order);
    }

    public Order findById(String orderId) {
        return orderMap.get(orderId);
    }

    public List<Order> findLastTenActiveOrdersByUserEmail(String emailAddress) {
        return orderMap.values().stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.DELIVERED
                        && order.getOrderStatus() != OrderStatus.CANCELLED)

                .sorted(Comparator.comparing(Order::getOrderTime).reversed())
                .limit(LAST_ORDERS_LIMIT)
                .collect(Collectors.toList());
    }

    public List<Order> findAll() {
        return new ArrayList<>(orderMap.values());
    }


}
