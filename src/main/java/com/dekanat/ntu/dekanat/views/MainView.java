package com.dekanat.ntu.dekanat.views;

import com.dekanat.ntu.dekanat.components.MenuComponent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

        // Styling
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        // Create a horizontal layout for navbar items
        HorizontalLayout navbarLayout = new HorizontalLayout(toggle, title, pib);
        navbarLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        navbarLayout.setSpacing(true);
        navbarLayout.getStyle().set("flex-grow", "1"); // Allow layout to grow and fill available space

        // Add info button to the right
        HorizontalLayout rightSideLayout = new HorizontalLayout(info);
        rightSideLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        // Create a main layout for the navbar
        HorizontalLayout mainLayout = new HorizontalLayout(navbarLayout, rightSideLayout);
        mainLayout.setWidthFull();
        mainLayout.expand(navbarLayout);
        mainLayout.getStyle().set("padding", "var(--lumo-space-s)");

        // Add the layout to the navbar
        addToNavbar(mainLayout);

        addToDrawer(new MenuComponent());

        setContent(new HomeView());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        String path = afterNavigationEvent.getLocation().getPath();
        if (path.isEmpty()) setContent(new HomeView());
    }
}
