package it.nfdev.ggk.action;

public class GameActionData {
    
    private boolean finalPosition;
    private int dice1;
    private int dice2;
    private int startPosition;
    private int endPosition;
    private String message;
    private String playerName;

    public GameActionData() {
        this.finalPosition = false;
        this.dice1 = 0;
        this.dice2 = 0;
        this.startPosition = 0;
        this.endPosition = 0;
        this.message = "";
        this.playerName = "";
    }

    public boolean isFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(boolean finalPosition) {
        this.finalPosition = finalPosition;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }    
}
