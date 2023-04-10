public class PotionHpBig extends Item {

    @Override
    public void useItem(Animon animon) {
        animon.hp += animon.maxHp*0.7;
    }
    
}    

