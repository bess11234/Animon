public class AniBall extends Item {

    @Override
    public void useItem(Player player, Animon animon) {
        player.setAnimals(animon);
    }
    
}
