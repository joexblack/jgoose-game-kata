package it.nfdev.ggk.control;

import it.nfdev.ggk.action.GameActionData;
import java.util.Set;
import java.util.stream.Collectors;

public class GameMessageFactory {

    public static String createNewPlayerMessage(Set<String> playersSet) {
        return String.format("players: %s", playersSet.stream().collect(Collectors.joining(", ")));
    }

    public static String createExistingPlayerMessage(String playerName) {
        return String.format("%s: already existing player", playerName);
    }

    public static String createWinMessage(GameActionData gameActionData) {
        return String.format("%s rolls %d, %d. %s moves from %d to %d. %s Wins!!!",
                gameActionData.getPlayerName(),
                gameActionData.getDice1(),
                gameActionData.getDice2(),
                gameActionData.getPlayerName(),
                gameActionData.getStartPosition(),
                gameActionData.getEndPosition(),
                gameActionData.getPlayerName());
    }

    public static String createGooseMessage(GameActionData gameActionData, int newEndPosition) {
        return String.format("%s rolls %d, %d. %s moves from %d to %d, The Goose. %s moves again to %d",
                gameActionData.getPlayerName(),
                gameActionData.getDice1(),
                gameActionData.getDice2(),
                gameActionData.getPlayerName(),
                gameActionData.getStartPosition(),
                gameActionData.getEndPosition(),
                gameActionData.getPlayerName(),
                newEndPosition);
    }

    public static String createMultipleGooseMessage(GameActionData gameActionData, int newEndPosition) {
        String original = gameActionData.getMessage();
        String toAddMessage = String.format(", The Goose. %s moves again and goes to %d", gameActionData.getPlayerName(), newEndPosition);
        return original + toAddMessage;
    }

    public static String createMoveMessage(GameActionData gameActionData) {
        String startPosition = gameActionData.getStartPosition() == 0 ? "Start" : String.valueOf(gameActionData.getStartPosition());
        String message = String.format("%s rolls %d, %d. %s moves from %s to %d",
                gameActionData.getPlayerName(),
                gameActionData.getDice1(),
                gameActionData.getDice2(),
                gameActionData.getPlayerName(),
                startPosition,
                gameActionData.getEndPosition());
        return message;
    }
    
    public static String createBounceMessage(GameActionData gameActionData, int newEndPosition) {
        return String.format("%s rolls %d, %d. %s moves from %d to 63. %s bounces! %s returns to %d",   
                gameActionData.getPlayerName(),
                gameActionData.getDice1(),
                gameActionData.getDice2(),
                gameActionData.getPlayerName(),
                gameActionData.getStartPosition(),
                gameActionData.getPlayerName(),
                gameActionData.getPlayerName(),
                newEndPosition);
    }

    public static String createBridgeMessage(GameActionData gameActionData) {
        return String.format("%s rolls %d, %d. %s moves from %d to The Bridge. %s jumps to %d", 
                gameActionData.getPlayerName(),
                gameActionData.getDice1(),
                gameActionData.getDice2(),
                gameActionData.getPlayerName(),
                gameActionData.getStartPosition(),
                gameActionData.getPlayerName(),
                gameActionData.getEndPosition());
    }

    public static String createPrankMessage(GameActionData gameActionData) {
        String prankPlayer = GameContext.instance().getPlayerInPosition(gameActionData.getEndPosition());
        return String.format("%s rolls %d, %d. %s moves from %d to %d. On %d there is %s, who returns to %d",                 
                gameActionData.getPlayerName(),
                gameActionData.getDice1(),
                gameActionData.getDice2(),
                gameActionData.getPlayerName(),
                gameActionData.getStartPosition(),
                gameActionData.getEndPosition(),
                gameActionData.getEndPosition(),
                prankPlayer,
                gameActionData.getStartPosition());
    }

}
