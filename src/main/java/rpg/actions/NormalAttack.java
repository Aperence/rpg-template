package rpg.actions;

import rpg.DamageType;
import rpg.entities.Entity;
import rpg.Fight;
import rpg.entities.EntityComposite;

import java.util.Optional;

public class NormalAttack implements Action{

    Entity target;
    Entity issuer;

    EntityComposite enemyGroup;


    public NormalAttack(Entity target, Entity issuer, EntityComposite enemyGroup){
        this.target = target;
        this.issuer = issuer;
        this.enemyGroup = enemyGroup;
    }

    @Override
    public void execute(Fight g) {
        if (target.eradicated()){
            Optional<Entity> o = enemyGroup.any();
            if (!o.isPresent()) return;
            target = o.get();
        }
        obs.signal(log());
        target.receiveHit(issuer.currStats.attack, DamageType.PHYSICAL);
    }

    @Override
    public boolean canExecute(Fight g) {
        return issuer.currStats.hp > 0;
    }

    @Override
    public String log() {
        return issuer.name + " has launched an attack to " + target.name;
    }
}
