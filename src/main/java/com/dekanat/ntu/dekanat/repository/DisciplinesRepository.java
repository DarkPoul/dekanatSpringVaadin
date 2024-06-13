package com.dekanat.ntu.dekanat.repository;


import com.dekanat.ntu.dekanat.models.DisciplineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinesRepository extends JpaRepository<DisciplineModel, Integer> {
    DisciplineModel findByTitle(String title);
    DisciplineModel findById(int id);
}
