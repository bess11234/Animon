public class PotionStaminaBig extends Item {

    public PotionStaminaBig(){
        name = "Potion Stamina(L)";
        price = 100;
    }
    @Override
    public void useItem(Animon animon) {
        animon.stamina += animon.maxStamina*0.7;
    }
    
}
