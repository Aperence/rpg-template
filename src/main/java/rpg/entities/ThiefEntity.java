package rpg.entities;

import rpg.Fight;
import rpg.actions.Heal;
import rpg.actions.Pass;
import rpg.actions.PoisonAttack;
import rpg.alterations.Poison;
import rpg.stats.Stats;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ThiefEntity extends Entity{

    String enemyGroup;
    public ThiefEntity(String name, String enemyGroup) {
        super(name);
        initStats = new Stats(70, 10, 5, 0, 10, 5, 15);
        currStats = initStats.clone();
        this.enemyGroup = enemyGroup;
    }

    @Override
    public void selectAction() {
        EntityComposite allies = Fight.getInstance().factions.getEntity(enemyGroup);
        List<Entity> entities = allies.getEntities();
        Optional<Entity> poisoned  =
                entities.stream()
                .filter((e) -> !e.eradicated() && e.alterations.stream().noneMatch((a) -> a instanceof Poison))
                .findFirst();
        if (poisoned.isPresent()){
            action = new PoisonAttack(poisoned.get(), this);
        }else{
            action = new Pass(this);
        }
    }
}
