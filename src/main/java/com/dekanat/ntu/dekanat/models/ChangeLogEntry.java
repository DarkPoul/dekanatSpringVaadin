package com.dekanat.ntu.dekanat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeLogEntry {
    private int id;
    private String version;
    private LocalDate date;
    private String changes;
}
