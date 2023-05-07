
import java.awt.Image;
import javax.swing.ImageIcon;

public class Fishmon extends Animon {

    private static final Image image = new ImageIcon("Monster_image/fish.png").getImage();
    private static final Image image_battle_mine = new ImageIcon("Monster_image/fish_mine.png").getImage();
    private static final Image image_battle_enemy = new ImageIcon("Monster_image/fish_ene.png").getImage();
    String[] listSkill;

    public Fishmon(int level) {
        super(level);
        name = "Fisho";
        maxHp = level * 6;
        hp = maxHp;
        baseAtk = level * 5;

        maxStamina = level * 5;
        stamina = maxStamina;
        skill.put("Aqua Tail", 4);
        skill.put("Water Gun", 3);
        skill.put("Buble", 2);
        skill.put("Dancig", 1);
        listSkill = new String[4];
        listSkill[0] = "Dancig";
        listSkill[1] = "Buble";
        listSkill[2] = "Water Gun";
        listSkill[3] = "Aqua Tail";
    }

    public boolean levelUp(Animon atked) {
        if (super.levelUp(atked)) {
            maxHp = level * 6;
            hp = maxHp;
            baseAtk = level * 5;

            maxStamina = level * 5;
            stamina = maxStamina;
        }
        return false;
    }

    public Image getImage() {
        return Fishmon.image;
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
