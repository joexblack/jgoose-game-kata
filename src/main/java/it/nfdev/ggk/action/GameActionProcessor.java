package it.nfdev.ggk.action;

import it.nfdev.ggk.conf.Application;
import it.nfdev.ggk.conf.Constants;
import it.nfdev.ggk.control.GameContext;
import java.util.LinkedList;
import java.util.List;

public class GameActionProcessor {

    // Move Actions
    private List<GameActionRing> moveActionsChain;

    public GameActionProcessor() {
        initMoveActionChain();
    }

    public GameActionData processMoveAction(String playerName, int dice1, int dice2) {
        int currentPosition = GameContext.instance().getPlayerPosition(playerName);
        GameActionData actionData = new GameActionData();
        actionData.setPlayerName(playerName);
        actionData.setDice1(dice1);
        actionData.setDice2(dice2);
        actionData.setStartPosition(currentPosition);
        actionData.setEndPosition(currentPosition + dice1 + dice2);
        while (!actionData.isFinalPosition()) {
            moveActionsChain.get(0).doAction(actionData);
        }
        return actionData;
    }

    private void initMoveActionChain() {
        moveActionsChain = new LinkedList<>();
        for (int i = 1; true; i++) {
            String actionClazzName = Application.instance().getProperty(Constants.KEY_GAME_ACTION_CLAZZ_PREFIX + i);
            if (actionClazzName == null) {
                break;
            }
            try {
                Class<?> actionClazz = Class.forName(actionClazzName);
                GameActionRing ring = (GameActionRing) actionClazz.newInstance();
                moveActionsChain.add(ring);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Error on Loading Move Actions", e);
            }
        }

        for (int i = 0; i < moveActionsChain.size() - 1; i++) {
            moveActionsChain.get(i).setNextRing(moveActionsChain.get(i + 1));
        }
    }
}
