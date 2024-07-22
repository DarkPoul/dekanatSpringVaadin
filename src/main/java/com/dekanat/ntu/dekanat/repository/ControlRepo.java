package com.dekanat.ntu.dekanat.repository;

import com.dekanat.ntu.dekanat.models.ControlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepo extends JpaRepository<ControlModel, Integer> {
    ControlModel findByTitle(String title);
    ControlModel findByType(String type);
    ControlModel findById(long id);
}
