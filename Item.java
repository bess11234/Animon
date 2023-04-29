
import java.io.Serializable;

public abstract class Item implements Serializable{
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
