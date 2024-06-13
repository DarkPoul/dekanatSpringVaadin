package com.dekanat.ntu.dekanat.repository;



import com.dekanat.ntu.dekanat.models.TrainingPlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlanModel, Integer> {
    ArrayList<TrainingPlanModel> findAllByStudentAndSemester(int studentId, int semester);

    @Transactional
    @Modifying
    void deleteByStudentAndSemesterAndDiscipline(int studentId, int semester, int DisciplineId);

    TrainingPlanModel findByStudentAndSemesterAndDiscipline(int i, int semester, int discipline);

    boolean existsByStudentAndSemesterAndDiscipline(int studentId, int semester, int discipline);

    TrainingPlanModel findFirstByOrderByIdDesc();










}
