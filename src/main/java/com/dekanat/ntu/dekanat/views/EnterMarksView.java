package com.dekanat.ntu.dekanat.views;


import com.dekanat.ntu.dekanat.entity.MarkEntity;
import com.dekanat.ntu.dekanat.models.MarkModel;
import com.dekanat.ntu.dekanat.services.StudentService;
import com.dekanat.ntu.dekanat.services.TrainingPlanService;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@PageTitle("Введення оцінок | Деканат")
@Route(value = "enter-grades", layout = MainView.class)
public class EnterMarksView extends Div {

    @Autowired
    private final StudentService studentService;
    @Autowired
    private final TrainingPlanService trainingPlanService;



    public EnterMarksView(StudentService studentService, TrainingPlanService trainingPlanService){
        this.studentService = studentService;
        this.trainingPlanService = trainingPlanService;

        Grid<MarkEntity> enterGradesTable = new Grid<>(MarkEntity.class, false);
        Editor<MarkEntity> markEditor = enterGradesTable.getEditor();

        Grid.Column<MarkEntity> idColumn = enterGradesTable.addColumn(MarkEntity::getId).setHeader("№");
        Grid.Column<MarkEntity> pibColumn = enterGradesTable.addColumn(MarkEntity::getPib).setHeader("Прізвище, ім'я, по батькові");
        Grid.Column<MarkEntity> markColumn = enterGradesTable.addColumn(MarkEntity::getMark).setHeader("Оцінка");
        Grid.Column<MarkEntity> ectsColumn = enterGradesTable.addColumn(MarkEntity::getEcts).setHeader("ECTS");
        Grid.Column<MarkEntity> dateColumn = enterGradesTable.addColumn(MarkEntity::getDate).setHeader("Дата відомості");
        Grid.Column<MarkEntity> numberColumn = enterGradesTable.addColumn(MarkEntity::getOrder).setHeader("Номер відомості");

        Grid.Column<MarkEntity> editMark = enterGradesTable.addComponentColumn(markModel -> {
           Button editButton = new Button("Редагувати");
           editButton.addClickListener(buttonClickEvent -> {
               if (markEditor.isOpen()) markEditor.cancel();
               enterGradesTable.getEditor().editItem(markModel);
           });
           return editButton;
        });

        Binder<MarkEntity> binder = new Binder<>(MarkEntity.class);
        markEditor.setBinder(binder);
        markEditor.setBuffered(true);

        TextField markField = new TextField();
        markField.setWidthFull();
        binder.forField(markField).asRequired("Не повинне бути пустим").bind(MarkEntity::getMark, MarkEntity::setMark);
        markColumn.setEditorComponent(markField);

        DatePicker datePicker = new DatePicker();
        datePicker.setI18n(setLocal());
        datePicker.setWidthFull();
        binder.forField(datePicker).asRequired("Не повинне бути пустим").bind(MarkEntity::getDate, MarkEntity::setDate);
        dateColumn.setEditorComponent(datePicker);

        TextField orderField = new TextField();
        orderField.setWidthFull();
        binder.forField(orderField).asRequired("Не повинне бути пустим").bind(MarkEntity::getOrder, MarkEntity::setOrder);
        numberColumn.setEditorComponent(orderField);


        Button saveButton = new Button("Зберегти", e -> markEditor.save());
        Button cancelButton = new Button(VaadinIcon.CLOSE.create(),
                e -> markEditor.cancel());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ICON,
                ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(saveButton,
                cancelButton);
        actions.setPadding(false);
        editMark.setEditorComponent(actions);


        MarkEntity test = new MarkEntity();
        test.setId(1);
        test.setPib("qweqwe");
        test.setMark("90");
        test.setEcts("A");
        test.setDate(LocalDate.of(2001, 11, 28));
        test.setOrder("123");

        enterGradesTable.setItems(test);




//        MarkModel markModel = new MarkModel();
//        markModel.setId(1);
//        markModel.setStudent(2);
//        markModel.setMark(12);
//        markModel.setDate(Date.valueOf("22.11.2001"));
//        markModel.setNumber(123456);

//        enterGradesTable.setItems(markModel);


        enterGradesTable.setHeight("100%");
        enterGradesTable.setHeight("850px");


        Select<String> selectGroup = new Select<>();
        selectGroup.setLabel("Виберіть групу");
        selectGroup.setItems(studentService.getGroups());

        Select<String> selectSession = new Select<>();
        selectSession.setLabel("Виберіть сесію");
        selectSession.setItems("Зимова", "Літня");



        Select<String> selectDiscipline = new Select<>();
        selectDiscipline.setLabel("Виберіть дисципліну");


        selectSession.addValueChangeListener(selectStringComponentValueChangeEvent -> {
            selectDiscipline.setItems(trainingPlanService.allDisc(selectGroup.getValue(), selectSession.getValue()));
        });



        Select<String> selectTypeOfControl = new Select<>();
        selectTypeOfControl.setLabel("Виберіть тип контролю");

        DatePicker datePickerControl = new DatePicker();
        datePickerControl.setI18n(setLocal());

        datePickerControl.setLabel("Оберіть дату відомості");

        TextField orderNumber = new TextField();
        orderNumber.setLabel("Введіть номер відомості");


        Button addButton = new Button();
        addButton.setText("Внести всім");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_SUCCESS);



        HorizontalLayout mainLayout = new HorizontalLayout();

        VerticalLayout controlLayout = new VerticalLayout();
        controlLayout.setWidth("15%");
        controlLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        controlLayout.setHeight("100%");
        controlLayout.setHeight("850px");

        VerticalLayout gridLayout = new VerticalLayout();
        gridLayout.setWidth("85%");
        gridLayout.setHeight("100%");

        controlLayout.add(selectGroup, selectSession, selectDiscipline, selectTypeOfControl, datePickerControl, orderNumber, addButton);
        gridLayout.add(enterGradesTable);
        mainLayout.add(controlLayout, gridLayout);







        add(mainLayout);



    }

    public DatePicker.DatePickerI18n setLocal(){
        DatePicker.DatePickerI18n ukrainian = new DatePicker.DatePickerI18n();
        ukrainian.setMonthNames(List.of("Січень", "Лютий", "Березень", "Квітень",
                "Травень", "Червень", "Липень", "Серпень", "Вересень", "Жовтень",
                "Листопад", "Грудень"));
        ukrainian.setWeekdays(List.of("Неділя", "Понеділок", "Вівторок",
                "Середа", "Четвер", "П'ятниця", "Субота"));
        ukrainian.setWeekdaysShort(
                List.of("Нд", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"));
        ukrainian.setToday("Сьогодні");
        ukrainian.setCancel("Скасувати");

        return ukrainian;
    }

}
