package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "mark")
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Getter
@Setter
public class MarkModel {
    @Id
    private int id;
    private int student;
    private int semester;
    private int discipline;
    @Column(name = "control")
    private int control;
    private int mark;
    private Date date;
    private Integer number;
}
