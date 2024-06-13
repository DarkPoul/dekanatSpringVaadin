package com.dekanat.ntu.dekanat.repository;

import com.dekanat.ntu.dekanat.models.StudentModel;
import jakarta.persistence.metamodel.IdentifiableType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {
    ArrayList<StudentModel> findBySpecialityAndCourseAndGroupAndEnterYear(String speciality, String course, String group, String enterYear);
    StudentModel findBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);

    StudentModel getStudentById(int studentId);
}
