package com.example.oopprojekt.src.ui.dialogs;

import com.example.oopprojekt.src.model.IdeaGenerator;
import com.example.oopprojekt.src.ui.components.Buttons;
import com.example.oopprojekt.src.ui.utils.DialogUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class AddIdeaDialog {

    public static void show(Stage owner, IdeaGenerator generator) {
        Stage dialog = DialogUtils.createDialogStage(owner, "Add New Idea");

        VBox vbox = DialogUtils.createDialogVBox();

        Label activityLabel = DialogUtils.createLabel("Activity:");
        TextField activityField = new TextField();
        activityField.setPromptText("Activity name");

        Label categoryLabel = DialogUtils.createLabel("Category:");
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("date", "hangout");

        Label descLabel = DialogUtils.createLabel("Description:");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Description");
        descriptionArea.setPrefRowCount(3);

        Button saveBtn = Buttons.create("Save", null);
        Button cancelBtn = Buttons.create("Cancel", e -> dialog.close());

        saveBtn.setOnAction(e -> {
            String activity = activityField.getText().trim();
            String category = categoryBox.getValue();
            String description = descriptionArea.getText().trim();

            if (activity.isEmpty() || category == null || description.isEmpty()) {
                AlertBox.show(owner, "Error", "Please fill all fields.");
                return;
            }

            String error = generator.addIdea(activity, category, description);

            if (error == null) {
                AlertBox.show(owner, "Success", "New idea added!");
                dialog.close();
            } else {
                AlertBox.show(owner, "Error", error);
            }
        });

        HBox buttons = new HBox(15, saveBtn, cancelBtn);
        buttons.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(activityLabel, activityField, categoryLabel, categoryBox, descLabel, descriptionArea, buttons);

        Scene scene = new Scene(vbox, 400, 400);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Objects.requireNonNull(RandomIdeaDialog.class.getResource("/com/example/oopprojekt/src/ui/style.css")).toExternalForm());
        dialog.setScene(scene);
        dialog.showAndWait();

    }
}
