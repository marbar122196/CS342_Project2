//package components;
//
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;
//import java.util.ArrayList;
//
//public class HelperStartNewGame{
//    private TextField namePlayerOne;
//    private TextField antePlayerOne;
//    private TextArea gameCommentary;
//    private ArrayList<Card> dealerHand;
//    private ImageView p1c1Image1, p1c1Image2, p1c1Image3;
//    private ImageView p2c1Image1, p2c2Image2, p2c3Image3;
//    private ImageView dc1Image1, dc2Image2, dc3Image3;
//    private Button dealGame;
//
//    public HelperStartNewGame(TextField namePlayerOne, TextField antePlayerOne, TextArea gameCommentary,
//                              ImageView p1c1Image1, ImageView p1c1Image2, ImageView p1c1Image3,
//                              ImageView p2c1Image1, ImageView p2c2Image2, ImageView p2c3Image3,
//                              ImageView dc1Image1, ImageView dc2Image2, ImageView dc3Image3,
//                              Button dealGame){
//
//        this.namePlayerOne = namePlayerOne;
//        this.antePlayerOne = antePlayerOne;
//        this.gameCommentary = gameCommentary;
//        this.p1c1Image1 = p1c1Image1;
//        this.p1c1Image2 = p1c1Image2;
//        this.p1c1Image3 = p1c1Image3;
//        this.p2c1Image1 = p2c1Image1;
//        this.p2c2Image2 = p2c2Image2;
//        this.p2c3Image3 = p2c3Image3;
//        this.dc1Image1 = dc1Image1;
//        this.dc2Image2 = dc2Image2;
//        this.dc3Image3 = dc3Image3;
//        this.dealGame = dealGame;
//    }
//
//    //to check if everything was entered before playing game
//    public void enableDeal(){
//        String name = namePlayerOne.getText();
//        String anteText = antePlayerOne.getText;
//
//        boolean hasName = !name.isEmpty();
//
//        int ante = Integer.parseInt(anteText);
//
//        if (ante < 5 || ante > 25){
//            gameCommentary.clear();
//            gameCommentary.setText("Ante too big! ");
//        }
//    }
//    //this is to reset the images to a facedown position after every round completed
//    public void resetImagesFaceDown(){
//
//        Image p1c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        p1c1Image1.setImage(p1c1);
//        Image p1c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        p1c1Image2.setImage(p1c2);
//        Image p1c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        p1c1Image3.setImage(p1c3);
//
//        Image p2c1 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        p2c1Image1.setImage(p2c1);
//        Image p2c2 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        p2c2Image2.setImage(p2c2);
//        Image p2c3 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        p2c3Image3.setImage(p2c3);
//
//        Image dc1 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        dc1Image1.setImage(dc1);
//        Image dc2 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        dc2Image2.setImage(dc2);
//        Image dc3 = new Image(getClass().getResourceAsStream("/facedown.png"));
//        dc3Image3.setImage(dc3);
//
//    }
//
//    private void revealDealersCards(){
//        dealerHand = dealer.getDeck();
//
//        //Converts PlayerOnes hands to strings to grab images
//        String dealerCardOne = "/" + dealerHand.get(0).getSuit() + " " + dealerHand.get(0).getValue() + ".png";
//        String dealerCardTwo = "/" + dealerHand.get(1).getSuit() + " " + dealerHand.get(1).getValue() + ".png";
//        String dealerCardThree = "/" + dealerHand.get(2).getSuit() + " " + dealerHand.get(2).getValue() + ".png";
//
//        Image dealerReavealedOne = new Image(getClass().getResourceAsStream(dealerCardOne));
//        Image dealerRevealedTwo = new Image(getClass().getResourceAsStream(dealerCardTwo));
//        Image dealerRevealedThree = new Image(getClass().getResourceAsStream(dealerCardThree));
//
//        //reveals dealers cards sequentially and then after reveal calls evaluate winner
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.seconds(1), event ->{
//                    dc1Image1.setImage(dealerReavealedOne);
//                }),
//                new KeyFrame(Duration.seconds(2), event ->{
//                    dc2Image2.setImage(dealerRevealedTwo);
//                }),
//                new KeyFrame(Duration.seconds(3), event ->{
//                    dc3Image3.setImage(dealerRevealedThree);
//
//                    dealGame.setDisable(false);
//                }),
//                new KeyFrame(Duration.seconds(3.5), event -> evaluateWinner())
//        );
//
//        timeline.play();
//
//    }
//
//    private void singlePlayerGame(Button playerOnePlay, Button playerOneFold, TextField p1Play){
//        revealDealersCards();
//
//        playerOnePlay.setOnAction(e -> {
//            p1Play.setText(playerOne.getAnteBet() + "");
//            playerOne.setPlayBet(playerOne.getAnteBet());
//        });
//        playerOneFold.setOnAction(e -> {
//            int p1Winnings = playerOne.getWinnings();
//            p1Winnings = p1Winnings - playerOne.getAnteBet();
//        });
//
//    }
//
//    //if two players play we need to check that both of them pressed a button
//    private void checkButtonPress(Button playerOnePlay, Button playerTwoPlay, Button playerOneFold, Button playerTwoFold, Player playerOne, Player playerTwo, TextField p1Play, TextField p2Play, ImageView d1, ImageView d2, ImageView d3, Dealer dealer, Button deal){
////        System.out.println("IN CHECK BUTTON PRESS SHOULD BE HEREEEEEEEEEEEEEEE");
//        ;
//
//        playerOnePlay.setOnAction(e -> {
//            playerOnePlay.setDisable(true);
//            playerOneFold.setDisable(true);
//            playerOnePress = true;
//            playerOnePressPlay = true;
//            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
//        });
//
//        playerTwoPlay.setOnAction(e -> {
//            playerTwoPlay.setDisable(true);
//            playerTwoFold.setDisable(true);
//            playerTwoPress = true;
//            playerTwoPressPlay = true;
//            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
//        });
//
//        playerOneFold.setOnAction(e -> {
//            playerOnePlay.setDisable(true);
//            playerOneFold.setDisable(true);
//            playerOnePress = true;
//            playerOnePressFold = true;
//            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
//        });
//
//        playerTwoFold.setOnAction(e -> {
//            playerTwoPlay.setDisable(true);
//            playerTwoFold.setDisable(true);
//            playerTwoPress = true;
//            playerTwoPressFold = true;
//            bothPlayersReady(playerOne, playerTwo, p1Play, p2Play, d1, d2, d3, dealer, deal);
//        });
//    }
//
//    //actually execute their button presses
//    private void bothPlayersReady(Player playerOne, Player playerTwo, TextField p1Play, TextField p2Play, ImageView d1, ImageView d2, ImageView d3, Dealer dealer, Button deal){
//        //checks if both players pressed a button
//        //now we have to differentiate between play and fold for both players
//        if (playerOnePress && playerTwoPress) {
//            if (playerOnePressPlay) {
//                p1Play.setText(playerOne.getAnteBet() + "");
//                playerOne.setPlayBet(playerOne.getAnteBet());
//            } else if (playerOnePressFold) {
//                int p1Winnings = playerOne.getWinnings();
//                p1Winnings = p1Winnings - playerOne.getAnteBet();
//                playerOne.setTotalWinnings(p1Winnings);
//            }
//
//            if (playerTwoPressPlay) {
//                p2Play.setText(playerOne.getAnteBet() + "");
//                playerTwo.setPlayBet(playerTwo.getAnteBet());
//            } else if (playerTwoPressFold) {
//                int p2Winnings = playerTwo.getWinnings();
//                p2Winnings = p2Winnings - playerTwo.getAnteBet();
//                playerTwo.setTotalWinnings(p2Winnings);
//            }
//
//
//            System.out.println("WAS REACHED ********************************");
//            revealDealersCards();
//
//        }
//    }
//
//    //evaluates the winner of the rounds
//    public void evaluateWinner(){
//
//        int dealHandVal = ThreeCardLogic.evalHand(dealerHand);
//        if (dealHandVal == 0) {
//            int highestCard = ThreeCardLogic.getHighest(dealerHand);
//
//            if (highestCard < 12) {
//                gameCommentary.setText("Dealer has lost! Ante will be pushed to next round.");
//                return;
//            }
//        }
//
//        int p1Hand = ThreeCardLogic.evalHand(playerOneHand);
//        int winnerP1 = ThreeCardLogic.compareHands(dealerHand, playerOneHand);
//
//        if (playerOnePressPlay && playerTwoPressPlay){
//            int p2Hand = ThreeCardLogic.evalHand(playerTwoHand);
//            int winnerP2 = ThreeCardLogic.compareHands(dealerHand, playerTwoHand);
//
//            if (winnerP1 == 2 && winnerP2 == 2){
//                gameCommentary.setText("Both players won against the dealer! :D");
//            }
//            else if (winnerP1 == 1 && winnerP2 == 2){
//                gameCommentary.setText("Player 1 lost against dealer. Congrats to Player 2!");
//            }
//            else if (winnerP1 == 2 && winnerP2 == 1){
//                gameCommentary.setText("Player 2 lost against dealer. Congrats to Player 1");
//            }
//            else if (winnerP1 == 1 && winnerP2 == 1){
//                gameCommentary.setText("Both player lost against the dealer! >:)");
//            }
//            else{
//                gameCommentary.setText("This game is a tie! :O");
//            }
//        }
//        else if ((playerOnePressPlay || playerTwoPressPlay) && (playerOnePressFold || playerTwoPressFold)){
//            int p2Hand = ThreeCardLogic.evalHand(playerTwoHand);
//            int winnerP2 = ThreeCardLogic.compareHands(dealerHand, playerTwoHand);
//
//            if (winnerP1 == 2 || winnerP2 == 2){
//                gameCommentary.setText("Player won against dealer! :D");
//            }
//            else if (winnerP1 == 1 || winnerP2 == 1){
//                gameCommentary.setText("Dealer won against player! >:)");
//            }
//            else {
//                gameCommentary.setText("This game is a tie! :O");
//            }
//        }
//
//    }
//}