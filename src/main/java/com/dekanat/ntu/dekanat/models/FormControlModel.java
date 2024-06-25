package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "form_control")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormControlModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int type;
}
