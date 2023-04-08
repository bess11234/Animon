
public class Fishmon extends Animon{
    public Fishmon(){
        this(1);
    }
    public Fishmon(int level){
        this.level = level;
        name = "Fisho";
        hp = level*10;
        baseAtk = level*2;
        skill.put("Dancig", 1.5);
        skill.put("Buble", 2);
        skill.put("Water Gun", 3);
        skill.put("Aqua Tail", 4);
    }
}
