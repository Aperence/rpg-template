package rpg.actions;

import rpg.Fight;
import rpg.alterations.Poison;
import rpg.entities.Entity;

public class PoisonAttack implements Action {

    Entity target;
    Entity issuer;
    public PoisonAttack(Entity target, Entity issuer){
        this.target = target;
        this.issuer = issuer;
    }
    @Override
    public void execute(Fight g) {
        target.alterations.add(new Poison(3, target));
        obs.signal(log());
    }

    @Override
    public boolean canExecute(Fight g) {
        return !target.eradicated() && target.alterations.stream().noneMatch((a) -> a instanceof Poison);
    }

    @Override
    public String log() {
        return issuer.name + " applied poison to " + target.name;
    }
}
