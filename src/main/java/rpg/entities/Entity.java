package rpg.entities;

import rpg.DamageType;
import rpg.Fight;
import rpg.Observer;
import rpg.StdoutObserver;
import rpg.actions.Action;

public abstract class Entity {

    public String name;
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

    public abstract void selectAction();

    public void play(){
        Fight fight = Fight.getInstance();
        if (action.canExecute(fight))
            action.execute(fight);
    }
}
