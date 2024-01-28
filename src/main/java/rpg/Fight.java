package rpg;

import rpg.entities.Entity;
import rpg.entities.EntityComposite;
import rpg.entities.Group;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
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

    public Optional<EntityComposite> winner(){
        return factions.winner();
    }

    public void selectActions(){
        factions.selectAction();
    }

    public void endOfTurn(){
        factions.endOfTurn();
    }
    public void play(){
        List<Entity> entities = factions.getEntities();
        PriorityQueue<Entity> pq = new PriorityQueue<>((a, b) -> b.currStats.speed - a.currStats.speed);
        pq.addAll(entities);
        while (!pq.isEmpty()){
            Entity e = pq.remove();
            e.play();
        }
    }
}
