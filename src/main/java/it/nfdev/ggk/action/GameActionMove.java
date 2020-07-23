package it.nfdev.ggk.action;

import it.nfdev.ggk.control.GameMessageFactory;

public class GameActionMove extends GameActionRing {

    @Override
    protected boolean matchRule(GameActionData actionData) {
        return true;
    }

    @Override
    protected void processAction(GameActionData actionData) {
        actionData.setFinalPosition(true);
        if (actionData.getMessage().isEmpty()) {
            actionData.setMessage(GameMessageFactory.createMoveMessage(actionData));
        }
    }
}
