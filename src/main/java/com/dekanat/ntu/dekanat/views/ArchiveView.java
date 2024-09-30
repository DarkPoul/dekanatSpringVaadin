package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.entity.ReviewingCardsEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@PageTitle("Архів | Деканат")
@Route(value = "archive", layout = MainView.class)
@Component
@UIScope
public class ArchiveView extends Div {

    // Таблиця
    private Grid<ReviewingCardsEntity> grid = new Grid<>(ReviewingCardsEntity.class, false);
    private GridListDataView<ReviewingCardsEntity> dataView;

    // Переменные для хранения фильтров
    private String lastNameFilter = "";
    private String firstNameFilter = "";
    private String middleNameFilter = "";
    private String specialtyFilter = "";
    private String courseFilter = "";
    private String groupFilter = "";
    private String yearFilter = "";
    private String birthDateFilter = "";
    private String genderFilter = "";


    public ArchiveView() {
// Поле для вводу № наказу
        TextField orderNumberField = new TextField("№ Наказу");

// Кнопки
        Button refreshFromArchiveButton = new Button("Поновити з архіву");
        Button academicCertificateButton = new Button("Академічна довідка");
        Button printDiplomaButton = new Button("Друк диплому");
        Button printAppendixButton = new Button("Друк додатку");

// Створення горизонтального макета для полів та кнопок
        HorizontalLayout toolbar = new HorizontalLayout(orderNumberField,
                refreshFromArchiveButton, academicCertificateButton,
                printDiplomaButton, printAppendixButton);
        orderNumberField.getStyle().set("padding", "0px 0px 0px 16px");


// Выравнивание по центру по вертикали
        toolbar.setAlignItems(FlexComponent.Alignment.END);
        toolbar.getStyle().set("padding", "16px 0px 0px 0px");


        // Додавання макету в основну панель
        add(toolbar);

        // Настройка колонок с фильтрами
        grid.addColumn(ReviewingCardsEntity::getLastNameUkrField)
                .setHeader(createHeaderWithFilter("Прізвище", value -> {
                    lastNameFilter = value;
                    applyFilters();
                }))
                .setKey("lastName")
                .setSortable(true);

        grid.addColumn(ReviewingCardsEntity::getFirstNameUkrField)
                .setHeader(createHeaderWithFilter("Ім'я", value -> {
                    firstNameFilter = value;
                    applyFilters();
                }))
                .setKey("firstName")
                .setSortable(true);

        grid.addColumn(ReviewingCardsEntity::getMiddleNameUkrField)
                .setHeader(createHeaderWithFilter("По батькові", value -> {
                    middleNameFilter = value;
                    applyFilters();
                }))
                .setKey("middleName")
                .setSortable(true);

        grid.addColumn(ReviewingCardsEntity::getGroupField)
                .setHeader(createHeaderWithFilter("Спеціальність", value -> {
                    specialtyFilter = value;
                    applyFilters();
                }))
                .setKey("specialty")
                .setSortable(true);

        grid.addColumn(ReviewingCardsEntity::getCourseField)
                .setHeader(createHeaderWithFilter("Курс", value -> {
                    courseFilter = value;
                    applyFilters();
                }))
                .setKey("course")
                .setSortable(true);

        grid.addColumn(ReviewingCardsEntity::getGroupNumberField)
                .setHeader(createHeaderWithFilter("Група", value -> {
                    groupFilter = value;
                    applyFilters();
                }))
                .setKey("group")
                .setSortable(true);

        grid.addColumn(ReviewingCardsEntity::getAdmissionYearField)
                .setHeader(createHeaderWithFilter("Рік", value -> {
                    yearFilter = value;
                    applyFilters();
                }))
                .setKey("year")
                .setSortable(true);

        grid.addColumn(ReviewingCardsEntity::getBirthDate)
                .setHeader(createHeaderWithFilter("День народження", value -> {
                    birthDateFilter = value;
                    applyFilters();
                }))
                .setKey("birthDate")
                .setSortable(true);

        grid.addColumn(ReviewingCardsEntity::getGender)
                .setHeader(createHeaderWithFilter("Стать", value -> {
                    genderFilter = value;
                    applyFilters();
                }))
                .setKey("gender")
                .setSortable(true);

        grid.getStyle().set("border", "1px solid #ddd");
        grid.getStyle().set("border-radius", "8px");
        grid.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        grid.getStyle().set("margin", "16px");
        grid.getStyle().set("position", "relative");
        grid.getStyle().set("background", "white");
        grid.getStyle().set("min-height", "530px");
        grid.getElement().executeJs(
                "this.shadowRoot.querySelector('#table').style.marginTop = '5px'; " +
                        "this.shadowRoot.querySelector('#table').style.marginBottom = '5px'; "
        );
        grid.getStyle().set("height", "auto");
        // Додавання даних в таблицю з прикладом
        List<ReviewingCardsEntity> sampleData = new ArrayList<>();
        sampleData.add(new ReviewingCardsEntity("Іванов", "Іван", "Іванович", "Комп'ютерні науки", 1, "КН-101", 2021, "2000-01-01", "Чоловіча"));
        sampleData.add(new ReviewingCardsEntity("Петренко", "Петро", "Петрович", "Математика", 2, "М-202", 2020, "1999-02-02", "Чоловіча"));
        sampleData.add(new ReviewingCardsEntity("Сидоренко", "Оксана", "Сидорівна", "Фізика", 3, "Ф-303", 2019, "1998-03-03", "Жіноча"));
        sampleData.add(new ReviewingCardsEntity("Коваленко", "Марія", "Ковалівна", "Хімія", 1, "Х-404", 2022, "2001-04-04", "Жіноча"));
        sampleData.add(new ReviewingCardsEntity("Коваленко", "Марія", "Ковалівна", "Хімія", 1, "Х-404", 2022, "2001-04-04", "Жіноча"));
        sampleData.add(new ReviewingCardsEntity("Коваленко", "Марія", "Ковалівна", "Хімія", 1, "Х-404", 2022, "2001-04-04", "Жіноча"));
        sampleData.add(new ReviewingCardsEntity("Коваленко", "Марія", "Ковалівна", "Хімія", 1, "Х-404", 2022, "2001-04-04", "Жіноча"));
        sampleData.add(new ReviewingCardsEntity("Коваленко", "Марія", "Ковалівна", "Хімія", 1, "Х-404", 2022, "2001-04-04", "Жіноча"));
        sampleData.add(new ReviewingCardsEntity("Коваленко", "Марія", "Ковалівна", "Хімія", 1, "Х-404", 2022, "2001-04-04", "Жіноча"));

        // Установка данных в таблицю
        dataView = grid.setItems(sampleData);

        // Додавання таблиці в основну панель
        add(grid);
    }

    private void applyFilters() {
        dataView.setFilter(item ->
                matches(item.getLastNameUkrField(), lastNameFilter) &&
                        matches(item.getFirstNameUkrField(), firstNameFilter) &&
                        matches(item.getMiddleNameUkrField(), middleNameFilter) &&
                        matches(item.getGroupField(), specialtyFilter) &&
                        matches(String.valueOf(item.getCourseField()), courseFilter) &&
                        matches(item.getGroupNumberField(), groupFilter) &&
                        matches(String.valueOf(item.getAdmissionYearField()), yearFilter) &&
                        matches(item.getBirthDate().toString(), birthDateFilter) &&
                        matches(item.getGender(), genderFilter)
        );
    }

    private static VerticalLayout createHeaderWithFilter(String headerText, Consumer<String> filterChangeConsumer) {
        TextField textField = new TextField();
        textField.setPlaceholder("Фільтр");
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.addValueChangeListener(e -> filterChangeConsumer.accept(e.getValue()));

        NativeLabel headerLabel = new NativeLabel(headerText);
        VerticalLayout layout = new VerticalLayout(headerLabel, textField);
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        return layout;
    }

    private static boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
