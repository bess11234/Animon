public class PotionHpSmall extends Item {

    @Override
    public void useItem(Animon animon) {
        animon.hp += animon.maxHp*0.3;
    }
    
}
