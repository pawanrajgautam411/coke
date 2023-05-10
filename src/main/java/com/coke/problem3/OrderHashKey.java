package com.coke.problem3;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class OrderHashKey {
    private String outletId;
    private String userEmail;
    private Set<String> setOfItems;
}