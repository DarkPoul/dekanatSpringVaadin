package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "training_plan")
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
public class TrainingPlanModel {
    @Id
    private int id;
    @Column(name = "student")
    private int student;
    @Column(name = "discipline")
    private int discipline;
    @Column(name = "semester")
    private int semester;
    @Column(name = "credits")
    private int credits;
    @Column(name = "first_type_control_id")
    private int first_type_control_id;
    @Column(name = "second_type_control_id")
    private int second_type_control_id;
    @Column(name = "part")
    private int part;
    @Column(name = "department_id")
    private int department_id;
}
