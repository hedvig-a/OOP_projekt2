package com.example.oopprojekt.src.ui.dialogs;

import com.example.oopprojekt.src.model.Idea;
import com.example.oopprojekt.src.model.IdeaGenerator;
import com.example.oopprojekt.src.ui.components.Buttons;
import com.example.oopprojekt.src.ui.utils.DialogUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class DropdownDialogBox {

    public static void show(Stage owner, String actionType, IdeaGenerator generator) {
        String title, header;
        List<Idea> ideaList;

        switch (actionType) {
            case "favorite" -> {
                title = "Mark Idea as Favorite";
                header = "Select an idea to mark as favorite";
                ideaList = generator.getFilteredIdeas("saved");
            }
            case "remove" -> {
                title = "Remove Idea";
                header = "Select an idea to remove";
                ideaList = generator.getAllIdeas();
            }
            default -> throw new IllegalArgumentException("Unsupported action type: " + actionType);
        }

        if (ideaList.isEmpty()) {
            AlertBox.show(owner, "No Ideas", "No ideas available for this action.");
            return;
        }

        Stage dialog = DialogUtils.createDialogStage(owner, title);
        VBox vbox = DialogUtils.createDialogVBox();

        Label label = DialogUtils.createLabel(header);

        ComboBox<String> comboBox = new ComboBox<>();
        List<String> ideaNames = ideaList.stream().map(Idea::getActivity).toList();
        comboBox.getItems().addAll(ideaNames);
        comboBox.setPrefWidth(450);

        Button confirmBtn = Buttons.create("Confirm", e -> {
            String selected = comboBox.getValue();
            if (selected == null || selected.trim().isEmpty()) {
                AlertBox.show(owner, "Error", "You must select an idea.");
                return;
            }

            boolean success = switch (actionType) {
                case "favorite" -> generator.markAsFavorite(selected);
                case "remove" -> generator.removeIdea(selected);
                default -> false;
            };

            String resultMsg = success ? "Success" : "Error";
            String actionMsg = switch (actionType) {
                case "favorite" -> success ? "Marked as favorite!" : "Idea not found.";
                case "remove" -> success ? "Idea removed." : "Idea not found.";
                default -> "";
            };

            AlertBox.show(owner, resultMsg, actionMsg);
            if (success) dialog.close();
        });

        Button cancelBtn = Buttons.create("Cancel", e -> dialog.close());

        HBox buttons = new HBox(15, confirmBtn, cancelBtn);
        buttons.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(label, comboBox, buttons);

        Scene scene = new Scene(vbox, 400, 200);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        scene.getStylesheets().add(Objects.requireNonNull(DropdownDialogBox.class.getResource("/com/example/oopprojekt/src/ui/style.css")).toExternalForm());

        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
