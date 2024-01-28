package rpg.actions;

import rpg.Fight;
import rpg.entities.Entity;


public class Pass implements Action{

    Entity issuer;
    public Pass(Entity issuer){
        this.issuer = issuer;
    }
    @Override
    public void execute(Fight g) {
        obs.signal(log());
    }

    @Override
    public boolean canExecute(Fight g) {
        return true;
    }

    @Override
    public String log() {
        return issuer.name + " passed turn";
    }
}
