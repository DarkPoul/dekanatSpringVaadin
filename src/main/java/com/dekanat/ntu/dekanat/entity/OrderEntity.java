package com.dekanat.ntu.dekanat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private String orderNumber;
    private String status;
    private LocalDate date;
}
