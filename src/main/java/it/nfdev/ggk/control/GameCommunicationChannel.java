package it.nfdev.ggk.control;

import it.nfdev.ggk.action.GameActionData;
import java.util.LinkedList;
import java.util.List;

public class GameCommunicationChannel {
        
    private List<GameCommandListener> commandListeners;
    private List<GameOutputListener> outputListeners;
    
    public GameCommunicationChannel() {
        commandListeners = new LinkedList<>();
        outputListeners = new LinkedList<>();
    }
    
    public void addCommandListener(GameCommandListener listener) {
        commandListeners.add(listener);
    }
    
    public void addOutputListener(GameOutputListener listener) {
        outputListeners.add(listener);
    }
        
    public void sendCommand(String command) {
        commandListeners.forEach(listener -> listener.listenCommand(command));
    }
    
    public void sendNewPlayer(String playerName) {
        outputListeners.forEach(listener -> listener.listenNewPlayer(playerName));
    }
    
    public void sendExistingPlayer(String playerName) {
        outputListeners.forEach(listener -> listener.listenExistingPlayer(playerName));
    }
    
    public void sendNewGameAction(GameActionData gameActionData) {
        outputListeners.forEach(listener -> listener.listenNewGameAction(gameActionData));
    }

}
