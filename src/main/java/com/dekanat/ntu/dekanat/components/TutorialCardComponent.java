package com.dekanat.ntu.dekanat.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class TutorialCardComponent extends VerticalLayout {

    public TutorialCardComponent(String imageUrl, String title, String info) {
        Image image = new Image(imageUrl, title);
        image.setWidth("160px");
        image.setHeight("100px");
        H4 titleLabel = new H4(title);
        Button infoButton = new Button("Дізнатися більше", event -> openInfoDialog(info));

        getElement().getStyle().set("border", "1px solid #e0e0e0");
        getElement().getStyle().set("background-color", "#f5f5f5");

        add(image, titleLabel, infoButton);
        setAlignItems(Alignment.CENTER);
    }

    private void openInfoDialog(String info) {
        Dialog dialog = new Dialog();
        dialog.add(new H4(info));
        dialog.setWidth("400px");
        dialog.setHeight("300px");
        dialog.open();
    }

}
