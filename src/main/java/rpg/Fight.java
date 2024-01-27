package rpg;

import java.util.List;
import java.util.stream.Collectors;

public class Fight {
    public List<Faction> factions;
    public static Fight instance;

    Fight(List<Faction> factions){
        this.factions = factions;
        instance = this;
    }

    public static Fight getInstance(){return instance;}

    public Faction getFaction(String name){
        return factions.stream().filter((f) -> f.name.equals(name)).collect(Collectors.toList()).get(0);
    }

    public boolean hasWon(){
        return factions.stream().filter((f) -> !f.eradicated()).collect(Collectors.toList()).size() <= 1;
    }

    public Faction winner(){
        return factions.stream().filter((f) -> !f.eradicated()).collect(Collectors.toList()).get(0);
    }

    public void selectActions(){
        factions.forEach(Faction::selectActions);
    }
    public void play(){
        factions.forEach(Faction::play);
    }
}
