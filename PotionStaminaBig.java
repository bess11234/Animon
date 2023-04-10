public class PotionStaminaBig extends Item {
    private String name = "Potion Stamina(L)";
    private int price = 100;
    @Override
    public void useItem(Animon animon) {
        animon.stamina += animon.maxStamina*0.7;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    
}