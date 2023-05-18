
import java.awt.Image;
import javax.swing.ImageIcon;

public class Dogmon extends Animon {

    private static final Image image = new ImageIcon("Monster_image/dog.png").getImage();
    private static final Image image_battle_mine = new ImageIcon("Monster_image/dog_mine.png").getImage();
    private static final Image image_battle_enemy = new ImageIcon("Monster_image/dog_ene.png").getImage();
    private String[] listSkill;

    public Dogmon(int level) {
        super(level);
        name = "Tomleng";
        maxHp = level * 12;
        hp = maxHp;
        baseAtk = level * 2;
        maxStamina = level * 7;
        stamina = maxStamina;
        skill.put("Quick Attack", 4);
        skill.put("Headbud", 3);
        skill.put("Bite", 2);
        skill.put("Bark", 1);
        listSkill = new String[4];
        listSkill[0] = "Bark";
        listSkill[1] = "Bite";
        listSkill[2] = "Headbud";
        listSkill[3] = "Quick Attack";
    }

    public boolean levelUp(Animon atked) {
        if (super.levelUp(atked)) {
            maxHp = level * 12;
            hp = maxHp;
            baseAtk = level * 2;

            maxStamina = level * 7;
            stamina = maxStamina;
        }
        return false;

    }

    public Image getImage() {
        return Dogmon.image;
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
