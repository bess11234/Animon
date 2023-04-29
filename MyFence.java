
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
public class MyFence {
    private Rectangle[] hitbox;
    private int x, y;
    private Image image;
    
    public MyFence(Image image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
        
        hitbox = new Rectangle[2];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle[] getHitbox() {
        return hitbox;
    }

    public void setHitbox1(Rectangle hitbox) {
        Rectangle temp = (Rectangle) hitbox.clone();
        this.hitbox[0] = temp;
    }
    public void setHitbox2(Rectangle hitbox) {
        Rectangle temp = (Rectangle) hitbox.clone();
        this.hitbox[1] = temp;
    }
}
