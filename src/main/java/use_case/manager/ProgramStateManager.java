package use_case.manager;

import entity.Card;
import entity.Pack;
import entity.ProgramState;
import entity.User;
import use_case.input_boundaries.ProgramStateInputBoundary;

import java.util.HashMap;

/**
 * A manager class for program state.
 * Note, we only create **one instance** of it throughout the entire program.
 */
public class ProgramStateManager implements ProgramStateInputBoundary {
    private final ProgramState ps;

    public ProgramStateManager() {
        ps = new ProgramState();
    }

    @Override
    public User getCurrUser() {
        return ps.getCurrUser();
    }

    @Override
    public Pack getCurrPack() {
        return ps.getCurrPack();
    }

    @Override
    public Card getCurrCard() {
        return ps.getCurrCard();
    }

    @Override
    public void setCurrUser(User user) {
        ps.setCurrUser(user);
    }

    @Override
    public void setCurrPack(String packname) {
        // Know: ps.currUser is not null
        HashMap<String, Pack> packMap = ps.getCurrUser().getPackageMap();
        ps.setCurrPack(packMap.get(packname));
    }

    @Override
    public void setCurrCard(String cardTerm) {
        // Know: ps.currPack is not null
        HashMap<String, Card> cardMap = ps.getCurrPack().getCardMap();
        ps.setCurrCard(cardMap.get(cardTerm));
    }
}
