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

import java.util.Objects;

public class MainFX extends Application {
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        VBox root = createMainRoot();

        mainScene = new Scene(root, 1000, 800);
        mainScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/oopprojekt/src/ui/style.css")).toExternalForm());

        // Now that mainScene exists, set up the responsive bindings here:
        setupBindings(root, primaryStage);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Relationship Diary");
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void setupBindings(VBox root, Stage primaryStage) {
        // Extract nodes you need to bind, you might want to keep references when creating UI
        Label welcomeLabel = (Label) ((VBox)root.getChildren().getFirst()).getChildren().getFirst();
        VBox contentBox = (VBox) root.getChildren().getFirst();
        VBox centerBox = (VBox) contentBox.getChildren().get(1);
        HBox buttonRow = (HBox) centerBox.getChildren().get(1);

        Button manageEntriesButton = (Button) buttonRow.getChildren().get(0);
        Button manageIdeasButton = (Button) buttonRow.getChildren().get(1);
        Button quitButton = (Button) buttonRow.getChildren().get(2);

        Label questionLabel = (Label) centerBox.getChildren().getFirst();

        // Bindings
        welcomeLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", mainScene.heightProperty().divide(20).asString(), "px;")
        );
        questionLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", mainScene.heightProperty().divide(30).asString(), "px;")
        );

        contentBox.spacingProperty().bind(mainScene.heightProperty().multiply(0.1));
        centerBox.spacingProperty().bind(mainScene.heightProperty().multiply(0.06));
        buttonRow.spacingProperty().bind(mainScene.widthProperty().multiply(0.02));

        centerBox.paddingProperty().bind(Bindings.createObjectBinding(() ->
                new Insets(mainScene.heightProperty().get() * 0.05, 0, 0, 0), mainScene.heightProperty()));

        root.paddingProperty().bind(Bindings.createObjectBinding(() ->
                new Insets(mainScene.heightProperty().get() * 0.15, 0, 0, 0), mainScene.heightProperty()));

        for (Button btn : new Button[]{manageEntriesButton, manageIdeasButton, quitButton}) {
            btn.prefWidthProperty().bind(mainScene.widthProperty().multiply(0.12));
            btn.prefHeightProperty().bind(mainScene.heightProperty().multiply(0.045));
        }

        // Button actions
        manageEntriesButton.setOnAction(e -> AlertBox.show(primaryStage, "Manage Entries", "Opening Manage Entries GUI... (implement this)"));
        manageIdeasButton.setOnAction(e -> {
            ManageIdeasUI ideasUI = new ManageIdeasUI();
            VBox ideasRoot = ideasUI.createManageIdeasPane(primaryStage, this);
            setRoot(ideasRoot);
        });

        quitButton.setOnAction(e -> primaryStage.close());
    }

    public void setRoot(VBox newRoot) {
        mainScene.setRoot(newRoot);
    }

    public void resetToMainView() {
        VBox newMainRoot = createMainRoot();
        setRoot(newMainRoot);
        setupBindings(newMainRoot, (Stage) mainScene.getWindow()); // re-apply bindings when resetting
    }

    public VBox createMainRoot() {
        Label welcomeLabel = new Label("Hi! Welcome to your relationship diary!");
        welcomeLabel.getStyleClass().add("title-label");

        Label questionLabel = new Label("What would you like to do?");
        questionLabel.getStyleClass().add("question-label");

        Button manageEntriesButton = new Button("Manage Entries");
        Button manageIdeasButton = new Button("Manage Ideas");
        Button quitButton = new Button("Quit");

        Button[] buttons = {manageEntriesButton, manageIdeasButton, quitButton};
        for (Button btn : buttons) {
            btn.getStyleClass().add("menu-button");
        }

        HBox buttonRow = new HBox(10, manageEntriesButton, manageIdeasButton, quitButton);
        buttonRow.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(20, questionLabel, buttonRow);
        centerBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(30, welcomeLabel, centerBox);
        contentBox.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(contentBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root-background");

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
