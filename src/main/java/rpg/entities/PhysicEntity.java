package rpg.entities;

import rpg.Fight;
import rpg.actions.NormalAttack;

import java.util.LinkedList;
import java.util.List;

public class PhysicEntity extends Entity{

    LinkedList<String> enemyFaction = new LinkedList<>();
    public PhysicEntity(String name, String enemyFaction){
        super(name);
        hp = 100;
        maxHP = hp;
        attack = 20;
        defense = 2;
        this.enemyFaction.add(enemyFaction);
    }

    public PhysicEntity(String name, List<String> enemyFaction){
        super(name);
        hp = 100;
        maxHP = hp;
        attack = 20;
        defense = 2;
        this.enemyFaction.addAll(enemyFaction);
    }
    @Override
    public void selectAction() {
        while (!enemyFaction.isEmpty()){
            String temp = enemyFaction.getFirst();
            Entity other = (Entity) Fight.getInstance().factions.getEntity(temp);
            if (!other.eradicated()){
                action = new NormalAttack(other, this);
                return;
            }
            enemyFaction.removeFirst();
        }
    }
}
