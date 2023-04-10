
public class PotionHpBig extends Item {

    public PotionHpBig(){
        name = "Potion HP(L)";
        price = 100;
    }
    @Override
    public void useItem(Animon animon) {
        if(animon.hp < animon.maxHp){
            animon.hp += animon.maxHp*0.7;
            if(animon.hp > animon.maxHp){animon.hp = animon.maxHp;}
        }
        else{System.out.println("Your HP are full.");}
    }
}