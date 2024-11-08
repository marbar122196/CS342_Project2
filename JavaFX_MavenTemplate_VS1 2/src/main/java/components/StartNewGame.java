package components;
//package gamelogic;
import gamelogic.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.util.ArrayList;


public class StartNewGame {
    private Font customFont;
    private int titleSize;
    private int bodySize;
    private Scene scene;

    public StartNewGame(Font customFont, int titleSize, int bodySize, Stage primaryStage, Player playerOne, Player playerTwo, Dealer theDealer) {
        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;


        Button optionsButton = new Button("Options");
        optionsButton.setMinWidth(100);
        HBox optionsBox = new HBox(optionsButton);
        optionsBox.setAlignment(Pos.TOP_RIGHT);
        optionsBox.setPadding(new Insets(10));

        Pane pane = new Pane();

        Label dealer = new Label("dealer");
        dealer.setFont(customFont);
        dealer.setMinWidth(300);

        // Text fields for player 1
        TextField playPlayerOne = new TextField();
        TextField antePlayerOne = new TextField();
        TextField pairPlusPlayerOne = new TextField();
        TextField namePlayerOne = new TextField();
        namePlayerOne.setPromptText("Enter name here: ");

        //Buttons for Player 1
        Button playerOneDeal = new Button("deal");
        Button playerOneFold = new Button("fold");

        // Text fields for player 2
        TextField playPlayerTwo = new TextField();
        TextField antePlayerTwo = new TextField();
        TextField pairPlusPlayerTwo = new TextField();
        TextField namePlayerTwo = new TextField();
        namePlayerTwo.setPromptText("Enter name here: ");


        //Buttons for Player 2
        Button playerTwoDeal = new Button("deal");
        Button playerTwoFold = new Button("fold");

        playPlayerOne.setEditable(false);
        playPlayerTwo.setEditable(false);

        // Player 1 bets VBox
        VBox betsPlayerOne = new VBox(10, playPlayerOne, antePlayerOne, pairPlusPlayerOne);
        betsPlayerOne.setLayoutX(100);  // Set the x-coordinate for player one's VBox
        betsPlayerOne.setLayoutY(500);  // Set the y-coordinate for player one's VBox

        //Player 1 buttons Hbox
        HBox buttonPlayerOne = new HBox(10, playerOneDeal, playerOneFold);
//        buttonPlayerOne.setLayoutX(260);
//        buttonPlayerOne.setLayoutY(350);

        // Player 2 bets VBox
        VBox betsPlayerTwo = new VBox(10, playPlayerTwo, antePlayerTwo, pairPlusPlayerTwo);
        betsPlayerTwo.setLayoutX(1250);  // Set the x-coordinate for player two's VBox
        betsPlayerTwo.setLayoutY(500);  // Set the y-coordinate for player two's VBox

        //Player 2 buttons HBox
        HBox buttonPlayerTwo = new HBox(10, playerTwoDeal, playerTwoFold);
//        buttonPlayerTwo.setLayoutX(600);
//        buttonPlayerTwo.setLayoutY(350);

        Region r1 = new Region();
        r1.setMinWidth(75);

        HBox dealerContents = new HBox(10, r1, dealer);

        //this needs to happen every round so we can get new cards
       theDealer.dealPlayer(playerOne);
       theDealer.dealPlayer(playerTwo);

       //Actually grabs the hands for both players
        ArrayList<Card> playerOneHand = playerOne.getHand();
        ArrayList<Card> playerTwoHand = playerTwo.getHand();


        //Converts PlayerOnes hands to strings to grab images
        String playerOneCardOne = playerOneHand.get(0).getSuit() + " " + playerOneHand.get(0).getValue() + ".png";
        String playerOneCardTwo = playerOneHand.get(1).getSuit() + " " + playerOneHand.get(1).getValue() + ".png";
        String playerOneCardThree = playerOneHand.get(2).getSuit() + " " + playerOneHand.get(2).getValue() + ".png";


        //Now doing the same thing but for PlayerTwo
        String playerTwoCardOne = playerTwoHand.get(0).getSuit() + " " + playerTwoHand.get(0).getValue() + ".png";
        String playerTwoCardTwo = playerTwoHand.get(1).getSuit() + " " + playerTwoHand.get(1).getValue() + ".png";
        String playerTwoCardThree = playerTwoHand.get(2).getSuit() + " " + playerTwoHand.get(2).getValue() + ".png";


        //should we do thing single cards so that it can flip one at a time as a transition?
        Image p1c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView p1c1Image1 = new ImageView(p1c1);
        p1c1Image1.setFitWidth(75);
        p1c1Image1.setFitHeight(75);

        Image p1c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView p1c1Image2 = new ImageView(p1c2);
        p1c1Image2.setFitWidth(75);
        p1c1Image2.setFitHeight(75);

        Image p1c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView p1c1Image3 = new ImageView(p1c3);
        p1c1Image3.setFitWidth(75);
        p1c1Image3.setFitHeight(75);

        Image p2c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView p2c1Image1 = new ImageView(p2c1);
        p2c1Image1.setFitWidth(75);
        p2c1Image1.setFitHeight(75);

        Image p2c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView p2c2Image2 = new ImageView(p2c2);
        p2c2Image2.setFitWidth(75);
        p2c2Image2.setFitHeight(75);

        Image p2c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView p2c3Image3 = new ImageView(p2c3);
        p2c3Image3.setFitWidth(75);
        p2c3Image3.setFitHeight(75);

        Image dc1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView dc1Image1 = new ImageView(dc1);
        dc1Image1.setFitWidth(75);
        dc1Image1.setFitHeight(75);

        Image dc2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView dc2Image2 = new ImageView(dc2);
        dc2Image2.setFitWidth(75);
        dc2Image2.setFitHeight(75);

        Image dc3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        ImageView dc3Image3 = new ImageView(dc3);
        dc3Image3.setFitWidth(75);
        dc3Image3.setFitHeight(75);

        HBox deckOfCardsP1 = new HBox(5, p1c1Image1,p1c1Image2,p1c1Image3);
        HBox deckOfCardsP2 = new HBox(5, p2c1Image1, p2c2Image2, p2c3Image3);
        HBox deckOfCardsD = new HBox(5, dc1Image1, dc2Image2, dc3Image3);

//        VBox p1CardsHolder= new VBox(10, deckOfCardsP1);
//        p1CardsHolder.setLayoutX(300);
//        p1CardsHolder.setLayoutY(100);

        //Player 1 contents VBox
        VBox contentsPlayerOne = new VBox(30, namePlayerOne, deckOfCardsP1, buttonPlayerOne);
        contentsPlayerOne.setLayoutX(260);
        contentsPlayerOne.setLayoutY(440);

        //Player 2 contents Vboc
        VBox contentsPlayerTwo = new VBox(30, namePlayerTwo, deckOfCardsP2, buttonPlayerTwo);
        contentsPlayerTwo.setLayoutX(1000);
        contentsPlayerTwo.setLayoutY(440);

        //Dealers contents VBox
        VBox dealerVBox = new VBox(10, deckOfCardsD, dealerContents);
        dealerVBox.setLayoutX(625);
        dealerVBox.setLayoutY(100);

        pane.getChildren().addAll(betsPlayerOne, betsPlayerTwo, contentsPlayerOne, contentsPlayerTwo, dealerVBox);

        BorderPane rootPane = new BorderPane();
        rootPane.setTop(optionsBox);
        rootPane.setCenter(pane);

        optionsButton.setOnAction(e -> new OptionsMenu(customFont, titleSize, bodySize).show(primaryStage));

        this.scene = new Scene(rootPane, 1500, 1000);
    }

    public Scene getScene() {
        return scene;
    }
}
