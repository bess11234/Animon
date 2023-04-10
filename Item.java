public abstract class Item {
    protected int price;
    protected String name;

    public int getPrice(){
        return price;
    }    
    public String getName(){
        return name;
    }
    public abstract void useItem(Animon animon);
}
