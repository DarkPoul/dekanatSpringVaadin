package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "type_control")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypeOfControlModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String type;

    @OneToMany(mappedBy = "firstTypeControl", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TrainingPlanModel> trainingPlansAsFirst;

    @OneToMany(mappedBy = "secondTypeControl", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TrainingPlanModel> trainingPlansAsSecond;
}
