package rpg.entities;

import rpg.Fight;
import rpg.actions.Fireball;
import rpg.actions.NormalAttack;

public class MagicEntity extends Entity {

    String enemyFaction;
    public MagicEntity(String name, String enemyFaction){
        super(name);
        hp = 50;
        maxHP = hp;
        magicPower = 20;
        mp = 20;
        defense = 0;
        this.enemyFaction = enemyFaction;
    }
    @Override
    public void selectAction() {
        EntityComposite other = Fight.getInstance().factions.getEntity(enemyFaction);
        action = new Fireball(other, this);
    }
}
