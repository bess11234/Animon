
import java.awt.Image;
import javax.swing.ImageIcon;

public class Birdmon extends Animon {

    static Image image = new ImageIcon("Monster_image/chicken.png").getImage();
    String[] listSkill;

    public Birdmon(int level) {
        super(level);
        name = "KFC";
        maxHp = level * 7;
        hp = maxHp;
        baseAtk = level * 4;

        maxStamina = level * 6;
        stamina = maxStamina;
        skill.put("Air Slash", 4);
        skill.put("Wing Attack", 3);
        skill.put("Pinch", 2);
        skill.put("Headbud", 1);

        listSkill = new String[4];
        listSkill[0] = "Headbud";
        listSkill[1] = "Pinch";
        listSkill[2] = "Wing Attack";
        listSkill[3] = "Air Slash";

    }

    public boolean levelUp(Animon atked) {
        if (super.levelUp(atked)) {
            maxHp = level * 7;
            hp = maxHp;
            baseAtk = level * 4;

            maxStamina = level * 6;
            stamina = maxStamina;
        }
        return false;
    }

    public Image getImage() {
        return Birdmon.image;
    }

    @Override
    public String[] getListSkill() {
        return listSkill;
    }
}
