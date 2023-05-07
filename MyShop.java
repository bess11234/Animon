
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bess1123
 */
public class MyShop extends Shop {

    private int x, y;
    private Rectangle hitbox = new Rectangle();
    private static int num;
    private static boolean visible;
    private static int time;
    private static Image image_back, image_front;

    public MyShop(int x, int y) {
        this.x = x;
        this.y = y;
        hitbox.x = x + 10;
        hitbox.y = y + 70;
        hitbox.width = 80;
        hitbox.height = 30;
        num = 0;
        time = 2;
        visible = false;
        image_back = new ImageIcon("shop.png").getImage();
        image_back = image_back.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        image_front = new ImageIcon("shop_front.png").getImage();
        image_front = image_front.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public static void setVisible(boolean visible) {
        num = 0;
        time = 2;
        MyShop.visible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static boolean isVisible() {
        return visible;
    }

    public static int getNum() {
        return num;
    }
    
    public static Image getImageBack(){
        return image_back;
    }
    
    public static Image getImageFront(){
        return image_front;
    }

    public static void setNum(int num) {
        MyShop.num = num;
        if (MyShop.num < 0) {
            MyShop.num = 0;
        }
        if (MyShop.num > 4) {
            MyShop.num = 4;
        }
        //else if (num > ) need Item size
    }

    public void selected(MyController ct, Player player) {
        if (time != 0){time -= 1;}
        
        if (time == 0) {
            if (ct.button_z) {
                this.sellItem(player, MyShop.getNum());
                time = 2;
            }
            if (ct.down) {
                MyShop.setNum(MyShop.getNum() + 1);
            } else if (ct.up) {
                MyShop.setNum(MyShop.getNum() - 1);
            } else if (ct.button_x) {
                visible = false;
            }
        }

    }

}
