package com.example.oopprojekt.src.ui.dialogs;

import com.example.oopprojekt.src.ui.components.Buttons;
import com.example.oopprojekt.src.ui.utils.DialogUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class AlertBox {

    public static void show(Stage owner, String title, String message) {
        Stage dialog = DialogUtils.createDialogStage(owner, title);

        VBox vbox = DialogUtils.createDialogVBox();
        vbox.setSpacing(15);

        Label label = DialogUtils.createLabel(message);
        label.setWrapText(true);

        javafx.scene.control.Button okBtn = Buttons.create("OK", e -> dialog.close());

        HBox buttonBox = new HBox(okBtn);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(label, buttonBox);

        Scene scene = new Scene(vbox);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(AlertBox.class.getResource("/com/example/oopprojekt/src/ui/style.css").toExternalForm()); // Ensure CSS is applied

        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
