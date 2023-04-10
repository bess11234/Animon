public class PotionStaminaSmall extends Item 

    @Override
    public void useItem(Animon animon) {
        animon.stamina += animon.maxStamina*0.3;
    }
    
}
