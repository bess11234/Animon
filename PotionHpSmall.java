
public class PotionHpSmall extends Item {

    public PotionHpSmall(){
        name = "Potion HP(S)";
        price = 20;
    }
    
    @Override
    public void useItem(Animon animon) {
        if(animon.hp < animon.maxHp){
            animon.hp += animon.maxHp*0.3;
            if(animon.hp > animon.maxHp){animon.hp = animon.maxHp;}
        }
        else{System.out.println("Your HP are full.");}    
    }
}