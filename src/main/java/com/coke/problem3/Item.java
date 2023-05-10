package com.coke.problem3;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private String product;
    private String desc;
    private String qty;
    private String basePrice;
    private String itemPrice;
    private List<Adjustment> adjustments;
}