package com.dekanat.ntu.dekanat.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class StudentInstInfoComponent extends VerticalLayout {

    HorizontalLayout studentInstInfoH1Layout = new HorizontalLayout();
    HorizontalLayout studentInstInfoH2Layout = new HorizontalLayout();
    HorizontalLayout studentInstInfoH3Layout = new HorizontalLayout();
    HorizontalLayout studentInstInfoH4Layout = new HorizontalLayout();
    HorizontalLayout studentInstInfoH5Layout = new HorizontalLayout();

    Select<String> degree = new Select<>();
    Select<String> form = new Select<>();
    Select<String> spec = new Select<>();
    Select<String> course = new Select<>();
    TextField numberGroup = new TextField();
    ComboBox<Integer> yearGroup = new ComboBox<>();
    Select<String> benefits = new Select<>();
    Select<String> payment = new Select<>();
    TextField numberBook = new TextField();
    TextField numberContract = new TextField();


    Button editStudentInfo = new Button();
    Button saveStudentInfo = new Button();
    Button cancelStudentInfo = new Button();

    Div spacer = new Div();

    public StudentInstInfoComponent() {


        getStyle().set("border", "1px solid #e0e0e0");
        spacer.getStyle().set("flex-grow", "1");




        degree.setLabel("Ступінь");
        spec.setLabel("Спеціальність");
        course.setLabel("Курс");
        numberGroup.setLabel("Номер групи");
        yearGroup.setLabel("Рік групи");
        form.setLabel("Форма навчання");
        benefits.setLabel("Пільги");
        payment.setLabel("Тип оплати");
        numberBook.setLabel("Залікова книжка");
        numberContract.setLabel("Номер контракту");


        degree.setReadOnly(true);
        spec.setReadOnly(true);
        course.setReadOnly(true);
        numberGroup.setReadOnly(true);
        yearGroup.setReadOnly(true);
        form.setReadOnly(true);
        benefits.setReadOnly(true);
        payment.setReadOnly(true);
        numberBook.setReadOnly(true);
        numberContract.setReadOnly(true);


        editStudentInfo.setText("Редагувати");



        studentInstInfoH1Layout.add(spec, form);
        studentInstInfoH2Layout.add(course, degree);
        studentInstInfoH3Layout.add(numberGroup, benefits);
        studentInstInfoH4Layout.add(yearGroup, numberBook);
        studentInstInfoH5Layout.add(payment, numberContract);



        setWidth("436px");
        setHorizontalComponentAlignment(FlexComponent.Alignment.END, editStudentInfo);
        add(new H3("Університет"),studentInstInfoH1Layout, studentInstInfoH2Layout, studentInstInfoH3Layout, studentInstInfoH4Layout, studentInstInfoH5Layout, editStudentInfo);

    }

}
