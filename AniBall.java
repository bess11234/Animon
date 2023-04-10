public class AniBall extends Item {
    private String name = "Aniball";
    private int price = 150;
    @Override
    public void useItem(Player player, Animon animon) {
        player.setAnimals(animon);
    }  
}
