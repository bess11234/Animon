public abstract class Item {
    private int price;
    private String name;
    public abstract void useItem(Animon animon);
    public int getPrice(){
        return price;
    }    
    public String getName(){
        return name;
    }
}