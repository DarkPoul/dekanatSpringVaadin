package com.dekanat.ntu.dekanat.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;

public class StudentMainInfoComponent extends VerticalLayout {

    HorizontalLayout studentInfoH1Layout = new HorizontalLayout();
    HorizontalLayout studentInfoH2Layout = new HorizontalLayout();
    HorizontalLayout studentInfoH3Layout = new HorizontalLayout();
    HorizontalLayout studentInfoH4Layout = new HorizontalLayout();
    HorizontalLayout studentInfoH5Layout = new HorizontalLayout();

    TextField pStudent = new TextField();
    TextField iStudent = new TextField();
    TextField bStudent = new TextField();
    TextField pEnStudent = new TextField();
    TextField iEnStudent = new TextField();
    DatePicker bdStudent = new DatePicker();

    HorizontalLayout studentInfoH1LayoutEdit = new HorizontalLayout();
    HorizontalLayout studentInfoH2LayoutEdit = new HorizontalLayout();
    HorizontalLayout studentInfoH3LayoutEdit = new HorizontalLayout();
    HorizontalLayout studentInfoH4LayoutEdit = new HorizontalLayout();
    HorizontalLayout studentInfoH5LayoutEdit = new HorizontalLayout();

    TextField pStudentEdit = new TextField();
    TextField iStudentEdit = new TextField();
    TextField bStudentEdit = new TextField();
    TextField pEnStudentEdit = new TextField();
    TextField iEnStudentEdit = new TextField();
    DatePicker bdStudentEdit = new DatePicker();
    TextField applicantCard = new TextField();
    TextField individualNum = new TextField();
    TextField phoneNum = new TextField();
    TextField email = new TextField();
    H3 studentInfo = new H3("Основна інформація");
    Button editStudentInfo = new Button();
    Button saveStudentInfo = new Button();
    Button cancelStudentInfo = new Button();

    public StudentMainInfoComponent(){
        studentInfoH1Layout.add(pStudent, pEnStudent);
        studentInfoH2Layout.add(iStudent, iEnStudent);
        studentInfoH3Layout.add(bStudent, bdStudent);
        studentInfoH4Layout.add(phoneNum, email);
        studentInfoH5Layout.add(applicantCard, individualNum);


        setHorizontalComponentAlignment(FlexComponent.Alignment.END, editStudentInfo);

        setWidth("436px");
        getStyle().set("border", "1px solid #e0e0e0");
        add(studentInfo ,studentInfoH1Layout, studentInfoH2Layout, studentInfoH3Layout, studentInfoH4Layout, studentInfoH5Layout, editStudentInfo);

        pStudent.setLabel("Прізвище");
        iStudent.setLabel("Ім'я");
        bStudent.setLabel("По батькові");
        pEnStudent.setLabel("Прізвище анг.");
        iEnStudent.setLabel("Ім'я анг.");
        bdStudent.setLabel("Дата народження");
        applicantCard.setLabel("Номер картки здобувача");
        individualNum.setLabel("Номер фізичної особи");
        phoneNum.setLabel("Номер телефону");
        email.setLabel("Електронна пошта");

        pStudent.setReadOnly(true);
        iStudent.setReadOnly(true);
        bStudent.setReadOnly(true);
        pEnStudent.setReadOnly(true);
        iEnStudent.setReadOnly(true);
        bdStudent.setReadOnly(true);
        applicantCard.setReadOnly(true);
        individualNum.setReadOnly(true);
        phoneNum.setReadOnly(true);
        email.setReadOnly(true);

        pStudent.setValue("Гончар");
        iStudent.setValue("Павло");
        bStudent.setValue("Олександрович");
        pEnStudent.setValue("Honchar");
        iEnStudent.setValue("Pavlo");
        bdStudent.setValue(LocalDate.of(2001, 11, 28));
        applicantCard.setValue("1234567890");
        individualNum.setValue("0987654321");
        phoneNum.setValue("+38 000 000 00 00");
        email.setValue("admin@test.com");


        editStudentInfo.setText("Редагувати");


        editStudentInfo.addClickListener(buttonClickEvent -> openInfoDialog(studentInfo.getText()));

    }

    private void openInfoDialog(String info) {
        Dialog dialog = new Dialog();

        studentInfoH1LayoutEdit.add(pStudentEdit, pEnStudentEdit);
        studentInfoH2LayoutEdit.add(iStudentEdit, iEnStudentEdit);
        studentInfoH3LayoutEdit.add(bStudentEdit, bdStudentEdit);
        studentInfoH3LayoutEdit.setHeight("40%");
        studentInfoH4LayoutEdit.add(cancelStudentInfo, saveStudentInfo);

        cancelStudentInfo.setText("Відміна");
        saveStudentInfo.setText("Зберегти");

        pStudentEdit.setLabel("Прізвище");
        iStudentEdit.setLabel("Ім'я");
        bStudentEdit.setLabel("По батькові");
        pEnStudentEdit.setLabel("Прізвище анг.");
        iEnStudentEdit.setLabel("Ім'я анг.");
        bdStudentEdit.setLabel("Дата народження");

        pStudentEdit.setValue("Гончар");
        iStudentEdit.setValue("Павло");
        bStudentEdit.setValue("Олександрович");
        pEnStudentEdit.setValue("Honchar");
        iEnStudentEdit.setValue("Pavlo");
        bdStudentEdit.setValue(LocalDate.of(2001, 11, 28));

        studentInfoH4LayoutEdit.setJustifyContentMode(JustifyContentMode.END);

        cancelStudentInfo.addClickListener(buttonClickEvent -> dialog.close());



        dialog.add(new H4(info), studentInfoH1LayoutEdit, studentInfoH2LayoutEdit, studentInfoH3LayoutEdit, studentInfoH4LayoutEdit);
        dialog.setWidth("450px");
        dialog.setHeight("400px");
        dialog.open();
    }

}
