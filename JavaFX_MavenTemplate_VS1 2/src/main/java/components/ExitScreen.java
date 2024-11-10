package components;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

public class ExitScreen {

    private Font customFont;
    private int titleSize;

    public ExitScreen(Font customFont, int titleSize) {
        this.customFont = customFont;
        this.titleSize = titleSize;
    }

    public void show(Stage optionsStage) {
        // Stage setup
        Stage exitStage = new Stage();
        exitStage.initModality(Modality.APPLICATION_MODAL);
        exitStage.initOwner(optionsStage);

        // Title label setup
        Label exitScreenTitle = new Label("return to game?");
        exitScreenTitle.getStyleClass().add("purple-label-title");

        // Yes button setup
        Button yesButton = new Button("yes<3");
        yesButton.getStyleClass().add("purple-button");
        yesButton.setOnAction(e -> optionsStage.close());

        // No button setup
        Button noButton = new Button("no</3");
        noButton.getStyleClass().add("purple-button");
        noButton.setOnAction(e -> Platform.exit());

        // VBox layout for title and buttons
        VBox exitScreenBox = new VBox(20, exitScreenTitle, yesButton, noButton);
        exitScreenBox.getStyleClass().add("purple-vbox");

        // BorderPane setup with styling
        BorderPane outerPane = new BorderPane();
        outerPane.getStyleClass().add("purple-outer-pane-border");
        outerPane.setCenter(exitScreenBox);

        // Scene setup and show
        Scene exitScene = new Scene(outerPane, 500, 300);
        exitScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());  // Load default CSS
        exitStage.setScene(exitScene);
        exitStage.showAndWait();
    }
}
