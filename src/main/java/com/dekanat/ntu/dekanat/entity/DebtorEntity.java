package com.dekanat.ntu.dekanat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DebtorEntity {
    private String lastName;
    private String firstName;
    private String patronymic;
    private int courseYear;
}
