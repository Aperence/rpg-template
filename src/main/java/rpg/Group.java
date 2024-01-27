package rpg;

import rpg.entities.Entity;

import java.util.List;

public class Group {
    public List<Entity> members;

    Group(List<Entity> entities){
        members = entities;
    }

    public boolean eradicated(){
        return members.stream().allMatch((e) -> e.hp == 0);
    }

    public void selectActions(){
        members.forEach(Entity::selectAction);
    }
    public void play(){
        members.forEach((m) -> m.play());
    }
}
