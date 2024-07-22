package com.dekanat.ntu.dekanat.repository;

import com.dekanat.ntu.dekanat.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentModel, Long> {
    DepartmentModel findByTitle(String title);
    DepartmentModel findById(long id);
}
