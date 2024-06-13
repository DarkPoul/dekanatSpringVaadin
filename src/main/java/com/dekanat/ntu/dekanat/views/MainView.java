package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.components.MenuComponent;
import com.dekanat.ntu.dekanat.models.ChangeLogEntry;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;

@Route("")
public class MainView extends AppLayout implements RouterLayout, AfterNavigationObserver {
    public MainView() {

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Деканат");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        addToNavbar(toggle, title);
        addToDrawer(new MenuComponent());



        setContent(new HomeView());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        String path = afterNavigationEvent.getLocation().getPath();
        if (path.isEmpty()) setContent(new HomeView());
    }
}
