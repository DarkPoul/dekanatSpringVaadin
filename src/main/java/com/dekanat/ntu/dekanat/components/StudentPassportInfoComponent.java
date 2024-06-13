package com.dekanat.ntu.dekanat.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;

public class StudentPassportInfoComponent extends VerticalLayout {

    HorizontalLayout studentPassH1Layout = new HorizontalLayout();
    HorizontalLayout studentPassH2Layout = new HorizontalLayout();
    HorizontalLayout studentPassH3Layout = new HorizontalLayout();
    HorizontalLayout studentPassH4Layout = new HorizontalLayout();
    HorizontalLayout studentPassH5Layout = new HorizontalLayout();

    Select<String> sexStudent = new Select<>();
    Select<String> natStudent = new Select<>();
    DatePicker dateInPass = new DatePicker();
    DatePicker dateOffPass = new DatePicker();
    TextField numOfRecPass = new TextField();
    TextField serOfPass = new TextField();
    TextField numOfPass = new TextField();
    TextField issAuthor = new TextField();
    TextField identNumber = new TextField();
    HorizontalLayout studentPassH1LayoutEdit = new HorizontalLayout();
    HorizontalLayout studentPassH2LayoutEdit = new HorizontalLayout();
    HorizontalLayout studentPassH3LayoutEdit = new HorizontalLayout();
    HorizontalLayout studentPassH4LayoutEdit = new HorizontalLayout();
    HorizontalLayout studentPassH5LayoutEdit = new HorizontalLayout();

    Select<String> sexStudentEdit = new Select<>();
    Select<String> natStudentEdit = new Select<>();
    DatePicker dateInPassEdit = new DatePicker();
    DatePicker dateOffPassEdit = new DatePicker();
    TextField numOfRecPassEdit = new TextField();
    TextField serOfPassEdit = new TextField();
    TextField numOfPassEdit = new TextField();
    TextField issAuthorEdit = new TextField();
    TextField identNumberEdit = new TextField();

    Button editStudentInfo = new Button();
    Button saveStudentInfo = new Button();
    Button cancelStudentInfo = new Button();

    public StudentPassportInfoComponent(){


        studentPassH1Layout.add(dateInPass, dateOffPass);
        studentPassH2Layout.add(numOfRecPass, serOfPass, numOfPass);
        studentPassH3Layout.add(issAuthor, identNumber);
        studentPassH4Layout.add(sexStudent, natStudent);






        sexStudent.setLabel("Стать");
        natStudent.setLabel("Національність");
        dateInPass.setLabel("Дата видачі паспорта");
        dateOffPass.setLabel("Паспорт дійсний до");
        numOfRecPass.setLabel("Номер запису");
        serOfPass.setLabel("Серія");
        serOfPass.setWidth("10%");
        numOfPass.setLabel("Номер");
        numOfPass.setWidth("19%");
        issAuthor.setLabel("Орган що видав документ");
        identNumber.setLabel("Ідентифікаційний номер");


        editStudentInfo.setText("Редагувати");

        sexStudent.setValue("Чоловіча");
        natStudent.setValue("Українська");
        dateInPass.setValue(LocalDate.of(2000, 1, 11));
        dateOffPass.setValue(LocalDate.of(2010, 1, 11));
        numOfRecPass.setValue("12345");
        serOfPass.setValue("");
        numOfPass.setValue("1234567890");
        issAuthor.setValue("321");
        identNumber.setValue("0987654321");


        sexStudent.setReadOnly(true);
        natStudent.setReadOnly(true);
        dateInPass.setReadOnly(true);
        dateOffPass.setReadOnly(true);
        numOfRecPass.setReadOnly(true);
        serOfPass.setReadOnly(true);
        numOfPass.setReadOnly(true);
        issAuthor.setReadOnly(true);
        identNumber.setReadOnly(true);

        setHorizontalComponentAlignment(FlexComponent.Alignment.END, editStudentInfo);

        getStyle().set("border", "1px solid #e0e0e0");
        setWidth("436px");

        add(new H3("Паспортна інформація"), studentPassH1Layout, studentPassH2Layout, studentPassH3Layout, studentPassH4Layout, editStudentInfo);

    }

}
