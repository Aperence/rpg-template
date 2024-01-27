package rpg.actions;

import rpg.DamageType;
import rpg.Observer;
import rpg.StdoutObserver;
import rpg.entities.Entity;
import rpg.Fight;
import rpg.entities.EntityComposite;

public class Fireball implements Action{

    EntityComposite target;
    Entity issuer;
    int COST = 10;
    double MULTIPLIER = 1.5;

    public Fireball(EntityComposite target, Entity issuer){
        this.target = target;
        this.issuer = issuer;
    }

    @Override
    public void execute(Fight g) {
        int dmg = (int) (issuer.magicPower * MULTIPLIER);
        obs.signal(log());
        issuer.mp -= COST;
        obs.signal(issuer.name + " has still " + issuer.mp + " mp");
        target.receiveHit(dmg, DamageType.MAGICAL);
    }

    @Override
    public boolean canExecute(Fight g) {
        if (issuer.hp > 0 && issuer.mp < COST){
            obs.signal(issuer.name + " has insufficent MPs to launch this skill");
        }
        return issuer.hp > 0 && issuer.mp >= COST && target.numberRemainingFactions() > 0;
    }

    @Override
    public String log() {
        return issuer.name + " has launched a fireball to " + target.name;
    }
}
