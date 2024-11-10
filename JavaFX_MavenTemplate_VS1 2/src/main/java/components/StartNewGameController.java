package components;

import gamelogic.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class StartNewGameController {
    public ArrayList<String> diffHands = new ArrayList<>();
    private boolean isPlayerTwo = false;
    private boolean playerOnePress = false;
    private boolean playerTwoPress = false;
    private boolean playerOnePressPlay = false;
    private boolean playerTwoPressPlay = false;
    private boolean playerOnePressFold = false;
    private boolean playerTwoPressFold = false;

    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerOneHand;
    private ArrayList<Card> playerTwoHand;

    private Player playerOne;
    private Player playerTwo;
    private Dealer dealer;

    @FXML private TextField namePlayerOne, antePlayerOne, pairPlusPlayerOne, amtWinningsPlayerOne;
    @FXML private TextField namePlayerTwo, antePlayerTwo, pairPlusPlayerTwo, amtWinningsPlayerTwo;
    @FXML private Label handNamePlayerOne, handNamePlayerTwo;
    @FXML private Button optionsButton, rulesButton, dealGame;
    @FXML private TextArea gameCommentary;
    @FXML private ImageView p1c1Image1, p1c1Image2, p1c1Image3;
    @FXML private ImageView p2c1Image1, p2c2Image2, p2c3Image3;
    @FXML private ImageView dc1Image1, dc2Image2, dc3Image3;
    @FXML private Pane pane;

    public void initialize() {
        populateDiffHands();
        gameCommentary.setText("Welcome! Enter player names and ante to start. Min bet: $5, Max bet: $25.");
    }

    public void setupGame(Player playerOne, Player playerTwo, Dealer dealer, Stage primaryStage) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.dealer = dealer;

        optionsButton.setOnAction(e -> new OptionsMenu(primaryStage).show());
        rulesButton.setOnAction(e -> new RulesScreen().show());
    }

    private void populateDiffHands() {
        diffHands.add("High-Card");
        diffHands.add("Straight Flush");
        diffHands.add("Three of a Kind");
        diffHands.add("Straight");
        diffHands.add("Flush");
        diffHands.add("Pair");
    }

    @FXML private void onDealGame() {
        namePlayerOne.setDisable(true);
        antePlayerOne.setDisable(true);
        if (!isPlayerTwo) {
            namePlayerTwo.setDisable(true);
            antePlayerTwo.setDisable(true);
            playerTwoPlay.setDisable(true);
            playerTwoFold.setDisable(true);
        }

        playerOnePress = false;
        playerOnePressPlay = false;
        playerOnePressFold = false;
        playerTwoPress = false;
        playerTwoPressPlay = false;
        playerTwoPressFold = false;

        resetImagesFaceDown();
        dealerHand = dealer.getHand();
        gameCommentary.clear();

        dealer.dealPlayer(playerOne);
        playerOneHand = playerOne.getHand();
        setPlayerImages(playerOneHand, p1c1Image1, p1c1Image2, p1c1Image3);

        if (isPlayerTwo) {
            dealer.dealPlayer(playerTwo);
            playerTwoHand = playerTwo.getHand();
            setPlayerImages(playerTwoHand, p2c1Image1, p2c2Image2, p2c3Image3);
        }

        revealCardsSequentially();
    }

    private void setPlayerImages(ArrayList<Card> hand, ImageView img1, ImageView img2, ImageView img3) {
        img1.setImage(new Image(getClass().getResourceAsStream("/" + hand.get(0).getSuit() + " " + hand.get(0).getValue() + ".png")));
        img2.setImage(new Image(getClass().getResourceAsStream("/" + hand.get(1).getSuit() + " " + hand.get(1).getValue() + ".png")));
        img3.setImage(new Image(getClass().getResourceAsStream("/" + hand.get(2).getSuit() + " " + hand.get(2).getValue() + ".png")));
    }

    private void revealCardsSequentially() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> p1c1Image1.setVisible(true)),
                new KeyFrame(Duration.seconds(2), e -> p1c1Image2.setVisible(true)),
                new KeyFrame(Duration.seconds(3), e -> p1c1Image3.setVisible(true)),
                new KeyFrame(Duration.seconds(4), e -> checkAndEnablePlayButtons())
        );
        timeline.play();
    }

    private void checkAndEnablePlayButtons() {
        playerOnePlay.setDisable(false);
        playerOneFold.setDisable(false);
        if (isPlayerTwo) {
            playerTwoPlay.setDisable(false);
            playerTwoFold.setDisable(false);
        }
    }

    private void resetImagesFaceDown() {
        Image faceDown = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image1.setImage(faceDown);
        p1c1Image2.setImage(faceDown);
        p1c1Image3.setImage(faceDown);
        p2c1Image1.setImage(faceDown);
        p2c2Image2.setImage(faceDown);
        p2c3Image3.setImage(faceDown);
        dc1Image1.setImage(faceDown);
        dc2Image2.setImage(faceDown);
        dc3Image3.setImage(faceDown);
    }
}
