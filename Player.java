import java.util.ArrayList;
public class Player {
    private Animon animals[] = new Animon[3];
    private double money;
    private ArrayList<Item> inventory;

    public Player() {
        this.animals = new Animon[3];
        this.money = 0.0;
        this.inventory = new ArrayList<Item>();
    }

    public void buyItem(Item item) {
        this.money -= item.getPrice();
        this.inventory.add(item);
    }

    public double getMoney() {
        return this.money;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
}

// Anything else you can tell me
