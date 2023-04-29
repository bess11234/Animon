
import java.util.Random;

public class AniBall extends Item {

    public AniBall(){
        name = "AniBall";
        price = 150;
    }
    
    public boolean useItem(Player player, Animon animon) {
        int num = 0;
        if ((animon.hp/animon.maxHp) <= 0.1){
            num = new Random().nextInt(0, 10);
            if (num <= 8){
                //player.setAnimals(animon, player.getCountAnimals());
                return true;
            }
        }
        else if ((animon.hp/animon.maxHp) <= 0.5){
            num = new Random().nextInt(0, 10);
            if (num <= 5){
                //player.setAnimals(animon, player.getCountAnimals());
                return true;
            }
        }
        return false;
    }

    @Override
    public void useItem(Animon animon) {
        
    }
}
