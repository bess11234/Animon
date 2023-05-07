
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
public class MyNPC {
    private int x, y;
    private Rectangle hitbox = new Rectangle();
    private ImageIcon image;
    private Animon[] animon;
    private int highestLevel;
    
    public MyNPC(int x, int y, ImageIcon image){
        this.x = x;
        this.y = y;
        
        hitbox.x = this.x + 25;
        hitbox.y = this.y;
        hitbox.width = 75 - 47;
        hitbox.height = 75;
        animon = getAnimon();
        highestLevel();
        
        this.image = image;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public Image getImage() {
        return image.getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public static Animon[] getAnimon(){
        Animon[] animon;
        int Listnum = new Random().nextInt(1, 4);
        
        int limit = Player.getHighestLevel() - 2;
        if (limit < 1) {
            limit = 1;
        }
        animon = new Animon[3];

        for (int i=0; i < Listnum; i++){
            int random_animon = new Random().nextInt(0, 3);
            int level = new Random().nextInt(limit, Player.getHighestLevel()+2);
            switch(random_animon){
                case 0 ->{animon[i] = new Dogmon(level);}
                case 1 ->{animon[i] = new Fishmon(level);}
                case 2 ->{animon[i] = new Birdmon(level);}
            }
        }
        
        for (int i=Listnum; i < 3; i++){
            animon[i] = null;
        }
        return animon;
    }
    
    public void highestLevel(){
        int high = 0;
        int count = 0;
        for (int i = 0; i < 3; i++){
            if (animon[i] != null){
                count ++;
            }
        }
        for (int i = 0; i < count; i++){
            if (animon[i].level > high){
                high = animon[i].level;
            }
        }
        highestLevel = high;
    }
    
    public void setAnimon(Animon[] animon){
        this.animon = animon;
    }
    
    public int getHighestLevel(){
        return highestLevel;
    }
    
    public Animon[] getCurrentAnimon(){
        return animon;
    }
}
