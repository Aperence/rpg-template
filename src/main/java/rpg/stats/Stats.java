package rpg.stats;

public class Stats {
    public int hp = 0;
    public int attack = 0;
    public int magicPower = 0;
    public int mp = 0;
    public int speed = 0;
    public int defense = 0;
    public int resistance = 0;

    public Stats(int hp, int attack, int magicPower, int mp, int speed, int defense, int resistance){
        this.hp = hp;
        this.attack = attack;
        this.magicPower = magicPower;
        this.mp = mp;
        this.speed = speed;
        this.defense = defense;
        this.resistance = resistance;
    }

    public Stats clone(){
        return new Stats(hp, attack, magicPower, mp, speed, defense, resistance);
    }
}
