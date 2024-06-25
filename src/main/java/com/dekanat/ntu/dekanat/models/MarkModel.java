package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "mark")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarkModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentModel student;

    private int semester;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private DisciplineModel discipline;

    @ManyToOne
    @JoinColumn(name = "control_id")
    private TypeOfControlModel control;

    private int mark;
    private Date date;
    private Integer number;
}
