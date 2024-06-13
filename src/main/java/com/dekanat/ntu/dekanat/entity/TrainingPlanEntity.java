package com.dekanat.ntu.dekanat.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingPlanEntity {
    private int id;
    private String discipline;
    private int credits;
    private String isChoice;
    private String firstType;
    private String secondType;
    private int parts;
    private String department;
}
