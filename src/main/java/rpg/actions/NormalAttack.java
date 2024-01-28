package rpg.actions;

import rpg.DamageType;
import rpg.entities.Entity;
import rpg.Fight;

public class NormalAttack implements Action{

    Entity target;
    Entity issuer;


    public NormalAttack(Entity target, Entity issuer){
        this.target = target;
        this.issuer = issuer;
    }

    @Override
    public void execute(Fight g) {
        obs.signal(log());
        target.receiveHit(issuer.currStats.attack, DamageType.PHYSICAL);
    }

    @Override
    public boolean canExecute(Fight g) {
        return issuer.currStats.hp > 0 && target.currStats.hp > 0;
    }

    @Override
    public String log() {
        return issuer.name + " has launched an attack to " + target.name;
    }
}
