package com.dekanat.ntu.dekanat.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;


@Route(value = "performance", layout = MainView.class)
public class PerformanceView extends Div {
    public PerformanceView() {
        setText("Performance Content");
    }
}
