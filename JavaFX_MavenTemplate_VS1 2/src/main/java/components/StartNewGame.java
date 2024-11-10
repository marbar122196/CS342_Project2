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
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;


import java.util.ArrayList;


public class StartNewGame {
    private Font customFont;
    private int titleSize;
    private int bodySize;
    private Scene scene;
    boolean isPlayerTwo = false;
    private boolean playerOnePress = false;
    private boolean playerTwoPress = false;
    private boolean playerOnePressPlay = false;
    private boolean playerTwoPressPlay = false;
    private boolean playerOnePressFold = false;
    private boolean playerTwoPressFold = false;
    private boolean madePairPlusP1 = false;
    private boolean madePairPlusP2 = false;
    ArrayList<Card> dealerHand;
    ArrayList<Card> playerOneHand;
    ArrayList<Card> playerTwoHand;
    TextArea gameCommentary;
    ImageView p1c1Image1;
    ImageView p1c1Image2;
    ImageView p1c1Image3;

    ImageView p2c1Image1;
    ImageView p2c2Image2;
    ImageView p2c3Image3;

    ImageView dc1Image1;
    ImageView dc2Image2;
    ImageView dc3Image3;
    Player playerOne;
    Player playerTwo;
    Dealer dealer;

    Button dealGame;



    //this is to reset the images to a facedown position after every round completed
    public void resetImagesFaceDown(){

        Image p1c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image1.setImage(p1c1);
        Image p1c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image2.setImage(p1c2);
        Image p1c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image3.setImage(p1c3);

        Image p2c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c1Image1.setImage(p2c1);
        Image p2c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c2Image2.setImage(p2c2);
        Image p2c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c3Image3.setImage(p2c3);

        Image dc1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc1Image1.setImage(dc1);
        Image dc2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc2Image2.setImage(dc2);
        Image dc3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc3Image3.setImage(dc3);

    }

    private void revealDealersCards(){
        dealerHand = dealer.getDeck();

        //Converts PlayerOnes hands to strings to grab images
        String dealerCardOne = "/" + dealerHand.get(0).getSuit() + " " + dealerHand.get(0).getValue() + ".png";
        String dealerCardTwo = "/" + dealerHand.get(1).getSuit() + " " + dealerHand.get(1).getValue() + ".png";
        String dealerCardThree = "/" + dealerHand.get(2).getSuit() + " " + dealerHand.get(2).getValue() + ".png";

        Image dealerReavealedOne = new Image(getClass().getResourceAsStream(dealerCardOne));
        Image dealerRevealedTwo = new Image(getClass().getResourceAsStream(dealerCardTwo));
        Image dealerRevealedThree = new Image(getClass().getResourceAsStream(dealerCardThree));

        //reveals dealers cards sequentially and then after reveal calls evaluate winner
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event ->{
                    dc1Image1.setImage(dealerReavealedOne);
                }),
                new KeyFrame(Duration.seconds(2), event ->{
                    dc2Image2.setImage(dealerRevealedTwo);
                }),
                new KeyFrame(Duration.seconds(3), event ->{
                    dc3Image3.setImage(dealerRevealedThree);

                    dealGame.setDisable(false);
                }),
                new KeyFrame(Duration.seconds(3.5), event -> evaluateWinner())
        );

        timeline.play();

    }

    private void singlePlayerGame(Button playerOnePlay, Button playerOneFold, TextField p1Play){
        revealDealersCards();

        playerOnePlay.setOnAction(e -> {
            p1Play.setText(playerOne.getAnteBet() + "");
            playerOne.setPlayBet(playerOne.getAnteBet());
        });
        playerOneFold.setOnAction(e -> {
            int p1Winnings = playerOne.getWinnings();
            p1Winnings = p1Winnings - playerOne.getAnteBet();
        });

    }

    //if two players play we need to check that both of them pressed a button
    private void checkButtonPress(Button playerOnePlay, Button playerTwoPlay, Button playerOneFold, Button playerTwoFold, Player playerOne, Player playerTwo, TextField p1Play, TextField p2Play, ImageView d1, ImageView d2, ImageView d3, Dealer dealer, Button deal){
        System.out.println("IN CHECK BUTTON PRESS SHOULD BE HEREEEEEEEEEEEEEEE");
        playerOnePlay.setOnAction(e -> {
           playerOnePress = true;
           playerOnePressPlay = true;
           bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
        });

        playerTwoPlay.setOnAction(e -> {
            playerTwoPress = true;
            playerTwoPressPlay = true;
            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
        });

        playerOneFold.setOnAction(e -> {
           playerOnePress = true;
           playerOnePressFold = true;
           bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
        });

        playerTwoFold.setOnAction(e -> {
           playerTwoPress = true;
           playerTwoPressFold = true;
           bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
        });
    }

    //actually execute their button presses
    private void bothPlayersReady(Player playerOne, Player playerTwo, TextField p1Play, TextField p2Play, ImageView d1, ImageView d2, ImageView d3, Dealer dealer, Button deal){
        //checks if both players pressed a button
        //now we have to differentiate between play and fold for both players
        if (playerOnePress && playerTwoPress) {
            if (playerOnePressPlay) {
                p1Play.setText(playerOne.getAnteBet() + "");
                playerOne.setPlayBet(playerOne.getAnteBet());
            } else if (playerOnePressFold) {
                int p1Winnings = playerOne.getWinnings();
                p1Winnings = p1Winnings - playerOne.getAnteBet();
                playerOne.setTotalWinnings(p1Winnings);
            }

            if (playerTwoPressPlay) {
                p2Play.setText(playerOne.getAnteBet() + "");
                playerTwo.setPlayBet(playerTwo.getAnteBet());
            } else if (playerTwoPressFold) {
                int p2Winnings = playerTwo.getWinnings();
                p2Winnings = p2Winnings - playerTwo.getAnteBet();
                playerTwo.setTotalWinnings(p2Winnings);
            }


            System.out.println("WAS REACHED ********************************");
            revealDealersCards();

        }
    }

    //evaluates the winner of the rounds
    public void evaluateWinner(){

        int dealHandVal = ThreeCardLogic.evalHand(dealerHand);
        if (dealHandVal == 0) {
            int highestCard = ThreeCardLogic.getHighest(dealerHand);

            if (highestCard < 12) {
                gameCommentary.setText("Dealer has lost! Ante will be pushed to next round.");
                return;
            }
        }

        int p1Hand = ThreeCardLogic.evalHand(playerOneHand);
        int winnerP1 = ThreeCardLogic.compareHands(dealerHand, playerOneHand);

        if (playerOnePressPlay && playerTwoPressPlay){
            int p2Hand = ThreeCardLogic.evalHand(playerTwoHand);
            int winnerP2 = ThreeCardLogic.compareHands(dealerHand, playerTwoHand);

            if (winnerP1 == 2 && winnerP2 == 2){
                gameCommentary.setText("Both players won against the dealer! :D");
            }
            else if (winnerP1 == 1 && winnerP2 == 2){
                gameCommentary.setText("Player 1 lost against dealer. Congrats to Player 2!");
            }
            else if (winnerP1 == 2 && winnerP2 == 1){
                gameCommentary.setText("Player 2 lost against dealer. Congrats to Player 1");
            }
            else if (winnerP1 == 1 && winnerP2 == 1){
                gameCommentary.setText("Both player lost against the dealer! >:)");
            }
            else{
                gameCommentary.setText("This game is a tie! :O");
            }
        }
        else if ((playerOnePressPlay || playerTwoPressPlay) && (playerOnePressFold || playerTwoPressFold)){
            int p2Hand = ThreeCardLogic.evalHand(playerTwoHand);
            int winnerP2 = ThreeCardLogic.compareHands(dealerHand, playerTwoHand);

            if (winnerP1 == 2 || winnerP2 == 2){
                gameCommentary.setText("Player won against dealer! :D");
            }
            else if (winnerP1 == 1 || winnerP2 == 1){
                gameCommentary.setText("Dealer won against player! >:)");
            }
            else {
                gameCommentary.setText("This game is a tie! :O");
            }
        }

        playerOnePress = false;
        playerOnePressPlay = false;
        playerOnePressFold = false;
        playerTwoPress = false;
        playerTwoPressPlay = false;
        playerTwoPressFold = false;
    }


    public StartNewGame(Font customFont, int titleSize, int bodySize, Stage primaryStage, Player playerOne, Player playerTwo, Dealer theDealer) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.dealer = theDealer;

        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;

        Button optionsButton = new Button("Options");
        optionsButton.setMinWidth(100);
        HBox optionsBox = new HBox(optionsButton);
        optionsBox.setAlignment(Pos.TOP_RIGHT);
        optionsBox.setPadding(new Insets(10));

        gameCommentary = new TextArea();
        gameCommentary.setPrefWidth(350);
        gameCommentary.setPrefHeight(150);
        gameCommentary.setLayoutX(575);
        gameCommentary.setLayoutY(500);
        gameCommentary.setEditable(false);
        gameCommentary.setWrapText(true);
        gameCommentary.setStyle("-fx-padding: 0;");
        gameCommentary.setText("Will there be a second player joining us? if so change name on right after entering in name of player one :)");

        dealGame = new Button("deal");
        dealGame.setFont(customFont);
        dealGame.setMinWidth(240);
        dealGame.setMinHeight(50);
        dealGame.setLayoutX(625);
        dealGame.setLayoutY(300);

        Pane pane = new Pane();

        Label dealer = new Label("dealer");
        dealer.setFont(customFont);
        dealer.setMinWidth(300);

        //positions for deck, player 1, and player 2
        double deckDealerX = 625, dealerDeckY = 100;
        double playerOneX = 260, playerOneY = 440;
        double playerTwoX = 1000, playerTwoY = 440;

        // Text fields for player 1
        TextField playPlayerOne = new TextField();
        TextField antePlayerOne = new TextField();
        TextField pairPlusPlayerOne = new TextField();
        Label winningsPlayerOne = new Label("Winnings");
        winningsPlayerOne.setFont(customFont);
        TextField amtWinningsPlayerOne = new TextField();
        TextField namePlayerOne = new TextField();
        namePlayerOne.setPromptText("Enter name here: ");

        //Buttons for Player 1
        Button playerOnePlay = new Button("play");
        playerOnePlay.setMinWidth(75);
        Button playerOneFold = new Button("fold");
        playerOneFold.setMinWidth(75);

        // Text fields for player 2
        TextField playPlayerTwo = new TextField();
        TextField antePlayerTwo = new TextField();
        TextField pairPlusPlayerTwo = new TextField();
        Label winningsPlayerTwo = new Label("Winnings");
        winningsPlayerTwo.setFont(customFont);
        TextField amtWinningsPlayerTwo = new TextField();
        TextField namePlayerTwo = new TextField();
        namePlayerTwo.setPromptText("Enter name here: ");
        namePlayerTwo.setEditable(false);

        //Buttons for Player 2
        Button playerTwoPlay = new Button("play");
        playerTwoPlay.setMinWidth(75);
        Button playerTwoFold = new Button("fold");
        playerTwoFold.setMinWidth(75);

        playPlayerOne.setEditable(false);
        playPlayerTwo.setEditable(false);

        // Player 1 bets VBox
        VBox betsPlayerOne = new VBox(10, playPlayerOne, antePlayerOne, pairPlusPlayerOne, winningsPlayerOne, amtWinningsPlayerOne);
        betsPlayerOne.setLayoutX(100);  // Set the x-coordinate for player one's VBox
        betsPlayerOne.setLayoutY(500);  // Set the y-coordinate for player one's VBox

        Region r1 = new Region();
        r1.setMinWidth(25);

        //Player 1 buttons Hbox
        HBox buttonPlayerOne = new HBox(10, r1, playerOnePlay, playerOneFold);
//        buttonPlayerOne.setLayoutX(260);
//        buttonPlayerOne.setLayoutY(350);

        Region r2 = new Region();
        r2.setMinWidth(25);

        // Player 2 bets VBox
        VBox betsPlayerTwo = new VBox(10, playPlayerTwo, antePlayerTwo, pairPlusPlayerTwo, winningsPlayerTwo, amtWinningsPlayerTwo);
        betsPlayerTwo.setLayoutX(1250);  // Set the x-coordinate for player two's VBox
        betsPlayerTwo.setLayoutY(500);  // Set the y-coordinate for player two's VBox

        //Player 2 buttons HBox
        HBox buttonPlayerTwo = new HBox(10, r2, playerTwoPlay, playerTwoFold);
//        buttonPlayerTwo.setLayoutX(600);
//        buttonPlayerTwo.setLayoutY(350);

        Region r3 = new Region();
        r3.setMinWidth(75);

        HBox dealerContents = new HBox(10, r3, dealer);

        //should we do thing single cards so that it can flip one at a time as a transition?
        Image p1c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image1 = new ImageView(p1c1);
        p1c1Image1.setFitWidth(75);
        p1c1Image1.setFitHeight(75);

        Image p1c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image2 = new ImageView(p1c2);
        p1c1Image2.setFitWidth(75);
        p1c1Image2.setFitHeight(75);

        Image p1c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image3 = new ImageView(p1c3);
        p1c1Image3.setFitWidth(75);
        p1c1Image3.setFitHeight(75);

        Image p2c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c1Image1 = new ImageView(p2c1);
        p2c1Image1.setFitWidth(75);
        p2c1Image1.setFitHeight(75);

        Image p2c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c2Image2 = new ImageView(p2c2);
        p2c2Image2.setFitWidth(75);
        p2c2Image2.setFitHeight(75);

        Image p2c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c3Image3 = new ImageView(p2c3);
        p2c3Image3.setFitWidth(75);
        p2c3Image3.setFitHeight(75);

        Image dc1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc1Image1 = new ImageView(dc1);
        dc1Image1.setFitWidth(75);
        dc1Image1.setFitHeight(75);

        Image dc2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc2Image2 = new ImageView(dc2);
        dc2Image2.setFitWidth(75);
        dc2Image2.setFitHeight(75);

        Image dc3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc3Image3 = new ImageView(dc3);
        dc3Image3.setFitWidth(75);
        dc3Image3.setFitHeight(75);

        HBox deckOfCardsP1 = new HBox(5, p1c1Image1,p1c1Image2,p1c1Image3);
        HBox deckOfCardsP2 = new HBox(5, p2c1Image1, p2c2Image2, p2c3Image3);
        HBox deckOfCardsD = new HBox(5, dc1Image1, dc2Image2, dc3Image3);

        namePlayerOne.setOnAction(event -> {
            String nameP1 = namePlayerOne.getText();
            namePlayerOne.setEditable(false);
            namePlayerOne.setText(nameP1);
            namePlayerTwo.setEditable(true);
        });

        namePlayerTwo.setOnAction(event -> {
            String nameP2 = namePlayerTwo.getText();
            isPlayerTwo = true;
            namePlayerTwo.setEditable(false);
            namePlayerTwo.setText(nameP2);

            checkButtonPress(playerOnePlay, playerTwoPlay, playerOneFold, playerTwoFold, playerOne, playerTwo, playPlayerOne, playPlayerTwo, dc1Image1, dc2Image2, dc3Image3, theDealer, dealGame);
        });

        dealGame.setOnAction( e -> {

            //these things needs to happen every round regardless of # of players
            resetImagesFaceDown(); //resets all cards to be face down
            dealerHand = theDealer.getHand(); //gets dealers hand
            gameCommentary.clear(); //clears text box

            //set up for Player ONE SOLELY
            theDealer.dealPlayer(playerOne); //deals to the player
            playerOneHand = playerOne.getHand(); //gets the hand of player

            //Converts PlayerOnes hands to strings to grab images
            String playerOneCardOne = "/" + playerOneHand.get(0).getSuit() + " " + playerOneHand.get(0).getValue() + ".png";
            String playerOneCardTwo = "/" + playerOneHand.get(1).getSuit() + " " + playerOneHand.get(1).getValue() + ".png";
            String playerOneCardThree = "/" + playerOneHand.get(2).getSuit() + " " + playerOneHand.get(2).getValue() + ".png";

            //Revealed Cards for Player One
            Image playerOneRevealedOne = new Image(getClass().getResourceAsStream(playerOneCardOne));
            Image playerOneRevealedTwo = new Image(getClass().getResourceAsStream(playerOneCardTwo));
            Image playerOneRevealedThree = new Image(getClass().getResourceAsStream(playerOneCardThree));


            //set up for player Two if there is one
            theDealer.dealPlayer(playerTwo); //deals to player
            playerTwoHand = playerTwo.getHand(); //gets hand of player

            //Now doing the same thing but for PlayerTwo
            String playerTwoCardOne = "/" + playerTwoHand.get(0).getSuit() + " " + playerTwoHand.get(0).getValue() + ".png";
            String playerTwoCardTwo = "/" + playerTwoHand.get(1).getSuit() + " " + playerTwoHand.get(1).getValue() + ".png";
            String playerTwoCardThree = "/" +playerTwoHand.get(2).getSuit() + " " + playerTwoHand.get(2).getValue() + ".png";

            //Revealed Cards for Player Two
            Image playerTwoRevealedOne = new Image(getClass().getResourceAsStream(playerTwoCardOne));
            Image playerTwoRevealedTwo = new Image(getClass().getResourceAsStream(playerTwoCardTwo));
            Image playerTwoRevealedThree = new Image(getClass().getResourceAsStream(playerTwoCardThree));


            //reveals cards sequentially
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event ->{
                       p1c1Image1.setImage(playerOneRevealedOne);
                       if (isPlayerTwo) {
                           p2c1Image1.setImage(playerTwoRevealedOne);
                       }
                    }),
                    new KeyFrame(Duration.seconds(2), event ->{
                        p1c1Image2.setImage(playerOneRevealedTwo);
                        if (isPlayerTwo) {
                            p2c2Image2.setImage(playerTwoRevealedTwo);
                        }
                    }),
                    new KeyFrame(Duration.seconds(3), event ->{
                        p1c1Image3.setImage(playerOneRevealedThree);
                        if (isPlayerTwo) {
                            p2c3Image3.setImage(playerTwoRevealedThree);
                        }

                        dealGame.setDisable(true);
                    })
            );

            timeline.play();

        });

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

        pane.getChildren().addAll(betsPlayerOne, betsPlayerTwo, contentsPlayerOne, contentsPlayerTwo, dealerVBox, dealGame, gameCommentary);

        BorderPane rootPane = new BorderPane();
        rootPane.setTop(optionsBox);
        rootPane.setCenter(pane);

        optionsButton.setOnAction(e -> new OptionsMenu(customFont, titleSize, bodySize, playerOne, playerTwo, theDealer, primaryStage).show(primaryStage));

        this.scene = new Scene(rootPane, 1500, 800);
    }

    public Scene getScene() {
        return scene;
    }

    public void show(Stage primaryStage) {
        primaryStage.setScene(getScene());  // Set the scene for primaryStage
        primaryStage.show();  // Show primaryStage with the new scene
    }
}
