package com.dekanat.ntu.dekanat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Table(name = "student")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Getter
@Setter
public class StudentModel {
    @Id
    private int id;
    private String name;
    private String name_eng;
    private String surname;
    private String surname_eng;
    private String patronymic;
    private Date birthday;
    private boolean sex;
    private boolean nationality;
    private Date issuing_of_the_passport;
    private Date passport_expiry_date;
    private String issuing_authority;
    private String record_number;
    private String serial;
    private String number;
    private String identification_code;
    private String degree;
    private String form_study;
    private String speciality;
    private String course;
    private String group;

    private String enterYear;
    private String number_of_the_credit;
    private String contract_number;
    private String benefits;
    private String pay;
    private String applicant_card;
    private String individual;
    private String district;
    private String address_home;
    private String index;
    private String phone_number;
    private String email;


}

