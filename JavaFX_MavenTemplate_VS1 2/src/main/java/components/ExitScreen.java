package components;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
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
        exitScreenTitle.setFont(Font.font(customFont.getFamily(), titleSize));
        exitScreenTitle.setStyle("-fx-text-fill: #A30262; -fx-text-alignment: center;");

        // Yes button setup
        Button yesButton = new Button("yes<3");
        yesButton.setFont(customFont);
        yesButton.setStyle("-fx-text-fill: #A30262;");
        yesButton.setOnAction(e -> optionsStage.close());

        // No button setup
        Button noButton = new Button("no</3");
        noButton.setFont(customFont);
        noButton.setStyle("-fx-text-fill: #A30262;");
        noButton.setOnAction(e -> Platform.exit());

        // Button layout setup
        HBox buttonBox = new HBox(50, yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Main layout setup
        VBox exitScreenBox = new VBox(20, exitScreenTitle, buttonBox);
        exitScreenBox.setAlignment(Pos.CENTER);
        exitScreenBox.setPadding(new Insets(30));
        exitScreenBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #A30262; -fx-border-width: 15px;");

        // Scene setup and show
        Scene exitScene = new Scene(exitScreenBox, 500, 250);
        exitStage.setScene(exitScene);
        exitStage.showAndWait();
    }
}
