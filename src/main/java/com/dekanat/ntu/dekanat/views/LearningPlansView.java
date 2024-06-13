package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.entity.TrainingPlanEntity;
import com.dekanat.ntu.dekanat.services.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Навчальні плани | Деканат")
@Route(value = "learning-plans", layout = MainView.class)
public class LearningPlansView extends Div {

    @Autowired
    private final StudentService studentService;
    @Autowired
    private final DisciplinesService disciplinesService;
    @Autowired
    private final DepartmentService departmentService;
    @Autowired
    private final FormControlService formControlService;
    @Autowired
    private final TrainingPlanService trainingPlanService;

    Select<String> selectGroup = new Select<>();
    Select<String> selectSession = new Select<>();
    Grid<TrainingPlanEntity> trainingPlanModelGrid = new Grid<>(TrainingPlanEntity.class, false);
    List<TrainingPlanEntity> TPElements = new ArrayList<>();
    ComboBox<String> selectDiscipline = new ComboBox<>();
    ComboBox<String> selectDepartment = new ComboBox<>();
    Select<String> selectFirstTypeControl = new Select<>();
    Select<String> selectSecondTypeControl = new Select<>();
    Select<String> selectPartTypeControl = new Select<>();
    TextField textFieldCredits = new TextField();
    RadioButtonGroup<String> choiceDiscipline = new RadioButtonGroup<>();
    Checkbox checkAllStudent = new Checkbox();
    CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
    List<String> students = new ArrayList<>();
    Button addButton = new Button("Додати");
    Button saveButton = new Button("Зберегти");

    VerticalLayout studentGroupLayout = new VerticalLayout();

    public LearningPlansView(StudentService studentService, DisciplinesService disciplinesService, DepartmentService departmentService, FormControlService formControlService, TrainingPlanService trainingPlanService) {
        this.studentService = studentService;
        this.disciplinesService = disciplinesService;
        this.departmentService = departmentService;
        this.formControlService = formControlService;
        this.trainingPlanService = trainingPlanService;

        selectPartTypeControl.setVisible(false);


        setUI();

        selectGroup.addValueChangeListener(selectStringComponentValueChangeEvent -> {
            if (!students.isEmpty()) students.clear();
            students.addAll(studentService.getStudents(selectGroup.getValue()));
            checkboxGroup.setItems(students);
            setTrainingPlan(students);
        });

        selectSession.addValueChangeListener(selectStringComponentValueChangeEvent -> setTrainingPlan(students));

        choiceDiscipline.addValueChangeListener(checkboxBooleanComponentValueChangeEvent -> studentGroupLayout.setEnabled(!choiceDiscipline.getValue().equals("Ні")));

        selectSecondTypeControl.addValueChangeListener(selectStringComponentValueChangeEvent -> selectPartTypeControl.setVisible(selectSecondTypeControl.getValue().equals("Розрахункова робота") ||
                selectSecondTypeControl.getValue().equals("Розрахунково-графічна робота")));


    }

    public void setGroupSelect() {
        selectGroup.setLabel("Оберіть групу");
        selectGroup.setItems(studentService.getGroups());
    }

    public void setSessionSelect() {
        selectSession.setLabel("Оберіть сесію");
        selectSession.setItems("Зимова", "Літня");
    }

    public void setTPTable() {
        trainingPlanModelGrid.addColumn(trainingPlan -> TPElements.indexOf(trainingPlan) + 1).setHeader("№").setSortable(false);
        trainingPlanModelGrid.addColumn(TrainingPlanEntity::getDiscipline).setHeader("Дисципліна");
        trainingPlanModelGrid.addColumn(TrainingPlanEntity::getCredits).setHeader("Години");
        trainingPlanModelGrid.addColumn(TrainingPlanEntity::getIsChoice).setHeader("Вибіркова");
        trainingPlanModelGrid.addColumn(TrainingPlanEntity::getFirstType).setHeader("1 контроль");
        trainingPlanModelGrid.addColumn(TrainingPlanEntity::getSecondType).setHeader("2 контроль");
        trainingPlanModelGrid.addColumn(TrainingPlanEntity::getParts).setHeader("Частини");
        trainingPlanModelGrid.addColumn(TrainingPlanEntity::getDepartment).setHeader("Кафедра");

        GridContextMenu<TrainingPlanEntity> menu = trainingPlanModelGrid.addContextMenu();
        menu.addItem("Редагувати", trainingPlanEntityGridContextMenuItemClickEvent -> {

            checkboxGroup.setValue(new HashSet<>(
                    trainingPlanService.studentWhoChoiceDiscipline(
                            students, selectSession.getValue(),
                            trainingPlanModelGrid.getSelectedItems().iterator().next().getDiscipline(),
                            selectGroup.getValue())
            ));

            if (trainingPlanModelGrid.getSelectedItems().iterator().next().getIsChoice().equals("Так")){
                studentGroupLayout.setEnabled(true);
                choiceDiscipline.setValue("Так");
            } else {
                studentGroupLayout.setEnabled(false);
                choiceDiscipline.setValue("Ні");
            }

            System.out.println("Edit");
            System.out.println(trainingPlanModelGrid.getSelectedItems().iterator().next().getId());
            trainingPlanModelGrid.setEnabled(false);

            selectDiscipline.setValue(trainingPlanModelGrid.getSelectedItems().iterator().next().getDiscipline());
            selectDepartment.setValue(trainingPlanModelGrid.getSelectedItems().iterator().next().getDepartment());
            selectFirstTypeControl.setValue(trainingPlanModelGrid.getSelectedItems().iterator().next().getFirstType());
            selectSecondTypeControl.setValue(trainingPlanModelGrid.getSelectedItems().iterator().next().getSecondType());
            selectPartTypeControl.setValue(String.valueOf(trainingPlanModelGrid.getSelectedItems().iterator().next().getParts()));
            textFieldCredits.setValue(String.valueOf(trainingPlanModelGrid.getSelectedItems().iterator().next().getCredits()));





            addButton.setVisible(false);
            saveButton.setVisible(true);

        });
        menu.addItem("Видалити", trainingPlanEntityGridContextMenuItemClickEvent -> {
            System.out.println("Delete");
            TPElements.remove(trainingPlanModelGrid.getSelectedItems().iterator().next().getId()-1);
            trainingPlanModelGrid.setItems(TPElements);

            Notification successAdd = Notification.show("Дисципліна успішно видалина з навчального плану");
            successAdd.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            successAdd.setPosition(Notification.Position.TOP_CENTER);

        });

        saveButton.addClickListener(buttonClickEvent -> {
            addButton.setVisible(true);
            saveButton.setVisible(false);

            for (TrainingPlanEntity entity : TPElements){
                if (entity.getId() == trainingPlanModelGrid.getSelectedItems().iterator().next().getId() -1){
                    TPElements.get(entity.getId()).setDiscipline(selectDiscipline.getValue());
                    TPElements.get(entity.getId()).setDepartment(selectDepartment.getValue());
                    TPElements.get(entity.getId()).setFirstType(selectFirstTypeControl.getValue());
                    TPElements.get(entity.getId()).setSecondType(selectSecondTypeControl.getValue());
                    TPElements.get(entity.getId()).setParts(Integer.parseInt(selectPartTypeControl.getValue()));
                    TPElements.get(entity.getId()).setCredits(Integer.parseInt(textFieldCredits.getValue()));
                    TPElements.get(entity.getId()).setIsChoice(choiceDiscipline.getValue());
                    break;
                }
            }

            trainingPlanModelGrid.setItems(TPElements);


            deActive();


        });

        addButton.addClickListener(buttonClickEvent -> {
            TrainingPlanEntity newTPlan = new TrainingPlanEntity();
            newTPlan.setId(TPElements.size()+1);
            newTPlan.setDiscipline(selectDiscipline.getValue());
            newTPlan.setCredits(Integer.parseInt(textFieldCredits.getValue()));
            if (choiceDiscipline.getValue().equals("Ні")){
                newTPlan.setIsChoice("Ні");
            } else newTPlan.setIsChoice("Так");
            newTPlan.setFirstType(selectFirstTypeControl.getValue());
            newTPlan.setSecondType(selectSecondTypeControl.getValue());
            try{
                newTPlan.setParts(Integer.parseInt(selectPartTypeControl.getValue()));
            } catch (NumberFormatException e){
                newTPlan.setParts(1);
            }

            newTPlan.setDepartment(selectDepartment.getValue());

            TPElements.add(newTPlan);

            trainingPlanModelGrid.setItems(TPElements);



            if (choiceDiscipline.getValue().equals("Ні")){
                trainingPlanService.saveTP(newTPlan, students, selectSession.getValue(), selectGroup.getValue());
            } else {
                System.out.println(new ArrayList<>(checkboxGroup.getSelectedItems()));
                trainingPlanService.saveTP(newTPlan, new ArrayList<>(checkboxGroup.getSelectedItems()), selectSession.getValue(), selectGroup.getValue());
            }

            deActive();



            Notification successAdd = Notification.show("Дисципліна успішно додана до навчального плану");
            successAdd.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            successAdd.setPosition(Notification.Position.TOP_CENTER);




        });

    }

    public void setSelectDiscipline() {
        selectDiscipline.setLabel("Оберіть дисципліну");
        selectDiscipline.setItems(disciplinesService.getAllDisciplines());
    }

    public void setSelectDepartment() {
        selectDepartment.setLabel("Оберіть кафедру");
        selectDepartment.setItems(departmentService.getAllDepartments());
    }

    public void setSelectFirstTypeControl() {
        selectFirstTypeControl.setLabel("Оберіть 1 тип контролю");
        selectFirstTypeControl.setItems(formControlService.getFormControlsByTypeControl(1));
    }

    public void setSelectSecondTypeControl() {
        selectSecondTypeControl.setLabel("Оберіть 2 тип контролю");
        selectSecondTypeControl.setItems(formControlService.getFormControlsByTypeControl(2));
    }

    public void setSelectPartTypeControl() {
        selectPartTypeControl.setLabel("Оберіть кількість частин");
        selectPartTypeControl.setItems("2", "4", "6", "8");
    }

    public void setTextFieldCredits() {
        textFieldCredits.setLabel("Години");
    }

    public void setChoiceDiscipline() {
        choiceDiscipline.setLabel("Вибіркова дисципліна");
        choiceDiscipline.setItems("Так", "Ні");
        choiceDiscipline.setValue("Ні");
    }

    public void setCheckAllStudent() {
        checkAllStudent.setLabel("Всі");
    }

    public void setCheckboxGroup() {
        checkboxGroup.setLabel("Студенти що вибрали дисципліну");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        checkboxGroup.setHeight("95%");
        checkboxGroup.getStyle().set("margin-left", "10px");
        checkAllStudent.setHeight("5%");
        checkAllStudent.getStyle().set("margin-left", "10px");

        checkboxGroup.addValueChangeListener(event -> {
            if (event.getValue().size() == students.size()) {
                checkAllStudent.setValue(true);
                checkAllStudent.setIndeterminate(false);
            } else if (event.getValue().isEmpty()) {
                checkAllStudent.setValue(false);
                checkAllStudent.setIndeterminate(false);
            } else {
                checkAllStudent.setIndeterminate(true);
            }
        });
        checkAllStudent.addValueChangeListener(event -> {
            if (checkAllStudent.getValue()) {
                checkboxGroup.setValue(new HashSet<>(students));
            } else {
                checkboxGroup.deselectAll();
            }
        });
    }

    public void setAddButton() {
        addButton.setText("Додати");
    }

    public void setSaveButton() {
        saveButton.setText("Зберегти");
        saveButton.setVisible(false);
    }

    public void setUI(){
        setGroupSelect();
        setSessionSelect();
        setTPTable();
        setSelectDiscipline();
        setSelectDepartment();
        setSelectFirstTypeControl();
        setSelectSecondTypeControl();
        setSelectPartTypeControl();
        setTextFieldCredits();
        setChoiceDiscipline();
        setCheckAllStudent();
        setCheckboxGroup();
        setAddButton();
        setSaveButton();

        VerticalLayout allLayout = new VerticalLayout();
        allLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.START);
        allLayout.setPadding(false);
        allLayout.setMargin(false);
        allLayout.getStyle().set("margin-left", "24px");

        HorizontalLayout selectLayout = new HorizontalLayout();
        selectLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START);
        selectLayout.setPadding(false);
        selectLayout.setMargin(false);
        selectLayout.setWidth("100%");

        HorizontalLayout mainActivitiesLayout = new HorizontalLayout();
        mainActivitiesLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START);
        mainActivitiesLayout.setPadding(false);
        mainActivitiesLayout.setMargin(false);
        mainActivitiesLayout.setWidth("100%");
        mainActivitiesLayout.setHeight("100%");

        VerticalLayout gridAndControlLayout = new VerticalLayout();
        gridAndControlLayout.setPadding(false);
        gridAndControlLayout.setMargin(false);
        gridAndControlLayout.setWidth("75%");

        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START);
        controlLayout.setPadding(false);
        controlLayout.setMargin(false);

        VerticalLayout controlLayout1 = new VerticalLayout();
        controlLayout1.setPadding(false);
        controlLayout1.setMargin(false);

        VerticalLayout controlLayout2 = new VerticalLayout();
        controlLayout2.setPadding(false);
        controlLayout2.setMargin(false);

        VerticalLayout controlLayout3 = new VerticalLayout();
        controlLayout3.setPadding(false);
        controlLayout3.setMargin(false);

        VerticalLayout controlLayout4 = new VerticalLayout();
        controlLayout4.setPadding(false);
        controlLayout4.setMargin(false);

        VerticalLayout controlLayout5 = new VerticalLayout();
        controlLayout5.setPadding(false);
        controlLayout5.setMargin(false);

        studentGroupLayout = new VerticalLayout();
        studentGroupLayout.getElement().getStyle().set("border", "1px solid #e0e0e0");
        studentGroupLayout.setHeight("600px");
        studentGroupLayout.setPadding(false);
        studentGroupLayout.setMargin(false);
        studentGroupLayout.setWidth("25%");
        studentGroupLayout.setEnabled(false);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setPadding(false);
        buttonLayout.setMargin(false);
        buttonLayout.setWidth("98%");

        selectLayout.add(selectGroup, selectSession);
        controlLayout1.add(selectDiscipline, selectDepartment);
        controlLayout2.add(selectFirstTypeControl);
        controlLayout3.add(selectSecondTypeControl, selectPartTypeControl);
        controlLayout4.add(textFieldCredits);
        controlLayout5.add(choiceDiscipline);

        controlLayout.add(controlLayout1, controlLayout2, controlLayout3, controlLayout4, controlLayout5);

        gridAndControlLayout.add(trainingPlanModelGrid, controlLayout);

        studentGroupLayout.add(checkboxGroup, checkAllStudent);

        mainActivitiesLayout.add(gridAndControlLayout, studentGroupLayout);

        buttonLayout.add(addButton, saveButton);
        allLayout.add(selectLayout, mainActivitiesLayout, buttonLayout);

        add(allLayout);
    }

    public void setTrainingPlan(List<String> pib){
        if (selectGroup.getValue() != null && selectSession.getValue() != null){
            TPElements.clear();
            TPElements.addAll(trainingPlanService.getTrainingPlan(pib, selectSession.getValue(), selectGroup.getValue()));
            trainingPlanModelGrid.setItems(TPElements);
        }
    }

    public void deActive(){
        selectDiscipline.setValue("");
        selectDepartment.setValue("");
        selectFirstTypeControl.setValue("");
        selectSecondTypeControl.setValue("");
        selectPartTypeControl.setValue("");
        textFieldCredits.setValue("");
        trainingPlanModelGrid.setEnabled(true);

        checkboxGroup.deselectAll();
        choiceDiscipline.setValue("Ні");
    }
}
