package com.dekanat.ntu.dekanat.services;

import com.dekanat.ntu.dekanat.entity.PlansEntity;
import com.dekanat.ntu.dekanat.models.PlansModel;
import com.dekanat.ntu.dekanat.models.StudentModel;
import com.dekanat.ntu.dekanat.repository.*;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlansServices {

    private final PlansRepo plansRepo;
    private final StudentRepo studentRepo;
    private final DiscRepo discRepo;
    private final ControlRepo controlRepo;
    private final DepartmentRepo departmentRepo;
    private final StudentService studentService;

    public PlansServices(PlansRepo plansRepo, StudentRepo studentRepo, DiscRepo discRepo, ControlRepo controlRepo, DepartmentRepo departmentRepo, StudentService studentService) {
        this.plansRepo = plansRepo;
        this.studentRepo = studentRepo;
        this.discRepo = discRepo;
        this.controlRepo = controlRepo;
        this.departmentRepo = departmentRepo;
        this.studentService = studentService;
    }

    public List<PlansEntity> getStudyPlansForGroup(String groupName, String semester) {
        // Розділяємо групове ім'я на частини
        String[] groupParts = groupName.split("-");
        List<StudentModel> students = studentRepo.findAllByGroupAndCourseAndNumberAndYear(
                groupParts[0],
                groupParts[1],
                groupParts[2],
                groupParts[3]
        );

        // Отримуємо список ID студентів як рядки
        List<String> studentsIds = students.stream().map(StudentModel::getId).map(String::valueOf).toList();

        // Використовуємо Set для зберігання унікальних дисциплін
        Set<String> uniqueDisciplinesSet = new HashSet<>();
        List<PlansModel> uniquePlansModelList = new ArrayList<>();

        for (String studentId : studentsIds) {
            List<PlansModel> plans = plansRepo.findByStudentsContainingValues(studentId, getNumberSemester(groupName, semester));
            for (PlansModel plan : plans) {
                if (uniqueDisciplinesSet.add(discRepo.findById(plan.getDiscipline()).getTitle())) { // додається тільки якщо ще не існує
                    uniquePlansModelList.add(plan);
                }
            }
        }

        List<PlansEntity> plansEntityList = new ArrayList<>();
        int iterator = 1;

        for (PlansModel model : uniquePlansModelList) {
            PlansEntity plansEntity = new PlansEntity();
            plansEntity.setPlanId(model.getId());
            plansEntity.setNumber(iterator);
            plansEntity.setDisc(discRepo.findById(model.getDiscipline()).getTitle());
            plansEntity.setHours(model.getCredits());
            plansEntity.setChoice(getChoice(studentsIds, Arrays.stream(model.getStudents().split(", ")).toList()));
            plansEntity.setFirst(controlRepo.findById(model.getFirst()).getTitle());
            plansEntity.setSecond(controlRepo.findById(model.getSecond()).getTitle());
            plansEntity.setPart(model.getParts());
            plansEntity.setDepartment(departmentRepo.findById(model.getDepartment()).getTitle());
            iterator++;
            plansEntityList.add(plansEntity);
        }

        return plansEntityList;
    }

    public void savePlan(PlansEntity plansEntity, List<String> students, String groupTitle, String semester){
        PlansModel model = new PlansModel();
        model.setId(Optional.ofNullable(plansRepo.findMaxId()).orElse(0L) + 1);
        model.setStudents(String.join(", ", studentService.getStudentsId(students, groupTitle)));
        model.setDiscipline(discRepo.findByTitle(plansEntity.getDisc()).getId());
        model.setSemester(getNumberSemester(groupTitle, semester));
        model.setCredits(plansEntity.getHours());
        model.setFirst(Math.toIntExact(controlRepo.findByTitle(plansEntity.getFirst()).getId()));
        model.setSecond(Math.toIntExact(controlRepo.findByTitle(plansEntity.getSecond()).getId()));
        model.setParts(plansEntity.getPart());
        model.setDepartment(Math.toIntExact(departmentRepo.findByTitle(plansEntity.getDepartment()).getId()));

        plansRepo.save(model);
        System.out.println("save successful");


    }

    public void removePlan(long id){
        plansRepo.deleteById(id);
        System.out.println("Remove successful");
    }

    public List<String> findChoiceStudent(PlansEntity plansEntity){
        return Arrays.stream(plansRepo.findById(plansEntity.getPlanId()).getStudents().split(", ")).toList();
    }

    private String getChoice(List<String> students, List<String> studentChoice){
        if (students.equals(studentChoice)){
            return "Ні";
        }
        else
            return "Так";
    }

    private int getNumberSemester(String groupTitle, String semester){
        String[] groupParts = groupTitle.split("-");
        if (semester.equals("Зимова")) return (Integer.parseInt(groupParts[1]) * 2 - 1);
        else return Integer.parseInt(groupParts[1]) * 2;


    }


    public void updatePlan(PlansEntity plansEntity, List<String> students, String groupTitle) {
        PlansModel model = plansRepo.findById(plansEntity.getPlanId());

        model.setStudents(String.join(", ", studentService.getStudentsId(students, groupTitle)));
        model.setDiscipline(discRepo.findByTitle(plansEntity.getDisc()).getId());
        model.setCredits(plansEntity.getHours());
        model.setFirst(Math.toIntExact(controlRepo.findByTitle(plansEntity.getFirst()).getId()));
        model.setSecond(Math.toIntExact(controlRepo.findByTitle(plansEntity.getSecond()).getId()));
        model.setParts(plansEntity.getPart());
        model.setDepartment(Math.toIntExact(departmentRepo.findByTitle(plansEntity.getDepartment()).getId()));

        plansRepo.save(model);
        System.out.println("update successful");
    }
}
