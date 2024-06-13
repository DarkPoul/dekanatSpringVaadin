package com.dekanat.ntu.dekanat.repository;

import com.dekanat.ntu.dekanat.models.FormControlModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormControlRepository extends JpaRepository<FormControlModel, Integer> {
    FormControlModel findById(int id);

    FormControlModel findByTitle(String title);
}
