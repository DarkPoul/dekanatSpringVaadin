package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.components.TrainingPlanDialog;
import com.dekanat.ntu.dekanat.entity.TrainingPlansEntity;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;


@PageTitle("Навчальні плани | Деканат")
@Route(value = "learning-plans", layout = MainView.class)
@Component
@UIScope
public class TrainingPlansView extends Div {

//    private final TrainingPlansPresenter presenter;


    private Select<String> groupSelect = new Select<>();
    private Select<String> sessionSelect = new Select<>();
    private Grid<TrainingPlansEntity> trainingPlansGrid = new Grid<>(TrainingPlansEntity.class, false);
    private Button newItemButton = new Button("Додати");

    private TrainingPlanDialog trainingPlanDialog = new TrainingPlanDialog();

    public TrainingPlansView() {

        newItemButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        groupSelect.setLabel("Група");
        sessionSelect.setLabel("Сессія");

        trainingPlansGrid.addColumn(TrainingPlansEntity::getNumber).setHeader("№");
        trainingPlansGrid.addColumn(TrainingPlansEntity::getTitle).setHeader("Дисципліна");
        trainingPlansGrid.addColumn(TrainingPlansEntity::getHours).setHeader("Години");
        trainingPlansGrid.addColumn(TrainingPlansEntity::getIsCommonDiscipline).setHeader("Вибіркова");
        trainingPlansGrid.addColumn(TrainingPlansEntity::getFirstType).setHeader("Перший к.");
        trainingPlansGrid.addColumn(TrainingPlansEntity::getSecondType).setHeader("Другий к.");
        trainingPlansGrid.addColumn(TrainingPlansEntity::getParts).setHeader("Частини");
        trainingPlansGrid.addColumn(TrainingPlansEntity::getDepartment).setHeader("Кафедра");


        Grid.Column<TrainingPlansEntity> buttonColumn = trainingPlansGrid.addComponentColumn(trainingPlansGrid -> {
            Button button = new Button(new Icon(VaadinIcon.EDIT));
            button.addClickListener(event -> {
                // Handle button click
                trainingPlanDialog.open(trainingPlansGrid);
                System.out.println("Button clicked for ");
            });
            return button;
        });

        newItemButton.addClickListener(event -> trainingPlanDialog.open());

        trainingPlansGrid.setHeight("100%");

        TrainingPlansEntity test = new TrainingPlansEntity(1, "Математика", "0", "0", "Залік", "Курсова робота", "2", "1");
        TrainingPlansEntity test1 = new TrainingPlansEntity(2, "Геометрія", "0", "0", "Екзамен", "Курсовий проєкт", "4", "2");
        TrainingPlansEntity test2 = new TrainingPlansEntity(3, "Укр. мова", "0", "0", "Диференційний залік", "Розрахункова робота", "6", "3");



        trainingPlansGrid.setItems(test, test1, test2);
        HorizontalLayout filterLayout = new HorizontalLayout();
        HorizontalLayout tableLayout = new HorizontalLayout();

        tableLayout.setHeight("80%");

        filterLayout.add(groupSelect, sessionSelect);
        tableLayout.add(trainingPlansGrid);

        setHeight("90%");
        add(filterLayout, tableLayout, newItemButton);
    }
}
