package rpg.entities;

import rpg.DamageType;
import rpg.Fight;

import java.util.LinkedList;
import java.util.List;

public abstract class EntityComposite {

    public String name = "";

    public abstract void receiveHit(int amount, DamageType type);

    public abstract void selectAction();

    public abstract void play();

    public abstract boolean eradicated();

    public abstract EntityComposite getEntity(String name);

    public abstract EntityComposite getEntity(LinkedList<String> name);

    public abstract int numberRemainingFactions();

    public abstract EntityComposite winner();

    public abstract String toString();

    public abstract List<Entity> getEntities();

    abstract void getEntities(List<Entity> acc);

    public abstract void endOfTurn();
}
