package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.entity.ReviewingCardsEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
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
    Grid<ReviewingCardsEntity> grid = new Grid<>(ReviewingCardsEntity.class, false);
    private GridListDataView<ReviewingCardsEntity> dataView;

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

        // Додавання макету в основну панель
        add(toolbar);

        grid.addColumn(ReviewingCardsEntity::getLastNameUkrField)
                .setHeader("Прізвище")
                .setKey("lastName")
                .setSortable(true);
        grid.addColumn(ReviewingCardsEntity::getFirstNameUkrField)
                .setHeader("Ім'я")
                .setKey("firstName")
                .setSortable(true);
        grid.addColumn(ReviewingCardsEntity::getMiddleNameUkrField)
                .setHeader("По батькові")
                .setKey("middleName")
                .setSortable(true);
        grid.addColumn(ReviewingCardsEntity::getGroupField)
                .setHeader("Спеціальність")
                .setKey("specialty")
                .setSortable(true);
        grid.addColumn(ReviewingCardsEntity::getCourseField)
                .setHeader("Курс")
                .setKey("course")
                .setSortable(true);
        grid.addColumn(ReviewingCardsEntity::getGroupNumberField)
                .setHeader("Група")
                .setKey("group")
                .setSortable(true);
        grid.addColumn(ReviewingCardsEntity::getAdmissionYearField)
                .setHeader("Рік")
                .setKey("year")
                .setSortable(true);
        grid.addColumn(ReviewingCardsEntity::getBirthDate)
                .setHeader("День народження")
                .setKey("birthDate")
                .setSortable(true);
        grid.addColumn(ReviewingCardsEntity::getGender)
                .setHeader("Стать")
                .setKey("gender")
                .setSortable(true);


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

        // Додати фільтри для кожного стовпця
        grid.getHeaderRows().clear();
        HeaderRow headerRow = grid.appendHeaderRow();
        headerRow.getCell(grid.getColumnByKey("lastName")).setComponent(
                createFilterHeader("Прізвище", value -> dataView.addFilter(entity -> matches(entity.getLastNameUkrField(), value))));
        headerRow.getCell(grid.getColumnByKey("firstName")).setComponent(
                createFilterHeader("Ім'я", value -> dataView.addFilter(entity -> matches(entity.getFirstNameUkrField(), value))));
        headerRow.getCell(grid.getColumnByKey("middleName")).setComponent(
                createFilterHeader("По батькові", value -> dataView.addFilter(entity -> matches(entity.getMiddleNameUkrField(), value))));
        headerRow.getCell(grid.getColumnByKey("specialty")).setComponent(
                createFilterHeader("Спеціальність", value -> dataView.addFilter(entity -> matches(entity.getGroupField(), value))));
        headerRow.getCell(grid.getColumnByKey("course")).setComponent(
                createFilterHeader("Курс", value -> dataView.addFilter(entity -> matches(String.valueOf(entity.getCourseField()), value))));
        headerRow.getCell(grid.getColumnByKey("group")).setComponent(
                createFilterHeader("Група", value -> dataView.addFilter(entity -> matches(entity.getGroupNumberField(), value))));
        headerRow.getCell(grid.getColumnByKey("year")).setComponent(
                createFilterHeader("Рік", value -> dataView.addFilter(entity -> matches(String.valueOf(entity.getAdmissionYearField()), value))));
        headerRow.getCell(grid.getColumnByKey("birthDate")).setComponent(
                createFilterHeader("День народження", value -> dataView.addFilter(entity -> matches(entity.getBirthDate().toString(), value))));
        headerRow.getCell(grid.getColumnByKey("gender")).setComponent(
                createFilterHeader("Стать", value -> dataView.addFilter(entity -> matches(entity.getGender(), value))));

        // Додавання таблиці в основну панель
        add(grid);
    }

    private static com.vaadin.flow.component.Component createFilterHeader(String labelText, Consumer<String> filterChangeConsumer) {
        NativeLabel label = new NativeLabel(labelText);
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.addValueChangeListener(e -> filterChangeConsumer.accept(e.getValue()));

        VerticalLayout layout = new VerticalLayout(label, textField);
        return layout;
    }

    private static boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
