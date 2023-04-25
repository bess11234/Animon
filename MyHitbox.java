
import java.awt.Rectangle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
public class MyHitbox {
    private Rectangle hitbox;
    private int x, y;
    
    public MyHitbox(int x, int y){
        this.x = x;
        this.y = y;
        hitbox = new Rectangle();
    }
    
    public void setHitbox(Rectangle temp){
        hitbox = (Rectangle) temp.clone();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
