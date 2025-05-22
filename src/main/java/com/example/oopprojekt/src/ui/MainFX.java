package com.example.oopprojekt.src.ui;

import com.example.oopprojekt.src.ui.dialogs.AlertBox;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene mainScene = createMainScene(primaryStage);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Relationship Diary");
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public Scene createMainScene(Stage primaryStage) {
        // Labels
        Label welcomeLabel = new Label("Hi! Welcome to your relationship diary!");
        welcomeLabel.getStyleClass().add("title-label");

        Label questionLabel = new Label("What would you like to do?");
        questionLabel.getStyleClass().add("question-label");

        // Buttons
        Button manageEntriesButton = new Button("Manage Entries");
        Button manageIdeasButton = new Button("Manage Ideas");
        Button quitButton = new Button("Quit");

        Button[] buttons = {manageEntriesButton, manageIdeasButton, quitButton};
        for (Button btn : buttons) {
            btn.getStyleClass().add("menu-button");
        }

        // Layouts
        HBox buttonRow = new HBox(10, manageEntriesButton, manageIdeasButton, quitButton);
        buttonRow.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(20, questionLabel, buttonRow);
        centerBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(30, welcomeLabel, centerBox);
        contentBox.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(contentBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root-background");

        // Scene
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("/com/example/oopprojekt/src/ui/style.css").toExternalForm());

        // Responsive font sizes
        welcomeLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", scene.heightProperty().divide(20).asString(), "px;")
        );
        questionLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", scene.heightProperty().divide(30).asString(), "px;")
        );

        // Responsive spacing/padding
        contentBox.spacingProperty().bind(scene.heightProperty().multiply(0.1));
        centerBox.spacingProperty().bind(scene.heightProperty().multiply(0.06));
        buttonRow.spacingProperty().bind(scene.widthProperty().multiply(0.02));

        centerBox.paddingProperty().bind(Bindings.createObjectBinding(() ->
                new Insets(scene.heightProperty().get() * 0.05, 0, 0, 0), scene.heightProperty()));
        root.paddingProperty().bind(Bindings.createObjectBinding(() ->
                new Insets(scene.heightProperty().get() * 0.15, 0, 0, 0), scene.heightProperty()));

        for (Button btn : buttons) {
            btn.prefWidthProperty().bind(scene.widthProperty().multiply(0.12));
            btn.prefHeightProperty().bind(scene.heightProperty().multiply(0.045));
        }

        // Button actions
        manageEntriesButton.setOnAction(e -> AlertBox.show(primaryStage, "Manage Entries", "Opening Manage Entries GUI... (implement this)"));
        manageIdeasButton.setOnAction(e -> {
            ManageIdeasUI ideasUI = new ManageIdeasUI();
            VBox ideasRoot = ideasUI.createManageIdeasPane(primaryStage);

            Scene ideaScene = new Scene(ideasRoot, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
            ideaScene.getStylesheets().add(getClass().getResource("/com/example/oopprojekt/src/ui/style.css").toExternalForm());
            ideasRoot.paddingProperty().bind(Bindings.createObjectBinding(() ->
                    new Insets(ideaScene.heightProperty().get() * 0.15, 0, 0, 0), ideaScene.heightProperty()));

            primaryStage.setScene(ideaScene);
        });
        quitButton.setOnAction(e -> primaryStage.close());

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
