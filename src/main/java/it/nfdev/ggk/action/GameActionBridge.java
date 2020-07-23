package it.nfdev.ggk.action;

import it.nfdev.ggk.conf.Constants;
import it.nfdev.ggk.control.GameMessageFactory;

public class GameActionBridge extends GameActionRing {

    @Override
    protected boolean matchRule(GameActionData actionData) {
        return Constants.BOX_BRIDGE_START == actionData.getEndPosition();
    }

    @Override
    protected void processAction(GameActionData actionData) {
        actionData.setEndPosition(Constants.BOX_BRIDGE_END);
        String message = GameMessageFactory.createBridgeMessage(actionData);
        actionData.setMessage(message);
    }
}
