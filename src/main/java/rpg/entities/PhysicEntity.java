package rpg.entities;

import rpg.Faction;
import rpg.Fight;
import rpg.Group;
import rpg.actions.NormalAttack;

public class PhysicEntity extends Entity{

    String enemyFaction;
    public PhysicEntity(String name, String enemyFaction){
        hp = 100;
        maxHP = hp;
        attack = 20;
        defense = 2;
        this.enemyFaction = enemyFaction;
        this.name = name;
    }
    @Override
    public void selectAction() {
        Faction other = Fight.getInstance().getFaction(enemyFaction);
        Group group = other.groups.get(0);
        action = new NormalAttack(group.members.get(0), this);
    }
}
