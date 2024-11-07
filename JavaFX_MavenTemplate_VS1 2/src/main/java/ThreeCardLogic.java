import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class ThreeCardLogic{
    private static final int[][] straight = {
            {12, 13, 14},
            {11, 12, 13},
            {10, 11, 12},
            {9, 10, 11},
            {8, 9, 10},
            {7, 8, 9},
            {6, 7, 8},
            {5, 6, 7},
            {4, 5, 6},
            {3, 4, 5},
            {2, 3, 4},
            {2, 3, 14},
    };

    public static int getHighest(ArrayList<Card> hand){
        int max = 0;

        for (int i = 0; i < 3; i++){
            if (hand.get(i).getValue() > max){
                max = hand.get(i).getValue();
            }
        }

        return max;
    }

    public static int evalHand(ArrayList<Card> hand){
        int[] combo = new int[3];
        boolean isPair = false;
        boolean isFlush = false;
        boolean isStraight = false;
        boolean isACombo = false;
        boolean matchFound = false;

        if (hand.get(0).getSuit() == hand.get(1).getSuit() && hand.get(0).getSuit() == hand.get(2).getSuit() && hand.get(1).getSuit() == hand.get(2).getSuit()){
            isFlush = true;
        }

        for (int i = 0; i < 3; i++){
            combo[i] = hand.get(i).getValue();
        }

        System.out.println("Before sort");
        System.out.println(Arrays.toString(combo));

        Arrays.sort(combo);


        System.out.println("After sort");
        System.out.println(Arrays.toString(combo));

        if (combo[0] == combo[1] && combo[0] == combo[2] && combo[1] == combo[2]){
            return 2;
        }
        else if (combo[0] == combo[1] || combo[1] == combo[2]){
            return 5;
        }

//        System.out.println("SHOULD BE HERE");
        for (int i = 0; i < straight.length; i++){
            matchFound = false;
            isACombo = true;
//            System.out.println("AND HERE");
            for (int j = 0; j < straight[i].length; j++){
                if (combo[j] == straight[i][j]){
                    if (j == 2 && isACombo){
                        matchFound = true;
//                        System.out.println(combo[j]);
//                        System.out.println(Arrays.toString(straight[i]));
//                        System.out.println(Arrays.toString(combo));
                        break;
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
            return 3;
        }
        else if (isFlush){
            return 4;
        }

        return 0;
    }

    public static int evalPPWinnings(ArrayList<Card> dealer, int bet){

        int handValue = evalHand(dealer);

        if (handValue == 1){
            return bet * 40;
        }
        if (handValue == 2){
            return bet * 30;
        }
        if (handValue == 3){
            return bet * 6;
        }
        if (handValue == 4){
            return bet * 3;
        }
        if (handValue == 5){
            return bet;
        }

        return 0;
    }


    //check this to see if we can do it differently
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){
        int playerVal = evalHand(player);
        int dealerVal = evalHand(dealer);

        if (playerVal < dealerVal){
            return 1;
        }
        else if (playerVal > dealerVal){
            return 2;
        }
        else{

            int dealerHighestCard = getHighest(dealer);
            int playersHighestCard = getHighest(player);

            if (playersHighestCard < dealerHighestCard){
                return 1;
            }
            else if (playersHighestCard > dealerHighestCard){
                return 2;
            }
            else{
               return 0;
            }
        }
    }
}