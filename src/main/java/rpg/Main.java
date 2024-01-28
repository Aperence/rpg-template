package rpg;

import rpg.entities.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Entity yuusha = new PhysicEntity("Yuusha", Arrays.asList("Maou", "Imp 1", "Imp 2", "Imp 3"));
        Entity caster = new MagicEntity("Caster", "enemy");
        Entity healer = new HealerEntity("Healer", "ally");
        Entity thief = new ThiefEntity("Thief", "enemy");
        Entity maou = new PhysicEntity("Maou", Arrays.asList("Yuusha", "Caster", "Healer", "Thief"));
        Entity imp = new PhysicEntity("Imp 1", Arrays.asList("Caster", "Yuusha", "Healer", "Thief"));
        Entity imp2 = new PhysicEntity("Imp 2", Arrays.asList("Caster", "Yuusha", "Healer", "Thief"));
        Entity imp3 = new PhysicEntity("Imp 3", Arrays.asList("Caster", "Yuusha", "Healer", "Thief"));

        Group faction1 = new Group("ally", Arrays.asList(new Group("1", Arrays.asList(yuusha, caster, healer, thief))));
        Group faction2 = new Group(
                "enemy", Arrays.asList(
                        new Group("1", Arrays.asList(maou)),
                        new Group("2", Arrays.asList(imp, imp2, imp3))
                )
        );

        Group factions = new Group("battle", Arrays.asList(faction1, faction2));

        // two ways to access entities inside a group
        System.out.println(factions.getEntity("ally").getEntities());
        System.out.println(faction2.getEntities());
        /*
        LinkedList<String> l = new LinkedList<>(Arrays.asList("battle", "enemy"));
        EntityComposite temp = factions.getEntity(l);
        System.out.println(temp);
        */

        Fight fight = new Fight(factions);
        while (!fight.hasWon()){
            fight.selectActions();
            fight.play();
            fight.endOfTurn();
            System.out.println();
        }

        Group winner = fight.winner();
        System.out.println("Winner is the team: " + winner.name);
    }
}