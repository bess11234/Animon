public class AniBall extends Item {
    private String name = "Aniball";
    private int price = 200;
    @Override
    public void useItem(Player player, Animon animon) {
        player.setAnimals(animon);
    }  
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
}
