/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
import java.awt.Image;
import java.awt.*;
import java.io.Serializable;
import javax.swing.*;

public class MyPlayer extends Player{

    private int my_x = 1360 / 2;
    private int my_y = 768 / 2;
    private int my_width = 50;
    private int my_height = 50;
    private int speed = 5;
    private int map = 1;
    private Rectangle hitbox = new Rectangle(), rect_up = new Rectangle(), rect_down = new Rectangle(), rect_left = new Rectangle(), rect_right = new Rectangle();
    private transient ImageIcon[] animation;
    private transient ImageIcon image;

    public int getSpeed() {
        return speed;
    }

    public ImageIcon[] getAnimation() {
        return animation;
    }

    public void setAnimation(ImageIcon[] animation) {
        this.animation = animation;
        this.image = animation[0];
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void updateMap(int map){
        this.map = map;
    }
    
    public int getMap(){
        return map;
    }

    public boolean hitted(Rectangle rect) {
        hitbox.x = my_x + 16;
        hitbox.y = my_y + 16 * 2;
        hitbox.width = 16;
        hitbox.height = 16;

        rect_up.x = rect.x;
        rect_up.width = rect.width;
        rect_up.y = rect.y;
        rect_up.height = 1;

        rect_down.x = rect.x;
        rect_down.width = rect.width;
        rect_down.y = rect.y + rect.height;
        rect_down.height = 1;

        rect_left.x = rect.x;
        rect_left.width = 1;
        rect_left.y = rect.y;
        rect_left.height = rect.height;

        rect_right.x = rect.x + rect.width;
        rect_right.width = 1;
        rect_right.y = rect.y;
        rect_right.height = rect.height;

        if (hitbox.intersects(rect_up)) {
            this.setMy_y(this.getMy_y()-speed);
            return true;
        }
        else if (hitbox.intersects(rect_right)) {
            this.setMy_x(this.getMy_x()+speed);
            return true;
        }
        else if (hitbox.intersects(rect_left)) {
            this.setMy_x(this.getMy_x()-speed);
            return true;
        }
        else if (hitbox.intersects(rect_down)) {
            this.setMy_y(this.getMy_y()+speed);
            return true;
        }
        return false;
    }

    public void movement(MyController ct) {

        if (ct.down) {
            this.setImage(animation[0]);
            this.setMy_y(my_y + speed);
        }
        if (ct.left) {
            this.setImage(animation[1]);
            this.setMy_x(my_x - speed);
        }
        if (ct.right) {
            this.setImage(animation[2]);
            this.setMy_x(my_x + speed);
        }
        if (ct.up) {
            this.setImage(animation[3]);
            this.setMy_y(my_y - speed);
        }
        
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setMy_x(int x) {
        my_x = x;
        if (my_x > 1360 - my_width) {
            my_x = 1360 - my_width;
        } else if (my_x < 0) {
            my_x = 0;
        }
    }

    public int getMy_x() {
        return my_x;
    }

    public void setMy_y(int y) {
        my_y = y;
        if (my_y > 768 - my_height) {
            my_y = 768 - my_height;
        } else if (my_y < 0) {
            my_y = 0;
        }
    }

    public int getMy_y() {
        return my_y;
    }

    public void setMy_Height(int h) {
        my_height = h;
    }

    public int getMy_Height() {
        return my_height;
    }

    public void setMy_Width(int w) {
        my_width = w;
    }

    public int getMy_Width() {
        return my_width;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public Image getImage() {
        return image.getImage();
    }
}
