package com.example.oopprojekt.src.ui;

import com.example.oopprojekt.src.model.IdeaGenerator;
import com.example.oopprojekt.src.ui.components.Buttons;
import com.example.oopprojekt.src.ui.dialogs.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ManageIdeasUI {
    private final IdeaGenerator ideaGenerator = new IdeaGenerator();

    public VBox createManageIdeasPane(Stage primaryStage) {
        // Title
        Label title = new Label("Manage Ideas");
        title.getStyleClass().add("title-label");

        // Buttons
        List<Button> buttons = List.of(
                Buttons.create("Add New Idea", e -> AddIdeaDialog.show(primaryStage, ideaGenerator)),
                Buttons.create("Get Random Idea", e -> RandomIdeaDialog.show(primaryStage, ideaGenerator)),
                Buttons.create("View Saved Ideas", e -> ListDialog.show(primaryStage, "Saved Ideas", ideaGenerator.getFilteredIdeas("saved"))),
                Buttons.create("View Favorite Ideas", e -> ListDialog.show(primaryStage, "Favorite Ideas", ideaGenerator.getFavorites())),
                Buttons.create("Mark Idea as Favorite", e -> TextInputDialogBox.show(primaryStage, "Mark as Favorite", "Enter the activity name to favorite:", input -> {
                    ideaGenerator.markAsFavorite(input);
                    AlertBox.show(primaryStage, "Done", "Marked as favorite if found.");
                })),
                Buttons.create("Remove Idea", e -> TextInputDialogBox.show(primaryStage, "Remove Idea", "Enter the activity name to remove:", input -> {
                    ideaGenerator.removeIdea(input);
                    AlertBox.show(primaryStage, "Done", "Removed idea if found.");
                })),
                Buttons.create("Back to Main Menu", e -> {
                    MainFX mainFX = new MainFX();
                    primaryStage.setScene(mainFX.createMainScene(primaryStage));
                })
        );

        // Apply common style class
        for (Button button : buttons) {
            button.getStyleClass().add("menu-button");
        }
        VBox buttonsBox = new VBox(20);
        buttonsBox.getChildren().addAll(buttons);
        buttonsBox.setAlignment(Pos.CENTER);
        VBox.setMargin(buttonsBox, new Insets(40, 0, 0, 0));

        // Main layout
        VBox ideasPane = new VBox(20);
        ideasPane.setAlignment(Pos.TOP_CENTER);
        ideasPane.getChildren().addAll(title, buttonsBox);
        ideasPane.setPadding(new Insets(25));

        VBox root = new VBox(ideasPane);
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root-background");
        root.setPadding(new Insets(50));

        return root;
    }
}
