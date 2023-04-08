
public class Birdmon extends Animon{
    public Birdmon(){
        this(1);
    }
    public Birdmon(int level){
        this.level = level;
        name = "KFC";
        hp = level*7;
        baseAtk = level*4;
        skill.put("Headbud", 1.5);
        skill.put("Pinch", 2);
        skill.put("Wing Attack", 3);
        skill.put("Air Slash", 4);
    }
}
