package components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;

public class WinningHandsScreen {

    private Font customFont;
    private int titleSize;
    private int bodySize;

    public WinningHandsScreen(Font customFont, int titleSize, int bodySize) {
        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;
    }

    public void show(Stage optionsStage) {
        // Create the new modal stage for the winning hands
        Stage winningHandsStage = new Stage();
        winningHandsStage.initModality(Modality.APPLICATION_MODAL);
        winningHandsStage.initOwner(optionsStage);
        winningHandsStage.setTitle("Winning Hands");


        // Title label setup
        Label winningHandsTitle = new Label("pair plus winnings");
        winningHandsTitle.getStyleClass().add("purple-label-title");

        // Winning hands text label setup
        Label winningHandsText = new Label(
                "Straight Flush: 40 to 1\n\n" +
                        "Three of a Kind: 30 to 1\n\n" +
                        "Straight: 6 to 1\n\n" +
                        "Flush: 3 to 1\n\n" +
                        "Pair: 1 to 1"
        );

        winningHandsText.getStyleClass().add("purple-body-text-smaller");
        winningHandsText.setWrapText(true);

        // Escape button setup
        Button escapeButton = new Button("escape");
        escapeButton.getStyleClass().add("purple-button-smaller");
        escapeButton.setOnAction(e -> winningHandsStage.close());

        // VBox layout setup
        VBox winningHandsBox = new VBox(5,winningHandsTitle, winningHandsText, escapeButton);
        winningHandsBox.getStyleClass().add("purple-vbox");

        BorderPane outerPane = new BorderPane();
        outerPane.getStyleClass().add("purple-outer-pane-border");
        outerPane.setCenter(winningHandsBox);

        // Scene setup and show
        Scene winningHandsScene = new Scene(outerPane, 500, 550);
        winningHandsScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        winningHandsStage.setScene(winningHandsScene);
        winningHandsStage.showAndWait();
    }
}
