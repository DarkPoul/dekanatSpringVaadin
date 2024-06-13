package com.dekanat.ntu.dekanat.services;


import com.dekanat.ntu.dekanat.models.StudentModel;
import com.dekanat.ntu.dekanat.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<String> getStudents(String fulTitleGroup) {

        List<String> students = new ArrayList<>();

        String speciality = fulTitleGroup.split("-")[0];
        String course = fulTitleGroup.split("-")[1];
        String group = fulTitleGroup.split("-")[2];
        String enterYear = fulTitleGroup.split("-")[3];

        List<StudentModel> studentModels = studentRepository.findBySpecialityAndCourseAndGroupAndEnterYear(speciality, course, group, enterYear);

        for (StudentModel student : studentModels){
            students.add(student.getSurname() + " " + student.getName() + " " + student.getPatronymic());
        }

        return students;
    }

    public List<String> getGroups(){
        List<String> groups = new ArrayList<>();
        List<StudentModel> AllStudent = studentRepository.findAll();
        for (StudentModel student : AllStudent) {
            String group = student.getSpeciality() + "-" + student.getCourse() + "-" + student.getGroup() + "-" + student.getEnterYear();
            if (!groups.contains(group)){
                groups.add(group);
            }
        }
        return groups;
    }

    public String getStudentName(int id){
        StudentModel student = studentRepository.findById(id).get();
        return studentRepository.findById(id).isPresent() ? student.getSurname()+ " " + student.getName() + " " + student.getPatronymic() : "Студент не знайдений";
    }


    public int getStudentId(String text) {
        String[] parts = text.split(" ");
        String surname = parts[0];
        String name = parts[1];
        String patronymic = parts[2];
        return studentRepository.findBySurnameAndNameAndPatronymic(surname, name, patronymic).getId();
    }

    public StudentModel getStudentById(int studentId) {
        return studentRepository.getStudentById(studentId);
    }
}
