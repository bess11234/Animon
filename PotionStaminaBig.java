
public class PotionStaminaBig extends Item {

    public PotionStaminaBig(){
        name = "Potion Stamina(L)";
        price = 100;
    }
    @Override
    public void useItem(Animon animon) {
        if(animon.stamina < animon.maxStamina){
            animon.stamina += animon.maxStamina*0.7;
            if(animon.stamina > animon.maxStamina){animon.stamina = animon.maxStamina;}
        }
        else{System.out.println("Your stamina are full.");}
    }
    
}