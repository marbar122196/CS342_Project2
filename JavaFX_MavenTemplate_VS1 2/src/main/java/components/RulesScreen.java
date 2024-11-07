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

public class RulesScreen {

    private Font customFont;
    private int titleSize;
    private int bodySize;

    public RulesScreen(Font customFont, int titleSize, int bodySize) {
        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;
    }

    public void show(Stage optionsStage) {
        // Creating the new modal stage for the rules
        Stage rulesStage = new Stage();
        rulesStage.initModality(Modality.APPLICATION_MODAL);
        rulesStage.initOwner(optionsStage);

        // Title label setup
        Label rulesTitle = new Label("rules");
        rulesTitle.setFont(Font.font(customFont.getFamily(), titleSize));
        rulesTitle.setStyle("-fx-text-fill: #A30262; -fx-text-alignment: center;");

        // Rules text label setup
        Label rulesText = new Label(
                "Ante Wager: Players place an ante wager ($5 - $25).\n\n" +
                        "Optional Pair Plus Bet: Players can also make a Pair Plus wager ($5 - $25), paying\n" +
                        "based solely on their hand if it has at least a pair of 2’s, regardless of the dealer's hand.\n\n" +
                        "Dealing Cards: Each player and the dealer receive three cards; players’ cards are face\n" +
                        "up, the dealer’s face down.\n\n" +
                        "Play or Fold:\n" +
                        "• Fold: Player loses both ante and Pair Plus wagers (if made).\n" +
                        "• Play: Player places a play wager equal to the ante.\n\n" +
                        "Dealer's Hand:\n" +
                        "• Below Queen High: Play wager is returned, and ante is pushed.\n" +
                        "• Queen High or Better: Dealer’s hand is compared to the player’s hand:\n" +
                        "  - Dealer Wins: Player loses both ante and play wagers.\n" +
                        "  - Player Wins: Player receives 1 to 1 payout on both wagers."
        );
        rulesText.setFont(Font.font(customFont.getFamily(), bodySize));
        rulesText.setStyle("-fx-text-fill: #A30262; -fx-text-alignment: center;");
        rulesText.setWrapText(true);
        rulesText.setAlignment(Pos.CENTER);

        // Escape button setup
        Button escapeButton = new Button("escape");
        escapeButton.setFont(customFont);
        escapeButton.setStyle("-fx-background-color: #A30262; -fx-text-fill: white;");
        escapeButton.setOnAction(e -> rulesStage.close());

        // VBox layout setup
        VBox rulesBox = new VBox(10, rulesTitle, rulesText, escapeButton);
        rulesBox.setAlignment(Pos.CENTER);
        rulesBox.setPadding(new Insets(20));
        rulesBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #A30262; -fx-border-width: 15px;");

        // Scene setup and show
        Scene rulesScene = new Scene(rulesBox, 850, 850);
        rulesStage.setScene(rulesScene);
        rulesStage.showAndWait();
    }
}
