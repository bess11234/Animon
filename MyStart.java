
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
public class MyStart {

    private static int num = 0;
    private static boolean visible = true, start_newgame = false, menu = true;
    private static Rectangle startPanel = new Rectangle();
    private static int time;
    private static boolean con = false;
    private static Animon animon;

    public static void paint(Graphics g) {
        startPanel.x = 1360 / 4;
        startPanel.y = 768 / 4;
        startPanel.width = 1360 / 2;
        startPanel.height = 768 / 2;
        Graphics2D g2D = (Graphics2D)g;
        if (menu){
            g2D.setPaint(new Color(128, 50, 11));
            g2D.fillRect(0, 0, 1360, 768);
            
            g2D.setPaint(Color.white);
            if(con){
                g2D.drawImage(new ImageIcon("main_menu.png").getImage(), 0, 0, null);
            }else{
                g2D.drawImage(new ImageIcon("main_menu2.png").getImage(), 0, 0, null);
            }
            
            g2D.setStroke(new BasicStroke(2));
            g2D.setColor(Color.white);
            switch (MyStart.getNum()){
                case 0 -> g2D.drawRect(1360/2 - 125, 768/2 - 120, 240, 60);
                case 1 -> g2D.drawRect(1360/2 - 125, 768/2 - 120 + 80*1, 240, 60);
                case 2 -> g2D.drawRect(1360/2 - 125, 768/2 - 120 + 80*2, 240, 60);
            }
            g2D.setStroke(new BasicStroke(1));
        }
        
        if (start_newgame){
            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRoundRect(startPanel.x, startPanel.y, startPanel.width, startPanel.height, 10, 10);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRoundRect(startPanel.x + 5, startPanel.y + 5, startPanel.width - 10, startPanel.height - 10, 10, 10);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRoundRect(startPanel.x + 10, startPanel.y + 10, startPanel.width - 20, startPanel.height - 20, 10, 10);
            g2D.setPaint(new Color(128, 50, 11));

            g2D.setFont(new Font("Dialog", 12, 14));
            g2D.drawImage(new ImageIcon("Monster_image/dog.png").getImage(), startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25, startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6, null);
            g2D.drawString("HP : 12", startPanel.x + 10 + 10 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 - "HP : 12".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100);
            g2D.drawString("Stamina : 7", startPanel.x + 10 + 10 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 - "Stamina : 7".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 1);
            g2D.drawString("ATK : 2", startPanel.x + 10 + 10 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 - "ATK : 2".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 2);

            g2D.drawImage(new ImageIcon("Monster_image/chicken.png").getImage(), startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 1, startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6, null);
            g2D.drawString("HP : 7", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 1 - "HP : 7".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100);
            g2D.drawString("Stamina : 6", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 1 - "Stamina : 6".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 1);
            g2D.drawString("ATK : 4", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 1 - "ATK : 4".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 2);

            g2D.drawImage(new ImageIcon("Monster_image/fish.png").getImage(), startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 2, startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6, null);
            g2D.drawString("HP : 6", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 2 - "HP : 6".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100);
            g2D.drawString("Stamina : 5", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 2 - "Stamina : 5".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 1);
            g2D.drawString("ATK : 5", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 2 - "ATK : 5".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 2);

            g2D.setFont(new Font("Dialog", 12, 12));

            switch (MyStart.getNum()){
                case 0 -> g2D.drawRoundRect(startPanel.x + 10 + 5, startPanel.y + 10 + 5, (1360 / 2 - 20 - 10) / 3, (768 / 2 - 20 - 10), 10, 10);
                case 1 -> g2D.drawRoundRect(startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 * 1, startPanel.y + 10 + 5, (1360 / 2 - 20 - 10) / 3, (768 / 2 - 20 - 10), 10, 10);
                case 2 -> g2D.drawRoundRect(startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 * 2, startPanel.y + 10 + 5, (1360 / 2 - 20 - 10) / 3, (768 / 2 - 20 - 10), 10, 10);
            }
        }
        
    }

    public static void selected(MyController ct, Player player) {
        if (menu){
            if (ct.button_z){
                switch (num){
                    case 0 -> {menu = false;start_newgame = true;time = 2;}
                    case 1 -> {if (con){menu = false;time = 2;visible = false;}}
                    case 2 -> {System.exit(0);}
                }
            }
            
            if (ct.up){
                num -= 1;
                if (num < 0){
                    num = 0;
                }
            }
            if (ct.down){
                num += 1;
                if (num > 2){
                    num = 2;
                }
            }
        }
        
        if (start_newgame){
            MyJPanel.setMap(1);
            if (ct.button_z && time == 0) {
                switch (num) {
                    case 0 ->
                        animon = new Dogmon(1);
                    case 1 ->
                        animon = new Birdmon(1);
                    case 2 ->
                        animon = new Fishmon(1);
                    default -> {
                    }
                }
                visible = false;
            }

            if (ct.right) {
                MyStart.setNum(MyStart.getNum() + 1);
            } else if (ct.left) {
                MyStart.setNum(MyStart.getNum() - 1);
            }
        }
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        MyStart.num = num;
        
        
        if (start_newgame){
            if (MyStart.num < 0) {
                MyStart.num = 2;
            } else if (MyStart.num > 2) {
                MyStart.num = 0;
            }
        }
        
    }

    public static boolean isVisible() {
        return visible;
    }

    public static void setVisible(boolean visible) {
        MyStart.visible = visible;
    }
    
    public static int getTime(){
        return time;
    }
    
    public static void setTime(int time){
        MyStart.time = time;
        if (MyStart.time < 0){
            MyStart.time = 0;
        }
    }

    public static boolean isStart_newgame() {
        return start_newgame;
    }

    public static void setStart_newgame(boolean start_newgame) {
        MyStart.start_newgame = start_newgame;
    }

    public static boolean isMenu() {
        return menu;
    }

    public static void setMenu(boolean menu) {
        MyStart.menu = menu;
    }
    
    public static Animon getAnimon(){
        return animon;
    }

    public static void setAnimon(Animon animon) {
        MyStart.animon = animon;
    }
    
    public static void setCon(boolean con){
        MyStart.con = con;
    }
}
