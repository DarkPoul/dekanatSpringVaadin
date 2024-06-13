package com.dekanat.ntu.dekanat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkEntity {

    private int id;
    private String pib;
    private String mark;
    private String ects;
    private LocalDate date;
    private String order;

}
