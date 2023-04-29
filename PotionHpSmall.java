public class PotionHpSmall extends Item {

    public PotionHpSmall(){
        name = "Potion HP(S)";
        price = 50;
    }
    
    @Override
    public void useItem(Animon animon) {
        animon.setHp((int) (animon.hp+animon.maxHp*0.3));
    }
}
