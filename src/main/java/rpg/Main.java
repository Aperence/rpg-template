package rpg;

import rpg.entities.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Entity yuusha = new PhysicEntity("Yuusha", Arrays.asList("Maou", "Imp"));
        Entity caster = new MagicEntity("Caster", "enemy");
        Entity maou = new PhysicEntity("Maou", Arrays.asList("Yuusha", "Caster"));
        Entity imp = new PhysicEntity("Imp", Arrays.asList("Caster", "Yuusha"));

        Group faction1 = new Group("ally", Arrays.asList(new Group("1", Arrays.asList(yuusha, caster))));
        Group faction2 = new Group(
                "enemy", Arrays.asList(
                        new Group("1", Arrays.asList(maou)),
                        new Group("2", Arrays.asList(imp))
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
            System.out.println();
        }

        Group winner = fight.winner();
        System.out.println("Winner is the team: " + winner.name);
    }
}