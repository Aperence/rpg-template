package rpg.entities;

import rpg.Fight;
import rpg.actions.Heal;
import rpg.stats.Stats;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HealerEntity extends Entity{

    String allyGroup;
    public HealerEntity(String name, String allyGroup) {
        super(name);
        initStats = new Stats(20, 0, 10, 100, 1, 0, 40);
        currStats = initStats.clone();
        this.allyGroup = allyGroup;
    }

    @Override
    public void selectAction() {
        EntityComposite allies = Fight.getInstance().factions.getEntity(allyGroup);
        List<Entity> entities = allies.getEntities();
        PriorityQueue<Entity> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.currStats.hp));
        pq.addAll(entities);
        Entity healed = pq.remove();
        action = new Heal(healed, this);
    }
}
