
public class PotionStaminaSmall extends Item {

    public PotionStaminaSmall(){
        name = "Potion Stamina(S)";
        price = 20;
    }
    @Override
    public void useItem(Animon animon) {
        if(animon.stamina < animon.maxStamina){
            animon.stamina += animon.maxStamina*0.3;
            if(animon.stamina > animon.maxStamina){animon.stamina = animon.maxStamina;}
        }
        else{System.out.println("Your stamina are full.");}
    }
}