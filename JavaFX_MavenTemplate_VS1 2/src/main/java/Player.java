import java.util.ArrayList;

public class Player{

    private ArrayList<Card> hand;
    private int playBet;
    private int pairPlusBet;
    private int anteBet;
    private int totalWinnings;

    public Player(){
        hand = new ArrayList<>();
    }

    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }

    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
    }

//    public void resetWinnings(){}
//    public boolean lockInBets(){}
//    public boolean isBetsLocked(){}
}

