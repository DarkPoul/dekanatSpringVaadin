package com.dekanat.ntu.dekanat.components;

import com.dekanat.ntu.dekanat.views.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MenuComponent extends VerticalLayout {

    public MenuComponent() {
        H1 title = new H1("Меню");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = getTabs();
        add(title, tabs);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(
                createTab(VaadinIcon.MENU, "Головна", MainView.class),
                createTab(VaadinIcon.BOOK, "Навчальні плани", TrainingPlansView.class),
                createTab(VaadinIcon.LINE_BAR_CHART, "Успішність", SuccessView.class),
                createTab(VaadinIcon.ABACUS, "Боржники", DebtorView.class),
                createTab(VaadinIcon.USER_CARD, "Перегляд інформації", StudentCardView.class),
                createTab(VaadinIcon.USER_CARD, "Перегляд карток", ReviewingCardsView.class),
                createTab(VaadinIcon.PENCIL, "Введення оцінок", EnterMarksView.class)
//                createTab(VaadinIcon.BOOK, "Навчальні плани"),
//                createTab(VaadinIcon.LINE_BAR_CHART, "Успішність"),
//                createTab(VaadinIcon.ANGLE_DOUBLE_UP, "Переведення на курс"),
//                createTab(VaadinIcon.PRINT, "Друк інформації"),
//                createTab(VaadinIcon.BAR_CHART_H, "Модульний контроль"),
//                createTab(VaadinIcon.CALC_BOOK, "Довідники"),
//                createTab(VaadinIcon.ARCHIVE, "Архів")
                // Додайте інші вкладки тут
        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> navigationTarget) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(navigationTarget);
        link.setTabIndex(-1);

        return new Tab(link);
    }
}