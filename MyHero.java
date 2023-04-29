/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
import java.awt.Image;
import javax.swing.*;

public class MyHero {

    private int my_x = 1360 / 2;
    private int my_y = 768 / 2;
    private int my_width = 50;
    private int my_height = 50;
    private int speed = 5;
    private String[] animation = new String[4];
    private ImageIcon image = new ImageIcon("chicken.png");

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void movement(MyController ct) {
        if (ct.down) {
            this.setMy_y(my_y + speed);
        }
        if (ct.left) {
            this.setMy_x(my_x - speed);
        }
        if (ct.right) {
            this.setMy_x(my_x + speed);
        }
        if (ct.up) {
            this.setMy_y(my_y - speed);
        }
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

    public void setImage(Image image) {
        this.image.setImage(image);
    }

    public Image getImage() {
        return image.getImage();
    }
}
