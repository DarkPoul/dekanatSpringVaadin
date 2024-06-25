package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "training_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingPlanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentModel student;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private DisciplineModel discipline;

    private int semester;
    private int credits;

    @ManyToOne
    @JoinColumn(name = "first_type_control_id")
    private TypeOfControlModel firstTypeControl;

    @ManyToOne
    @JoinColumn(name = "second_type_control_id")
    private TypeOfControlModel secondTypeControl;

    private int part;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentModel department;

    private boolean isCommonDiscipline;
}
