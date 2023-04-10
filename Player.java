import java.util.ArrayList;
public class Player {
    private Animon animals[] = new Animon[3];
    private double money;
    private ArrayList<Item> inventory;
    private int count_animals;
    private int highestLevel;

    public Player() {
        this.animals = new Animon[3];
        this.money = 0.0;
        //this.inventory = new ArrayList<Item>();
        this.count_animals = 0;
        this.highestLevel = 0;
    }

    public void buyItem(Item item) {
        this.money -= item.getCost();
        this.inventory.add(item);
    }

    public double getMoney() {
        return this.money;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void setAnimals(Animon animals) {
        if (count_animals != 2){
            this.animals[count_animals] = animals;
            this.count_animals += 1;
        }
        highestLevel();
    }
    
    public Animon[] getAnimals() {
        return this.animals;
    }

    public int getCountAnimals() {
        return this.count_animals;
    }
    
    private void highestLevel() {
        for (int i = 0; i < this.animals.length; i++) {
            if (this.animals[i].level > this.highestLevel) { // && this.animals[i] != null???
                this.highestLevel = this.animals[i].level;
            }
        }
    }
    
    public int getHighestLevel() {
        return this.highestLevel;
    }
}
