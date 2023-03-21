
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    public static void itemShop() throws InterruptedException{
        Scanner buy = new Scanner(System.in);
        ArrayList<String> itemShop = new ArrayList();
        itemShop.add("1.Potion Hp 70%");
        itemShop.add("2.Potion Hp 30%");
        itemShop.add("3.Potion Stamina 70%");
        itemShop.add("4.Potion Stamina 30%");
        itemShop.add("5.AniBall");
        
        for(int i = 0; i < itemShop.size(); i++){
            System.out.println(itemShop.get(i));
        }
        
        System.out.println("\nWhat items you wanna buy ? 1-5");
        String buyItems = buy.nextLine();
        itemShop.remove(buyItems);
        Thread.sleep(1000);
        System.out.println("ok wait a sec...");
        Thread.sleep(7000);
        System.out.println("You just got scamed xDDDD");
    }
    
}
