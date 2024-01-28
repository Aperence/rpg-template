package rpg.alterations;

import rpg.DamageType;
import rpg.entities.Entity;

public class Poison implements Alterations{


    int turns = 0;
    Entity e;
    public Poison(int turns, Entity e){
        this.turns = turns;
        this.e = e;
    }
    @Override
    public void apply() {
        int dmg = (int) (e.initStats.hp * 0.1);
        e.receiveHit(dmg, DamageType.TRUE);
    }

    @Override
    public boolean canAct() {
        return true;
    }

    @Override
    public void endOfTurn() {
        obs.signal(log());
        apply();
        turns--;
    }

    @Override
    public boolean expired() {
        if (turns <= 0){
            obs.signal("Poison expired on " + e.name);
        }
        return turns <= 0;
    }

    @Override
    public String log() {
        return e.name + " suffered from poison";
    }
}
