package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "plans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlansModel {
    @Id
    private long id;
    private String students;
    private long discipline;
    private int semester;
    private int credits;
    private int first;
    private int second;
    private int parts;
    private int department;
}
