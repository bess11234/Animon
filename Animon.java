
import java.awt.Image;
import java.io.Serializable;
import java.util.*;

public abstract class Animon implements Attackable, Serializable {

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
    protected boolean dead;
    protected boolean dead_before;

    public Animon(int level) {
        dead = false;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp > maxHp) {
            this.hp = maxHp;
        }
    }

    public void dead() {
        dead = true;
        exp = 0;
        dead_before = true;
        stamina = 0;
        //wait for player
    }


    public int dropMoney() {
        //for player
        this.money = new Random().nextInt(40 + this.level * 5 - 10, 40 + this.level * 5 + 10);
        return this.money;
    }

    public boolean levelUp(Animon atked) {
        if (exp + atked.level * 3 >= maxExp) {
            exp += atked.level * 3;
            while (exp >= maxExp) {
                level++;

                exp -= maxExp;
                maxExp = level * 5;
            }
            return true;
        } else {
            exp += atked.level * 3;
            return false;
        }

    }

    public boolean useItem(Item item, Player player, Animon enemy) {
        if (item instanceof PotionHpSmall) {
            ((PotionHpSmall) item).useItem(this);
            player.getInventory()[0][player.getCount_potionHPS()] = null;
            player.setCount_potionHPS(player.getCount_potionHPS() - 1);
            dead = false;

        } else if (item instanceof PotionHpBig) {
            ((PotionHpBig) item).useItem(this);
            player.getInventory()[1][player.getCount_potionHPL()] = null;
            player.setCount_potionHPL(player.getCount_potionHPL() - 1);
            dead = false;

        } else if (item instanceof PotionStaminaSmall) {
            ((PotionStaminaSmall) item).useItem(this);
            player.getInventory()[2][player.getCount_potionSTS()] = null;
            player.setCount_potionSTS(player.getCount_potionSTS() - 1);

        } else if (item instanceof PotionStaminaBig) {
            ((PotionStaminaBig) item).useItem(this);
            player.getInventory()[3][player.getCount_potionSTL()] = null;
            player.setCount_potionSTL(player.getCount_potionSTL() - 1);

        } else if (item instanceof AniBall) {
            player.getInventory()[4][player.getCount_aniball()] = null;
            player.setCount_aniball(player.getCount_aniball() - 1);

            if (((AniBall) item).useItem(player, enemy)) {

                return true;
            }

        }
        return false;
    }

    public void attacked(Animon attacker) {
        if (this.hp - attacker.baseAtk <= 0) {
//            System.out.println("Your animon are dead.");
            this.dead();
            attacker.levelUp(this);
        } else {
            this.hp -= attacker.baseAtk;
            attacker.stamina += attacker.baseAtk;
        }
    }

    public void attacked(Animon attacker, int damage) {
        if (this.hp - damage <= 0) {
            this.hp = 0;
//            System.out.println("Your animon are dead.");
            this.dead();
            attacker.levelUp(this);
        } else {
            this.hp -= damage;
        }
    }

    @Override
    public void nomalAttack(Animon atked) {
        atked.attacked(this, this.baseAtk/2);
        setStamina(stamina + (maxStamina * 0.2));
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(double stamina) {
        this.stamina = (int) stamina;
        if (this.stamina > maxStamina) {
            this.stamina = maxStamina;
        }
    }

    public void useskill(Animon atked, String skillName) {
        int multi = (int) skill.get(skillName);
        if (this.stamina >= this.level * multi) {
            this.stamina -= level * multi;
            atked.attacked(this, multi * this.baseAtk);
        } else {
//            System.out.println("Your stamina are not enough.");

        }
    
    }

    public abstract Image getImage();
    public abstract Image getImageBattleMine();
    public abstract Image getImageBattleEnemy();

    public abstract String[] getListSkill();
}
