package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.components.OrderTableComponent;
import com.dekanat.ntu.dekanat.components.StudentInstInfoComponent;
import com.dekanat.ntu.dekanat.components.StudentMainInfoComponent;
//import com.dekanat.ntu.dekanat.components.StudentPassportInfoComponent;

import com.dekanat.ntu.dekanat.components.StudentPassportInfoComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Картка студента | Деканат")
@Route(value = "student-card", layout = MainView.class)
public class StudentCardView  extends Div {


    HorizontalLayout selectGroupAndStudentHLayout = new HorizontalLayout();
    HorizontalLayout componentHLayout = new HorizontalLayout();
    VerticalLayout componentVLayout = new VerticalLayout();
    HorizontalLayout instInfoHLayout = new HorizontalLayout();
    VerticalLayout selectGroupAndStudentVLayout = new VerticalLayout();
    Div mainInfoDiv = new Div();
    TabSheet tabs = new TabSheet();
    Select<String> selectGroup = new Select<>();
    Select<String> selectStudent = new Select<>();

    public StudentCardView(){

        setElementSetting();

        StudentMainInfoComponent studentMainInfoComponent = new StudentMainInfoComponent();
        StudentInstInfoComponent studentInstInfoComponent = new StudentInstInfoComponent();
        OrderTableComponent orderTableComponent = new OrderTableComponent();

//        mainInfoDiv.add(componentVLayout);
//        mainInfoDiv.add(new H3("Hello"));

        tabs.add("Головна інформація", componentVLayout);
        tabs.add("Паспорт + прописка", new StudentPassportInfoComponent());



        selectGroupAndStudentHLayout.add(selectGroup, selectStudent);
        selectGroupAndStudentVLayout.add(selectGroupAndStudentHLayout);


        instInfoHLayout.add(studentInstInfoComponent);
        instInfoHLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        componentHLayout.add(studentMainInfoComponent, studentInstInfoComponent, orderTableComponent);
        componentVLayout.add(componentHLayout);

        add(selectGroupAndStudentVLayout, tabs);

    }

    public void setElementSetting(){
        selectGroup.setLabel("Оберіть групу");
        selectStudent.setLabel("Оберіть студента");

    }

}
