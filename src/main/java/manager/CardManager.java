package manager;

import entity.Card;
import entity.Pack;
import input_boundaries.CardInputBoundary;

import java.util.HashMap;

/**
 * A CardManager contains all cards in the system.
 */
public class CardManager extends Manager<Card> implements CardInputBoundary {
    private Card currCard = null; // Initialize to the state where the user is not in a card
    public CardManager() {
        super();
    }

    /**
     * Create and return a new card with defined term and definition.
     * Stores id and the corresponding card in idToItem.
     *
     * @param term       The term of the card
     * @param definition The definition of the term
     * @return           The newly-created card
     */
    public Card createNewCard(String term, String definition) {
        String id = generateId();
        Card c = new Card(id, term, definition);
        this.idToItem.put(id, c);
        return c;
    }

    /**
     * Change the card's term to a new term specified by user.
     * @param newTerm the new term the card should change to
     */
    public void editCardTerm(String newTerm) {
        this.currCard.setTerm(newTerm);
    }

    /**
     * Change the card's definition to a new definition specified by user.
     * @param newDefinition the new definition the card should change to
     */
    public void editCardDefinition(String newDefinition) {
        this.currCard.setDefinition(newDefinition);
    }

    /**
     * Increase the proficiency of the card by 1.
     */
    public void increaseProficiency() {
        this.currCard.setProficiency(Math.min(this.currCard.getProficiency() + 1, 5));
    }

    /**
     * Decrease the proficiency of the card by 1.
     */
    public void decreaseProficiency() {
        this.currCard.setProficiency(Math.max(this.currCard.getProficiency() - 1, 1));
    }

    /**
     * Getter for the current pack the user is in.
     * @return the current pack.
     */
    public Card getCurrCard() {
        return this.currCard;
    }

    /**
     * Change to the current card the user is in.
     */
    public void setCurrCard(Card card) {
        this.currCard = card;
    }
}
