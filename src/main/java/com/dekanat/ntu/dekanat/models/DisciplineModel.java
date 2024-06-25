package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "discipline")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisciplineModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String title_eng;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TrainingPlanModel> trainingPlans;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MarkModel> marks;
}
