package rpg.actions;

import rpg.DamageType;
import rpg.Observer;
import rpg.StdoutObserver;
import rpg.entities.Entity;
import rpg.Fight;

public class Fireball implements Action{

    Entity target;
    Entity issuer;
    int COST = 0;
    double MULTIPLIER = 1.5;

    public Fireball(Entity target, Entity issuer){
        this.target = target;
        this.issuer = issuer;
    }

    @Override
    public void execute(Fight g) {
        int dmg = (int) (issuer.magicPower * MULTIPLIER);
        obs.signal(log());
        target.receiveHit(dmg, DamageType.MAGICAL);
    }

    @Override
    public boolean canExecute(Fight g) {
        return issuer.hp > 0 && issuer.mp > COST;
    }

    @Override
    public String log() {
        return issuer.name + " has launched a fireball to " + target.name;
    }
}
