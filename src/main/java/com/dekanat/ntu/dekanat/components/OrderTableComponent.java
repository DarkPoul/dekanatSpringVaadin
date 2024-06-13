package com.dekanat.ntu.dekanat.components;

import com.dekanat.ntu.dekanat.entity.OrderEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDate;

public class OrderTableComponent extends VerticalLayout {

    Grid<OrderEntity> orderTable = new Grid<>(OrderEntity.class, false);
    Button addStudentInfo = new Button();

//    Button saveStudentInfo = new Button();
//    Button cancelStudentInfo = new Button();


    public OrderTableComponent(){

        orderTable.addColumn(OrderEntity::getNumber).setHeader("Номер").setWidth("18%");

        orderTable.addColumn(OrderEntity::getDate).setHeader("Дата").setWidth("30%");
        orderTable.addColumn(OrderEntity::getType).setHeader("Тип").setWidth("52%");


        OrderEntity test = new OrderEntity("123", LocalDate.of(2024, 6, 13), "Зарахований");
        OrderEntity test2 = new OrderEntity("124", LocalDate.of(2024, 6, 14), "Відрахований");
        OrderEntity test3 = new OrderEntity("125", LocalDate.of(2024, 6, 15), "Поновлений");
        OrderEntity test4 = new OrderEntity("126", LocalDate.of(2024, 6, 16), "Академ відпустка");
        OrderEntity test5 = new OrderEntity("127", LocalDate.of(2024, 6, 17), "Переведений на наступний навчальний рік");
        OrderEntity test6 = new OrderEntity("128", LocalDate.of(2024, 6, 18), "Такий що закінчив навчання");

        orderTable.setItems(test, test2, test3, test4, test5, test6);

        H3 info = new H3("Накази");

        getStyle().set("border", "1px solid #e0e0e0");
        setWidth("436px");
        setHorizontalComponentAlignment(FlexComponent.Alignment.END, addStudentInfo);
        addStudentInfo.setText("Додати");

        add(info, orderTable, addStudentInfo);


    }

}
