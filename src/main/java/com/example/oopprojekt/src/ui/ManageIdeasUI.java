package com.example.oopprojekt.src.ui;

import com.example.oopprojekt.src.model.IdeaGenerator;
import com.example.oopprojekt.src.ui.components.Buttons;
import com.example.oopprojekt.src.ui.dialogs.*;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.List;

public class ManageIdeasUI {
    private final IdeaGenerator ideaGenerator = new IdeaGenerator();

    public VBox createManageIdeasPane(Stage primaryStage, MainFX mainFX) {
        // Pealkiri
        Label title = new Label("Manage Ideas");
        title.getStyleClass().add("title-label");

        // Buttons
        List<Button> buttons = List.of(
                Buttons.create("Add New Idea", e -> AddIdeaDialog.show(primaryStage, ideaGenerator)),
                Buttons.create("Get Random Idea", e -> RandomIdeaDialog.show(primaryStage, ideaGenerator)),
                Buttons.create("View Saved Ideas", e -> ListDialog.show(primaryStage, "Saved Ideas", ideaGenerator.getFilteredIdeas("saved"))),
                Buttons.create("View Favorite Ideas", e -> ListDialog.show(primaryStage, "Favorite Ideas", ideaGenerator.getFavorites())),
                Buttons.create("Mark Idea as Favorite", e -> DropdownDialogBox.show(primaryStage, "favorite", ideaGenerator)),
                Buttons.create("Remove Idea", e -> DropdownDialogBox.show(primaryStage, "remove", ideaGenerator)),
                Buttons.create("Back to Main Menu", e -> mainFX.resetToMainView())
        );

        // Apply common style class
        for (Button button : buttons) {
            button.getStyleClass().add("menu-button");
        }
        VBox buttonsBox = new VBox();
        buttonsBox.getChildren().addAll(buttons);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(title, buttonsBox);
        contentBox.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(contentBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root-background");

        root.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {

                buttonsBox.spacingProperty().bind(newScene.heightProperty().multiply(0.035));
                contentBox.spacingProperty().bind(newScene.heightProperty().multiply(0.08));

                root.paddingProperty().bind(Bindings.createObjectBinding(() ->
                    new javafx.geometry.Insets(newScene.heightProperty().get() * 0.15, 0, 0, 0),
                    newScene.heightProperty()));

                title.styleProperty().bind(Bindings.concat(
            "-fx-font-size: ", newScene.heightProperty().divide(20).asString(), "px;"
                ));

                for (Button btn : buttons) {
                    btn.prefWidthProperty().bind(newScene.widthProperty().multiply(0.20));
                    btn.prefHeightProperty().bind(newScene.heightProperty().multiply(0.045));
                }
            }
        });

        return root;
    }
}
