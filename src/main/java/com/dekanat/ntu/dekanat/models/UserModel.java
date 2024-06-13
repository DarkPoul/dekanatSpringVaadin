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
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Getter
@Setter
public class UserModel {
    @Id
    private int id;
    private String login;
    private String password;
    private int enabled;
    private String role;
    private int faculty_id;
}
