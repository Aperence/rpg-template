package rpg.actions;

import rpg.DamageType;
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
        int dmg = (int) (issuer.currStats.magicPower * MULTIPLIER);
        obs.signal(log());
        issuer.currStats.mp -= COST;
        obs.signal(issuer.name + " has still " + issuer.currStats.mp + " mp");
        target.receiveHit(dmg, DamageType.MAGICAL);
    }

    @Override
    public boolean canExecute(Fight g) {
        if (issuer.currStats.hp > 0 && issuer.currStats.mp < COST){
            obs.signal(issuer.name + " has insufficent MPs to launch this skill");
        }
        return issuer.currStats.hp > 0 && issuer.currStats.mp >= COST && target.numberRemainingFactions() > 0;
    }

    @Override
    public String log() {
        return issuer.name + " has launched a fireball to " + target.name;
    }
}
