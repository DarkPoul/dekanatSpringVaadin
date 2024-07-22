package com.dekanat.ntu.dekanat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewingCardsEntity {
    private String lastNameUkrField;
    private String firstNameUkrField;
    private String middleNameUkrField;
    private String lastNameEngField;
    private String firstNameEngField;
    private String groupField;
    private String courseField;
    private String groupNumberField;
    private String admissionYearField;
    private String recordBookNumberField;
}
