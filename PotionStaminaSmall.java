public class PotionStaminaSmall extends Item {

    public PotionStaminaSmall(){
        name = "Potion Stamina(S)";
        price = 50;
    }
    @Override
    public void useItem(Animon animon) {
        animon.setStamina(animon.stamina + animon.maxStamina*0.3);
    }
}
