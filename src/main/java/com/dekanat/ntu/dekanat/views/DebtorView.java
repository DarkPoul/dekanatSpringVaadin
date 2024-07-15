package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.entity.DebtorEntity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;
import com.vaadin.flow.component.html.Span;

@PageTitle("Боржники | Деканат")
@Route(value = "debtor", layout = MainView.class)
@Component
@UIScope
public class DebtorView extends Div {
    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout selectors = new HorizontalLayout();
    private HorizontalLayout gridLayout = new HorizontalLayout();
    private Select<String> selectFaculty = new Select<>();
    private Select<String> selectCourse = new Select<>();
    private Select<String> selectGroup = new Select<>();
    private Select<String> selectGroupNumber = new Select<>();
    private Grid<DebtorEntity> studentGrid = new Grid<>(DebtorEntity.class, false);
    private Grid<String> reasonGrid = new Grid<>();
    private Span totalStudentsLabel = new Span();
    private Span arrearsCountLabel = new Span();

    public DebtorView() {
        selectFaculty.setLabel("Факультет");
        selectFaculty.setItems("Транспортних та інформаційних технологій", "Інші факультети");
        selectFaculty.setWidth("100%");

        selectCourse.setLabel("Курс");
        selectCourse.setItems("1", "2", "3", "4", "Всі");
        selectCourse.setWidth("100%");

        selectGroup.setLabel("Група");
        selectGroup.setItems("МП", "Інші групи");
        selectGroup.setWidth("100%");

        selectGroupNumber.setLabel("Номер групи");
        selectGroupNumber.setItems("Всі", "1", "2", "3", "4");
        selectGroupNumber.setWidth("100%");

        studentGrid.addColumn(DebtorEntity::getLastName).setHeader("Прізвище").setAutoWidth(true);
        studentGrid.addColumn(DebtorEntity::getFirstName).setHeader("Ім'я").setAutoWidth(true);
        studentGrid.addColumn(DebtorEntity::getPatronymic).setHeader("По батькові").setAutoWidth(true);
        studentGrid.addColumn(DebtorEntity::getCourseYear).setHeader("Рік Курсу").setAutoWidth(true);

        studentGrid.setItems(
                new DebtorEntity("Абраменко", "Олексій", "Романович", 21),
                new DebtorEntity("Костюк", "Максим", "Миколайович", 21),
                new DebtorEntity("Крисько", "Віталій", "Андрійович", 21),
                new DebtorEntity("Лемзяков", "Олександр", "Віталійович", 21),
                new DebtorEntity("Тріц", "Дмитро", "Вікторович", 21),
                new DebtorEntity("Тушенко", "Нікіта", "Миколайович", 21),
                new DebtorEntity("Юрченя", "Владислав", "Юрійович", 21)
        );

        reasonGrid.addColumn(String::toString).setHeader("Причини");
        reasonGrid.setItems(
                "Неявка на екзамен",
                "Неуспішна здача сесії",
                "Проблеми з академічною доброчесністю",
                "Інші причини"
        );

        totalStudentsLabel.setText("Кількість студентів які мають заборгованості: 7");
        arrearsCountLabel.setText("Кількість заборгованостей студента Костюк М.М. [ МП-3-1(21) ]: 0");

        selectors.add(selectFaculty, selectCourse, selectGroup, selectGroupNumber);
        selectors.setWidth("100%");

        leftLayout.add(studentGrid, totalStudentsLabel);
        rightLayout.add(reasonGrid, arrearsCountLabel);
        gridLayout.add(leftLayout, rightLayout);
        mainLayout.add(selectors, gridLayout);

        gridLayout.setWidth("100%");
        mainLayout.setHeight("100%");
        leftLayout.setHeight("100%");
        leftLayout.setWidth("100%");

        add(mainLayout);
        setHeight("100%");
    }
}
