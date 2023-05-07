
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bess1123
 */
public class MyMenu {

    private static boolean visible = false;
    private static int num = 0;
    private static int time = 2;
    private static Rectangle menuPanel = new Rectangle();
    private static int item_num;
    private static int animon_num;
    private static boolean item;
    private static boolean use_item;
    private static boolean save;
    private static boolean exit;

    ;

    public static boolean isVisible() {
        return visible;
    }

    public static void setVisible(boolean visible) {
        MyMenu.visible = visible;

        menuPanel.x = 1360 / 4;
        menuPanel.y = 768 / 4;
        menuPanel.width = 1360 / 2;
        menuPanel.height = 768 / 2;
        time = 2;
        num = 0;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        MyMenu.num = num;
        if (!item && !use_item) {
            if (MyMenu.num < 0) {
                MyMenu.num = 0;
            } else if (MyMenu.num > 2) {
                MyMenu.num = 2;
            }
        }

    }

    public static void selected(MyController ct, Player player) {
        boolean use = false;
        if (item) {

            if (ct.button_x && time == 0 && !use_item) {
                item = false;
                num = 0;
                time = 2;
            }

            if (ct.button_z && time == 0 && !use_item && ((num == 0 && player.getCount_potionHPS() != 0) || (num == 1 && player.getCount_potionHPL() != 0) || (num == 2 && player.getCount_potionSTS() != 0) || (num == 3 && player.getCount_potionSTL() != 0))) {
                item_num = num;
                use_item = true;
                time = 2;
                num = 0;
            }

            if (use_item) {

                if (ct.button_x && time == 0) {
                    use_item = false;
                    item_num = 0;
                    num = 0;
                    time = 2;

                } else if (ct.button_z && time == 0) {
                    
                    switch (item_num) {
                        case 0 -> {
                            if (player.getAnimals()[num].hp < player.getAnimals()[num].maxHp){
                                player.getAnimals()[num].useItem(player.getInventory()[item_num][player.getCount_potionHPS() - 1], player, null);
                                use = true;
                            }
                        }
                        case 1 -> {
                            if (player.getAnimals()[num].hp < player.getAnimals()[num].maxHp){
                                player.getAnimals()[num].useItem(player.getInventory()[item_num][player.getCount_potionHPL() - 1], player, null);
                                use = true;
                            }
                        }
                        case 2 -> {
                            if (player.getAnimals()[num].stamina < player.getAnimals()[num].maxStamina){
                                player.getAnimals()[num].useItem(player.getInventory()[item_num][player.getCount_potionSTS() - 1], player, null);
                                use = true;
                            }
                        }
                        case 3 -> {
                            if (player.getAnimals()[num].stamina < player.getAnimals()[num].maxStamina){
                                player.getAnimals()[num].useItem(player.getInventory()[item_num][player.getCount_potionSTL() - 1], player, null);
                                use = true;
                            }
                        }
                        default -> {
                        }
                    }
                    
                    if (use){
                        item_num = 0;
                        use_item = false;
                        num = 0;
                        time = 2;
                    }
                    

                }

                if (ct.up) {
                    MyMenu.setNum(num - 1);
                    if (num < 0) {
                        num = 0;
                    }
                } else if (ct.down) {
                    MyMenu.setNum(num + 1);
                    if (num > player.getCountAnimals() - 1) {
                        num = player.getCountAnimals() - 1;
                    }
                }

            } else {

                if (ct.down && num == 0) {
                    MyMenu.setNum(2);
                }
                if (ct.right && num == 0) {
                    MyMenu.setNum(1);
                }
                if (ct.down && num == 1) {
                    MyMenu.setNum(3);
                }
                if (ct.left && num == 1) {
                    MyMenu.setNum(0);
                }
                if (ct.up && num == 2) {
                    MyMenu.setNum(0);
                }
                if (ct.right && num == 2) {
                    MyMenu.setNum(3);
                }
                if (ct.up && num == 3) {
                    MyMenu.setNum(1);
                }
                if (ct.left && num == 3) {
                    MyMenu.setNum(2);
                }
            }

        } else {
            if (ct.button_x && time == 0) {
                visible = false;
                num = 0;
            }

            if (ct.button_z && num == 0) { // Item
                item = true;
                time = 2;
            }

            if (ct.button_z && num == 1) { // Save
                save = true;
                try (FileOutputStream file = new FileOutputStream("save/save1.dat"); BufferedOutputStream file2 = new BufferedOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(file2);) {
                    out.writeObject(player);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                time = 8;
            }
            
            if (ct.button_z && num == 2){ //Exit
                MyJPanel.getFrame().dispose();
                System.exit(0);
            }

            if (ct.up) {
                MyMenu.setNum(num - 1);
            } else if (ct.down) {
                MyMenu.setNum(num + 1);
            }
        }

    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        MyMenu.time = time;
        if (MyMenu.time < 0) {
            MyMenu.time = 0;
        }
    }

    public static void paint(Graphics g, Player player) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(new Font("Dialog", 12, 15));

        g2D.setPaint(new Color(254, 147, 7));
        g2D.fillRect(menuPanel.x, menuPanel.y, menuPanel.width, menuPanel.height);
        g2D.setPaint(new Color(128, 59, 0));
        g2D.fillRect(menuPanel.x + 5, menuPanel.y + 5, menuPanel.width - 10, menuPanel.height - 10);
        g2D.setPaint(new Color(255, 210, 133));
        g2D.fillRect(menuPanel.x + 10, menuPanel.y + 10, menuPanel.width - 20, menuPanel.height - 20);
        g2D.setPaint(new Color(128, 50, 11));

        g2D.setPaint(new Color(254, 147, 7));
        g2D.fillRect(menuPanel.x, menuPanel.y, menuPanel.width / 4, menuPanel.height);
        g2D.setPaint(new Color(128, 59, 0));
        g2D.fillRect(menuPanel.x + 5, menuPanel.y + 5, menuPanel.width / 4 - 10, menuPanel.height - 10);
        g2D.setPaint(new Color(255, 210, 133));
        g2D.fillRect(menuPanel.x + 10, menuPanel.y + 10, menuPanel.width / 4 - 20, menuPanel.height - 20);
        g2D.setPaint(new Color(128, 50, 11));

        g2D.drawString("Item", menuPanel.x + menuPanel.width / 4 / 2 - 10 - "Item".length(), menuPanel.y + 35);
        g2D.drawString("Save", menuPanel.x + menuPanel.width / 4 / 2 - 13 - "Save".length(), menuPanel.y + 35 + 30 * 1);
        g2D.drawString("Game End", menuPanel.x + menuPanel.width / 4 / 2 - 10 - 18 - "Game End".length() / 2, menuPanel.y + 35 + 30 * 2);

        g2D.drawLine(menuPanel.x + menuPanel.width / 4, menuPanel.y + 10 + (menuPanel.height - 20) / 3, menuPanel.x + menuPanel.width / 4 + menuPanel.width - menuPanel.width / 4 - 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3);
        for (int i = 0; i < player.getCountAnimals(); i++) {
            if (player.getAnimals()[i] != null) {
                g2D.drawString("Level : " + player.getAnimals()[i].level + "", menuPanel.x + menuPanel.width / 4 + 15, menuPanel.y + 10 + 25 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawImage(player.getAnimals()[i].getImage(), menuPanel.x + menuPanel.width / 4 + 20, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 3) * i, null);
                g2D.drawString(player.getAnimals()[i].getName() + "", menuPanel.x + menuPanel.width / 4 + 20 + 100, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("HP : " + player.getAnimals()[i].hp + "/" + player.getAnimals()[i].maxHp + "", menuPanel.x + menuPanel.width / 4 + 20 + 200, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("Stamina : " + player.getAnimals()[i].stamina + "/" + player.getAnimals()[i].maxStamina + "", menuPanel.x + menuPanel.width / 4 + 20 + 325, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("Exp : " + player.getAnimals()[i].exp + "/" + player.getAnimals()[i].maxExp + "", menuPanel.x + menuPanel.width / 4 + 15, menuPanel.y + 10 + 25 + 75 + ((menuPanel.height - 20) / 3) * i);
            }

        }

        g2D.drawLine(menuPanel.x + menuPanel.width / 4, menuPanel.y + 10 + (menuPanel.height - 20) / 3 + (menuPanel.height - 20) / 3, menuPanel.x + menuPanel.width / 4 + menuPanel.width - menuPanel.width / 4 - 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3 + (menuPanel.height - 20) / 3);
        

        g2D.setPaint(Color.white);
        g2D.setStroke(new BasicStroke(2));
        if (!item) {
            switch (num) {
                case 0 ->
                    g2D.drawRect(menuPanel.x + 10 + 5, menuPanel.y + 10 + 5, menuPanel.width / 4 - 20 - 10, 35 - 10);
                case 1 ->
                    g2D.drawRect(menuPanel.x + 10 + 5, menuPanel.y + 10 + 30 * 1 + 5, menuPanel.width / 4 - 20 - 10, 35 - 10);
                case 2 ->
                    g2D.drawRect(menuPanel.x + 10 + 5, menuPanel.y + 10 + 30 * 2 + 5, menuPanel.width / 4 - 20 - 10, 35 - 10);
            }
        } else {

            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRect(menuPanel.x, menuPanel.y - 100 - 25, menuPanel.width, 95 + 25);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRect(menuPanel.x + 5, menuPanel.y - 100 + 5 - 25, menuPanel.width - 10, 95 - 10 + 25);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRect(menuPanel.x + 10, menuPanel.y - 100 + 10 - 25, menuPanel.width - 20, 95 - 20 + 25);
            g2D.setPaint(new Color(128, 50, 11));
            
            g2D.setFont(new Font("Dialog", 12, 15));
            g2D.drawString("   Potion HP(S)", menuPanel.x + 10 + 5, menuPanel.y - 100 + 25 * 1 - 15);
            g2D.drawString(String.format("%02d", player.getCount_potionHPS()), menuPanel.x + 10 + 5 + 150 + 50, menuPanel.y - 100 + 25 * 1 - 15);
            g2D.drawString("   Potion HP(L)", menuPanel.x + 10 + 5 + 350, menuPanel.y - 100 + 25 * 1 - 15);
            g2D.drawString(String.format("%02d", player.getCount_potionHPL()), menuPanel.x + 10 + 5 + 350 + 150 + 50, menuPanel.y - 100 + 25 * 1 - 15);
            g2D.drawString("   Potion Stamina(S)", menuPanel.x + 10 + 5, menuPanel.y - 100 + 25 * 2 - 10);
            g2D.drawString(String.format("%02d", player.getCount_potionSTS()), menuPanel.x + 10 + 5 + 150 + 50, menuPanel.y - 100 + 25 * 2 - 10);
            g2D.drawString("   Potion Stamina(L)", menuPanel.x + 10 + 5 + 350, menuPanel.y - 100 + 25 * 2 - 10);
            g2D.drawString(String.format("%02d", player.getCount_potionSTL()), menuPanel.x + 10 + 5 + 350 + 150 + 50, menuPanel.y - 100 + 25 * 2 - 10);

            g2D.setPaint(new Color(212, 135, 97));
            g2D.drawString("   AniBall", menuPanel.x + 10 + 5, menuPanel.y - 100 + 25 * 3 - 5);
            g2D.drawString(String.format("%02d", player.getCount_aniball()), menuPanel.x + 10 + 5 + 150 + 50, menuPanel.y - 100 + 25 * 3 - 5);
            g2D.setPaint(Color.white);
            g2D.setStroke(new BasicStroke(2));
            

            if (!use_item) {
                switch (num) {
                    case 0 ->
                        g2D.drawRect(menuPanel.x + 10 + 4 + 5, menuPanel.y - 100 + 10 + 2 - 20, 220, 25);
                    case 1 ->
                        g2D.drawRect(menuPanel.x + 10 + 350 + 4 + 5, menuPanel.y - 100 + 10 + 2 - 20, 220, 25);
                    case 2 ->
                        g2D.drawRect(menuPanel.x + 10 + 4 + 5, menuPanel.y - 100 + 10 + 25 * 1 + 2 - 15, 220, 25);
                    case 3 ->
                        g2D.drawRect(menuPanel.x + 10 + 350 + 4 + 5, menuPanel.y - 100 + 10 + 25 * 1 + 2 - 15, 220, 25);
                }
            } else {
                switch (num) {
                    case 0 ->
                        g2D.drawRect(menuPanel.x + menuPanel.width / 4 + 5, menuPanel.y + 10 + ((menuPanel.height - 20) / 3) * num + 5, menuPanel.width - menuPanel.width / 4 - 10 - 10, (menuPanel.height - 20) / 3 - 10);
                    case 1 ->
                        g2D.drawRect(menuPanel.x + menuPanel.width / 4 + 5, menuPanel.y + 10 + ((menuPanel.height - 20) / 3) * num + 5, menuPanel.width - menuPanel.width / 4 - 10 - 10, (menuPanel.height - 20) / 3 - 10);
                    case 2 ->
                        g2D.drawRect(menuPanel.x + menuPanel.width / 4 + 5, menuPanel.y + 10 + ((menuPanel.height - 20) / 3) * num + 5, menuPanel.width - menuPanel.width / 4 - 10 - 10, (menuPanel.height - 20) / 3 - 10);
                }
            }
            g2D.setStroke(new BasicStroke(1));
        }
        
        if (save && time > 0){
            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRect(1360 / 2 - 1360 / 8 +50, 768 / 2 - 768 / 8 +50, 1360 / 4 -100, 768 / 4 -100);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRect((1360 / 2 - 1360 / 8) + 5 +50, (768 / 2 - 768 / 8) + 5 +50, 1360 / 4 - 10 -100, 768 / 4 - 10 -100);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRect((1360 / 2 - 1360 / 8) + 10 +50, (768 / 2 - 768 / 8) + 10 +50, 1360 / 4 - 20 -100, 768 / 4 - 20 -100);
            g2D.setPaint(new Color(128, 50, 11));
            
            g2D.setFont(new Font("Dialog", 12,20));
            g2D.drawString("Saved", (1360 / 2 - 1360 / 8) + 10 +50 + (1360/4-20-100)/2 - 27, (768 / 2 - 768 / 8) + 10 +50 + (768 / 4 - 20 -100)/2 + 5);
            g2D.setFont(new Font("Dialog", 12, 12));
        }else{
            save = false;
        }
        g2D.setFont(new Font("Dialog", 12, 12));

    }

}
