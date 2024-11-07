import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class ThreeCardLogic{
    private final String[][] straight = {
            {"14", "13", "12"},
            {"13", "12", "11"},
            {"12", "11", "10"},
            {"11", "10", "9"},
            {"10", "9", "8"},
            {"9", "8", "7"},
            {"8", "7", "6"},
            {"7", "6", "5"},
            {"6", "5", "4"},
            {"5", "4", "3"},
            {"4", "3", "2"},
            {"14", "3", "2"},
    };

    public static int evalHand(ArrayList<Card> hand){
        int[] combo = new int[3];
        boolean isPair = false;
        boolean isFlush = false;
        boolean isStraight = false;
        boolean isACombo = false;
        boolean matchFound = false;

        if (hand.get(0).getSuit().equals(hand.get(1).getSuit()) && hand.get(0).getSuit().equals(hand.get(2).getSuit()) && hand.get(1).getSuit().equals(hand.get(2).getSuit())){
            isFlush = true;
        }

        for (int i = 0; i < 3; i++){
            combo[i] = hand.get(i).getValue();
        }

        Arrays.sort(combo);

        if (combo[0] == combo[1] && combo[0] == combo[2] && combo[1] == combo[2]){
            return 2;
        }
        else if (combo[0] == combo[1] || combo[1] == combo[2]){
            return 5;
        }


        for (int i = 0; i < straight.length; i++){
            for (int j = 0; j < straight[i].length; j++){
                if (combo[j] == straight[i][j]){
                    if (j == 2 && isACombo){
                        matchFound = true;
                        break;
                    }
                    else{
                        isACombo = true;
                    }
                }
                else{
                    isACombo = false;
                }
            }
            if (matchFound){
                break;
            }
        }

        if (matchFound && isFlush){
            return 1;
        }
        else if (matchFound) {
            return 4;
        }

    }
//    public static int evalPPWinnings(ArrayList<Card> dealer, int bet){}
//    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){}
}