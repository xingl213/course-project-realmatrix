package interface_adapter.Controller;

import entity.Card;
import entity.Pack;
import entity.ProgramState;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import use_case.generator.ReviewGenerator;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;

import java.io.IOException;
import java.util.ArrayList;

public class ReviewController {
    private final ReviewInputBoundary reviewIB;
    private final IDataInOut dataInOut = new DataInOut();
    private final ProgramState programState;

    public ReviewController(ReviewInputBoundary reviewIB, ProgramState programState) {
        this.reviewIB = reviewIB;
        this.programState = programState;
    }

    public void next() throws IOException {
        // before going to the next card, update current card's proficiency in database
        Card currCard = reviewIB.getCurrCard();
        if (currCard != null) {
            dataInOut.write(new String[] {programState.getCurrUser().getName(), programState.getCurrPack().getName()},
                    reviewIB.getCurrCard());
        }
        reviewIB.next();
    }

    public void setCantRecall() {
        reviewIB.setCantRecall();
    }

    public void setShowDefinition() {
        reviewIB.setShowDefinition();
    }
}
