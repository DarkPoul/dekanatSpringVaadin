package com.dekanat.ntu.dekanat.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@PageTitle("Перегляд карток | Деканат")
@Route(value = "reviewingcards", layout = MainView.class)
@Component
@UIScope
public class ReviewingCardsView extends Div {
    private VerticalLayout mainLayout = new VerticalLayout();
    private HorizontalLayout leftLayout1Page = new HorizontalLayout();
    private HorizontalLayout rightLayout1Page = new HorizontalLayout();
    private HorizontalLayout selectors = new HorizontalLayout();
    private Select<String> selectStudent = new Select<>();
    private Select<String> selectGroup = new Select<>();
    private Tabs tabs = new Tabs();

    // Buttons
    private Button addCardButton = new Button("Додати картку");
    private Button previousCardButton = new Button("Попередня картка");
    private Button nextCardButton = new Button("Наступна картка");
    private Button sendToArchiveButton = new Button("Відправити в архів");
    private Button callStudentButton = new Button("Виклик студента");
    private Button notificationButton = new Button("Повідомлення");
    private Button submitDataButton = new Button("внесення відомості");  // New button

    // Additional Selects and Inputs
    private Select<String> typeOfInformationSelect = new Select<>();
    private DatePicker datePicker = new DatePicker("Дата");
    private TextField numberField = new TextField("Номер");
    private Select<String> studentOrGroupSelect = new Select<>();

    public ReviewingCardsView() {
        // Setup selectors
        selectStudent.setLabel("Студент");
        selectStudent.setItems("Олексій Абраменко", "Максим Костюк", "Віталій Крисько"); // Example items
        selectStudent.setWidth("100%");
        selectStudent.getStyle().set("padding", "0");

        selectGroup.setLabel("Група");
        selectGroup.setItems("КН-4-1", "ІБК-4-1");
        selectGroup.setWidth("100%");
        selectGroup.getStyle().set("padding", "0");

        selectors.add(selectGroup, selectStudent);
        selectors.setWidth("100%");

        // Buttons Layout
        VerticalLayout buttonLayout = new VerticalLayout();

        // Additional Controls Layout
        VerticalLayout additionalControlsLayout = new VerticalLayout();
        typeOfInformationSelect.setLabel("Тип відомості");
        typeOfInformationSelect.setItems("Зарахований"); // Example items
        typeOfInformationSelect.getStyle().set("padding", "0");
        typeOfInformationSelect.setWidth("45%");
        datePicker.getStyle().set("padding", "0");
        datePicker.setWidth("45%");

        studentOrGroupSelect.setLabel("Тип");
        studentOrGroupSelect.setItems("Один студент", "Вся група");
        studentOrGroupSelect.setWidth("25%");
        studentOrGroupSelect.getStyle().set("padding", "0");
        numberField.getStyle().set("padding", "0");
        numberField.setWidth("25%");
        submitDataButton.setWidth("35%");

        HorizontalLayout topRow = new HorizontalLayout();
        topRow.add(typeOfInformationSelect, datePicker);

        HorizontalLayout bottomRow = new HorizontalLayout();
        bottomRow.add(numberField, studentOrGroupSelect, submitDataButton);  // Add submitDataButton
        bottomRow.setWidth("530px");

        additionalControlsLayout.add(topRow, bottomRow);
        additionalControlsLayout.setWidth("100%");
        additionalControlsLayout.getStyle().set("padding", "0");
        additionalControlsLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        // Combined Layout for Buttons and Controls
        HorizontalLayout buttonsAndControlsLayout = new HorizontalLayout();
        buttonsAndControlsLayout.add(buttonLayout, additionalControlsLayout);
        buttonsAndControlsLayout.setWidth("100%");
        buttonsAndControlsLayout.setSpacing(true);
        buttonsAndControlsLayout.getStyle().set("padding", "0");
        buttonsAndControlsLayout.getStyle().set("align-items", "baseline");

        // Arrange Buttons and Additional Controls in Two Rows
        VerticalLayout twoRowLayout = new VerticalLayout();
        HorizontalLayout buttonRow1 = new HorizontalLayout();
        buttonRow1.add(addCardButton, previousCardButton, nextCardButton);

        HorizontalLayout buttonRow2 = new HorizontalLayout();
        buttonRow2.add(sendToArchiveButton, callStudentButton, notificationButton);

        VerticalLayout buttonRowsLayout = new VerticalLayout();
        buttonRowsLayout.add(buttonRow1, buttonRow2);
        buttonRowsLayout.setWidth("100%");
        buttonRowsLayout.setSpacing(true);
        buttonRowsLayout.getStyle().set("padding", "0");
        buttonLayout.add(buttonRowsLayout);
        buttonLayout.setWidth("100%");
        buttonLayout.setSpacing(true);
        buttonLayout.getStyle().set("padding", "0");
        buttonLayout.setAlignItems(FlexComponent.Alignment.START);

        // Setup tabs
        Tab mainInfoTab = new Tab("Основна Інформація");
        Tab additionalInfoTab = new Tab("Додаткова Інформація");
        tabs.add(mainInfoTab, additionalInfoTab);

        // Main info text fields
        TextField lastNameUkrField = new TextField("Прізвище");
        lastNameUkrField.setWidth("24%");

        TextField firstNameUkrField = new TextField("Ім'я");
        firstNameUkrField.setWidth("24%");

        TextField middleNameUkrField = new TextField("По батькові");
        middleNameUkrField.setWidth("24%");

        TextField lastNameEngField = new TextField("Прізвище (англ)");
        lastNameEngField.setWidth("24%");

        TextField firstNameEngField = new TextField("Ім'я (англ)");
        firstNameEngField.setWidth("24%");

        TextField groupField = new TextField("Група");
        groupField.setWidth("24%");

        TextField courseField = new TextField("Курс");
        courseField.setWidth("24%");

        TextField groupNumberField = new TextField("Номер групи");
        groupNumberField.setWidth("24%");

        TextField admissionYearField = new TextField("Рік вступу");
        admissionYearField.setWidth("24%");

        TextField recordBookNumberField = new TextField("Номер заліковки");
        recordBookNumberField.setWidth("24%");

        // Add border and title to leftLayout1Page
        Div leftLayoutWrapper = new Div();
        leftLayoutWrapper.getStyle().set("border", "1px solid #ddd");
        leftLayoutWrapper.getStyle().set("border-radius", "8px");
        leftLayoutWrapper.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        leftLayoutWrapper.getStyle().set("padding", "20px");
        leftLayoutWrapper.getStyle().set("position", "relative");
        leftLayoutWrapper.getStyle().set("background", "white");

        Span leftLayoutTitle = new Span("Персональні дані");
        leftLayoutTitle.getStyle().set("position", "absolute");
        leftLayoutTitle.getStyle().set("top", "-10px");
        leftLayoutTitle.getStyle().set("left", "20px");
        leftLayoutTitle.getStyle().set("background", "white");
        leftLayoutTitle.getStyle().set("padding", "0 10px");
        leftLayoutTitle.getStyle().set("font-weight", "bold");

        leftLayoutWrapper.add(leftLayoutTitle, leftLayout1Page);

        // Add border and title to rightLayout1Page
        Div rightLayoutWrapper = new Div();
        rightLayoutWrapper.getStyle().set("border", "1px solid #ddd");
        rightLayoutWrapper.getStyle().set("border-radius", "8px");
        rightLayoutWrapper.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        rightLayoutWrapper.getStyle().set("padding", "20px");
        rightLayoutWrapper.getStyle().set("position", "relative");
        rightLayoutWrapper.getStyle().set("background", "white");

        Span rightLayoutTitle = new Span("Академічні дані");
        rightLayoutTitle.getStyle().set("position", "absolute");
        rightLayoutTitle.getStyle().set("top", "-10px");
        rightLayoutTitle.getStyle().set("left", "20px");
        rightLayoutTitle.getStyle().set("background", "white");
        rightLayoutTitle.getStyle().set("padding", "0 10px");
        rightLayoutTitle.getStyle().set("font-weight", "bold");

        rightLayoutWrapper.add(rightLayoutTitle, rightLayout1Page);

        leftLayout1Page.add(lastNameUkrField, firstNameUkrField, middleNameUkrField, lastNameEngField, firstNameEngField);
        rightLayout1Page.add(groupField, courseField, groupNumberField, admissionYearField, recordBookNumberField);

        // Layout for main info text fields
        VerticalLayout mainInfoLayout = new VerticalLayout();
        mainInfoLayout.setWidth("100%");
        mainInfoLayout.add(leftLayoutWrapper, rightLayoutWrapper);
        mainInfoLayout.getStyle().set("padding", "0px");
        leftLayoutWrapper.getStyle().set("width", "97%");
        rightLayoutWrapper.getStyle().set("width", "97%");

// Additional info text fields
        TextField caseNumberField = new TextField("Номер справи");
        TextField idCodeField = new TextField("Ідентифікаційний код");
        TextField birthDateField = new TextField("Дата народження");
        TextField nationalityField = new TextField("Національність");
        TextField regionField = new TextField("Область");
        TextField indexField = new TextField("Індекс");
        TextField fullAddressField = new TextField("Повна адреса");
        Select<String> benefitsSelect = new Select<>();
        benefitsSelect.setLabel("Пільги");
        benefitsSelect.setItems("Пільга 1", "Пільга 2"); // Example items
        Select<String> genderSelect = new Select<>();
        genderSelect.setLabel("Стать");
        genderSelect.setItems("Чоловіча", "Жіноча");

        TextField passportSeriesField = new TextField("Серія паспорту");
        TextField passportNumberField = new TextField("№ паспорту");
        TextField passportIssueDateField = new TextField("Коли виданий");
        TextField passportIssuedByField = new TextField("Ким виданий");
        TextField passportExpiryDateField = new TextField("Коли закінчиться дія паспорту");
        Select<String> educationFormSelect = new Select<>();
        educationFormSelect.setLabel("Форма навчання");
        educationFormSelect.setItems("Денна", "Заочна");

        TextField admissionToUniField = new TextField("Зарахованно до ВУЗу для навчання");
        TextField degreeField = new TextField("Здобуття звання");
        Select<String> admissionConditionSelect = new Select<>();
        admissionConditionSelect.setLabel("Умови вступу");
        admissionConditionSelect.setItems("Умова 1", "Умова 2"); // Example items
        TextField paymentSourceField = new TextField("За кошти (фізичних, юридичних осіб)");
        TextField contractNumberField = new TextField("Договір за номером");
        TextField amountField = new TextField("Сума");

// Group 1: Personal Details
        Div personalDetailsWrapper = new Div();
        personalDetailsWrapper.getStyle().set("border", "1px solid #ddd");
        personalDetailsWrapper.getStyle().set("border-radius", "8px");
        personalDetailsWrapper.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        personalDetailsWrapper.getStyle().set("padding", "20px");
        personalDetailsWrapper.getStyle().set("position", "relative");
        personalDetailsWrapper.getStyle().set("background", "white");

        Span personalDetailsTitle = new Span("Особисті дані");
        personalDetailsTitle.getStyle().set("position", "absolute");
        personalDetailsTitle.getStyle().set("top", "-10px");
        personalDetailsTitle.getStyle().set("left", "20px");
        personalDetailsTitle.getStyle().set("background", "white");
        personalDetailsTitle.getStyle().set("padding", "0 10px");
        personalDetailsTitle.getStyle().set("font-weight", "bold");

        FormLayout personalDetailsLayout = new FormLayout();
        personalDetailsLayout.add(caseNumberField, idCodeField, birthDateField, nationalityField, genderSelect);

        personalDetailsWrapper.add(personalDetailsTitle, personalDetailsLayout);

// Group 2: Address Details
        Div addressDetailsWrapper = new Div();
        addressDetailsWrapper.getStyle().set("border", "1px solid #ddd");
        addressDetailsWrapper.getStyle().set("border-radius", "8px");
        addressDetailsWrapper.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        addressDetailsWrapper.getStyle().set("padding", "20px");
        addressDetailsWrapper.getStyle().set("position", "relative");
        addressDetailsWrapper.getStyle().set("background", "white");

        Span addressDetailsTitle = new Span("Адреса");
        addressDetailsTitle.getStyle().set("position", "absolute");
        addressDetailsTitle.getStyle().set("top", "-10px");
        addressDetailsTitle.getStyle().set("left", "20px");
        addressDetailsTitle.getStyle().set("background", "white");
        addressDetailsTitle.getStyle().set("padding", "0 10px");
        addressDetailsTitle.getStyle().set("font-weight", "bold");

        FormLayout addressDetailsLayout = new FormLayout();
        addressDetailsLayout.add(regionField, indexField, fullAddressField, benefitsSelect);

        addressDetailsWrapper.add(addressDetailsTitle, addressDetailsLayout);

// Group 3: Passport Details
        Div passportDetailsWrapper = new Div();
        passportDetailsWrapper.getStyle().set("border", "1px solid #ddd");
        passportDetailsWrapper.getStyle().set("border-radius", "8px");
        passportDetailsWrapper.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        passportDetailsWrapper.getStyle().set("padding", "20px");
        passportDetailsWrapper.getStyle().set("position", "relative");
        passportDetailsWrapper.getStyle().set("background", "white");

        Span passportDetailsTitle = new Span("Паспортні дані");
        passportDetailsTitle.getStyle().set("position", "absolute");
        passportDetailsTitle.getStyle().set("top", "-10px");
        passportDetailsTitle.getStyle().set("left", "20px");
        passportDetailsTitle.getStyle().set("background", "white");
        passportDetailsTitle.getStyle().set("padding", "0 10px");
        passportDetailsTitle.getStyle().set("font-weight", "bold");

        FormLayout passportDetailsLayout = new FormLayout();
        passportDetailsLayout.add(passportSeriesField, passportNumberField, passportIssueDateField, passportIssuedByField, passportExpiryDateField);

        passportDetailsWrapper.add(passportDetailsTitle, passportDetailsLayout);

// Group 4: Education Details
        Div educationDetailsWrapper = new Div();
        educationDetailsWrapper.getStyle().set("border", "1px solid #ddd");
        educationDetailsWrapper.getStyle().set("border-radius", "8px");
        educationDetailsWrapper.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        educationDetailsWrapper.getStyle().set("padding", "20px");
        educationDetailsWrapper.getStyle().set("position", "relative");
        educationDetailsWrapper.getStyle().set("background", "white");

        Span educationDetailsTitle = new Span("Дані про навчання");
        educationDetailsTitle.getStyle().set("position", "absolute");
        educationDetailsTitle.getStyle().set("top", "-10px");
        educationDetailsTitle.getStyle().set("left", "20px");
        educationDetailsTitle.getStyle().set("background", "white");
        educationDetailsTitle.getStyle().set("padding", "0 10px");
        educationDetailsTitle.getStyle().set("font-weight", "bold");

        FormLayout educationDetailsLayout = new FormLayout();
        educationDetailsLayout.add(educationFormSelect, admissionToUniField, degreeField, admissionConditionSelect, paymentSourceField, contractNumberField, amountField);

        educationDetailsWrapper.add(educationDetailsTitle, educationDetailsLayout);

// Layout for additional info text fields
        VerticalLayout additionalInfoLayout = new VerticalLayout();
        additionalInfoLayout.setWidth("100%");
        additionalInfoLayout.add(personalDetailsWrapper, addressDetailsWrapper, passportDetailsWrapper, educationDetailsWrapper);
        additionalInfoLayout.getStyle().set("padding", "0px");

// Display content for selected tab
        tabs.addSelectedChangeListener(event -> {
            mainLayout.removeAll();
            if (tabs.getSelectedTab().equals(mainInfoTab)) {
                mainLayout.add(selectors, tabs, mainInfoLayout, buttonsAndControlsLayout);
            } else if (tabs.getSelectedTab().equals(additionalInfoTab)) {
                mainLayout.add(selectors, tabs, additionalInfoLayout);
            }
        });

        mainLayout.add(selectors, tabs, mainInfoLayout, buttonsAndControlsLayout);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("100%");

        add(mainLayout);
        setHeight("100%");
    }
}