package com.dekanat.ntu.dekanat.repository;

import com.dekanat.ntu.dekanat.models.DiscModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscRepo extends JpaRepository<DiscModel, Long> {
    DiscModel findByTitle(String title);
    DiscModel findById(long id);
}
