package com.dekanat.ntu.dekanat.repository;


import com.dekanat.ntu.dekanat.models.MarkModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<MarkModel, Integer> {
    MarkModel findFirstByOrderByIdDesc();

    void deleteByDisciplineAndSemesterAndStudentAndControl(int discipline, int semester, int student, int control);
    boolean existsByStudentAndSemesterAndDisciplineAndControl(int studentId, int semester, int discipline, int control);
    MarkModel findByStudentAndSemesterAndDisciplineAndControl(int student, int semester, int discipline, int control);
    List<MarkModel> findAllBySemesterAndStudent(int semester, int student);

    List<MarkModel> findAllByStudentAndSemesterAndDiscipline(int student, int semester, int discipline);
}
