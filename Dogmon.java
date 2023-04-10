
public class Dogmon extends Animon{
    public Dogmon(int level){
        super(level);
        name = "Tomleng";
        maxHp = level*10;
        hp = maxHp;
        baseAtk = level*2;
        skill.put("Bark", 1.5);
        skill.put("Bite", 2);
        skill.put("Headbud", 3);
        skill.put("Quick Attack", 4);
    }
    public void levelUp(Animon atked){
        if(exp+atked.level*5 >= maxExp){
            exp = exp+atked.level*5-maxExp;
            level++;
            maxStamina = level*5;
            stamina = maxStamina;
            maxExp = level*5;
            maxHp = level*10;
            hp = maxHp;
            baseAtk = level*2;
        }
        else{exp += atked.level*5;}
    }
}
