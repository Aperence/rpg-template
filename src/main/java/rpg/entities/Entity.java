package rpg.entities;

import rpg.DamageType;
import rpg.Fight;
import rpg.observers.Observer;
import rpg.observers.StdoutObserver;
import rpg.actions.Action;
import rpg.alterations.Alterations;
import rpg.stats.Stats;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Entity extends EntityComposite{

    public Stats initStats;
    public Stats currStats;

    public Observer obs = new StdoutObserver();

    public List<Alterations> alterations = new LinkedList<>();

    public Action action;

    Entity(String name){
        this.name = name;
    }


    public void receiveHit(int amount, DamageType type){
        if (type == DamageType.PHYSICAL){
            amount = Math.max(0, amount - currStats.defense);
        } else if (type == DamageType.MAGICAL) {
            amount = (amount * (100 - currStats.resistance)) / (100);
        }else if (type == DamageType.TRUE){
            amount = amount;
        }
        currStats.hp = Math.max(0, currStats.hp - amount);
        obs.signal(name + " has received " + amount + " damage, HPs : " + currStats.hp + "/" + initStats.hp);
        if (currStats.hp == 0){
            obs.signal(name + " is defeated");
        }
    }

    public boolean eradicated(){
        return currStats.hp == 0;
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
        return currStats.hp > 0 ? 1 : 0;
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

    @Override
    public void endOfTurn() {
        if (eradicated()){
            alterations.clear();
            return;
        }
        List<Alterations> toRemove = new LinkedList<>();
        for (Alterations a : alterations){
            a.endOfTurn();
            if (a.expired()) toRemove.add(a);
        }
        alterations.removeAll(toRemove);
    }
}
