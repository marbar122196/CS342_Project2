package components;

import gamelogic.*;
import javafx.scene.control.TextArea;
import java.util.ArrayList;

public class GameLogic {
    private Player playerOne;
    private Player playerTwo;
    private Dealer dealer;
    private TextArea gameCommentary;
    private boolean playerOnePressPlay;
    private boolean playerTwoPressPlay;
    private boolean playerOnePressFold;
    private boolean playerTwoPressFold;

    public GameLogic(Player playerOne, Player playerTwo, Dealer dealer, TextArea gameCommentary) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.dealer = dealer;
        this.gameCommentary = gameCommentary;
    }

    public void dealCards(PlayerUI playerOneUI, PlayerUI playerTwoUI, DealerUI dealerUI) {
        dealer.dealPlayer(playerOne);
        dealer.dealPlayer(playerTwo);

        playerOneUI.updateCardImages(playerOne.getHand());
        playerTwoUI.updateCardImages(playerTwo.getHand());
        dealerUI.resetDealerCards();
    }

    public void setPlayerOnePressPlay(boolean pressPlay) {
        playerOnePressPlay = pressPlay;
    }

    public void setPlayerTwoPressPlay(boolean pressPlay) {
        playerTwoPressPlay = pressPlay;
    }

    public void setPlayerOnePressFold(boolean pressFold) {
        playerOnePressFold = pressFold;
    }

    public void setPlayerTwoPressFold(boolean pressFold) {
        playerTwoPressFold = pressFold;
    }

    public boolean isReadyToEvaluate() {
        return (playerOnePressPlay || playerOnePressFold) && (playerTwoPressPlay || playerTwoPressFold);
    }

    public void evaluateWinner() {
        ArrayList<Card> dealerHand = dealer.getHand();
        ArrayList<Card> playerOneHand = playerOne.getHand();
        ArrayList<Card> playerTwoHand = playerTwo.getHand();

        int dealHandVal = ThreeCardLogic.evalHand(dealerHand);
        if (dealHandVal == 0) {
            int highestCard = ThreeCardLogic.getHighest(dealerHand);

            if (highestCard < 12) {
                gameCommentary.setText("Dealer has lost! Ante will be pushed to next round.");
                return;
            }
        }

        int winnerP1 = ThreeCardLogic.compareHands(dealerHand, playerOneHand);
        int winnerP2 = ThreeCardLogic.compareHands(dealerHand, playerTwoHand);

        if (playerOnePressPlay && playerTwoPressPlay) {
            if (winnerP1 == 2 && winnerP2 == 2) {
                gameCommentary.setText("Both players won against the dealer! :D");
            }
            else if (winnerP1 == 1 && winnerP2 == 2) {
                gameCommentary.setText("Player 1 lost against dealer. Congrats to Player 2!");
            }
            else if (winnerP1 == 2 && winnerP2 == 1) {
                gameCommentary.setText("Player 2 lost against dealer. Congrats to Player 1");
            }
            else if (winnerP1 == 1 && winnerP2 == 1) {
                gameCommentary.setText("Both players lost against the dealer! >:)");
            }
            else {
                gameCommentary.setText("This game is a tie! :O");
            }
        }
        else if ((playerOnePressPlay || playerTwoPressPlay) && (playerOnePressFold || playerTwoPressFold)) {
            if (winnerP1 == 2 || winnerP2 == 2) {
                gameCommentary.setText("Player won against dealer! :D");
            }
            else if (winnerP1 == 1 || winnerP2 == 1) {
                gameCommentary.setText("Dealer won against player! >:)");
            }
            else {
                gameCommentary.setText("This game is a tie! :O");
            }
        }
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Dealer getDealer(){
        return dealer;
    }
}
