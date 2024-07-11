package com.dekanat.ntu.dekanat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SuccessEntity {
    private String discipline;
    private int semester;
    private String hours;
    private String test;
    private String exam;
    private String d_exam;
    private String kp;
    private String kr;
    private String rr;
    private String rgr;
}