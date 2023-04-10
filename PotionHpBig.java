public class PotionHpBig extends Item {
    private String name = "Potion HP(L)";
    private int Price = 100;
    @Override
    public void useItem(Animon animon) {
        animon.hp += animon.maxHp*0.7;
    }
}    