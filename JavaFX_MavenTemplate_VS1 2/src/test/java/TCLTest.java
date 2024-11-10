import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

import gamelogic.*;
import components.*;

public class TCLTest {

    @Test
    public void testThreeOfAKind(){
        ArrayList<Card> hand = new ArrayList<>();

        Card first = new Card('H', 10);
        hand.add(first);
        Card second = new Card('D', 10);
        hand.add(second);
        Card third = new Card('C', 10);
        hand.add(third);

        assertEquals(2, ThreeCardLogic.evalHand(hand), "should be three of a kind value (2)");
    }

    @Test
    public void testStraightFlush(){
        ArrayList<Card> hand = new ArrayList<>();

        Card first = new Card('H', 14);
        hand.add(first);
        Card second = new Card('H', 13);
        hand.add(second);
        Card third = new Card('H', 12);
        hand.add(third);

        assertEquals(1, ThreeCardLogic.evalHand(hand), "should be a straight flush");
    }

    @Test
    public void testAnotherStraightFlush(){
        ArrayList<Card> hand = new ArrayList<>();

        Card first = new Card('S', 5);
        hand.add(first);
        Card second = new Card('S', 4);
        hand.add(second);
        Card third = new Card('S', 3);
        hand.add(third);

        assertEquals(1, ThreeCardLogic.evalHand(hand), "should be a straight flush");
    }

    @Test
    public void testPair(){
        ArrayList<Card> hand = new ArrayList<>();

        Card first = new Card('S', 5);
        hand.add(first);
        Card second = new Card('D', 5);
        hand.add(second);
        Card third = new Card('S', 3);
        hand.add(third);

        assertEquals(5, ThreeCardLogic.evalHand(hand), "should be recognized as a pair");
    }

    @Test
    public void testFlush(){
        ArrayList<Card> hand = new ArrayList<>();

        Card first = new Card('H', 6);
        hand.add(first);
        Card second = new Card('H', 5);
        hand.add(second);
        Card third = new Card('H', 2);
        hand.add(third);

        assertEquals(4, ThreeCardLogic.evalHand(hand), "should be recognized as a flush");
    }

    @Test
    public void testStraight(){
        ArrayList<Card> hand = new ArrayList<>();
        System.out.println("IN THIS TEST");
        Card first = new Card('H', 4);
        hand.add(first);
        Card second = new Card('D', 5);
        hand.add(second);
        Card third = new Card('S', 3);
        hand.add(third);

        assertEquals(3, ThreeCardLogic.evalHand(hand), "should be recognized as a straight even if unordered");
    }

    @Test
    public void testHighCard(){
        ArrayList<Card> hand = new ArrayList<>();

        Card first = new Card('H', 2);
        hand.add(first);
        Card second = new Card('D', 6);
        hand.add(second);
        Card third = new Card('S', 12);
        hand.add(third);

        assertEquals(0, ThreeCardLogic.evalHand(hand), "should be recognized as a high card");
    }
}