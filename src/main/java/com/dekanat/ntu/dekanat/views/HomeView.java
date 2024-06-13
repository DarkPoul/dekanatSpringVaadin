package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.components.TutorialCardComponent;
import com.dekanat.ntu.dekanat.models.ChangeLogEntry;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDate;
public class HomeView extends Div {

    public HomeView(){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setHeight("100%");
        mainLayout.setWidth("100%");

        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.setHeight("50%");
        infoLayout.setWidth("100%");

        VerticalLayout allBookLayout = new VerticalLayout();

        HorizontalLayout bookLayout = new HorizontalLayout();
        bookLayout.setHeight("50%");
        bookLayout.setWidth("100%");



        Grid<ChangeLogEntry> changeLogEntryGrid = new Grid<>(ChangeLogEntry.class, false);
        changeLogEntryGrid.addColumn("version").setHeader("Версія");
        changeLogEntryGrid.addColumn("date").setHeader("Дата");
        changeLogEntryGrid.addColumn("changes").setHeader("Зміни");

        changeLogEntryGrid.setItems(new ChangeLogEntry(1, "0.0.1", LocalDate.of(2024, 6, 10), "Create project"));

        bookLayout.getStyle().set("overflow", "auto");
//        bookLayout.getStyle().set("flex-wrap", "wrap");


        bookLayout.add(
                new TutorialCardComponent("images/TP.png", "Навчальні плани", "Навчальні плани"),
                new TutorialCardComponent("images/TP.png", "Успішність", "Успішність"),
                new TutorialCardComponent("images/EM.png", "Введення оцінок", "Введення оцінок"),
                new TutorialCardComponent("images/TP.png", "test", "test info"),
                new TutorialCardComponent("images/TP.png", "test", "test info"),
                new TutorialCardComponent("images/TP.png", "test", "test info"),
                new TutorialCardComponent("images/TP.png", "test", "test info"),
                new TutorialCardComponent("images/TP.png", "test", "test info")
        );


        infoLayout.add(new H3("Оновлення") ,changeLogEntryGrid);
        allBookLayout.add(new H3("Інструкція з використання"), bookLayout);

        mainLayout.add(infoLayout, allBookLayout);

        add(mainLayout);
    }

}
