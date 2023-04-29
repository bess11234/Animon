
import java.io.Serializable;


public class Player implements Serializable{
    private Animon animals[] = new Animon[3];
    private int money;
    private transient Item[][] inventory;
    private int count_animals;
    private static int highestLevel;
    private int count_potionHPS = 0;
    private int count_potionHPL = 0;
    private int count_potionSTS = 0;
    private int count_potionSTL = 0;
    private int count_aniball = 0;

    public Player() {
        this.animals = new Animon[3];
        this.money = 500;
        this.inventory = new Item[5][100];
        this.count_animals = 0;
        highestLevel = 1;
    }
    
    public void update(){
        this.inventory = new Item[5][100];
        for (int i =0; i < count_potionHPS;i++){
            this.inventory[0][i] = new PotionHpSmall();
        }
        for (int i =0; i < count_potionHPL;i++){
            this.inventory[1][i] = new PotionHpBig();
        }
        for (int i =0; i < count_potionSTS;i++){
            this.inventory[2][i] = new PotionStaminaSmall();
        }
        for (int i =0; i < count_potionSTL;i++){
            this.inventory[3][i] = new PotionStaminaBig();
        }
        for (int i =0; i < count_aniball;i++){
            this.inventory[4][i] = new AniBall();
        }
        highestLevel();
    }

    public void buyItem(Item item) {
        if (item instanceof PotionHpSmall){
            
            if (count_potionHPS < 100){
                this.inventory[0][count_potionHPS] = item;
                count_potionHPS += 1;
            }else{
                this.money += ((PotionHpSmall) item).price;
            }
            
        }else if (item instanceof PotionHpBig){

            if (count_potionHPL < 100){
                this.inventory[1][count_potionHPL] = item;
                count_potionHPL += 1;
            }else{
                this.money += ((PotionHpBig) item).price;
            }
            
        }else if (item instanceof PotionStaminaSmall){
            
            if (count_potionSTS < 100){
                this.inventory[2][count_potionSTS] = item;
                count_potionSTS += 1;
            }else{
                this.money += ((PotionStaminaSmall) item).price;
            }
            
        }else if (item instanceof PotionStaminaBig){
            
            if (count_potionSTL < 100){
                this.inventory[3][count_potionSTL] = item;
                count_potionSTL += 1;
            }else{
                this.money += ((PotionStaminaBig) item).price;
            }
            
        }else if (item instanceof AniBall){
            
            if (count_aniball < 100){
                this.inventory[4][count_aniball] = item;
                count_aniball += 1;
            }else{
                this.money += ((AniBall) item).price;
            }
            
        }
    }
    
    public void setMoney(int money){
        this.money = money;
        if (this.money < 0){
            this.money = 0;
        }
    }

    public int getMoney() {
        return this.money;
    }

    public Item[][] getInventory() {
        return inventory;
    }

    public void setAnimals(Animon animals, int num) {
        if (num != 3){
            if (this.animals[num] == null){
                this.count_animals += 1;
            }
            this.animals[num] = animals;
        }
        highestLevel();
    }
    
    public Animon[] getAnimals() {
        return this.animals;
    }

    public int getCountAnimals() {
        return this.count_animals;
    }
    
    public void highestLevel() {
        for (int i = 0; i < this.count_animals; i++) {
            if (this.animals[i].level > highestLevel) { // && this.animals[i] != null???
                highestLevel = this.animals[i].level;
            }
        }
    }
    
    public static int getHighestLevel() {
        return highestLevel;
    }

    public int getCount_animals() {
        return count_animals;
    }

    public void setCount_animals(int count_animals) {
        this.count_animals = count_animals;
    }

    public int getCount_potionHPS() {
        return count_potionHPS;
    }

    public void setCount_potionHPS(int count_potionHPS) {
        this.count_potionHPS = count_potionHPS;
    }

    public int getCount_potionHPL() {
        return count_potionHPL;
    }

    public void setCount_potionHPL(int count_potionHPL) {
        this.count_potionHPL = count_potionHPL;
    }


    public int getCount_potionSTS() {
        return count_potionSTS;
    }

    public void setCount_potionSTS(int count_potionSTS) {
        this.count_potionSTS = count_potionSTS;
    }

    public int getCount_aniball() {
        return count_aniball;
    }

    public void setCount_aniball(int count_aniball) {
        this.count_aniball = count_aniball;
    }
    
    public void setCount_potionSTL(int count_potionSTL){
        this.count_potionSTL = count_potionSTL;
    }
    
    public int getCount_potionSTL(){
        return count_potionSTL;
    }

}
