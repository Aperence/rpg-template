package rpg.entities;

import rpg.Fight;
import rpg.actions.NormalAttack;
import rpg.stats.Stats;

import java.util.LinkedList;
import java.util.List;

public class PhysicEntity extends Entity{

    String enemy;
    String enemyFaction;
    public PhysicEntity(String name, String enemy, String enemyFaction){
        super(name);
        initStats = new Stats(100, 10, 0, 0, 5, 2, 0);
        currStats = initStats.clone();
        this.enemyFaction = enemyFaction;
        this.enemy = enemy;
    }

    @Override
    public void selectAction() {
        Entity other = (Entity) Fight.getInstance().factions.getEntity(enemy);
        EntityComposite group = Fight.getInstance().factions.getEntity(enemyFaction);
        action = new NormalAttack(other, this, group);
    }
}
