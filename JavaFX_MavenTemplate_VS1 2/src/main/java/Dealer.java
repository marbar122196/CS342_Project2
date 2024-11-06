import java.util.ArrayList;

public class Dealer {

    private Deck theDeck;
    private ArrayList<Card> dealersHand;

    public Dealer(){
        theDeck = new Deck();
        dealersHand = new ArrayList<>();
    }

    public ArrayList<Card> dealHand() {
        if (theDeck.size() <= 34){
            theDeck.newDeck();
        }

        dealersHand.clear();

        for (int i = 0; i < 3; i++){
            dealersHand.add(theDeck.get(0));
            theDeck.remove(0);
        }

        return dealersHand;
    }

    public static ArrayList<Card> dealPlayerHand(){
        //should I add a check here for the amount of cards? or just leave it. ask coda

        ArrayList<Card> playerHand = new ArrayList<>)();

        for (int i = 0; i < 3; i++){
            playerHand.add(theDeck.get(0));
            theDeck.remove(0);
        }

        return playerHand;
    }

}