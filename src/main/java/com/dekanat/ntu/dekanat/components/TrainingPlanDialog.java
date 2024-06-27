package com.dekanat.ntu.dekanat.components;


import com.dekanat.ntu.dekanat.entity.TrainingPlansEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TrainingPlanDialog extends Dialog {

    private ComboBox<String> discipline = new ComboBox<>();
    private TextField hours = new TextField();
    private RadioButtonGroup<String> choiceDiscipline = new RadioButtonGroup<>();
    private Select<String> firstControl = new Select<>();
    private Select<String> secondControl = new Select<>();
    private Select<String> parts = new Select<>();
    private ComboBox<String> depart = new ComboBox<>();
    private Button save = new Button("Зберегти");
    private Button cancel = new Button("Відміна");
    private Button remove = new Button("Видалити");
    private HorizontalLayout horizontalLayout0 = new HorizontalLayout();
    private HorizontalLayout horizontalLayout1 = new HorizontalLayout();
    private VerticalLayout verticalLayout0 = new VerticalLayout();
    private VerticalLayout verticalLayout1 = new VerticalLayout();
    private VerticalLayout verticalLayout2 = new VerticalLayout();
    Checkbox checkAllStudent = new Checkbox();
    CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
    private List<String> students = new ArrayList<>();

    public TrainingPlanDialog(){


        List<String> testDisc = new ArrayList<>();
        testDisc.add("Математика");
        testDisc.add("Геометрія");
        testDisc.add("Укр. мова");
        discipline.setItems(testDisc);

        List<String> testControl = new ArrayList<>();
        testControl.add("Залік");
        testControl.add("Екзамен");
        testControl.add("Диференційний залік");
        firstControl.setItems(testControl);

        List<String> testControl1 = new ArrayList<>();
        testControl1.add("Курсова робота");
        testControl1.add("Курсовий проєкт");
        testControl1.add("Розрахункова робота");
        secondControl.setItems(testControl1);

        List<String> testPart = new ArrayList<>();
        testPart.add("2");
        testPart.add("4");
        testPart.add("6");
        parts.setItems(testPart);

        List<String> testDepatt = new ArrayList<>();
        testDepatt.add("1");
        testDepatt.add("2");
        testDepatt.add("3");
        testDepatt.add("4");
        depart.setItems(testDepatt);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        cancel.addThemeVariants();
        remove.addThemeVariants(ButtonVariant.LUMO_ERROR);

        students.add("Item0");
        students.add("Item1");
        students.add("Item2");
        students.add("Item3");
        students.add("Item4");
        students.add("Item5");

        choiceDiscipline.setItems("Так", "Ні");

        discipline.setLabel("Дисципліни");
        hours.setLabel("Години");
        choiceDiscipline.setLabel("Вибіркові");
        firstControl.setLabel("Перший контроль");
        secondControl.setLabel("Другий контроль");
        parts.setLabel("Частини");
        depart.setLabel("Кафедра");

        verticalLayout0.add(discipline, hours, choiceDiscipline, firstControl, secondControl, parts, depart);
        verticalLayout1.add(checkboxGroup, checkAllStudent);
        verticalLayout1.setHorizontalComponentAlignment(FlexComponent.Alignment.END, checkAllStudent);
        verticalLayout0.setWidth("200px");

        verticalLayout1.getStyle().set("border", "1px solid #e0e0e0");
        verticalLayout1.setWidth("250px");

        checkboxGroup.setWidth("100%");
        checkboxGroup.setHeight("95%");

        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
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

        checkAllStudent.setLabel("Обрати всіх");

        checkboxGroup.setItems(students);

        horizontalLayout0.add(verticalLayout0, verticalLayout1);
        horizontalLayout1.add(save, cancel, remove);

        verticalLayout2.add(horizontalLayout0);

        add(horizontalLayout0, horizontalLayout1);
    }

    public void open(TrainingPlansEntity trainingPlansEntity) {

        hours.setValue(trainingPlansEntity.getHours());

        discipline.setValue(trainingPlansEntity.getTitle());
        firstControl.setValue(trainingPlansEntity.getFirstType());
        secondControl.setValue(trainingPlansEntity.getSecondType());
        parts.setValue(trainingPlansEntity.getParts());
        depart.setValue(trainingPlansEntity.getDepartment());
        remove.setVisible(true);
        super.open();
    }

    public void open() {
        remove.setVisible(false);
        super.open();
    }
}
