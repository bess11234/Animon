
import java.awt.Image;
import java.io.Serializable;
import java.util.*;

public abstract class Animon implements Attackable, Serializable{

    protected String name;
    protected int level;
    protected int hp;
    protected int maxStamina;
    protected int maxHp;
    protected int maxExp;
    protected int stamina;
    protected int baseAtk;
    protected int exp;
    protected int money;
    protected HashMap skill;
    

    public Animon(int level) {
        skill = new HashMap();
        this.level = level;
        maxStamina = level * 5;
        stamina = maxStamina;
        maxExp = level * 5;
        exp = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void dead() {
        //wait for player
    }

    public int dropMoney(Animon animon) {
        //for player
        animon.money = animon.level * 20;
        return animon.money;
    }

    public void levelUp(Animon atked) {
        if (exp + atked.level * 5 >= maxExp) {
            exp = exp + atked.level * 5 - maxExp;
            level++;
            maxStamina = level * 5;
            stamina = maxStamina;
            maxExp = level * 5;
        } else {
            exp += atked.level * 5;
        }
    }

    public void useItem(Item item, Player player) {
        if (item instanceof PotionHpSmall) {
            ((PotionHpSmall) item).useItem(this);
            player.getInventory()[0][player.getCount_potionHPS()] = null;
            player.setCount_potionHPS(player.getCount_potionHPS() - 1);

        } else if (item instanceof PotionHpBig) {
            ((PotionHpBig) item).useItem(this);
            player.getInventory()[1][player.getCount_potionHPL()] = null;
            player.setCount_potionHPL(player.getCount_potionHPL() - 1);

        } else if (item instanceof PotionStaminaSmall) {
            ((PotionStaminaSmall) item).useItem(this);
            player.getInventory()[2][player.getCount_potionSTS()] = null;
            player.setCount_potionSTS(player.getCount_potionSTS() - 1);

        } else if (item instanceof PotionStaminaBig) {
            ((PotionStaminaBig) item).useItem(this);
            player.getInventory()[3][player.getCount_potionSTL()] = null;
            player.setCount_potionSTL(player.getCount_potionSTL() - 1);

        } else if (item instanceof AniBall) {
            ((AniBall) item).useItem(player, this);
            player.getInventory()[4][player.getCount_aniball()] = null;
            player.setCount_aniball(player.getCount_aniball() - 1);
            
        }
    }

    public void attacked(Animon attacker) {
        if (this.hp - attacker.baseAtk <= 0) {
            System.out.println("Your animon are dead.");
            this.dead();
            attacker.levelUp(this);
        } else {
            this.hp -= attacker.baseAtk;
            attacker.stamina += attacker.baseAtk;
        }
    }

    public void attacked(Animon attacker, int damage) {
        if (this.hp - damage <= 0) {
            System.out.println("Your animon are dead.");
            this.dead();
            attacker.levelUp(this);
        } else {
            this.hp -= damage;
        }
    }

    public void nomalAttack(Animon atked) {
        atked.attacked(this, this.baseAtk);
    }

    public void useskill(Animon atked, String skillName) {
        int multi = (int) skill.get(skillName);
        if (this.stamina > this.level * multi) {
            this.stamina -= level * multi;
            atked.attacked(this, multi * this.baseAtk);
        } else {
            System.out.println("Your stamina are not enough.");
        }
    }
    
    public abstract Image getImage();
}
