package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "department")
public class DepartmentModel {
    @Id
    private long id;
    private String title;
    private String abbreviation;
    private String eng;
}
