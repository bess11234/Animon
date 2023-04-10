import java.util.Scanner;

public class Shop {
    private Item[] itemShop = new Item[5];
    
    public void sellItem(Player player, int num){
        player.getInventory().add(itemShop[num]);
    }
    public Shop(){
        Scanner buy = new Scanner(System.in);
        itemShop[0] = new PotionHpSmall();
        itemShop[1] = new PotionHpBig();
        itemShop[2] = new PotionStaminaSmall();
        itemShop[3] = new PotionStaminaBig();
        itemShop[4] = new AniBall();
        
        for (Item itemShop1 : itemShop) {
            System.out.println(itemShop1);
        }
        
    }
    
}
