package com.dekanat.ntu.dekanat.services;

import com.dekanat.ntu.dekanat.entity.TrainingPlanEntity;
import com.dekanat.ntu.dekanat.models.StudentModel;
import com.dekanat.ntu.dekanat.models.TrainingPlanModel;
import com.dekanat.ntu.dekanat.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainingPlanService {

    private final DisciplinesRepository disciplinesRepository;
    private final TrainingPlanRepository trainingPlanRepository;
    private final StudentRepository studentRepository;
    private final FormControlRepository formControlRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentService studentService;

    private final List<Integer> disc = new ArrayList<>();
    private final List<TrainingPlanEntity> TPEntity = new ArrayList<>();

    public TrainingPlanService(DisciplinesRepository disciplinesRepository, TrainingPlanRepository trainingPlanRepository, StudentRepository studentRepository, FormControlRepository formControlRepository, DepartmentRepository departmentRepository, StudentService studentService) {
        this.disciplinesRepository = disciplinesRepository;
        this.trainingPlanRepository = trainingPlanRepository;
        this.studentRepository = studentRepository;
        this.formControlRepository = formControlRepository;
        this.departmentRepository = departmentRepository;

        this.studentService = studentService;
    }

    public List<TrainingPlanEntity> getTrainingPlan(List<String> studentsPIB, String semester, String group) {

        TPEntity.clear();
        disc.clear();
        int id = 1;

        int totalSemester = getSemester(semester, group);

        for (String student : studentsPIB) {
            for (TrainingPlanModel model : trainingPlanRepository.findAllByStudentAndSemester(getStudentID(student), totalSemester)) {
                if (!disc.contains(model.getDiscipline())){
                    TPEntity.add(createTrainingPlan(model, studentsPIB, semester, group, id));
                    disc.add(model.getDiscipline());
                    id++;
                }


            }
        }

        return TPEntity;
    }

    public List<String> studentWhoChoiceDiscipline(List<String> studentsPIB, String semester, String discipline, String group){
        int sem = getSemester(semester, group);
        int disc = disciplinesRepository.findByTitle(discipline).getId();

        List<String> students = new ArrayList<>();
        for (String st : studentsPIB){
            int student = getStudentID(st);
            try{
                StudentModel studentModel = studentRepository.getStudentById(trainingPlanRepository.findByStudentAndSemesterAndDiscipline(student, sem, disc).getStudent());
                students.add(studentModel.getSurname() + " " + studentModel.getName() + " " + studentModel.getPatronymic());
            } catch (NullPointerException e){
                e.fillInStackTrace();
            }
        }
        return students;
    }

    public int getStudentID(String pib) {
        String[] parts = pib.split(" ");
        String p = parts[0];
        String i = parts[1];
        String b = parts[2];

        return studentRepository.findBySurnameAndNameAndPatronymic(p, i, b).getId();
    }

    public int getSemester(String semester, String group) {
        return Integer.parseInt(group.split("-")[1]) * 2 - (semester.equals("Зимова") ? 1 : 0);
    }

    private TrainingPlanEntity createTrainingPlan(TrainingPlanModel tpm, List<String> students, String semester, String group, int id){
        TrainingPlanEntity tpe = new TrainingPlanEntity();
        tpe.setId(id);
        tpe.setDiscipline(disciplinesRepository.findById(tpm.getDiscipline()).getTitle());
        tpe.setCredits(tpm.getCredits());
        if (students.equals(studentWhoChoiceDiscipline(students, semester, tpe.getDiscipline(), group))){
            tpe.setIsChoice("Ні");
        } else tpe.setIsChoice("Так");

        tpe.setFirstType(formControlRepository.findById(tpm.getFirst_type_control_id()).getTitle());
        tpe.setSecondType(formControlRepository.findById(tpm.getSecond_type_control_id()).getTitle());
        tpe.setParts(tpm.getPart());
        tpe.setDepartment("(" + tpm.getDepartment_id() + ") " + ((departmentRepository.findById(tpm.getDepartment_id()).isPresent()) ? departmentRepository.findById(tpm.getDepartment_id()).get().getTitle() : "Error"));

        return tpe;
    }


    public void saveTP(TrainingPlanEntity newTPlan, List<String> students, String semester, String group) {

        for (String student : students){
            TrainingPlanModel addTP = new TrainingPlanModel();
            addTP.setId(trainingPlanRepository.findFirstByOrderByIdDesc().getId() + 1);
            addTP.setStudent(getStudentID(student));
            addTP.setDiscipline(disciplinesRepository.findByTitle(newTPlan.getDiscipline()).getId());
            addTP.setSemester(getSemester(semester, group));
            addTP.setCredits(newTPlan.getCredits());
            addTP.setFirst_type_control_id(formControlRepository.findByTitle(newTPlan.getFirstType()).getId());
            addTP.setSecond_type_control_id(formControlRepository.findByTitle(newTPlan.getSecondType()).getId());
            addTP.setPart(newTPlan.getParts());

            addTP.setDepartment_id(Integer.parseInt(newTPlan.getDepartment().replaceAll(".*\\((.*?)\\).*", "$1")));
            trainingPlanRepository.save(addTP);
            System.out.println(true);
        }

    }

    public List<String> allDisc(String group, String semester){

        List<String> disc = new ArrayList<>();
        List<TrainingPlanModel> allDiscEntity = new ArrayList<>();
        List<String> allStudentGroup = new ArrayList<>(studentService.getStudents(group));
        for (String student : allStudentGroup){
            allDiscEntity.addAll(trainingPlanRepository.findAllByStudentAndSemester(getStudentID(student), getSemester(semester, group)));
        }

        for (TrainingPlanModel tpm : allDiscEntity){
            if (!disc.contains(disciplinesRepository.findById(tpm.getDiscipline()).getTitle())){
                disc.add(disciplinesRepository.findById(tpm.getDiscipline()).getTitle());
            }
        }


        return disc;

    }
}
