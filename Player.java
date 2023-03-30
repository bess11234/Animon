public class Player {
    private Animal[] animals;
    private double money;

    public Player() {
        this.animals = new Animal[3];
        this.money = 0.0;
    }

    public void buyItem(Item item) {
        this.money -= item.getPrice();
        // add item to player's inventory???
    }

    public double getMoney() {
        return this.money;
    }
}
// anything else you can tell me