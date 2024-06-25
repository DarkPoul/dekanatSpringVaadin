package com.dekanat.ntu.dekanat.components;

import com.dekanat.ntu.dekanat.entity.TrainingPlansEntity;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class TrainingPlansFormDialog extends Dialog {

    ComboBox<String> selectDiscipline = new ComboBox<>();
    ComboBox<String> selectDepartment = new ComboBox<>();
    Select<String> selectFirstTypeControl = new Select<>();
    Select<String> selectSecondTypeControl = new Select<>();
    Select<String> selectPartTypeControl = new Select<>();
    TextField textFieldCredits = new TextField();
    RadioButtonGroup<String> choiceDiscipline = new RadioButtonGroup<>();

    Checkbox checkAllStudent = new Checkbox();
    CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();


    // Add more fields as needed

    public TrainingPlansFormDialog() {

        VerticalLayout allLayout = new VerticalLayout();

        HorizontalLayout typeControlLayout = new HorizontalLayout(selectFirstTypeControl, selectSecondTypeControl);
        HorizontalLayout otherControlLayout = new HorizontalLayout(selectPartTypeControl, textFieldCredits, choiceDiscipline);

        choiceDiscipline.setLabel("Вибіркова дисципліна");
        choiceDiscipline.setItems("Так", "Ні");
        choiceDiscipline.setValue("Ні");

        FormLayout formLayout = new FormLayout();
        allLayout.add(selectDiscipline, selectDepartment, typeControlLayout, otherControlLayout);

        // Add more fields to the form layout

        add(allLayout);
    }

    public void setEntity(TrainingPlansEntity entity) {
//        titleField.setValue(entity.getTitle());
//        hoursField.setValue(entity.getHours());

        // Set values for other fields
    }

//    public TrainingPlansEntity getEntity() {
//        return new TrainingPlansEntity(
//                numberField.getValue(),
//                titleField.getValue(),
//                hoursField.getValue()
//                // Get values from other fields
//        );

}
