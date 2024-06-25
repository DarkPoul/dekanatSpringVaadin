package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.components.TrainingPlansFormDialog;
import com.dekanat.ntu.dekanat.entity.TrainingPlansEntity;
import com.dekanat.ntu.dekanat.models.TrainingPlanModel;
import com.dekanat.ntu.dekanat.presenter.TrainingPlansPresenter;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@PageTitle("Навчальні плани | Деканат")
@Route(value = "learning-plans", layout = MainView.class)
@Component
@UIScope
public class TrainingPlansView extends Div {

//    private final TrainingPlansPresenter presenter;

    private TrainingPlansFormDialog formDialog = new TrainingPlansFormDialog();

    private Select<String> groupSelect = new Select<>();
    private Select<String> sessionSelect = new Select<>();
    private Grid<TrainingPlansEntity> trainingPlansGrid = new Grid<>(TrainingPlansEntity.class, false);

    public TrainingPlansView() {

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
        trainingPlansGrid.setHeight("100%");


        GridContextMenu<TrainingPlansEntity> contextMenu = new GridContextMenu<>(trainingPlansGrid);
        contextMenu.addItem("Додати", event -> {
            System.out.println("Додати");
            formDialog.setEntity(new TrainingPlansEntity(1, "0", "0", "0", "0", "0", "0", "0"));
            formDialog.open();
        });
        contextMenu.addItem("Редагувати", event -> {
            System.out.println("Редагувати");
        });
        contextMenu.addItem("Видалити", event -> {
            System.out.println("Видалити");
        });

        contextMenu.addGridContextMenuOpenedListener(event -> {
            if (trainingPlansGrid.getSelectedItems().isEmpty()){
                contextMenu.getItems().get(1).setEnabled(false);
                contextMenu.getItems().get(2).setEnabled(false);
            } else {
                contextMenu.getItems().get(0).setEnabled(true);
                contextMenu.getItems().get(1).setEnabled(true);
                contextMenu.getItems().get(2).setEnabled(true);
            }
        });

        TrainingPlansEntity test = new TrainingPlansEntity(1, "null", "0", "0", "0", "0", "0", "0");

        trainingPlansGrid.setItems(test);
        HorizontalLayout filterLayout = new HorizontalLayout();
        HorizontalLayout tableLayout = new HorizontalLayout();

        tableLayout.setHeight("100%");

        filterLayout.add(groupSelect, sessionSelect);
        tableLayout.add(trainingPlansGrid);

        setHeight("90%");
        add(filterLayout, tableLayout);
    }


}
