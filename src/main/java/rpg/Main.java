package rpg;

import rpg.entities.Entity;
import rpg.entities.PhysicEntity;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Entity yuusha = new PhysicEntity("Yuusha", "enemy");
        Entity maou = new PhysicEntity("Maou", "ally");
        Faction faction1 = new Faction("ally", Arrays.asList(new Group(Arrays.asList(yuusha))));
        Faction faction2 = new Faction("enemy", Arrays.asList(new Group(Arrays.asList(maou))));

        Fight fight = new Fight(Arrays.asList(faction1, faction2));

        while (!fight.hasWon()){
            fight.selectActions();
            fight.play();
        }

        Faction winner = fight.winner();
        System.out.println("Winner is the team: " + winner.name);
    }
}