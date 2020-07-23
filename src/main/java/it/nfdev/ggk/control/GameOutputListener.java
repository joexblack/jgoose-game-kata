package it.nfdev.ggk.control;

import it.nfdev.ggk.action.GameActionData;

public interface GameOutputListener {

    public void listenNewPlayer(String playerName);
    
    public void listenExistingPlayer(String playerName);
    
    public void listenNewGameAction(GameActionData gameActionData);
}
