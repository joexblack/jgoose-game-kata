package it.nfdev.ggk.action;

import it.nfdev.ggk.conf.Constants;
import it.nfdev.ggk.control.GameMessageFactory;

public class GameActionBounce extends GameActionRing {

    @Override
    protected boolean matchRule(GameActionData actionData) {
        return Constants.BOX_LAST < actionData.getEndPosition();
    }

    @Override
    protected void processAction(GameActionData actionData) {
        int newEndPosition = Constants.BOX_LAST - ((actionData.getDice1() + actionData.getDice2()) - (Constants.BOX_LAST - actionData.getStartPosition()));
        actionData.setEndPosition(newEndPosition);
        String message = GameMessageFactory.createBounceMessage(actionData, newEndPosition);
        actionData.setMessage(message);
    }
}
