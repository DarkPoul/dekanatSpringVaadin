package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.entity.DebtorEntity;
import com.dekanat.ntu.dekanat.views.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@PageTitle("Боржники | Деканат")
@Route(value = "debtor", layout = MainView.class)
@Component
@UIScope
@CssImport("./styles/my-grid-styles.css")
public class DebtorView extends Div {
    private VerticalLayout mainLayout = new VerticalLayout();
    private HorizontalLayout selectors = new HorizontalLayout();
    private VerticalLayout studentColumn = new VerticalLayout();
    private VerticalLayout disciplineColumn = new VerticalLayout();
    private Select<String> selectGroup = new Select<>();
    private TextField orderField = new TextField();
    private DatePicker dateField = new DatePicker();
    private Button transferButton = new Button("Переведення");
    private Grid<DebtorEntity> studentGrid = new Grid<>(DebtorEntity.class, false);
    private Grid<String> disciplineGrid = new Grid<>();

    public DebtorView() {
        // Настройка селекторов
        selectGroup.setLabel("Група");
        selectGroup.setItems("МП", "Інші групи");
        selectGroup.getStyle().set("width", "350px");

        orderField.setLabel("Наказ");

        dateField.setLabel("Дата");

        // Центрирование кнопки
        transferButton.getStyle().set("margin", "35px auto 0px");
        selectors.add(selectGroup, orderField, dateField, transferButton);
        selectors.setWidth("100%");
        selectors.setSpacing(true);

        // Настройка таблицы студентов
        studentGrid.addColumn(DebtorEntity::getLastName).setHeader("Студент").setWidth("250px");
        studentGrid.addColumn(DebtorEntity::getCourseYear).setHeader("Готовий").setWidth("95px");

        studentGrid.getStyle().set("border", "1px solid #ddd");
        studentGrid.getStyle().set("border-radius", "8px");
        studentGrid.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        studentGrid.getStyle().set("position", "relative");
        studentGrid.getStyle().set("width", "350px");

        // Добавление данных в таблицу студентов
        studentGrid.setItems(
                new DebtorEntity("Іванов", 1),
                new DebtorEntity("Петров", 0),
                new DebtorEntity("Сидоров", 1)
        );

        // Добавляем таблицу студентов в левую колонку
        studentColumn.add(studentGrid);
        studentColumn.getStyle().set("padding", "0px");

        // Настройка таблицы дисциплин
        disciplineGrid.addColumn(String::toString).setHeader("Дисципліна").setAutoWidth(true);
        disciplineGrid.addColumn(String::toString).setHeader("Причини").setAutoWidth(true);

        disciplineGrid.getStyle().set("border", "1px solid #ddd");
        disciplineGrid.getStyle().set("border-radius", "8px");
        disciplineGrid.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        disciplineGrid.getStyle().set("position", "relative");
        disciplineGrid.getStyle().set("width", "600px");

        // Добавляем таблицу дисциплин в правую колонку
        disciplineColumn.add(disciplineGrid);
        disciplineColumn.getStyle().set("padding", "0px");

        // Добавляем элементы на основную страницу
        mainLayout.add(selectors, new HorizontalLayout(studentColumn, disciplineColumn));
        add(mainLayout);
    }
}
