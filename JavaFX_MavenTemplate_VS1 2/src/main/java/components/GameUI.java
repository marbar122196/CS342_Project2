package components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.image.Image;

public class GameUI {
    private Button dealGameButton;
    private TextArea gameCommentary;
    private ImageView dc1Image1;
    private ImageView dc2Image2;
    private ImageView dc3Image3;
    private Scene scene;
    private Font customFont; // Declare customFont as an instance variable
    private int titleSize;
    private int bodySize;

    public GameUI(Font customFont, int titleSize, int bodySize) {
        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;

        gameCommentary = new TextArea();
        gameCommentary.setPrefWidth(350);
        gameCommentary.setPrefHeight(150);
        gameCommentary.setEditable(false);
        gameCommentary.setWrapText(true);
        gameCommentary.setText("Hello! Enter a name and wager to start.");

        dealGameButton = new Button("Deal");
        dealGameButton.setFont(customFont);
        dealGameButton.setMinWidth(240);
        dealGameButton.setMinHeight(50);
        dealGameButton.setDisable(true);

        Image facedown = new Image(getClass().getResourceAsStream("/facedown.png"));

        dc1Image1 = new ImageView(facedown);
        dc1Image1.setFitWidth(75);
        dc1Image1.setFitHeight(75);

        dc2Image2 = new ImageView(facedown);
        dc2Image2.setFitWidth(75);
        dc2Image2.setFitHeight(75);

        dc3Image3 = new ImageView(facedown);
        dc3Image3.setFitWidth(75);
        dc3Image3.setFitHeight(75);

        HBox dealerCardsBox = new HBox(10, dc1Image1, dc2Image2, dc3Image3);
        Pane pane = new Pane(dealerCardsBox, gameCommentary);

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(pane);
        this.scene = new Scene(rootPane, 1500, 800);
    }

    public Button getDealGameButton() {
        return dealGameButton;
    }

    public TextArea getGameCommentary() {
        return gameCommentary;
    }

    public Scene getScene() {
        return scene;
    }

    public Font getFont() {
        return customFont;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public int getBodySize() {
        return bodySize;
    }

}
