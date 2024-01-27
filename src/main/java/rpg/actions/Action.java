package rpg.actions;

import rpg.Fight;
import rpg.Observer;
import rpg.StdoutObserver;

public interface Action {

    Observer obs = new StdoutObserver();

    public void execute(Fight g);

    public boolean canExecute(Fight g);

    public String log();
}
