package interface_adapter.Controller;

import entity.Card;
import entity.ProgramState;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.CardInputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;

import java.io.IOException;

public class CardController {
    private final CardInputBoundary cardInputBoundary;
    private final IDataInOut dataInOut;
    private final ProgramState programState;

    public CardController(CardInputBoundary cardInputBoundary, IDataInOut dataInOut, ProgramState programState) {
        this.cardInputBoundary = cardInputBoundary;
        this.dataInOut = dataInOut;
        this.programState = programState;
    }

    /**
     * Methods also in CardManager.java
     */
    public Card createNewCard(String term, String definition) {
        return this.cardInputBoundary.createNewCard(term, definition);
    }

    public void changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary) throws IOException {
        if (this.cardInputBoundary.changeCardTerm(newTerm, changeOutputBoundary)) {
            dataInOut.write(this.programState, this.programState.getCurrCard());
        }
    }

    public void changeCardDefinition(String newDefinition) throws IOException {
        this.cardInputBoundary.changeCardDefinition(newDefinition);
    }

    public void increaseProficiency() {
        this.cardInputBoundary.increaseProficiency();
    }

    public void decreaseProficiency() {
        this.cardInputBoundary.decreaseProficiency();
    }

    //TODO: the following two methods may not be needed, since we have ProgramState
    public Card getCurrCard() {
        return this.cardInputBoundary.getCurrCard();
    }

    public void setCurrCard(Card card) {
        this.cardInputBoundary.setCurrCard(card);
    }
}
