package com.example.oopprojekt.src.ui.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Buttons {

    public static Button create(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        button.getStyleClass().add("menu-button");
        button.setPrefWidth(240);
        button.setPrefHeight(45);
        button.setOnAction(action);
        return button;
    }
}