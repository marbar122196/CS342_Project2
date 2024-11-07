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

    public Deck getDeck(){
        return theDeck;
    }

    public ArrayList<Card> dealPlayerHand(){
        //should I add a check here for the amount of cards? or just leave it. ask coda

        ArrayList<Card> playerHand = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            playerHand.add(theDeck.get(0));
            theDeck.remove(0);
        }

        return playerHand;
    }

    public void setPlayerHand(Player player){
        player.setHand(dealPlayerHand());
    }

}