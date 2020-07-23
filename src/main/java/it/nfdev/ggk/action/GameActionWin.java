package it.nfdev.ggk.action;

import it.nfdev.ggk.conf.Constants;
import it.nfdev.ggk.control.GameMessageFactory;

public class GameActionWin extends GameActionRing {

    @Override
    protected boolean matchRule(GameActionData actionData) {
        return Constants.BOX_LAST == actionData.getEndPosition();
    }

    @Override
    protected void processAction(GameActionData actionData) {
        String message = GameMessageFactory.createWinMessage(actionData);
        actionData.setFinalPosition(true);
        actionData.setMessage(message);
    }
}
