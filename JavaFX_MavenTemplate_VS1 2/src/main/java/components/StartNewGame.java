package components;
//package gamelogic;
import gamelogic.*;
//TO DO:
//IMPLEMENT COMMENTARY FOR BOTH PLAYERS FOLDING
//CHANGE WINNINGS EARNED BY WHAT CARD THEY GOT
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
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.Scene;


import java.util.ArrayList;

public class StartNewGame {
    public ArrayList<String> diffHands = new ArrayList<>();
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
    TextField namePlayerOne;
    TextField antePlayerOne;
    TextField pairPlusPlayerOne;
    TextField amtWinningsPlayerOne;
    Label handNamePlayerOne;


    Player playerTwo;
    TextField namePlayerTwo;
    TextField antePlayerTwo;
    TextField pairPlusPlayerTwo;
    TextField amtWinningsPlayerTwo;
    Label handNamePlayerTwo;


    Dealer dealer;


    Button dealGame;

    public void populateDiffHands(){
        diffHands.add("High-Card");
        diffHands.add("Straight Flush");
        diffHands.add("Three of a Kind");
        diffHands.add("Straight");
        diffHands.add("Flush");
        diffHands.add("Pair");
    }

    public String getHandLabel(int Hand){

        return diffHands.get(Hand);
    }

    //if bet is invalid return -1, otherwise return 0
    public int checkBetValid(int bet){
        if (bet < 5 || bet > 25){
            return -1;
        }
        return 0;
    }

    public int checkPairPlus(){
        String pairPlus = pairPlusPlayerOne.getText();
        int pairPlusNum = Integer.parseInt(pairPlus);

        return checkBetValid(pairPlusNum);
    }

    //helper function to determine winnings for pair plus bet
    public int ifPairPlusBetMade(Player player){
        int playerPP = ThreeCardLogic.evalHand(player.getHand());

        if (player.getPairPlusBet() == 0){
            return -2;
        }

        if (playerPP > 0){
            return ThreeCardLogic.evalPPWinnings(player.getHand(), player.getPairPlusBet());
        }
        else {
            return -1;
        }
    }

    //to check if bets was entered before playing game
    public int enableDeal(){
        String nameP1 = namePlayerOne.getText();
        String anteTextP1 = antePlayerOne.getText();

        boolean hasName = !nameP1.isEmpty();
        int anteP1 = Integer.parseInt(anteTextP1);
        int p1Bet = checkBetValid(anteP1);

        if (isPlayerTwo){
            String nameP2 = namePlayerTwo.getText();
            String anteTextP2 = antePlayerTwo.getText();

            hasName = !nameP1.isEmpty() && !nameP2.isEmpty();
            if (hasName) {
                int anteP2 = Integer.parseInt(anteTextP2);

                int p2Bet = checkBetValid(anteP2);

                if (p1Bet == -1 && p2Bet == 0) {
                    gameCommentary.setText("Player 1's bet is invalid please try again");
                    return 0;
                }
                else if (p2Bet == -1 && p1Bet == 0) {
                    gameCommentary.setText("Player 2's bet is invalid please try again");
                    return 0;
                }
                else if (p1Bet == -1 && p2Bet == -1) {
                    gameCommentary.setText("Both bets invalid. try again.");
                    return 0;
                }

                else {
                    gameCommentary.setText("Bets look good!");
                    playerOne.setAnteBet(anteP1);
                    playerTwo.setAnteBet(anteP2);
                    dealGame.setDisable(false);
                    return 1;
                }
            }
        }

        if (p1Bet == -1){
            gameCommentary.setText("Bet is invalid try again");
            return 0;
        }
        else{
            gameCommentary.setText("Looks great lets get started");
            playerOne.setAnteBet(anteP1);
            dealGame.setDisable(false);
            return 1;
        }
    }
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

    //if two players play we need to check that both of them pressed a button
    private void checkButtonPress(Button playerOnePlay, Button playerTwoPlay, Button playerOneFold, Button playerTwoFold, Player playerOne, Player playerTwo, TextField p1Play, TextField p2Play, ImageView d1, ImageView d2, ImageView d3, Dealer dealer, Button deal){
        System.out.println("HELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLOOOOOOOOOOOOOOOOOOo + " + playerOnePlay.isDisabled());

        System.out.println("HELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLOOOOOOOOOOOOOOOOOOo + " + playerTwoPlay.isDisabled());
        playerOnePlay.setOnAction(e -> {
            System.out.println("playerOnePlay PRESSEDDDDDDDDDDDDdd");

            playerOnePlay.setDisable(true);
            playerOneFold.setDisable(true);
            playerOnePress = true;
            playerOnePressPlay = true;
            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
        });


        playerTwoPlay.setOnAction(e -> {
            System.out.println("playerTwoPlay PRESSEDDDDDDDDDDDDdd");
            playerTwoPlay.setDisable(true);
            playerTwoFold.setDisable(true);
            playerTwoPress = true;
            playerTwoPressPlay = true;
            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
        });


        playerOneFold.setOnAction(e -> {
            playerOnePlay.setDisable(true);
            playerOneFold.setDisable(true);
            playerOnePress = true;
            playerOnePressFold = true;
            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
        });


        playerTwoFold.setOnAction(e -> {
            playerTwoPlay.setDisable(true);
            playerTwoFold.setDisable(true);
            playerTwoPress = true;
            playerTwoPressFold = true;
            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
        });
    }


    private void bothPlayersReady(Player playerOne, Player playerTwo, TextField p1Play, TextField p2Play, ImageView d1, ImageView d2, ImageView d3, Dealer dealer, Button deal) {

        if (playerOnePress && (!isPlayerTwo || playerTwoPress)) { //makes it so it works for both single and 2 player mode
            if (playerOnePressPlay) {
                p1Play.setText(playerOne.getAnteBet() + "");
                playerOne.setPlayBet(playerOne.getAnteBet());
            }
            else if (playerOnePressFold) {
                int p1Winnings = playerOne.getTotalWinnings();
                p1Winnings = p1Winnings - playerOne.getAnteBet();
                playerOne.setTotalWinnings(p1Winnings);
            }

            if (isPlayerTwo) {
                if (playerTwoPressPlay) {
                    p2Play.setText(playerTwo.getAnteBet() + "");
                    playerTwo.setPlayBet(playerTwo.getAnteBet());
                }
                else if (playerTwoPressFold) {
                    int p2Winnings = playerTwo.getTotalWinnings();
                    p2Winnings = p2Winnings - playerTwo.getAnteBet();
                    playerTwo.setTotalWinnings(p2Winnings);
                }
            }

            revealDealersCards();
        }
    }



    //evaluates the winner of the rounds
    public void evaluateWinner(){


        int dealHandVal = ThreeCardLogic.evalHand(dealerHand);
        if (dealHandVal == 0) {
            int highestCard = ThreeCardLogic.getHighest(dealerHand);


            if (highestCard < 12) {
                gameCommentary.setText("Dealer does not have at least Queen high; ante wager is pushed");
                antePlayerOne.setEditable(false);

                if (isPlayerTwo){
                    antePlayerTwo.setEditable(false);
                }
                return;
            }
        }

        int winnerP1 = ThreeCardLogic.compareHands(dealerHand, playerOneHand);
        int winningsP1 = playerOne.getTotalWinnings();
        int winningsP2 = playerTwo.getTotalWinnings();


        if (playerOnePressPlay && playerTwoPressPlay){
            int winnerP2 = ThreeCardLogic.compareHands(dealerHand, playerTwoHand);

            if (winnerP1 == 2 && winnerP2 == 2){
                winningsP1 = winningsP1 + (playerOne.getAnteBet() * 4);
                winningsP2 = winningsP2 + (playerTwo.getAnteBet() * 4);

                gameCommentary.setText("Both players won against the dealer! :D");
            }
            else if (winnerP1 == 1 && winnerP2 == 2){
                gameCommentary.setText("Player 1 lost against dealer. Congrats to Player 2!");
                winningsP1 = winningsP1 - (playerOne.getAnteBet() * playerOne.getPlayBet());
                winningsP2 = winningsP2 + (playerTwo.getAnteBet() * 4);
            }
            else if (winnerP1 == 2 && winnerP2 == 1){
                gameCommentary.setText("Player 2 lost against dealer. Congrats to Player 1");
                winningsP1 = winningsP1 + (playerOne.getAnteBet() * 4);
                winningsP2 = winningsP2 - (playerTwo.getAnteBet() + playerTwo.getPlayBet());
            }
            else if (winnerP1 == 1 && winnerP2 == 1){
                gameCommentary.setText("Both player lost against the dealer! >:)");
                winningsP1 = winningsP1 - (playerOne.getAnteBet() + playerOne.getAnteBet());
                winningsP2 = winningsP2 - (playerTwo.getAnteBet() + playerTwo.getAnteBet());
            }
            else{
                gameCommentary.setText("This game is a tie! :O");
            }
        }
        else if ((playerOnePressPlay || playerTwoPressPlay) && (playerOnePressFold || playerTwoPressFold)){
            int p2Hand = ThreeCardLogic.evalHand(playerTwoHand);
            int winnerP2 = ThreeCardLogic.compareHands(dealerHand, playerTwoHand);


            if (winnerP1 == 2 || winnerP2 == 2){
                if (winnerP1 == 2){
                    winningsP1 = winningsP1 + (playerOne.getAnteBet() * 4);
                }
                else if (winnerP2 == 2){
                    winningsP2 = winningsP2 + (playerTwo.getAnteBet() * 4);
                }
                gameCommentary.setText("Player won against dealer! :D");
            }
            else if (winnerP1 == 1 || winnerP2 == 1){
                if (winnerP1 == 1){
                    winningsP1 = winningsP1 - (playerOne.getAnteBet() + playerOne.getPlayBet());
                }
                else if (winnerP2 == 1){
                    winningsP2 = winningsP2 - (playerTwo.getAnteBet() + playerTwo.getPlayBet());
                }
                gameCommentary.setText("Dealer won against player! >:)");
            }
            else {
                gameCommentary.setText("This game is a tie! :O");
            }
        }
        else if (playerOnePressPlay && !isPlayerTwo){
            if (winnerP1 == 2){
                winningsP1 = winningsP1 + (playerOne.getAnteBet() * 4);
                gameCommentary.setText("Player won against dealer!");
            }
            else if (winnerP1 == 1){
                winningsP1 = winningsP1 - (playerOne.getAnteBet() + playerOne.getPlayBet());
                gameCommentary.setText("Player lost against dealer");
            }
            else{
                gameCommentary.setText("This is a draw!");
            }
        }

        int playerOnePairPlusMade = ifPairPlusBetMade(playerOne);
        int playerTwoPairPlusMade = ifPairPlusBetMade(playerTwo);

        if (playerOnePairPlusMade != -2){
            if (playerOnePairPlusMade != -1){
                winningsP1 = winningsP1 + playerOnePairPlusMade;
                gameCommentary.appendText(" Player One won pair plus wager :D +" + playerOnePairPlusMade);
            }
            else{
                winningsP1 = winningsP1 - playerOne.getPairPlusBet();
                gameCommentary.appendText(" Player One lost pair plus wager :( -" + playerOne.getPairPlusBet());
            }
        }

        if (playerTwoPairPlusMade != -2){
            if (playerTwoPairPlusMade != -1){
                winningsP2 = winningsP2 + playerTwoPairPlusMade;
                gameCommentary.appendText(" Player Two won pair plus wager +" + playerTwoPairPlusMade);
            }
            else {
                winningsP2 = winningsP2 - playerTwo.getPairPlusBet();
                gameCommentary.appendText(" Player Two lost pair plus wager :( -" + playerTwo.getPairPlusBet());
            }
        }

        playerOne.setTotalWinnings(winningsP1);
        amtWinningsPlayerOne.setText(playerOne.getTotalWinnings() + "");

        if (isPlayerTwo){
            playerTwo.setTotalWinnings(winningsP2);
            amtWinningsPlayerTwo.setText(playerTwo.getTotalWinnings() + "");
        }

//        antePlayerOne.clear();
//        antePlayerTwo.clear();
    }

    public StartNewGame(Font customFont, int titleSize, int bodySize, Stage primaryStage, Player playerOne, Player playerTwo, Dealer theDealer) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.dealer = theDealer;

        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;


        Button optionsButton = new Button("Options");
        Button rulesButton = new Button("rules");
        optionsButton.setMinWidth(100);
        VBox optionsBox = new VBox(optionsButton,rulesButton);
        optionsBox.setAlignment(Pos.TOP_RIGHT);
        optionsBox.setPadding(new Insets(10));
        optionsBox.setStyle("-fx-border-color: red; -fx-border-width: 1px;");



        gameCommentary = new TextArea();
        gameCommentary.setPrefWidth(350);
        gameCommentary.setPrefHeight(150);
        gameCommentary.setLayoutX(575);
        gameCommentary.setLayoutY(500);
        gameCommentary.setEditable(false);
        gameCommentary.setWrapText(true);
        gameCommentary.setStyle("-fx-padding: 0;");
        gameCommentary.setText("Hello! Please at least enter in a name and ante wager to get started ($5 <= bet <= $25). Will there be a second player joining us? if so change name on right after entering in name of player one :) (press enter after completing names)");
        gameCommentary.getStyleClass().add("orange-body-text-smaller");
// Outer BorderPane (White Border)
        // Outer BorderPane (White Border)
        BorderPane outerBorderPane = new BorderPane();
        outerBorderPane.getStyleClass().add("orange-outer-pane-border");
        outerBorderPane.setPrefSize(700, 150); // Adjust size to create the border effect

// Inner BorderPane (Orange Background)
        BorderPane innerBorderPane = new BorderPane();
        innerBorderPane.setStyle("-fx-background-color: #FF9A56;"); // Apply the orange background directly to the inner pane
        innerBorderPane.setPrefSize(680, 130); // Set preferred size for inner pane


// Add gameCommentary to the inner pane
        innerBorderPane.setCenter(gameCommentary);

// Add the inner pane to the center of the outer pane
        outerBorderPane.setCenter(innerBorderPane);

// Positioning
        outerBorderPane.setLayoutX(410); // Adjust as needed
        outerBorderPane.setLayoutY(530); // Adjust as needed




        Region r4 = new Region();
        r4.setMinWidth(20);

        dealGame = new Button("deal");
//        dealGame.setFont(customFont);
//        dealGame.setMinWidth(240);
//        dealGame.setMinHeight(50);
//        dealGame.setLayoutX(625);
//        dealGame.setLayoutY(300);
        dealGame.getStyleClass().add("orange-button-deal");
        dealGame.setDisable(true);
        dealGame.setStyle("-fx-opacity: 1;");
        HBox dealGameBox = new HBox(r4,dealGame);




        Pane pane = new Pane();


        Label dealer = new Label("dealer");
        dealer.getStyleClass().add("orange-body-text");
        dealer.setMinWidth(300);


        //positions for deck, player 1, and player 2
        double deckDealerX = 625, dealerDeckY = 100;
        double playerOneX = 260, playerOneY = 440;
        double playerTwoX = 1000, playerTwoY = 440;


        // Text fields and labels for player 1
        Label playP1 = new Label("Play:");
        playP1.getStyleClass().add("orange-body-text");
        TextField playPlayerOne = new TextField();


        Label anteP1 = new Label("Ante: ");
        anteP1.getStyleClass().add("orange-body-text");
        antePlayerOne = new TextField();


        Label pairPlusP1 = new Label("Pair Plus: ");
        pairPlusP1.getStyleClass().add("orange-body-text");
        pairPlusPlayerOne = new TextField();


        Label winningsPlayerOne = new Label("Winnings:");
        winningsPlayerOne.getStyleClass().add("orange-body-text");
        amtWinningsPlayerOne = new TextField();


        namePlayerOne = new TextField();
        namePlayerOne.setPromptText("Enter name here: ");
        namePlayerOne.getStyleClass().add("transparent-text-field");

        handNamePlayerOne = new Label("YOUR HAND");
        handNamePlayerOne.getStyleClass().add("orange-body-text-smaller");
        handNamePlayerOne.getStyleClass().add("orange-body-text");


        //Buttons for Player 1
        Button playerOnePlay = new Button("play");
        playerOnePlay.getStyleClass().add("orange-button-smallest");
        Button playerOneFold = new Button("fold");
        playerOneFold.getStyleClass().add("orange-button-smallest");
        playerOnePlay.setDisable(true);
        playerOneFold.setDisable(true);
        playerOnePlay.setStyle("-fx-opacity: 1;");
        playerOneFold.setStyle("-fx-opacity: 1;");


        // Text fields and labels for player 2
        Label playP2 = new Label("Play:");
//        playP2.setFont(customFont);
        playP2.getStyleClass().add("orange-body-text");
        TextField playPlayerTwo = new TextField();


        Label anteP2 = new Label("Ante: ");
//        anteP2.setFont(customFont);
        anteP2.getStyleClass().add("orange-body-text");
        antePlayerTwo = new TextField();


        Label pairPlusP2 = new Label("Pair Plus: ");
        pairPlusP2.getStyleClass().add("orange-body-text");
        pairPlusPlayerTwo = new TextField();


        Label winningsPlayerTwo = new Label("Winnings:");
        winningsPlayerTwo.getStyleClass().add("orange-body-text");
        amtWinningsPlayerTwo = new TextField();


        namePlayerTwo = new TextField();
        namePlayerTwo.setPromptText("Enter name here: ");
        namePlayerTwo.getStyleClass().add("transparent-text-field");
        namePlayerTwo.setEditable(false);

        handNamePlayerTwo = new Label("YOUR HAND");
        handNamePlayerTwo.getStyleClass().add("orange-body-text-smaller");
        handNamePlayerTwo.getStyleClass().add("orange-body-text");


        //Buttons for Player 2
        Button playerTwoPlay = new Button("play");
//        playerTwoPlay.setMinWidth(75);
        playerTwoPlay.getStyleClass().add("orange-button-smallest");
        Button playerTwoFold = new Button("fold");
//        playerTwoFold.setMinWidth(75);
        playerTwoFold.getStyleClass().add("orange-button-smallest");
        playerTwoPlay.setDisable(true);
        playerTwoFold.setDisable(true);
        playerTwoPlay.setStyle("-fx-opacity: 1;");
        playerTwoFold.setStyle("-fx-opacity: 1;");


        playPlayerOne.setEditable(false);
        playPlayerTwo.setEditable(false);


        // Player 1 bets VBox
        VBox betsPlayerOne = new VBox(10, playP1, playPlayerOne, anteP1, antePlayerOne, pairPlusP1, pairPlusPlayerOne, winningsPlayerOne, amtWinningsPlayerOne);
        betsPlayerOne.setLayoutX(100);  // Set the x-coordinate for player one's VBox
        betsPlayerOne.setLayoutY(220);  // Set the y-coordinate for player one's VBox
        betsPlayerOne.setStyle("-fx-border-color: red; -fx-border-width: 1px;");


        Region r1 = new Region();
        r1.setMinWidth(12);


        //Player 1 buttons Hbox
        HBox buttonPlayerOne = new HBox(20, r1, playerOnePlay, playerOneFold);
//        buttonPlayerOne.setLayoutX(260);
//        buttonPlayerOne.setLayoutY(350);
        buttonPlayerOne.setStyle("-fx-border-color: red; -fx-border-width: 1px;");



        Region r2 = new Region();
        r2.setMinWidth(15);


        // Player 2 bets VBox
        VBox betsPlayerTwo = new VBox(10, playP2, playPlayerTwo, anteP2, antePlayerTwo, pairPlusP2, pairPlusPlayerTwo, winningsPlayerTwo, amtWinningsPlayerTwo);
        betsPlayerTwo.setLayoutX(1250);  // Set the x-coordinate for player two's VBox
        betsPlayerTwo.setLayoutY(220);  // Set the y-coordinate for player two's VBox
        betsPlayerTwo.setStyle("-fx-border-color: red; -fx-border-width: 1px;");


        //Player 2 buttons HBox
        HBox buttonPlayerTwo = new HBox(20, r2, playerTwoPlay, playerTwoFold);
//        buttonPlayerTwo.setLayoutX(600);
//        buttonPlayerTwo.setLayoutY(350);
        buttonPlayerTwo.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
        HBox buttonPlayerTwo = new HBox(10, r2, playerTwoPlay, playerTwoFold);


        Region r3 = new Region();
        r3.setMinWidth(75);

        HBox dealerContents = new HBox(10, r3, dealer);
        dealerContents.setAlignment(Pos.CENTER);
//        dealerVBox.setStyle("-fx-border-color: red; -fx-border-width: 1px;");


        //should we do thing single cards so that it can flip one at a time as a transition?
        Image p1c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image1 = new ImageView(p1c1);
        p1c1Image1.setFitWidth(75);
        p1c1Image1.setFitHeight(100);


        Image p1c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image2 = new ImageView(p1c2);
        p1c1Image2.setFitWidth(75);
        p1c1Image2.setFitHeight(100);


        Image p1c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p1c1Image3 = new ImageView(p1c3);
        p1c1Image3.setFitWidth(75);
        p1c1Image3.setFitHeight(100);


        Image p2c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c1Image1 = new ImageView(p2c1);
        p2c1Image1.setFitWidth(75);
        p2c1Image1.setFitHeight(100);


        Image p2c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c2Image2 = new ImageView(p2c2);
        p2c2Image2.setFitWidth(75);
        p2c2Image2.setFitHeight(100);


        Image p2c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        p2c3Image3 = new ImageView(p2c3);
        p2c3Image3.setFitWidth(75);
        p2c3Image3.setFitHeight(100);


        Image dc1 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc1Image1 = new ImageView(dc1);
        dc1Image1.setFitWidth(75);
        dc1Image1.setFitHeight(100);


        Image dc2 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc2Image2 = new ImageView(dc2);
        dc2Image2.setFitWidth(75);
        dc2Image2.setFitHeight(100);


        Image dc3 = new Image(getClass().getResourceAsStream("/facedown.png"));
        dc3Image3 = new ImageView(dc3);
        dc3Image3.setFitWidth(75);
        dc3Image3.setFitHeight(100);


        HBox deckOfCardsP1 = new HBox(5, p1c1Image1,p1c1Image2,p1c1Image3);
        HBox deckOfCardsP2 = new HBox(5, p2c1Image1, p2c2Image2, p2c3Image3);
        HBox deckOfCardsD = new HBox(5, dc1Image1, dc2Image2, dc3Image3);
        deckOfCardsP1.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
        deckOfCardsP2.setStyle("-fx-border-color: red; -fx-border-width: 1px;");



        namePlayerOne.setOnAction(event -> {
            String nameP1 = namePlayerOne.getText();
            namePlayerOne.setEditable(false);
            namePlayerOne.setText(nameP1);
            namePlayerTwo.setEditable(true);
            enableDeal();
//            dealGame.setDisable(false);
        });


        antePlayerOne.setOnAction(event -> {
            if (enableDeal() == 1){
                checkButtonPress(playerOnePlay, playerTwoPlay, playerOneFold, playerTwoFold, playerOne, playerTwo, playPlayerOne, playPlayerTwo, dc1Image1, dc2Image2, dc3Image3, theDealer, dealGame);
            }
        });

        pairPlusPlayerOne.setOnAction(event ->{
            if(checkPairPlus() == 0){
                gameCommentary.setText("Player 1's pair plus bet is valid");
                pairPlusPlayerOne.setEditable(false);
                playerOne.setPairPlusBet(Integer.parseInt(pairPlusPlayerOne.getText()));
            }
            else{
                gameCommentary.setText("Pair plus bet is invalid");
            }

            System.out.println("it is getting called in player ones pp");
            checkButtonPress(playerOnePlay, playerTwoPlay, playerOneFold, playerTwoFold, playerOne, playerTwo, playPlayerOne, playPlayerTwo, dc1Image1, dc2Image2, dc3Image3, theDealer, dealGame);
        });


        namePlayerTwo.setOnAction(event -> {
            String nameP2 = namePlayerTwo.getText();
            isPlayerTwo = true;
            namePlayerTwo.setEditable(false);
            namePlayerTwo.setText(nameP2);
            enableDeal();

        });

        antePlayerTwo.setOnAction(event ->{
            if (enableDeal() == 1){
                checkButtonPress(playerOnePlay, playerTwoPlay, playerOneFold, playerTwoFold, playerOne, playerTwo, playPlayerOne, playPlayerTwo, dc1Image1, dc2Image2, dc3Image3, theDealer, dealGame);
            }
        });

        pairPlusPlayerTwo.setOnAction(event ->{
            if(checkPairPlus() == 0){
                gameCommentary.setText("Player Twos pair plus bet is valid!");
                pairPlusPlayerTwo.setEditable(false);
                playerTwo.setPairPlusBet(Integer.parseInt(pairPlusPlayerTwo.getText()));
            }
            else{
                gameCommentary.setText("Pair plus bet is invalid");
            }

            System.out.println("it is getting called in player two pp");
            checkButtonPress(playerOnePlay, playerTwoPlay, playerOneFold, playerTwoFold, playerOne, playerTwo, playPlayerOne, playPlayerTwo, dc1Image1, dc2Image2, dc3Image3, theDealer, dealGame);
        });


        dealGame.setOnAction( e -> {
            namePlayerOne.setDisable(true);
            antePlayerOne.setDisable(true);
            if (!isPlayerTwo){
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

                        playerOnePlay.setDisable(false);
                        playerOneFold.setDisable(false);
                        if (isPlayerTwo){
                            playerTwoPlay.setDisable(false);
                            playerTwoFold.setDisable(false);
                        }
                        checkButtonPress(playerOnePlay, playerTwoPlay, playerOneFold, playerTwoFold, playerOne, playerTwo, playPlayerOne, playPlayerTwo, dc1Image1, dc2Image2, dc3Image3, theDealer, dealGame);

                        dealGame.setDisable(true);
                    })
            );


            timeline.play();


        });



        //Player 1 contents VBox
        VBox contentsPlayerOne = new VBox(10, namePlayerOne, handNamePlayerOne, deckOfCardsP1, buttonPlayerOne);

        contentsPlayerOne.setLayoutX(320);
        contentsPlayerOne.setLayoutY(310);
        contentsPlayerOne.setAlignment(Pos.CENTER);
        contentsPlayerOne.setStyle("-fx-border-color: red; -fx-border-width: 1px;");


        contentsPlayerOne.setLayoutX(260);
        contentsPlayerOne.setLayoutY(440);



        //Player 2 contents Vboc
        VBox contentsPlayerTwo = new VBox(10, namePlayerTwo, handNamePlayerTwo, deckOfCardsP2, buttonPlayerTwo);

        contentsPlayerTwo.setLayoutX(930);
        contentsPlayerTwo.setLayoutY(310);
        contentsPlayerTwo.setAlignment(Pos.CENTER);
        contentsPlayerTwo.setStyle("-fx-border-color: red; -fx-border-width: 1px;");

        contentsPlayerTwo.setLayoutX(1000);
        contentsPlayerTwo.setLayoutY(440);


        //Dealers contents VBox
        VBox dealerVBox = new VBox(10, deckOfCardsD, dealerContents,dealGameBox);
        dealerVBox.setAlignment(Pos.CENTER);
        dealerVBox.setLayoutX(625);
        dealerVBox.setLayoutY(60);
        dealerVBox.setStyle("-fx-border-color: red; -fx-border-width: 1px;");




        pane.getChildren().addAll(betsPlayerOne, betsPlayerTwo, contentsPlayerOne, contentsPlayerTwo, dealerVBox, outerBorderPane);


        BorderPane rootPane = new BorderPane();
        rootPane.setTop(optionsBox);
        rootPane.setCenter(pane);


        optionsButton.setOnAction(e -> new OptionsMenu(customFont, titleSize, bodySize, playerOne, playerTwo, theDealer, primaryStage).show(primaryStage));

        rulesButton.setOnAction(e -> new RulesScreen(customFont, titleSize, bodySize).show(primaryStage));

        this.scene = new Scene(rootPane, 1500, 800);
        this.scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.scene.getRoot().getStyleClass().add("new-game-scene");

    }


    public Scene getScene() {
        return scene;
    }


    public void show(Stage primaryStage) {
        primaryStage.setScene(getScene());  // Set the scene for primaryStage
        primaryStage.show();  // Show primaryStage with the new scene
    }
}

