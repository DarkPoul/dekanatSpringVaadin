package com.dekanat.ntu.dekanat.repository;

import com.dekanat.ntu.dekanat.models.PlansModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlansRepo extends JpaRepository<PlansModel, Long> {

    @Query("SELECT p FROM PlansModel p WHERE " +
            "p.students LIKE %:values% AND p.semester = :semester")
    List<PlansModel> findByStudentsContainingValues(String values, int semester);

    @Query(value = "SELECT MAX(id) FROM PlansModel")
    Long findMaxId();

    PlansModel findById(long id);


}
