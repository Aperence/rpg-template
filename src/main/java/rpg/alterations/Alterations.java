package rpg.alterations;

import rpg.observers.Observer;
import rpg.observers.StdoutObserver;

public interface Alterations {

    Observer obs = new StdoutObserver();

    public abstract void apply();

    public abstract boolean canAct();

    public abstract void endOfTurn();

    public abstract boolean expired();

    public abstract String log();
}
