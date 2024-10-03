package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.components.TrainingPlanDialog;
import com.dekanat.ntu.dekanat.entity.PlansEntity;
import com.dekanat.ntu.dekanat.services.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Навчальні плани | Деканат")
@Route(value = "learning-plans", layout = MainView.class)
@Component
@UIScope
public class TrainingPlansView extends Div {

    private final Select<String> groupSelect = new Select<>();
    private final Select<String> sessionSelect = new Select<>();
    private final Grid<PlansEntity> trainingPlansGrid = new Grid<>(PlansEntity.class, false);
    private final Button newItemButton = new Button("Додати");

    private TrainingPlanDialog trainingPlanDialog;
    private final StudentService studentService;
    private final PlansServices plansServices;
    private final DiscService discService;
    private final DepartmentService departmentService;
    private final ControlService controlService;

    public TrainingPlansView(StudentService studentService, PlansServices plansServices, DiscService discService, DepartmentService departmentService, ControlService controlService) {

        this.studentService = studentService;
        this.plansServices = plansServices;
        this.discService = discService;
        this.departmentService = departmentService;
        this.controlService = controlService;

        newItemButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newItemButton.getStyle().set("margin", "20px 20px 20px 20px");

        groupSelect.setLabel("Група");
        groupSelect.setItems(studentService.getAllGroups());
        sessionSelect.setLabel("Сессія");
        sessionSelect.setItems("Зимова", "Літня");

        sessionSelect.addValueChangeListener(event -> getGroupAndSession());

        groupSelect.addValueChangeListener(event -> getGroupAndSession());

        trainingPlansGrid.addColumn(PlansEntity::getNumber).setHeader("№");
        trainingPlansGrid.addColumn(PlansEntity::getDisc).setHeader("Дисципліна");
        trainingPlansGrid.addColumn(PlansEntity::getHours).setHeader("Години");
        trainingPlansGrid.addColumn(PlansEntity::getChoice).setHeader("Вибіркова");
        trainingPlansGrid.addColumn(PlansEntity::getFirst).setHeader("Перший к.");
        trainingPlansGrid.addColumn(PlansEntity::getSecond).setHeader("Другий к.");
        trainingPlansGrid.addColumn(PlansEntity::getPart).setHeader("Частини");
        trainingPlansGrid.addColumn(PlansEntity::getDepartment).setHeader("Кафедра");

        trainingPlansGrid.addComponentColumn(trainingPlans -> {
            Button button = new Button(new Icon(VaadinIcon.EDIT));
            button.addClickListener(event -> {
                trainingPlanDialog = new TrainingPlanDialog(discService.getAllDisc(), departmentService.getAllDepartment(), controlService.getControlOfType("1"), controlService.getControlOfType("2"), studentService.getGroupStudentsPIB(groupSelect.getValue()));
                trainingPlanDialog.setSavePlanListener((trainingPlanEntity, students) -> {});
                trainingPlanDialog.setRemovePlanListener(id -> {
                    plansServices.removePlan(id);
                    trainingPlansGrid.setItems(plansServices.getStudyPlansForGroup(groupSelect.getValue(), sessionSelect.getValue()));
                });
                trainingPlanDialog.setUpdatePlanListener((plansEntity, students) -> {
                    plansServices.updatePlan(plansEntity, students, groupSelect.getValue());
                    trainingPlansGrid.setItems(plansServices.getStudyPlansForGroup(groupSelect.getValue(), sessionSelect.getValue()));
                });
                trainingPlanDialog.open(trainingPlans, studentService.getStudentPIB(plansServices.findChoiceStudent(trainingPlans).stream().toList()));
            });
            return button;
        });

        newItemButton.addClickListener(event -> {
            trainingPlanDialog = new TrainingPlanDialog(discService.getAllDisc(), departmentService.getAllDepartment(), controlService.getControlOfType("1"), controlService.getControlOfType("2"), studentService.getGroupStudentsPIB(groupSelect.getValue()));
            trainingPlanDialog.setSavePlanListener((trainingPlanEntity, students) -> {
                plansServices.savePlan(trainingPlanEntity, students, groupSelect.getValue(), sessionSelect.getValue());
                trainingPlansGrid.setItems(plansServices.getStudyPlansForGroup(groupSelect.getValue(), sessionSelect.getValue()));
            });
            trainingPlanDialog.open();
        });

        trainingPlansGrid.setHeight("100%");

        HorizontalLayout filterLayout = new HorizontalLayout();
        HorizontalLayout tableLayout = new HorizontalLayout();

        // Apply padding to filterLayout and tableLayout
        filterLayout.getStyle().set("padding", "20px 20px 0px 20px");
        tableLayout.getStyle().set("padding", "20px");

        tableLayout.setHeight("80%");

        // Apply padding to grid
        trainingPlansGrid.getStyle().set("padding", "10px");

        filterLayout.add(groupSelect, sessionSelect);
        tableLayout.add(trainingPlansGrid);

        setHeight("90%");
        add(filterLayout, tableLayout, newItemButton);
    }

    private void getGroupAndSession() {
        if (sessionSelect.getValue() != null && groupSelect.getValue() != null) {
            trainingPlansGrid.setItems(plansServices.getStudyPlansForGroup(groupSelect.getValue(), sessionSelect.getValue()));
        }
    }
}
