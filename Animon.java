import java.util.*;
public abstract class Animon implements Attackable{
    protected String name;
    protected int level;
    protected int hp;
    protected static int maxStamina;
    protected int maxHp;
    protected static int maxExp;
    protected int stamina;
    protected int baseAtk;
    protected int exp;
    protected int money;
    protected HashMap skill;
    
    public Animon(int level){
        this.level = level;
        maxStamina = level*5;
        stamina = maxStamina;
        maxExp = level*5;
        exp = 0;
    }
    
    public void dead(){
        //wait for player
    }
    
    public int dropMoney(Animon animon){
        //for player
        animon.money = animon.level*20;
        return animon.money;
    }
    
    public void levelUp(Animon atked){
        if(exp+atked.level*5 >= maxExp){
            exp = exp+atked.level*5-maxExp;
            level++;
            maxStamina = level*5;
            stamina = maxStamina;
            maxExp = level*5;
        }
        else{exp += atked.level*5;}
    }
    
     public void useItem(Item item){
        if(item.getName().equals("Potion HP(L)")){
            item.useItem(this);
        }
        else if(item.getName().equals("Potion HP(S)")){
            item.useItem(this);
        }
        else if(item.getName().equals("Potion Stamina(L)")){
            item.useItem(this);
        }
        else if(item.getName().equals("Potion Stamina(S)")){
            item.useItem(this);
        }
        else{System.out.println("Error");}
        
    }
    
    public void attacked(Animon attacker){
        if(this.hp - attacker.baseAtk <= 0){
            System.out.println("Your animon are dead.");
            this.dead();
            attacker.levelUp(this);
        }
        else{
            this.hp -= attacker.baseAtk;
            attacker.stamina += attacker.baseAtk;
        }   
    }
    
    public void attacked(Animon attacker, int damage){
        if(this.hp - damage <= 0){
            System.out.println("Your animon are dead.");
            this.dead();
            attacker.levelUp(this);
        }
        else{
            this.hp -= damage;
        }
    }
    
    public void nomalAttack(Animon atked){
        atked.attacked(this, this.baseAtk);
    }
    
    public void useskill(Animon atked, String skillName){
        int multi = (int)skill.get(skillName);
        if(this.stamina > this.level*multi){
            this.stamina -= level*multi;
            atked.attacked(this, multi*this.baseAtk);
        }
        else{
            System.out.println("Your stamina are not enough.");
        }
    }
}
