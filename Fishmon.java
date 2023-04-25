
import java.awt.Image;
import javax.swing.ImageIcon;


public class Fishmon extends Animon{
    static Image image = new ImageIcon("Monster_image/fish.png").getImage();
    public Fishmon(int level){
        super(level);
        name = "Fisho";
        maxHp = level*10;
        hp = maxHp;
        baseAtk = level*5;
        skill.put("Dancig", 1.5);
        skill.put("Buble", 2);
        skill.put("Water Gun", 3);
        skill.put("Aqua Tail", 4);
        
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
            baseAtk = level*5;
        }
        else{exp += atked.level*5;}
    }
    public Image getImage(){
        return Fishmon.image;
    }
}
