package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.components.MenuComponent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;



@Route("")
public class MainView extends AppLayout implements RouterLayout, AfterNavigationObserver {
    public MainView() {

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Деканат");
        H3 pib = new H3("Іванов Іван Івнанович");
        Button info = new Button("Інструкція");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        addToNavbar(toggle, title, pib, info);
        addToDrawer(new MenuComponent());



        setContent(new HomeView());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        String path = afterNavigationEvent.getLocation().getPath();
        if (path.isEmpty()) setContent(new HomeView());
    }
}