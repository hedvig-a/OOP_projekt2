package com.example.oopprojekt.src.ui.dialogs;

import com.example.oopprojekt.src.ui.components.Buttons;
import com.example.oopprojekt.src.ui.utils.DialogUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class TextInputDialogBox {

    public static void show(Stage owner, String title, String header, Consumer<String> onConfirm) {
        Stage dialog = DialogUtils.createDialogStage(owner, title);

        VBox vbox = DialogUtils.createDialogVBox();
        vbox.setSpacing(12);
        vbox.setAlignment(Pos.CENTER_LEFT);

        Label label = DialogUtils.createLabel(header);
        TextField input = new TextField();
        input.getStyleClass().add("dialog-input");

        Button confirmBtn = Buttons.create("Confirm", e -> {
            String text = input.getText().trim();
            if (text.isEmpty()) {
                AlertBox.show(owner, "Error", "Input cannot be empty.");
                return;
            }
            onConfirm.accept(text);
            dialog.close();
        });

        Button cancelBtn = Buttons.create("Cancel", e -> dialog.close());

        HBox buttons = new HBox(15, confirmBtn, cancelBtn);
        buttons.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(label, input, buttons);

        Scene scene = new Scene(vbox, 400, 200);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        scene.getStylesheets().add(TextInputDialogBox.class.getResource("/com/example/oopprojekt/src/ui/style.css").toExternalForm());

        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
