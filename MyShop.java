
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
    private static Rectangle shopPanel;

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

        shopPanel = new Rectangle();
        shopPanel.x = 1360 / 4;
        shopPanel.y = 768 / 4;
        shopPanel.width = 1360 / 2;
        shopPanel.height = 768 / 2;
    }

    public static void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setPaint(new Color(254, 147, 7));
        g2D.fillRoundRect(shopPanel.x, shopPanel.y, shopPanel.width, shopPanel.height, 10, 10);
        g2D.setPaint(new Color(128, 59, 0));
        g2D.fillRoundRect(shopPanel.x + 5, shopPanel.y + 5, shopPanel.width - 10, shopPanel.height - 10, 10, 10);
        g2D.setPaint(new Color(255, 210, 133));
        g2D.fillRoundRect(shopPanel.x + 10, shopPanel.y + 10, shopPanel.width - 20, shopPanel.height - 20, 10, 10);
        g2D.setPaint(new Color(128, 50, 11));

        g2D.setPaint(Color.white);
        g2D.setStroke(new BasicStroke(2));

        if (MyShop.getNum() == 0) {
            g2D.drawRoundRect(shopPanel.x + 10 + 5, shopPanel.y + 10 + 5, (1360 / 2 - 20 - 10), (768 / 2 - 20 - 10) / 5, 10, 10);
        }
        if (MyShop.getNum() == 1) {
            g2D.drawRoundRect(shopPanel.x + 10 + 5, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 1, (1360 / 2 - 20 - 10), (768 / 2 - 20 - 10) / 5, 10, 10);
        }
        if (MyShop.getNum() == 2) {
            g2D.drawRoundRect(shopPanel.x + 10 + 5, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 2, (1360 / 2 - 20 - 10), (768 / 2 - 20 - 10) / 5, 10, 10);
        }
        if (MyShop.getNum() == 3) {
            g2D.drawRoundRect(shopPanel.x + 10 + 5, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 3, (1360 / 2 - 20 - 10), (768 / 2 - 20 - 10) / 5, 10, 10);
        }
        if (MyShop.getNum() == 4) {
            g2D.drawRoundRect(shopPanel.x + 10 + 5, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 4, (1360 / 2 - 20 - 10), (768 / 2 - 20 - 10) / 5, 10, 10);
        }
        g2D.setPaint(new Color(128, 50, 11));
        g2D.setStroke(new BasicStroke(1));

        g2D.setFont(new Font("Dialog", 12, 15));
        g2D.drawImage(new ImageIcon("Item/SamllHP.png").getImage(), shopPanel.x + 10 + 5 + 10 - 5, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 / 2 + 5 - 30, null);
        g2D.drawString(itemShop[0].getName(), shopPanel.x + 10 + 5 + 10 + 45, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
        g2D.drawString(String.format("%3d", itemShop[0].getPrice()), shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 / 2 + 5);

        g2D.drawImage(new ImageIcon("Item/LargeHP.png").getImage(), shopPanel.x + 10 + 5 + 10 - 3, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 1 + (768 / 2 - 20 - 10) / 5 / 2 + 5 - 25, null);
        g2D.drawString(itemShop[1].getName(), shopPanel.x + 10 + 5 + 10 + 45, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 1 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
        g2D.drawString(String.format("%3d", itemShop[1].getPrice()), shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 1 + (768 / 2 - 20 - 10) / 5 / 2 + 5);

        g2D.drawImage(new ImageIcon("Item/SamllStamina.png").getImage(), shopPanel.x + 10 + 5 + 10 - 5, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 2 + (768 / 2 - 20 - 10) / 5 / 2 + 5 - 30, null);
        g2D.drawString(itemShop[2].getName(), shopPanel.x + 10 + 5 + 10 + 45, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 2 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
        g2D.drawString(String.format("%3d", itemShop[2].getPrice()), shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 2 + (768 / 2 - 20 - 10) / 5 / 2 + 5);

        g2D.drawImage(new ImageIcon("Item/LargeStamina.png").getImage(), shopPanel.x + 10 + 5 + 10 - 3, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 3 + (768 / 2 - 20 - 10) / 5 / 2 + 5 - 25, null);
        g2D.drawString(itemShop[3].getName(), shopPanel.x + 10 + 5 + 10 + 45, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 3 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
        g2D.drawString(String.format("%3d", itemShop[3].getPrice()), shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 3 + (768 / 2 - 20 - 10) / 5 / 2 + 5);

        g2D.drawImage(new ImageIcon("Item/Aniball.png").getImage(), shopPanel.x + 10 + 5 + 10, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 4 + (768 / 2 - 20 - 10) / 5 / 2 + 5 - 30, null);
        g2D.drawString(itemShop[4].getName(), shopPanel.x + 10 + 5 + 10 + 45, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 4 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
        g2D.drawString(String.format("%3d", itemShop[4].getPrice()), shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 4 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
        g2D.setFont(new Font("Dialog", 12, 12));
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

    public static Image getImageBack() {
        return image_back;
    }

    public static Image getImageFront() {
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
        if (time != 0) {
            time -= 1;
        }

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
