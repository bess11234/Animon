public class PotionStaminaSmall extends Item {
    private String name = "Potion Stamina(S)";
    private int price = 20;
    @Override
    public void useItem(Animon animon) {
        animon.stamina += animon.maxStamina*0.3;
    }
}