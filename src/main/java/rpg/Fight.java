package rpg;

import rpg.entities.EntityComposite;
import rpg.entities.Group;

import java.util.List;
import java.util.stream.Collectors;

public class Fight {
    public Group factions;
    public static Fight instance;

    Fight(Group factions){
        this.factions = factions;
        instance = this;
    }

    public static Fight getInstance(){return instance;}

    public boolean hasWon(){
        return factions.numberRemainingFactions() <= 1;
    }

    public Group winner(){
        return (Group) factions.winner();
    }

    public void selectActions(){
        factions.selectAction();
    }

    public void endOfTurn(){
        factions.endOfTurn();
    }
    public void play(){
        factions.play();
    }
}
