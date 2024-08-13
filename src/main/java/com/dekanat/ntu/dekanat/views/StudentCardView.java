package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.entity.StudentCardEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@PageTitle("Перегляд інформації | Деканат")
@Route(value = "group", layout = MainView.class)
@Component
@UIScope
public class StudentCardView extends Div {
    private VerticalLayout mainLayout = new VerticalLayout();
    private HorizontalLayout topLayout = new HorizontalLayout();
    private HorizontalLayout Layout = new HorizontalLayout();
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private Select<String> selectGroup = new Select<>();
    private Grid<StudentCardEntity> studentGrid = new Grid<>(StudentCardEntity.class, false);
    private Button evaluateAppButton = new Button("Оцінка додатку");
    private Button callStudentButton = new Button("Виклик студента");
    private Button notificationButton = new Button("Повідомлення");

    public StudentCardView() {
        selectGroup.setLabel("Виберіть групу");
        selectGroup.setItems("ІБК-4-1-20", "ІБК-4-2-20", "ІБК-4-3-20");
        selectGroup.setWidth("250px"); // Set a fixed width

        studentGrid.addColumn(StudentCardEntity::getLastName).setHeader("Прізвище").setAutoWidth(true);
        studentGrid.addColumn(StudentCardEntity::getFirstName).setHeader("Ім'я").setAutoWidth(true);
        studentGrid.addColumn(StudentCardEntity::getPatronymic).setHeader("По батькові").setAutoWidth(true);
        studentGrid.addColumn(StudentCardEntity::getRecordBookNumber).setHeader("№ Залікової книжки").setAutoWidth(true);

        studentGrid.setItems(
                new StudentCardEntity("Абдулла", "Олександра", "Віталіївна", 40258),
                new StudentCardEntity("Аль-Хуссейн", "Данііл", "Турчи", 40259),
                new StudentCardEntity("Андрющко", "Олександр", "Анатолійович", 40260),
                new StudentCardEntity("Бутова", "Марія", "Олексіївна", 40261),
                new StudentCardEntity("Кондратенко", "Сергій", "Сергійович", 40263),
                new StudentCardEntity("Костяков", "Владислав", "Володимирович", 49223),
                new StudentCardEntity("Криницький", "Микола", "Георгійович", 40264)
        );

        studentGrid.getStyle().set("border", "1px solid #ddd");
        studentGrid.getStyle().set("border-radius", "8px");
        studentGrid.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        studentGrid.getStyle().set("padding", "20px");
        studentGrid.getStyle().set("position", "relative");
        studentGrid.getStyle().set("background", "white");
        studentGrid.getStyle().set("width", "97%"); // Set the width to 97%

        topLayout.add(selectGroup);
        topLayout.setWidth("100%");
        leftLayout.add(studentGrid);
        rightLayout.add(evaluateAppButton, callStudentButton, notificationButton); // Add the new buttons here
        mainLayout.add(topLayout, Layout );
        Layout.add(leftLayout, rightLayout);

        mainLayout.setWidth("100%");
        Layout.setWidth("100%");
        leftLayout.setWidth("70%");
        rightLayout.setWidth("30%");

        add(mainLayout);
        setHeight("100%");
    }
}
