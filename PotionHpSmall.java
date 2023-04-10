public class PotionHpSmall extends Item {
    private String name = "Potion HP(S)";
    private int Price = 20;
    @Override
    public void useItem(Animon animon) {
        animon.hp += animon.maxHp*0.3;
    }
}
