
public class Dogmon extends Animon{
    public Dogmon(int level){
        this.level = level;
        hp = level*6;
        baseAtk = level*2;
        skill.put("Bark", 1.5);
        skill.put("Bite", 2);
        skill.put("Headbud", 3);
        skill.put("Quick Attack", 4);
    }
}
