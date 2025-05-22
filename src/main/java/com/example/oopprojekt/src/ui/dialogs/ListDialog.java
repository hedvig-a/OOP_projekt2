package com.example.oopprojekt.src.ui.dialogs;

import com.example.oopprojekt.src.model.Idea;
import com.example.oopprojekt.src.ui.components.Buttons;
import com.example.oopprojekt.src.ui.utils.DialogUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class ListDialog {

    public static void show(Stage owner, String title, List<Idea> ideas) {
        Stage dialog = DialogUtils.createDialogStage(owner, title);

        VBox vbox = DialogUtils.createDialogVBox(); // consistent style & padding
        vbox.setSpacing(16);
        vbox.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = DialogUtils.createLabel(title);

        Label contentLabel = new Label(ideas.isEmpty()
                ? "No ideas found."
                : ideas.stream().map(Object::toString).collect(Collectors.joining("\n\n")));
        contentLabel.setWrapText(true);
        contentLabel.getStyleClass().add("dialog-text");

        VBox textContainer = new VBox(contentLabel);
        textContainer.setSpacing(10);
        textContainer.setAlignment(Pos.TOP_LEFT);
        textContainer.setStyle("-fx-background-color: transparent;");
        textContainer.setPadding(new javafx.geometry.Insets(10));

        ScrollPane scrollPane = new ScrollPane(textContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        HBox buttonBox = new HBox(Buttons.create("Close", e -> dialog.close()));
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(titleLabel, scrollPane, buttonBox);

        Scene scene = new Scene(vbox, 500, 450);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(ListDialog.class.getResource("/com/example/oopprojekt/src/ui/style.css").toExternalForm());

        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
