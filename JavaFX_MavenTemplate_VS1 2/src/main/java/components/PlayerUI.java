package components;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import gamelogic.Card;

public class PlayerUI {
    private ImageView p1c1Image;
    private ImageView p1c2Image;
    private ImageView p1c3Image;
    private Button playButton;
    private Button foldButton;
    private VBox playerPane;

    public PlayerUI() {
        // Initialize individual card images with face-down default
        Image faceDownImage = new Image(getClass().getResourceAsStream("/facedown.png"));

        p1c1Image = new ImageView(faceDownImage);
        p1c1Image.setFitWidth(75);
        p1c1Image.setFitHeight(75);

        p1c2Image = new ImageView(faceDownImage);
        p1c2Image.setFitWidth(75);
        p1c2Image.setFitHeight(75);

        p1c3Image = new ImageView(faceDownImage);
        p1c3Image.setFitWidth(75);
        p1c3Image.setFitHeight(75);

        // Initialize buttons
        playButton = new Button("play");
        foldButton = new Button("fold");

        // Place cards and buttons in a VBox
        playerPane = new VBox(10, p1c1Image, p1c2Image, p1c3Image, playButton, foldButton);
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getFoldButton() {
        return foldButton;
    }

    public VBox getPlayerPane() {
        return playerPane;
    }

    public void updateCardImages(ArrayList<Card> hand) {
        String card1Path = "/" + hand.get(0).getSuit() + " " + hand.get(0).getValue() + ".png";
        p1c1Image.setImage(new Image(getClass().getResourceAsStream(card1Path)));

        String card2Path = "/" + hand.get(1).getSuit() + " " + hand.get(1).getValue() + ".png";
        p1c2Image.setImage(new Image(getClass().getResourceAsStream(card2Path)));

        String card3Path = "/" + hand.get(2).getSuit() + " " + hand.get(2).getValue() + ".png";
        p1c3Image.setImage(new Image(getClass().getResourceAsStream(card3Path)));
    }

    public void resetCardImages() {
        Image faceDownImage = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image.setImage(faceDownImage);
        p1c2Image.setImage(faceDownImage);
        p1c3Image.setImage(faceDownImage);
    }
}
