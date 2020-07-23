package it.nfdev.ggk.action;

abstract class GameActionRing {
        
    private GameActionRing nextRing;

    public void setNextRing(GameActionRing nextRing) {
        this.nextRing = nextRing;
    }
    
    public void doAction(GameActionData actionData) {
        if (matchRule(actionData)) {
            processAction(actionData);
        } else if (nextRing != null) {
            nextRing.doAction(actionData);
        }
    }
        
    protected abstract boolean matchRule(GameActionData actionData);
    
    protected abstract void processAction(GameActionData actionData);
}
