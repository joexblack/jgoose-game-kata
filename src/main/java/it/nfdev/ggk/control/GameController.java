package it.nfdev.ggk.control;

import it.nfdev.ggk.action.GameActionProcessor;
import it.nfdev.ggk.action.GameActionData;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/*
 * Commands:
 *  add player <Name>
 *  move <player>
 */
public class GameController implements GameCommandListener {

    // Commands Map
    private Map<String, Consumer<List<String>>> commandsMap;
    
    private GameActionProcessor gameProcessor;

    private GameCommunicationChannel channel;

    public GameController(GameCommunicationChannel channel) {
        initCommandsMap();        
        this.channel = channel;
        this.gameProcessor = new GameActionProcessor();
    }

    private void initCommandsMap() {
        commandsMap = new HashMap<>();
        commandsMap.put("add player", this::addPlayerCommand);
        commandsMap.put("move", this::moveCommand);
        commandsMap.put("exit", (command) -> System.exit(0));
    }

    @Override
    public void listenCommand(String command) {
        Consumer<List<String>> commandExecutor = commandsMap.entrySet().stream()
                .filter(entry -> command.toLowerCase().startsWith(entry.getKey()))
                .map(entry -> entry.getValue())
                .findFirst().orElse(null);
        if (commandExecutor != null) {
            StringTokenizer tokenizer = new StringTokenizer(command, ", ");
            List<String> commandTokens = new LinkedList<>();
            while(tokenizer.hasMoreTokens()) { commandTokens.add(tokenizer.nextToken()); }
            commandExecutor.accept(commandTokens);
        }
    }

    private void addPlayerCommand(List<String> commandTokens) {
        if (commandTokens.size() < 3) {
            return; // Do nothing
        }
        if (GameContext.instance().playerExists(commandTokens.get(2))) {
            channel.sendExistingPlayer(commandTokens.get(2));
            return;
        }
        GameContext.instance().addPlayer(commandTokens.get(2), 1);
        channel.sendNewPlayer(commandTokens.get(2));
    }

    private void moveCommand(List<String> commandTokens) {
        if (commandTokens.size() < 2 || commandTokens.size() == 3) {
            return; // Incorrect number of parameters
        }

        String playerName = commandTokens.get(1);
        if (!GameContext.instance().playerExists(playerName)) {
            return;  // Not existing player
        }
        int dice1 = rollDice();
        int dice2 = rollDice();
        if (commandTokens.size() >= 4) {
            dice1 = Integer.parseInt(commandTokens.get(2).trim());
            dice2 = Integer.parseInt(commandTokens.get(3).trim());
        }

        executeMovingAction(playerName, dice1, dice2);
    }

    public String getAllPlayers() {
        return GameContext.instance().getAllPlayers().stream().collect(Collectors.joining(","));
    }

    private int rollDice() {
        return (int) (Math.random() * 6 + 1);
    }

    private void executeMovingAction(String playerName, int dice1, int dice2) {
        GameActionData actionData = gameProcessor.processMoveAction(playerName, dice1, dice2);        
        GameContext.instance().addPlayer(playerName, actionData.getEndPosition());
        channel.sendNewGameAction(actionData);
    }
}
