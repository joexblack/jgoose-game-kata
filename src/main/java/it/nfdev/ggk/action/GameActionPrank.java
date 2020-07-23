package it.nfdev.ggk.action;

import it.nfdev.ggk.control.GameContext;
import it.nfdev.ggk.control.GameMessageFactory;

public class GameActionPrank extends GameActionRing {

    @Override
    protected boolean matchRule(GameActionData actionData) {
        return GameContext.instance().isPositionBusy(actionData.getEndPosition());
    }

    @Override
    protected void processAction(GameActionData actionData) {
        String message = GameMessageFactory.createPrankMessage(actionData);
        actionData.setMessage(message);
        actionData.setEndPosition(actionData.getStartPosition());
        actionData.setFinalPosition(true);
    }
}
