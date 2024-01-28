package rpg.actions;

import rpg.Fight;
import rpg.entities.Entity;

public class Heal implements Action{

    Entity target;
    Entity issuer;
    int cost = 20;
    public Heal(Entity target, Entity issuer){
        this.target = target;
        this.issuer = issuer;
    }
    @Override
    public void execute(Fight g) {
        issuer.currStats.mp -= cost;
        target.currStats.hp = Math.min(target.initStats.hp, target.currStats.hp + cost);
        obs.signal(log());
    }

    @Override
    public boolean canExecute(Fight g) {
        if (issuer.currStats.hp > 0 && issuer.currStats.mp < cost)
            obs.signal(issuer.name + " has insufficient mps to launch heal");
        return issuer.currStats.hp > 0
                && issuer.currStats.mp >= cost
                && target.currStats.hp > 0;
    }

    @Override
    public String log() {
        return issuer.name + " healed " + cost + " hps from " + target.name + " : hps = " + target.currStats.hp + "/" + target.initStats.hp;
    }
}
