package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.entity.SuccessEntity;
import com.dekanat.ntu.dekanat.entity.TrainingPlansEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@PageTitle("Успішність | Деканат")
@Route(value = "success", layout = MainView.class)
@Component
@UIScope
public class SuccessView extends Div {
    private HorizontalLayout mainLayout = new HorizontalLayout();
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout synchronizationLayout = new HorizontalLayout();
    private Select<String> selectGroup = new Select<>();
    private Select<String> selectFirstStudent = new Select<>();
    private Select<String> selectSecondStudent = new Select<>();
    private Button buttonSynchronization = new Button("Перенести");
    private ListBox<String> listStudents = new ListBox<>();
    private Grid<SuccessEntity> marks = new Grid<>(SuccessEntity.class, false);

    public SuccessView() {
        selectGroup.setLabel("Оберіть групу");
        selectGroup.setItems("ІBK4-1", "KN-4-1");

        selectFirstStudent.setLabel("Перенести з");
        selectFirstStudent.setItems("Пупков");

        selectSecondStudent.setLabel("Перенести для");
        selectSecondStudent.setItems("Непупков");

        listStudents.setItems("Пупков", "Непупков");
        listStudents.getStyle().set("border", "2px solid #cccccc");
        listStudents.getStyle().set("border-radius", "10px");
        listStudents.setWidth("100%");
        listStudents.setHeight("90%");

        marks.addColumn(SuccessEntity::getDiscipline).setHeader("Дисципліна").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getSemester).setHeader("Семестр").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getHours).setHeader("К. годин").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getTest).setHeader("Залік").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getExam).setHeader("Екзамен").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getD_exam).setHeader("Диферен. Е.").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getKp).setHeader("Курс. п.").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getKr).setHeader("Курс. р.").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getRr).setHeader("РР").setAutoWidth(true);
        marks.addColumn(SuccessEntity::getRgr).setHeader("РГР").setAutoWidth(true);

        SuccessEntity test = new SuccessEntity("Такато", 0, "160", "90", "95", "95", "95", "90", "90", "90");

        marks.setItems(test);

        leftLayout.add(selectGroup, listStudents);
        synchronizationLayout.add(selectFirstStudent, selectSecondStudent, buttonSynchronization);
        synchronizationLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END); // Center items vertically
        rightLayout.add(synchronizationLayout, marks);
        mainLayout.add(leftLayout, rightLayout);
        mainLayout.setHeight("100%");
        leftLayout.setHeight("100%");
        leftLayout.setWidth("30%");

        add(mainLayout);
        setHeight("100%");
    }
}
