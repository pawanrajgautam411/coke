package com.coke.problem3;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class OrderInput {
    private String totalOrderValue;
    private String totalDiscount;
    private String totalOrderValuePostDiscount;
    private String totalCouponDiscount;
    private String totalTax;
    private String movFee;
    private String deliveryFee;
    private String netInvoice;
    private String outletId;
    private List<Item> item;

    private ServerMetaData serverMetaData;
    private UserDetail userDetail;
}
