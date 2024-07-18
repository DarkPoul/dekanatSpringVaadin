package com.dekanat.ntu.dekanat.repository;

import com.dekanat.ntu.dekanat.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel, Long> {

    List<StudentModel> findAllByGroupAndCourseAndNumberAndYear(String group, String course, String number, String year);

    StudentModel findById(long id);

    StudentModel findByGroupAndCourseAndNumberAndYearAndSurnameAndNameAndPatronymic(String group, String course, String number, String year, String SurName, String name, String patronymic);
}
