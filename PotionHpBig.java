public class PotionHpBig extends Item {

    public PotionHpBig(){
        name = "Potion HP(L)";
        price = 100;
    }
    @Override
    public void useItem(Animon animon) {
        animon.setHp((int) (animon.hp+animon.maxHp*0.7));
    }
}
