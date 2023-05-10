package com.coke.problem3;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private String orderId;
    private int orderStatus;

    private OrderInput orderInput;

    LocalDateTime getOrderTime() {
        return orderInput.getServerMetaData().getDateTime();
    }
}

interface OrderStatus {
    int PLACED = 1;
    int CANCELLED = 2;
    int PREPARING = 3;
    int DELIVERED = 4;
}