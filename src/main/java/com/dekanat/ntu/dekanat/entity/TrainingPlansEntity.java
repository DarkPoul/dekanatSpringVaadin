package com.dekanat.ntu.dekanat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlansEntity {
    public int number;
    public String title;
    public String hours;
    public String isCommonDiscipline;
    public String firstType;
    public String secondType;
    public String parts;
    public String department;
}
