package rpg.entities;

import rpg.Fight;
import rpg.actions.Fireball;
import rpg.actions.NormalAttack;
import rpg.stats.Stats;

public class MagicEntity extends Entity {

    String enemyFaction;
    public MagicEntity(String name, String enemyFaction){
        super(name);
        initStats = new Stats(50, 0, 20, 20, 1, 0, 10);
        currStats = initStats.clone();
        this.enemyFaction = enemyFaction;
    }
    @Override
    public void selectAction() {
        EntityComposite other = Fight.getInstance().factions.getEntity(enemyFaction);
        action = new Fireball(other, this);
    }
}
