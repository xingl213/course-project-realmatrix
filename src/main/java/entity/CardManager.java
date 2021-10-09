package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire system of Cards
 */
public class CardManager {
    private List<Card> cards;

    /**
     * Creates a CardManager with lists of Card that are empty
     */
    public CardManager() {
        cards = new ArrayList<>();
    }

    /**
     * Adds an instance of Card to the overall list of Cards
     * @param id ID of the Card
     * @param term Term that Card contains
     * @param definition Definition of the Term that Card contains
     * @param proficiency  Proficiency of the Term that Card contains
     */
    public void addCard(String id, String term, String definition, int proficiency) {
        Card c = new Card(id, term, definition, proficiency);
        cards.add(c);
    }
    // We can generate id here?

    /**
     * Update proficiencies of all Card in the system.
     * @param change_value The value we want to change to Proficiency.
     */
    public void updateAllProficiency(int change_value){
        for (Card c : cards) {
            c.changeProficiency(change_value);
        }
    }

    /**
     * Represents a CardManager as a String containing all Card term and definition in the system.
     * @return a list of card terms and definitions, separated by comma.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Card c : cards) {
            res.append(c.toString());
            res.append(", ");
        }
        return res.toString(); //includes a trailing ", "
    }
}
