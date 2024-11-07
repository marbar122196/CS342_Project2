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
        winningHandsTitle.setFont(Font.font(customFont.getFamily(), titleSize));
        winningHandsTitle.setStyle("-fx-text-fill: #A30262; -fx-text-alignment: center;");

        // Winning hands text label setup
        Label winningHandsText = new Label(
                "Straight Flush: 40 to 1\n\n" +
                        "Three of a Kind: 30 to 1\n\n" +
                        "Straight: 6 to 1\n\n" +
                        "Flush: 3 to 1\n\n" +
                        "Pair: 1 to 1"
        );
        winningHandsText.setFont(Font.font(customFont.getFamily(), bodySize));
        winningHandsText.setStyle("-fx-text-fill: #A30262; -fx-text-alignment: center;");
        winningHandsText.setWrapText(true);

        // Escape button setup
        Button escapeButton = new Button("escape");
        escapeButton.setFont(customFont);
        escapeButton.setStyle("-fx-background-color: #A30262; -fx-text-fill: white;");
        escapeButton.setOnAction(e -> winningHandsStage.close());

        // VBox layout setup
        VBox winningHandsBox = new VBox(10, winningHandsTitle, winningHandsText, escapeButton);
        winningHandsBox.setAlignment(Pos.CENTER);
        winningHandsBox.setPadding(new Insets(30));
        winningHandsBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #A30262; -fx-border-width: 15px;");

        // Scene setup and show
        Scene winningHandsScene = new Scene(winningHandsBox, 500, 500);
        winningHandsStage.setScene(winningHandsScene);
        winningHandsStage.showAndWait();
    }
}
