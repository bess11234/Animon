public class PotionStaminaSmall extends Item {

    public PotionStaminaSmall(){
        name = "Potion Stamina(S)";
        price = 20;
    }
    @Override
    public void useItem(Animon animon) {
        animon.stamina += animon.maxStamina*0.3;
    }
}
