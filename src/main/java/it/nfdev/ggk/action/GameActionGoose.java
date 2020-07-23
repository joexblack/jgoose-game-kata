package it.nfdev.ggk.action;

import it.nfdev.ggk.conf.Constants;
import it.nfdev.ggk.control.GameMessageFactory;

public class GameActionGoose extends GameActionRing {

    @Override
    protected boolean matchRule(GameActionData actionData) {
        return Constants.BOX_GOOSES.contains(actionData.getEndPosition());
    }

    @Override
    protected void processAction(GameActionData actionData) {
        int newEndPosition = actionData.getEndPosition() + actionData.getDice1() + actionData.getDice2();
        String message = null;
        if (!actionData.getMessage().isEmpty()) {
            message = GameMessageFactory.createMultipleGooseMessage(actionData, newEndPosition);
        } else {
            message = GameMessageFactory.createGooseMessage(actionData, newEndPosition);
        }
        
        actionData.setMessage(message);
        actionData.setEndPosition(newEndPosition);
        actionData.setFinalPosition(false);
    }
}
