import java.util.ArrayList;

public class Player{

    private ArrayList<Card> hand;
    private int playBet;
    private int pairPlusBet;
    private int anteBet;
    private int totalWinnings;

    public Player(){
        Deck gameDeck = Dealer.getDeck();
        hand = Dealer.dealPlayerHand();
    }

//    public void resetWinnings(){}
//    public boolean lockInBets(){}
//    public boolean isBetsLocked(){}
}

