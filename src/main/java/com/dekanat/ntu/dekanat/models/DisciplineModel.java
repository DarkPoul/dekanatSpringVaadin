package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discipline")
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Getter
@Setter
public class DisciplineModel {
    @Id
    private int id;
    private String title;
    private String title_eng;
}
