package rpg;

import rpg.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class Faction {
    String name;
    public List<Group> groups;

    Faction(String name, List<Group> groups){
        this.groups = groups;
        this.name = name;
    }

    public boolean eradicated(){
        return groups.stream().allMatch(Group::eradicated);
    }

    public void selectActions(){
        groups.forEach(Group::selectActions);
    }
    public void play(){
        groups.forEach((g) -> g.play());
    }
}
