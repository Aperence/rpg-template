package rpg.entities;

import rpg.DamageType;
import rpg.Fight;
import rpg.Observer;
import rpg.StdoutObserver;
import rpg.actions.Action;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Entity extends EntityComposite{

    int maxHP = 0;
    public int hp = 0;
    public int attack = 0;
    public int magicPower = 0;
    public int mp = 0;
    public int speed = 0;
    public int defense = 0;
    public int resistance = 0;

    public Observer obs = new StdoutObserver();

    public Action action;

    Entity(String name){
        this.name = name;
    }


    public void receiveHit(int amount, DamageType type){
        if (type == DamageType.PHYSICAL){
            amount = Math.max(0, amount - defense);
        } else if (type == DamageType.MAGICAL) {
            amount = (amount * (100 - resistance)) / (100);
        }
        hp = Math.max(0, hp - amount);
        obs.signal(name + " has received " + amount + " damage, HPs : " + hp + "/" + maxHP);
        if (hp == 0){
            obs.signal(name + " is defeated");
        }
    }

    public boolean eradicated(){
        return hp == 0;
    }

    @Override
    public EntityComposite getEntity(String name) {
        if (this.name.equals(name)) return this;
        return null;
    }

    public EntityComposite getEntity(LinkedList<String> name){
        if (name.size() > 2 || name.isEmpty()) return null;
        if (name.get(0).equals(this.name)) return this;
        return null;
    }

    public abstract void selectAction();

    public int numberRemainingFactions(){
        return hp > 0 ? 1 : 0;
    }

    public void play(){
        Fight fight = Fight.getInstance();
        if (action.canExecute(fight))
            action.execute(fight);
    }

    public EntityComposite winner(){return this;}

    public String toString(){ return name;}

    public List<Entity> getEntities(){
        return Arrays.asList(this);
    }

    void getEntities(List<Entity> acc){
        acc.add(this);
    }
}
