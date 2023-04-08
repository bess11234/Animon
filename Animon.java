import java.util.*;
public abstract class Animon implements Attackable{
    protected String name;
    protected int level;
    protected int hp;
    protected int stamina = level*5;
    protected int baseAtk;
    protected int exp = level*5;
    protected HashMap skill;
    public void attacked(Animon attacker){
        this.hp -= attacker.baseAtk;
        attacker.stamina += attacker.baseAtk;
    }
    public void attacked(Animon attacker, int damage){
        if(this.hp - damage < 0){
            System.out.println("Your animon die.");
            this.dead();
        }
        else{
            this.hp -= damage;
        }
    }
    public void nomalAttack(Animon atked){
        atked.attacked(this, this.baseAtk);
    }
    public void useItem(){
        //wait for dev
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
    public void dead(){
        //wait for player
    }
}
