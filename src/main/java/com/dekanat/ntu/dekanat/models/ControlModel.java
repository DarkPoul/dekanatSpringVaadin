package com.dekanat.ntu.dekanat.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "control")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ControlModel {
    @Id
    private Long id;
    private String title;
    private String type;
}
