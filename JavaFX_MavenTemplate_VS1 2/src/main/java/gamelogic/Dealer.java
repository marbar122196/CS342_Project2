package gamelogic;

import java.util.ArrayList;

public class Dealer {

    private static Deck theDeck; // we CANNOT change signature so this cannot be static :(
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

    public void dealPlayer(Player player){
        if (theDeck.size() <= 34){
            theDeck.newDeck();
        }

        ArrayList<Card> hand = player.getHand();
        hand.clear();

        for (int i = 0; i < 3; i++){
            hand.add(theDeck.get(0));
            theDeck.remove(0);
        }
    }

    public Deck getDeck(){
        return theDeck;
    }

}