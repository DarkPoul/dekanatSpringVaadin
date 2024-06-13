package com.dekanat.ntu.dekanat.repository;


import com.dekanat.ntu.dekanat.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel, Integer> {

    DepartmentModel findByTitle(String s);
}
