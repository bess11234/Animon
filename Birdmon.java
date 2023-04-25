
import java.awt.Image;
import javax.swing.ImageIcon;


public class Birdmon extends Animon{
    static Image image = new ImageIcon("Monster_image/chicken.png").getImage();
    public Birdmon(int level){
        super(level);
        name = "KFC";
        maxHp = level*7;
        hp = maxHp;
        baseAtk = level*4;
        skill.put("Headbud", 1.5);
        skill.put("Pinch", 2);
        skill.put("Wing Attack", 3);
        skill.put("Air Slash", 4);
        
    }
    public void levelUp(Animon atked){
        if(exp+atked.level*5 >= maxExp){
            exp = exp+atked.level*5-maxExp;
            level++;
            maxStamina = level*5;
            stamina = maxStamina;
            maxExp = level*5;
            maxHp = level*7;
            hp = maxHp;
            baseAtk = level*4;
        }
        else{exp += atked.level*5;}
    }
    
    public Image getImage(){
        return Birdmon.image;
    }
}
