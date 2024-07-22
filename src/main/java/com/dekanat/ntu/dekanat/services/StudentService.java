package com.dekanat.ntu.dekanat.services;

import com.dekanat.ntu.dekanat.entity.StudentEntity;
import com.dekanat.ntu.dekanat.models.StudentModel;
import com.dekanat.ntu.dekanat.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService{

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<String> getAllGroups(){
        List<StudentModel> studentModels = new ArrayList<>(studentRepo.findAll());
        List<String> allGroup = new ArrayList<>();
        for (StudentModel model : studentModels){
            String group = model.getGroup() + "-" + model.getCourse() + "-" + model.getNumber() + "-" + model.getYear();
            if (!allGroup.contains(group)){
                allGroup.add(group);
            }
        }
        return allGroup;
    }

    public List<String> getGroupStudentsPIB(String fullTitleGroup) {

        List<String> students = new ArrayList<>();

        String speciality = fullTitleGroup.split("-")[0];
        String course = fullTitleGroup.split("-")[1];
        String group = fullTitleGroup.split("-")[2];
        String enterYear = fullTitleGroup.split("-")[3];

        List<StudentModel> studentModels =  studentRepo.findAllByGroupAndCourseAndNumberAndYear(speciality, course, group, enterYear);


        for (StudentModel student : studentModels){
            students.add(student.getSurname() + " " + student.getName() + " " + student.getPatronymic());
        }

        return students;
    }

    public List<StudentEntity> getStudents(String groupTitle){

        List<StudentModel> allStudentModel = studentRepo.findAllByGroupAndCourseAndNumberAndYear(
                groupTitle.split("-")[0],
                groupTitle.split("-")[1],
                groupTitle.split("-")[2],
                groupTitle.split("-")[3]
        );
        List<StudentEntity> allStudents = new ArrayList<>();

        for (StudentModel m: allStudentModel){

            allStudents.add(new StudentEntity(
                    m.getId(),
                    m.getName(),
                    m.getSurname(),
                    m.getPatronymic()
            ));
        }

        return allStudents;
    }

    public List<String> getStudentPIB(List<String> studentsId){
        List<String> studentsPIB = new ArrayList<>();
        for (String student: studentsId){
            long id = Long.parseLong(student);
            StudentModel studentModel = studentRepo.findById(id);
            studentsPIB.add(studentModel.getSurname() + " " + studentModel.getName() + " " + studentModel.getPatronymic());
        }

        return studentsPIB;
    }

    public List<String> getStudentsId(List<String> studentsPIB, String groupTitle){
        List<String> studentsId = new ArrayList<>();
        for (String student : studentsPIB){
            studentsId.add(String.valueOf(studentRepo.findByGroupAndCourseAndNumberAndYearAndSurnameAndNameAndPatronymic(
                    groupTitle.split("-")[0],
                    groupTitle.split("-")[1],
                    groupTitle.split("-")[2],
                    groupTitle.split("-")[3],
                    student.split(" ")[0],
                    student.split(" ")[1],
                    student.split(" ")[2]
            ).getId()));
        }
        return studentsId;
    }










}
