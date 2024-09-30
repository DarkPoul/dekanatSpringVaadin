package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.entity.DepartmentEntity;
import com.dekanat.ntu.dekanat.entity.DisciplineEntity;
import com.dekanat.ntu.dekanat.entity.SpecialtyEntity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Довідник | Деканат")
@Route(value = "handbook", layout = MainView.class)
@Component
@UIScope
public class HandbookView extends Div {
    private VerticalLayout mainLayout = new VerticalLayout();
    private Grid<DepartmentEntity> departmentGrid = new Grid<>(DepartmentEntity.class, false);
    private Grid<DisciplineEntity> disciplineGrid = new Grid<>(DisciplineEntity.class, false);
    private Grid<SpecialtyEntity> specialtyGrid = new Grid<>(SpecialtyEntity.class, false);

    public HandbookView() {
        // Create Tabs
        Tab disciplinesTab = new Tab("Довідник дисциплін");
        Tab departmentsTab = new Tab("Довідник кафедр");
        Tab specialtiesTab = new Tab("Довідник спеціальностей");

        // Create Tabs component
        Tabs tabs = new Tabs(disciplinesTab, departmentsTab, specialtiesTab);


        // Create content containers for each tab
        Div disciplinesContent = new Div();
        Div departmentsContent = new Div();
        Div specialtiesContent = new Div();

        // Configure the grids directly in the constructor
        disciplineGrid.addColumn(DisciplineEntity::getCode).setHeader("Код").setWidth("100px").setFlexGrow(0);
        disciplineGrid.addColumn(DisciplineEntity::getShortName).setHeader("Скорочена назва").setAutoWidth(true);

        // Add test data for disciplines
        List<DisciplineEntity> disciplineTestData = new ArrayList<>();
        disciplineTestData.add(new DisciplineEntity(101, "Основи програмування"));
        disciplineTestData.add(new DisciplineEntity(201, "Вища математика"));
        disciplineTestData.add(new DisciplineEntity(301, "Теоретична фізика"));
        disciplineGrid.setItems(disciplineTestData);

        disciplineGrid.getStyle().set("border", "1px solid #ddd");
        disciplineGrid.getStyle().set("border-radius", "8px");
        disciplineGrid.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        disciplineGrid.getStyle().set("padding", "20px");
        disciplineGrid.getStyle().set("position", "relative");
        disciplineGrid.getStyle().set("background", "white");
        disciplineGrid.getStyle().set("min-height", "230px");
        disciplineGrid.getElement().executeJs(
                "this.shadowRoot.querySelector('#table').style.marginTop = '5px'; " +
                        "this.shadowRoot.querySelector('#table').style.marginBottom = '5px'; "
        );

        departmentGrid.addColumn(DepartmentEntity::getId).setHeader("ID").setWidth("100px").setFlexGrow(0);
        departmentGrid.addColumn(DepartmentEntity::getName).setHeader("Назва").setAutoWidth(true);
        departmentGrid.addColumn(DepartmentEntity::getPhone).setHeader("Телефон").setAutoWidth(true);

        List<DepartmentEntity> departmentTestData = new ArrayList<>();
        departmentTestData.add(new DepartmentEntity(1, "Кафедра комп'ютерних наук", "123-456-789"));
        departmentTestData.add(new DepartmentEntity(2, "Кафедра математики", "987-654-321"));
        departmentTestData.add(new DepartmentEntity(3, "Кафедра фізики", "555-666-777"));
        departmentGrid.setItems(departmentTestData);

        departmentGrid.getStyle().set("border", "1px solid #ddd");
        departmentGrid.getStyle().set("border-radius", "8px");
        departmentGrid.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        departmentGrid.getStyle().set("padding", "20px");
        departmentGrid.getStyle().set("position", "relative");
        departmentGrid.getStyle().set("background", "white");
        departmentGrid.getStyle().set("min-height", "230px");
        departmentGrid.getElement().executeJs(
                "this.shadowRoot.querySelector('#table').style.marginTop = '5px'; " +
                        "this.shadowRoot.querySelector('#table').style.marginBottom = '5px'; "
        );

        specialtyGrid.addColumn(SpecialtyEntity::getCode).setHeader("Код").setWidth("100px").setFlexGrow(0);
        specialtyGrid.addColumn(SpecialtyEntity::getName).setHeader("Назва").setAutoWidth(true);
        specialtyGrid.addColumn(SpecialtyEntity::getShortName).setHeader("Скорочена назва").setAutoWidth(true);

        // Add test data for specialties
        List<SpecialtyEntity> specialtyTestData = new ArrayList<>();
        specialtyTestData.add(new SpecialtyEntity(122, "Комп'ютерні науки", "КН"));
        specialtyTestData.add(new SpecialtyEntity(113, "Прикладна математика", "ПМ"));
        specialtyTestData.add(new SpecialtyEntity(104, "Фізика", "Ф"));
        specialtyGrid.setItems(specialtyTestData);

        specialtyGrid.getStyle().set("border", "1px solid #ddd");
        specialtyGrid.getStyle().set("border-radius", "8px");
        specialtyGrid.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        specialtyGrid.getStyle().set("padding", "20px");
        specialtyGrid.getStyle().set("position", "relative");
        specialtyGrid.getStyle().set("background", "white");
        specialtyGrid.getStyle().set("min-height", "230px");
        specialtyGrid.getElement().executeJs(
                "this.shadowRoot.querySelector('#table').style.marginTop = '5px'; " +
                        "this.shadowRoot.querySelector('#table').style.marginBottom = '5px'; "
        );

        // Add grids to the content containers
        disciplinesContent.add(disciplineGrid);
        disciplinesContent.getStyle().set("padding", "20px");
        disciplinesContent.getStyle().set("width", "98%");
        departmentsContent.add(departmentGrid);
        departmentsContent.getStyle().set("padding", "20px");
        departmentsContent.getStyle().set("width", "98%");
        specialtiesContent.add(specialtyGrid);
        specialtiesContent.getStyle().set("padding", "20px");
        specialtiesContent.getStyle().set("width", "98%");

        mainLayout.add(tabs, disciplinesContent);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("100%");
        mainLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(mainLayout);

        // Add a listener to switch between tabs
        tabs.addSelectedChangeListener(event -> {
            mainLayout.removeAll();
            if (event.getSelectedTab() == disciplinesTab) {
                mainLayout.add(tabs, disciplinesContent);
                disciplineGrid.getElement().executeJs(
                        "this.shadowRoot.querySelector('#table').style.marginTop = '5px'; " +
                                "this.shadowRoot.querySelector('#table').style.marginBottom = '5px'; "
                );
            } else if (event.getSelectedTab() == departmentsTab) {
                mainLayout.add(tabs, departmentsContent);
                departmentGrid.getElement().executeJs(
                        "this.shadowRoot.querySelector('#table').style.marginTop = '5px'; " +
                                "this.shadowRoot.querySelector('#table').style.marginBottom = '5px'; "
                );
            } else if (event.getSelectedTab() == specialtiesTab) {
                mainLayout.add(tabs, specialtiesContent);
                specialtyGrid.getElement().executeJs(
                        "this.shadowRoot.querySelector('#table').style.marginTop = '5px'; " +
                                "this.shadowRoot.querySelector('#table').style.marginBottom = '5px';"
                );
            }
        });
    }
}
