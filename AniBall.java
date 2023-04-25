public class AniBall extends Item {

    public AniBall(){
        name = "AniBall";
        price = 150;
    }
    
    public void useItem(Player player, Animon animon) {
        player.setAnimals(animon);
    }  

    @Override
    public void useItem(Animon animon) {
        
    }
}
