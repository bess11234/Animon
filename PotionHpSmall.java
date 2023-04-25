public class PotionHpSmall extends Item {

    public PotionHpSmall(){
        name = "Potion HP(S)";
        price = 20;
    }
    
    @Override
    public void useItem(Animon animon) {
        animon.hp += animon.maxHp*0.3;
    }
}
