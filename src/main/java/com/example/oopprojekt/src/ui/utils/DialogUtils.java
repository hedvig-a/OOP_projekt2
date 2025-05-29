package com.example.oopprojekt.src.ui.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogUtils {

    public static Stage createDialogStage(Stage owner, String title) {
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.initOwner(owner);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        return stage;
    }

    public static VBox createDialogVBox() {
        VBox vbox = new VBox(12);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getStyleClass().add("dialog-bg");
        vbox.setPadding(new Insets(20));
        return vbox;
    }

    public static Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("dialog-label");
        return label;
    }
}
