package it.nfdev.ggk.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameContext {

    private static GameContext instance;

    // Game Data
    private Map<String, Integer> playersMap; // Name -> Position

    private GameContext() {
        playersMap = new HashMap<>();
    }
    
    public static final GameContext instance() {
        if (instance == null) {
            synchronized(GameContext.class) {
                if (instance == null) {
                    instance = new GameContext();
                }
            }
        }
        return instance;
    }
    
    public void addPlayer(String playerName, int position) {
        playersMap.put(playerName, position);
    }
    
    public boolean playerExists(String playerName) {
        return playersMap.containsKey(playerName);
    }
    
    public Set<String> getAllPlayers() {
        return playersMap.keySet();
    }

    public int getPlayerPosition(String playerName) {
        return playersMap.get(playerName);
    }
    
    public String getPlayerInPosition(int position) {
        return playersMap.entrySet().stream()
                .filter(entry -> position == entry.getValue())
                .findFirst().get().getKey();
    }
    
    public boolean isPositionBusy(int position) {
        return playersMap.entrySet().stream()
                .filter(entry -> position == entry.getValue())
                .findFirst().orElse(null) != null;
    }

}
