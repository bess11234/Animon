
import java.awt.Image;
import javax.swing.ImageIcon;

public class Birdmon extends Animon {

    private static final Image image = new ImageIcon("Monster_image/chicken.png").getImage();
    private static final Image image_battle_mine = new ImageIcon("Monster_image/kai_mine.png").getImage();
    private static final Image image_battle_enemy = new ImageIcon("Monster_image/kai_ene.png").getImage();
    private String[] listSkill;

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

    public Image getImageBattleMine() {
        return image_battle_mine;
    }

    public Image getImageBattleEnemy() {
        return image_battle_enemy;
    }


}
