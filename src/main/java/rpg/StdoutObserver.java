package rpg;

public class StdoutObserver implements Observer{
    @Override
    public void signal(String event) {
        System.out.println(event);
    }
}
