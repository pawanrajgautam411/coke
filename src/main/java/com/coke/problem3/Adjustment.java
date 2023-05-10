package com.coke.problem3;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adjustment {
    private String adjustment;
    private String amount;
    private String desc;
}