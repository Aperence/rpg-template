package rpg.entities;

import rpg.DamageType;

import java.util.LinkedList;
import java.util.List;

public class Group extends EntityComposite {
    public List<EntityComposite> groups;

    public Group(String name, List<EntityComposite> groups){
        this.groups = groups;
        this.name = name;
    }

    public boolean eradicated(){
        return groups.stream().allMatch(EntityComposite::eradicated);
    }

    @Override
    public EntityComposite getEntity(String name) {
        if (this.name.equals(name)) return this;
        for (EntityComposite e : groups){
            EntityComposite ret = e.getEntity(name);
            if (ret != null){
                return ret;
            }
        }
        return null;
    }

    public EntityComposite getEntity(LinkedList<String> name){
        if (name.isEmpty()) return null;
        if (!name.get(0).equals(this.name)) return null;
        if (name.size() == 1) return this;
        String temp = name.removeFirst();
        for (EntityComposite e : groups){
            EntityComposite ret = e.getEntity(name);
            if (ret != null){
                return ret;
            }
        }
        name.addFirst(temp);
        return null;
    }

    public void selectAction(){
        groups.forEach(EntityComposite::selectAction);
    }

    @Override
    public void receiveHit(int amount, DamageType type) {
        groups.forEach((g) -> g.receiveHit(amount, type));
    }

    public void play(){
        groups.forEach(EntityComposite::play);
    }

    public int numberRemainingFactions(){
        int count = 0;
        for (EntityComposite e : groups){
            if (e.numberRemainingFactions() > 0) count++;
        }
        return count;
    }

    public EntityComposite winner(){
        for (EntityComposite e : groups){
            if (e.numberRemainingFactions() > 0) return e;
        }
        return null;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(" : [");
        int last = groups.size();
        int i = 0;
        for (EntityComposite e : groups){
            builder.append(e.toString());
            i++;
            if (i != last) builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }

    public List<Entity> getEntities(){
        List<Entity> ret = new LinkedList<>();
        getEntities(ret);
        return ret;
    }

    void getEntities(List<Entity> acc){
        for (EntityComposite e : groups){
            e.getEntities(acc);
        }
    }
}
