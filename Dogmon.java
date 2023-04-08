
public class Dogmon extends Animon{
    public Dogmon(){
        this(1);
    }
    public Dogmon(int level){
        this.level = level;
        name = "Tomleng";
        hp = level*10;
        baseAtk = level*2;
        skill.put("Bark", 1.5);
        skill.put("Bite", 2);
        skill.put("Headbud", 3);
        skill.put("Quick Attack", 4);
    }
}
