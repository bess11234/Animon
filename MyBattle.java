
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class MyBattle {

    private static boolean start;

    private static boolean visible = false;

    private static boolean chose_animon = false;
    private static boolean chose_fight = false;
    private static boolean chose_inventory = false;
    private static boolean chose_run = false;
    private static int num_animon = 0;
    private static Rectangle menuPanel = new Rectangle();
    private static int time;
    private static boolean capture_animon;
    private static boolean end;

    private static String txt = "", txt2 = "";
    private static int save_money;
    private static int save_exp;
    private static int count_enemy;

    private static boolean turn_player = true;
    private static String skill_player;
    private static Animon[] listEnemy;
    private static Animon enemy;

    public static Animon[] getListEnemy() {
        return listEnemy;
    }

    public static void setListEnemy(Animon[] listEnemy) {
        MyBattle.listEnemy = listEnemy;
    }

    public static Animon getEnemy() {
        return enemy;
    }

    public static void setEnemy(Animon enemy) {
        MyBattle.enemy = enemy;
    }

    private static int num = 0;

    public static boolean isVisible() {
        return visible;
    }

    public synchronized static void setVisible(boolean visible) {
        MyBattle.visible = visible;
        MyBattle.chose_animon = true;
        start = true;
        end = false;
        num = 0;
        save_money = 0;
        save_exp = 0;
        
        count_enemy();
        if(count_enemy != 0){setEnemy(listEnemy[count_enemy-1]);}
        
        if (MyBattle.visible == false){
            MyJPanel.setEnemy(new Animon[3]);
        }

        menuPanel.x = 1360 / 4;
        menuPanel.y = 768 / 4;
        menuPanel.width = 1360 / 2;
        menuPanel.height = 768 / 2;
    }

    public static void paint(Graphics g, Player player) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(230, 230, 230));
        g2D.fillRect(0, 0, 1360, 768);

        g2D.drawImage(enemy.getImageBattleEnemy(), 0, 0, null);
        if (!chose_animon) {
            //GUI STATUS Enemy
            g2D.drawImage(new ImageIcon("enemy_frame.png").getImage(), 167 + 15 + 25, 768 / 3 + 15 - 768 / 4 - 40 + 15, null);
            g2D.setPaint(new Color(32, 56, 0));
            g2D.fillRoundRect(150 + 15 + 50, 768 / 3 + 15 - 768 / 4 - 30 + 15, 1360 / 3 - 30, 768 / 4 - 30 - 45, 10, 10);
            g2D.setPaint(new Color(206, 218, 176));
            g2D.fillRoundRect(150 + 15 + 50 + 5, 768 / 3 + 15 - 768 / 4 - 30 + 5 + 15, 1360 / 3 - 30 - 10, 768 / 4 - 30 - 10 - 45, 10, 10);
            g2D.setPaint(new Color(248, 248, 216));
            g2D.fillRoundRect(150 + 15 + 50 + 10, 768 / 3 + 15 - 768 / 4 - 30 + 10 + 15, 1360 / 3 - 30 - 20, 768 / 4 - 30 - 20 - 45, 10, 10);

            g2D.setPaint(Color.black);
            g2D.setFont(new Font("Dialog", 12, 20));

            g2D.drawString(enemy.getName(), 150 + 15 + 50 + 30, 768 / 3 + 15 - 768 / 4 - 30 + 10 + 15 + 35);
            g2D.drawString("Lv." + enemy.level, 150 + 15 + 50 + 30 + 200, 768 / 3 + 15 - 768 / 4 - 30 + 10 + 15 + 35);

            //HP
            g2D.setPaint(Color.red);
            g2D.fillRect(150 + 15 + 50 + 30 * 3 + 5 - 10, 768 / 3 + 17 - 768 / 4 - 30 + 10 + 5 + 15 + 35 + 15, 367 - 10 - 53, 37 - 15);
            g2D.setPaint(new Color(188, 251, 106));
            g2D.fillRect(150 + 15 + 50 + 30 * 3 + 5 - 10, 768 / 3 + 17 - 768 / 4 - 30 + 10 + 5 + 15 + 35 + 15, (367 - 10 - 53) * enemy.hp / enemy.maxHp, 37 - 15);

            g2D.setFont(new Font("Dialog", 12, 12));
            g2D.setPaint(Color.black);
            g2D.drawString(enemy.hp + "/" + enemy.maxHp, 150 + 15 + 50 + 30 + 367 / 2, 768 / 3 + 15 - 768 / 4 - 30 + 10 + 15 + 35 * 2 + 2);
            g2D.drawImage(new ImageIcon("Hp_Bar.png").getImage(), 150 + 15 + 50 + 30, 768 / 3 + 15 - 768 / 4 - 30 + 10 + 15 + 35 + 15, null);

            //GUI STATUS Player
            g2D.drawImage(player.getAnimals()[num_animon].getImageBattleMine(), 0, 0, null);
            g2D.drawImage(new ImageIcon("player_frame.png").getImage(), 1360 / 2 + 15 + 25, 768 - 768 / 4 + 15 - 768 / 3 - 40 + 15, null);
            g2D.setPaint(new Color(32, 56, 0));
            g2D.fillRoundRect(1360 / 2 + 15 + 50, 768 - 768 / 4 + 15 - 768 / 3 - 30 + 15, 1360 / 3 - 30, 768 / 4 - 30, 10, 10);
            g2D.setPaint(new Color(206, 218, 176));
            g2D.fillRoundRect(1360 / 2 + 15 + 50 + 5, 768 - 768 / 4 + 15 - 768 / 3 - 30 + 5 + 15, 1360 / 3 - 30 - 10, 768 / 4 - 30 - 10, 10, 10);
            g2D.setPaint(new Color(248, 248, 216));
            g2D.fillRoundRect(1360 / 2 + 15 + 50 + 10, 768 - 768 / 4 + 15 - 768 / 3 - 30 + 10 + 15, 1360 / 3 - 30 - 20, 768 / 4 - 30 - 20, 10, 10);

            g2D.setPaint(Color.black);
            g2D.setFont(new Font("Dialog", 12, 20));

            g2D.drawString(player.getAnimals()[num_animon].getName(), 1360 / 2 + 15 + 50 + 30, 768 - 768 / 4 + 15 - 768 / 3 - 30 + 10 + 15 + 35);
            g2D.drawString("Lv." + player.getAnimals()[num_animon].level, 1360 / 2 + 15 + 50 + 30 + 200, 768 - 768 / 4 + 15 - 768 / 3 - 30 + 10 + 15 + 35);

            //HP
            g2D.setPaint(Color.red);
            g2D.fillRect(1360 / 2 + 15 + 50 + 30 * 3 + 5 - 10, 768 - 768 / 4 + 17 - 768 / 3 - 30 + 10 + 5 + 15 + 35 + 15, 367 - 10 - 53, 37 - 15);
            g2D.setPaint(new Color(188, 251, 106));
            g2D.fillRect(1360 / 2 + 15 + 50 + 30 * 3 + 5 - 10, 768 - 768 / 4 + 17 - 768 / 3 - 30 + 10 + 5 + 15 + 35 + 15, (367 - 10 - 53) * player.getAnimals()[num_animon].hp / player.getAnimals()[num_animon].maxHp, 37 - 15);
            //Stamina
            g2D.setPaint(Color.GRAY);
            g2D.fillRect(1360 / 2 + 15 + 50 + 30 * 3 + 5 - 10 + 78, 768 - 768 / 4 + 17 - 768 / 3 - 30 + 10 + 5 + 15 + 35 + 15 + 27, (367 - 10 - 137), 37 - 15);
            g2D.setPaint(new Color(255, 227, 146));
            g2D.fillRect(1360 / 2 + 15 + 50 + 30 * 3 + 5 - 10 + 78, 768 - 768 / 4 + 17 - 768 / 3 - 30 + 10 + 5 + 15 + 35 + 15 + 27, (367 - 10 - 137) * player.getAnimals()[num_animon].stamina / player.getAnimals()[num_animon].maxStamina, 37 - 15);
            g2D.drawImage(new ImageIcon("Bar.png").getImage(), 1360 / 2 + 15 + 50 + 30, 768 - 768 / 4 + 15 - 768 / 3 - 30 + 10 + 15 + 35 + 15, null);

            g2D.setFont(new Font("Dialog", 12, 12));
            g2D.setPaint(Color.black);
            g2D.drawString(player.getAnimals()[num_animon].hp + "/" + player.getAnimals()[num_animon].maxHp, 1360 / 2 + 15 + 50 + 30 + 367 / 2, 768 - 768 / 4 + 15 - 768 / 3 - 30 + 10 + 15 + 35 * 2 + 2);
            g2D.drawString(player.getAnimals()[num_animon].stamina + "/" + player.getAnimals()[num_animon].maxStamina, 1360 / 2 + 15 + 50 + 30 + 225, 768 - 768 / 4 + 15 - 768 / 3 - 30 + 10 + 15 + 35 * 3 - 3);
        }
        //draw big GUI
        g2D.setPaint(new Color(37, 53, 67));
        g2D.fillRect(0, 768 - 768 / 3, 1360, 768);

        g2D.setPaint(new Color(201, 179, 37));
        g2D.fillRoundRect(0 + 5, 768 - 768 / 3 + 5, 1360 - 10, 768 / 3 - 10, 20, 20);
        g2D.setPaint(Color.white);
        g2D.fillRoundRect(0 + 15, 768 - 768 / 3 + 15, 1360 - 30, 768 / 3 - 30, 10, 10);
        g2D.setPaint(new Color(51, 102, 153));
        g2D.fillRoundRect(0 + 20, 768 - 768 / 3 + 20, 1360 - 40, 768 / 3 - 40, 10, 10);
        //draw half GUI
        g2D.setPaint(new Color(37, 53, 67));
        g2D.fillRoundRect(1360 / 2, 768 - 768 / 3, 1360, 768, 20, 20);
        g2D.setPaint(new Color(135, 135, 212));
        g2D.fillRoundRect(1360 / 2 + 5, 768 - 768 / 3 + 5, 1360 / 2 - 10, 768 / 3 - 10, 10, 10);
        g2D.setPaint(new Color(92, 92, 125));
        g2D.fillRect(1360 / 2 + 10, 768 - 768 / 3 + 10, 1360 / 2 - 20, 768 / 3 - 20);
        g2D.setPaint(new Color(250, 250, 250));
        g2D.fillRoundRect(1360 / 2 + 15, 768 - 768 / 3 + 15, 1360 / 2 - 30, 768 / 3 - 30, 10, 10);

        //draw select
        g2D.setPaint(Color.black);

        g2D.setFont(new Font("Dialog", 12, 40));
        g2D.drawString("FIGHT", (1360 / 2 + 20) + (340 - 40) / 2 - 50, (768 - 768 / 3 + 20) + (128 - 40) / 2 + 10);
        g2D.drawString("INVENTORY", (1360 / 2 + 20 + 340) + (340 - 40) / 2 - 110, (768 - 768 / 3 + 20) + (128 - 40) / 2 + 10);
        g2D.drawString("ANIMON", (1360 / 2 + 20) + (340 - 40) / 2 - 70, (768 - 768 / 3 + 20 + 128) + (128 - 40) / 2 + 10);
        g2D.drawString("RUN", (1360 / 2 + 20 + 340) + (340 - 40) / 2 - 45, (768 - 768 / 3 + 20 + 128) + (128 - 40) / 2 + 10);

        //Text in the GUI
        g2D.setPaint(Color.white);
        g2D.drawString(txt, 0 + 20 + 20, 768 - 768 / 3 + 20 + 50 * 1 + 50);
        g2D.drawString(txt2, 0 + 20 + 20, 768 - 768 / 3 + 20 + 50 * 3 + 25);

        g2D.setFont(new Font("Dialog", 12, 12));

        g2D.setStroke(new BasicStroke(5));
        if (!MyBattle.chose_animon && !MyBattle.chose_inventory && !MyBattle.chose_fight && !end && !capture_animon) {
            g2D.setPaint(Color.BLACK);
            if (MyBattle.getNum() == 0) {
                g2D.drawRoundRect(1360 / 2 + 20, 768 - 768 / 3 + 20, (340 - 40), 128 - 40, 10, 10);
            }
            if (MyBattle.getNum() == 1) {
                g2D.drawRoundRect(1360 / 2 + 20 + 340, 768 - 768 / 3 + 20, (340 - 40), 128 - 40, 10, 10);
            }
            if (MyBattle.getNum() == 2) {
                g2D.drawRoundRect(1360 / 2 + 20, 768 - 768 / 3 + 20 + 128, (340 - 40), 128 - 40, 10, 10);
            }
            if (MyBattle.getNum() == 3) {
                g2D.drawRoundRect(1360 / 2 + 20 + 340, 768 - 768 / 3 + 20 + 128, (340 - 40), 128 - 40, 10, 10);
            }
        }

        if (MyBattle.chose_animon) {
            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRect(menuPanel.x, menuPanel.y, menuPanel.width, menuPanel.height);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRect(menuPanel.x + 5, menuPanel.y + 5, menuPanel.width - 10, menuPanel.height - 10);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRect(menuPanel.x + 10, menuPanel.y + 10, menuPanel.width - 20, menuPanel.height - 20);
            g2D.setPaint(new Color(128, 50, 11));

            g2D.drawLine(menuPanel.x + 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3, menuPanel.x + menuPanel.width / 4 + menuPanel.width - menuPanel.width / 4 - 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3);
            g2D.drawLine(menuPanel.x + 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3 + (menuPanel.height - 20) / 3, menuPanel.x + menuPanel.width / 4 + menuPanel.width - menuPanel.width / 4 - 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3 + (menuPanel.height - 20) / 3);
            g2D.setFont(new Font("Dialog", 12, 15));
            for (int i = 0; i < player.getCountAnimals(); i++) {
                
                if (player.getAnimals()[i].dead){
                    g2D.setPaint(Color.RED);
                    g2D.drawString("Dead", menuPanel.x + menuPanel.width / 7 - 40, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                    g2D.setPaint(new Color(128, 50, 11));
                }

                g2D.drawString("Level : " + player.getAnimals()[i].level + "", menuPanel.x + menuPanel.width / 4 + 15 - 40, menuPanel.y + 10 + 25 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawImage(player.getAnimals()[i].getImage(), menuPanel.x + menuPanel.width / 4 + 20 - 45, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 3) * i, null);
                g2D.drawString(player.getAnimals()[i].getName() + "", menuPanel.x + menuPanel.width / 4 + 20 + 100 - 40, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("HP : " + player.getAnimals()[i].hp + "/" + player.getAnimals()[i].maxHp + "", menuPanel.x + menuPanel.width / 4 + 20 + 200 - 40, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("Stamina : " + player.getAnimals()[i].stamina + "/" + player.getAnimals()[i].maxStamina + "", menuPanel.x + menuPanel.width / 4 + 20 + 325 - 40, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("Exp : " + player.getAnimals()[i].exp + "/" + player.getAnimals()[i].maxExp + "", menuPanel.x + menuPanel.width / 4 + 15 - 40, menuPanel.y + 10 + 25 + 75 + ((menuPanel.height - 20) / 3) * i);

            }
            g2D.setFont(new Font("Dialog", 12, 12));
            g2D.setColor(Color.white);
            g2D.setStroke(new BasicStroke(2));
            switch (MyBattle.num) {
                case 0 ->
                    g2D.drawRect(menuPanel.x + 20, menuPanel.y + 20, (menuPanel.width - 40), (menuPanel.height) / 3 - 30);
                case 1 ->
                    g2D.drawRect(menuPanel.x + 20, menuPanel.y + 20 + ((menuPanel.height - 20) / 3) * 1, (menuPanel.width - 40), (menuPanel.height) / 3 - 30);
                case 2 ->
                    g2D.drawRect(menuPanel.x + 20, menuPanel.y + 20 + ((menuPanel.height - 20) / 3) * 2, (menuPanel.width - 40), (menuPanel.height) / 3 - 30);
            }
            g2D.setStroke(new BasicStroke(1));
            g2D.setPaint(new Color(128, 50, 11));

        }

        if (MyBattle.chose_fight) {

            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRect(menuPanel.x, menuPanel.y, menuPanel.width, menuPanel.height);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRect(menuPanel.x + 5, menuPanel.y + 5, menuPanel.width - 10, menuPanel.height - 10);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRect(menuPanel.x + 10, menuPanel.y + 10, menuPanel.width - 20, menuPanel.height - 20);
            g2D.setPaint(new Color(70, 73, 82));

            g2D.setStroke(new BasicStroke(2));

            g2D.setFont(new Font("Dialog", 12, 15));

            g2D.drawString("Normal Attack", menuPanel.x + 50, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 6) * 0);

            g2D.setPaint(new Color(255, 132, 71));
            g2D.drawString(String.format("%5d", 0), menuPanel.x + 50 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 6) * 0);

            for (int i = 0; i < 4; i++) {
                String tmp = player.getAnimals()[num_animon].getListSkill()[i];
                int stamina = (int) player.getAnimals()[num_animon].skill.get(tmp) * player.getAnimals()[num_animon].level;

                switch (i) {
                    case 0 -> {
                        g2D.setPaint(new Color(70, 73, 82));
                        g2D.drawString(tmp, menuPanel.x + 50 + menuPanel.width / 2, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8) * 0);
                        g2D.setPaint(new Color(255, 132, 71));
                        g2D.drawString(String.format("%5d", stamina), menuPanel.x + 50 + menuPanel.width / 2 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8) * 0);
                    }
                    case 1 -> {
                        g2D.setPaint(new Color(70, 73, 82));
                        g2D.drawString(tmp, menuPanel.x + 50, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8));
                        g2D.setPaint(new Color(255, 132, 71));
                        g2D.drawString(String.format("%5d", stamina), menuPanel.x + 50 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8));
                    }
                    case 2 -> {
                        g2D.setPaint(new Color(70, 73, 82));
                        g2D.drawString(tmp, menuPanel.x + 50 + menuPanel.width / 2, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8));
                        g2D.setPaint(new Color(255, 132, 71));
                        g2D.drawString(String.format("%5d", stamina), menuPanel.x + 50 + menuPanel.width / 2 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8));
                    }
                    case 3 -> {
                        g2D.setPaint(new Color(70, 73, 82));
                        g2D.drawString(tmp, menuPanel.x + 50, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8) * 2);
                        g2D.setPaint(new Color(255, 132, 71));
                        g2D.drawString(String.format("%5d", stamina), menuPanel.x + 50 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8) * 2);
                    }
                }

            }
            g2D.setFont(new Font("Dialog", 12, 12));
            g2D.setStroke(new BasicStroke(2));
            g2D.setPaint(Color.white);
            switch (MyBattle.num) {
                case 0 ->
                    g2D.drawRect(menuPanel.x + 20 + 10, menuPanel.y + 20, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 1 ->
                    g2D.drawRect(menuPanel.x + 20 + (menuPanel.width - 40) / 2 + 30, menuPanel.y + 20, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 2 ->
                    g2D.drawRect(menuPanel.x + 20 + 10, menuPanel.y + 20 + ((menuPanel.height - 20) / 8) * 1, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 3 ->
                    g2D.drawRect(menuPanel.x + 20 + (menuPanel.width - 40) / 2 + 30, menuPanel.y + 20 + ((menuPanel.height - 20) / 8) * 1, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 4 ->
                    g2D.drawRect(menuPanel.x + 20 + 10, menuPanel.y + 20 + ((menuPanel.height - 20) / 8) * 2, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 5 ->
                    g2D.drawRect(menuPanel.x + 20 + (menuPanel.width - 40) / 2 + 30, menuPanel.y + 20 + ((menuPanel.height - 20) / 8) * 2, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);

            }
            g2D.setStroke(new BasicStroke(1));
            g2D.setPaint(new Color(70, 73, 82));
        }

        if (MyBattle.chose_inventory) {

            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRect(menuPanel.x, menuPanel.y, menuPanel.width, menuPanel.height);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRect(menuPanel.x + 5, menuPanel.y + 5, menuPanel.width - 10, menuPanel.height - 10);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRect(menuPanel.x + 10, menuPanel.y + 10, menuPanel.width - 20, menuPanel.height - 20);
            g2D.setPaint(new Color(128, 50, 11));

            g2D.setStroke(new BasicStroke(2));

            g2D.setFont(new Font("Dialog", 12, 15));

            g2D.setPaint(new Color(70, 73, 82));
            g2D.drawString("Potion HP(S)", menuPanel.x + 50, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 6) * 0);
            //g2D.setPaint(new Color(255, 132, 71));
            g2D.drawString(String.format("%5d", player.getCount_potionHPS()), menuPanel.x + 50 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 6) * 0);

            g2D.setPaint(new Color(70, 73, 82));
            g2D.drawString("Potion HP(L)", menuPanel.x + 50 + menuPanel.width / 2, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8) * 0);
            g2D.drawString(String.format("%5d", player.getCount_potionHPL()), menuPanel.x + 50 + menuPanel.width / 2 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8) * 0);

            g2D.setPaint(new Color(70, 73, 82));
            g2D.drawString("Potion Stamina(S)", menuPanel.x + 50, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8));
            g2D.drawString(String.format("%5d", player.getCount_potionSTS()), menuPanel.x + 50 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8));

            g2D.setPaint(new Color(70, 73, 82));
            g2D.drawString("Potion Stamina(L)", menuPanel.x + 50 + menuPanel.width / 2, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8));
            g2D.drawString(String.format("%5d", player.getCount_potionSTL()), menuPanel.x + 50 + menuPanel.width / 2 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8));

            g2D.setPaint(new Color(70, 73, 82));
            g2D.drawString("AniBall", menuPanel.x + 50, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8) * 2);
            g2D.drawString(String.format("%5d", player.getCount_aniball()), menuPanel.x + 50 + 200, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 8) * 2);

            g2D.setFont(new Font("Dialog", 12, 12));
            g2D.setStroke(new BasicStroke(2));
            g2D.setPaint(Color.white);
            switch (MyBattle.num) {
                case 0 ->
                    g2D.drawRect(menuPanel.x + 20 + 10, menuPanel.y + 20, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 1 ->
                    g2D.drawRect(menuPanel.x + 20 + (menuPanel.width - 40) / 2 + 30, menuPanel.y + 20, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 2 ->
                    g2D.drawRect(menuPanel.x + 20 + 10, menuPanel.y + 20 + ((menuPanel.height - 20) / 8) * 1, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 3 ->
                    g2D.drawRect(menuPanel.x + 20 + (menuPanel.width - 40) / 2 + 30, menuPanel.y + 20 + ((menuPanel.height - 20) / 8) * 1, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 4 ->
                    g2D.drawRect(menuPanel.x + 20 + 10, menuPanel.y + 20 + ((menuPanel.height - 20) / 8) * 2, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);
                case 5 ->
                    g2D.drawRect(menuPanel.x + 20 + (menuPanel.width - 40) / 2 + 30, menuPanel.y + 20 + ((menuPanel.height - 20) / 8) * 2, (menuPanel.width - 40) / 2 - 50, (menuPanel.height) / 6 - 30);

            }
            g2D.setStroke(new BasicStroke(1));
            g2D.setPaint(new Color(70, 73, 82));
        }

        if (end) {
            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRect(1360 / 2 - 1360 / 8, 768 / 2 - 768 / 8, 1360 / 4, 768 / 4);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRect((1360 / 2 - 1360 / 8) + 5, (768 / 2 - 768 / 8) + 5, 1360 / 4 - 10, 768 / 4 - 10);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRect((1360 / 2 - 1360 / 8) + 10, (768 / 2 - 768 / 8) + 10, 1360 / 4 - 20, 768 / 4 - 20);
            g2D.setPaint(new Color(128, 50, 11));

            g2D.setFont(new Font("Dialog", 12, 25));
            g2D.drawString("+ " + save_money + " Coin(s)", (1360 / 2 - 1360 / 8) + 1360 / 4 / 2 - 25 * 2 - 20, (768 / 2 - 768 / 8) + 768 / 4 / 2 - 30 + 20);
            g2D.drawString("+ " + save_exp + " Exp", (1360 / 2 - 1360 / 8) + 1360 / 4 / 2 - 25 * 2 - 20, (768 / 2 - 768 / 8) + 768 / 4 / 2 + 20);
            g2D.setFont(new Font("Dialog", 12, 12));
        }

        if (capture_animon) {
            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRect(menuPanel.x, menuPanel.y, menuPanel.width, menuPanel.height);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRect(menuPanel.x + 5, menuPanel.y + 5, menuPanel.width - 10, menuPanel.height - 10);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRect(menuPanel.x + 10, menuPanel.y + 10, menuPanel.width - 20, menuPanel.height - 20);
            g2D.setPaint(new Color(128, 50, 11));

            g2D.drawLine(menuPanel.x + 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3, menuPanel.x + menuPanel.width / 4 + menuPanel.width - menuPanel.width / 4 - 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3);
            g2D.drawLine(menuPanel.x + 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3 + (menuPanel.height - 20) / 3, menuPanel.x + menuPanel.width / 4 + menuPanel.width - menuPanel.width / 4 - 10, menuPanel.y + 10 + (menuPanel.height - 20) / 3 + (menuPanel.height - 20) / 3);
            g2D.setFont(new Font("Dialog", 12, 15));

            for (int i = 0; i < player.getCountAnimals(); i++) {
                
                if (player.getAnimals()[i].dead){
                    g2D.setPaint(Color.RED);
                    g2D.drawString("Dead", menuPanel.x + menuPanel.width / 7 - 40, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                    g2D.setPaint(new Color(128, 50, 11));
                }

                g2D.drawString("Level : " + player.getAnimals()[i].level + "", menuPanel.x + menuPanel.width / 4 + 15 - 40, menuPanel.y + 10 + 25 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawImage(player.getAnimals()[i].getImage(), menuPanel.x + menuPanel.width / 4 + 20 - 45, menuPanel.y + 10 + 30 + ((menuPanel.height - 20) / 3) * i, null);
                g2D.drawString(player.getAnimals()[i].getName() + "", menuPanel.x + menuPanel.width / 4 + 20 + 100 - 40, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("HP : " + player.getAnimals()[i].hp + "/" + player.getAnimals()[i].maxHp + "", menuPanel.x + menuPanel.width / 4 + 20 + 200 - 40, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("Stamina : " + player.getAnimals()[i].stamina + "/" + player.getAnimals()[i].maxStamina + "", menuPanel.x + menuPanel.width / 4 + 20 + 325 - 40, menuPanel.y + 10 + 30 + 35 + ((menuPanel.height - 20) / 3) * i);
                g2D.drawString("Exp : " + player.getAnimals()[i].exp + "/" + player.getAnimals()[i].maxExp + "", menuPanel.x + menuPanel.width / 4 + 15 - 40, menuPanel.y + 10 + 25 + 75 + ((menuPanel.height - 20) / 3) * i);

            }
            g2D.setFont(new Font("Dialog", 12, 12));
            g2D.setStroke(new BasicStroke(2));
            g2D.setPaint(Color.white);
            switch (MyBattle.num) {
                case 0 ->{
                    g2D.drawRect(menuPanel.x + 20, menuPanel.y + 20, (menuPanel.width - 40), (menuPanel.height) / 3 - 30);
                    
                    g2D.setPaint(new Color(254, 147, 7));
                    g2D.fillRect(menuPanel.x + menuPanel.width + 20 -10, menuPanel.y + (menuPanel.height/3)*0, 200, menuPanel.height/3);
                    g2D.setPaint(new Color(128, 59, 0));
                    g2D.fillRect(menuPanel.x + 5 + menuPanel.width + 20 -10, menuPanel.y + 5 + (menuPanel.height/3)*0, 200 - 10, menuPanel.height/3 - 10);
                    g2D.setPaint(new Color(255, 210, 133));
                    g2D.fillRect(menuPanel.x + 10 + menuPanel.width + 20 -10, menuPanel.y + 10 + (menuPanel.height/3)*0, 200 - 20, menuPanel.height/3 - 20);
                    g2D.setPaint(new Color(128, 50, 11));
                    
                    g2D.drawString("Level : "+enemy.level, menuPanel.x + 10 + menuPanel.width + 20 -10 + (200-20)/2 -25, menuPanel.y + 10 + (menuPanel.height/3)*0 + (menuPanel.height/3 - 20)/2 -25 - 5);
                    g2D.drawImage(enemy.getImage(), menuPanel.x + 10 + menuPanel.width + 20 -10 + (200-20)/2 -25, menuPanel.y + 10 + (menuPanel.height/3)*0 + (menuPanel.height/3 - 20)/2 -25, null);
                }
                case 1 ->{
                    g2D.drawRect(menuPanel.x + 20, menuPanel.y + 20 + ((menuPanel.height - 20) / 3) * 1, (menuPanel.width - 40), (menuPanel.height) / 3 - 30);
                
                    g2D.setPaint(new Color(254, 147, 7));
                    g2D.fillRect(menuPanel.x + menuPanel.width + 20 -10, menuPanel.y + (menuPanel.height/3)*1, 200, menuPanel.height/3);
                    g2D.setPaint(new Color(128, 59, 0));
                    g2D.fillRect(menuPanel.x + 5 + menuPanel.width + 20 -10, menuPanel.y + 5 + (menuPanel.height/3)*1, 200 - 10, menuPanel.height/3 - 10);
                    g2D.setPaint(new Color(255, 210, 133));
                    g2D.fillRect(menuPanel.x + 10 + menuPanel.width + 20 -10, menuPanel.y + 10 + (menuPanel.height/3)*1, 200 - 20, menuPanel.height/3 - 20);
                    g2D.setPaint(new Color(128, 50, 11));
                    g2D.drawString("Level : "+enemy.level, menuPanel.x + 10 + menuPanel.width + 20 -10 + (200-20)/2 -25, menuPanel.y + 10 + (menuPanel.height/3)*1 + (menuPanel.height/3 - 20)/2 -25 - 5);
                    g2D.drawImage(enemy.getImage(), menuPanel.x + 10 + menuPanel.width + 20 -10 + (200-20)/2 -25, menuPanel.y + 10 + (menuPanel.height/3)*1 + (menuPanel.height/3 - 20)/2 -25, null);
                }
                case 2 ->{
                    g2D.drawRect(menuPanel.x + 20, menuPanel.y + 20 + ((menuPanel.height - 20) / 3) * 2, (menuPanel.width - 40), (menuPanel.height) / 3 - 30);
                
                    g2D.setPaint(new Color(254, 147, 7));
                    g2D.fillRect(menuPanel.x + menuPanel.width + 20 -10, menuPanel.y + (menuPanel.height/3)*2, 200, menuPanel.height/3);
                    g2D.setPaint(new Color(128, 59, 0));
                    g2D.fillRect(menuPanel.x + 5 + menuPanel.width + 20 -10, menuPanel.y + 5 + (menuPanel.height/3)*2, 200 - 10, menuPanel.height/3 - 10);
                    g2D.setPaint(new Color(255, 210, 133));
                    g2D.fillRect(menuPanel.x + 10 + menuPanel.width + 20 -10, menuPanel.y + 10 + (menuPanel.height/3)*2, 200 - 20, menuPanel.height/3 - 20);
                    g2D.setPaint(new Color(128, 50, 11));
                    g2D.drawString("Level : "+enemy.level, menuPanel.x + 10 + menuPanel.width + 20 -10 + (200-20)/2 -25, menuPanel.y + 10 + (menuPanel.height/3)*2 + (menuPanel.height/3 - 20)/2 -25 - 5);
                    g2D.drawImage(enemy.getImage(), menuPanel.x + 10 + menuPanel.width + 20 -10 + (200-20)/2 -25, menuPanel.y + 10 + (menuPanel.height/3)*2 + (menuPanel.height/3 - 20)/2 -25, null);
                }
            }
            g2D.setStroke(new BasicStroke(1));
            g2D.setPaint(new Color(128, 50, 11));
        }
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        MyBattle.time = time;
        if (MyBattle.time < 0) {
            MyBattle.time = 0;
        }
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        MyBattle.num = num;
    }

    public static void EnemyAction(Player player) {
        turn_player = true;
        int num = new Random().nextInt(0, 9);
        int skill_num = new Random().nextInt(0, 4);
        if (!enemy.dead) {
            if (num <= 7) {
                enemy.nomalAttack(player.getAnimals()[num_animon]);
                txt = "Enemy use Normal Attack";
                txt2 = "DMG " + (int) (enemy.baseAtk / 2);
            } else {
                if (enemy.getStamina() >= (int) enemy.skill.get(enemy.getListSkill()[skill_num])) {
                    enemy.useskill(player.getAnimals()[num_animon], enemy.getListSkill()[skill_num]);
                } else {
                    enemy.nomalAttack(player.getAnimals()[num_animon]);
                    txt = "Enemy use Normal Attack";
                    txt2 = "DMG " + (int) (enemy.baseAtk / 2);
                }
            }
            time = 4;
            if (player.getAnimals()[num_animon].dead) {
                time = 8;
            }
            
        } else {
            if (Deadall_enemy()){
                end = true;
                enemy.dead = false;
                save_money += enemy.dropMoney();
                save_exp += enemy.level * 3;
                player.setMoney(player.getMoney() + save_money);
                player.highestLevel();
            }else{
                save_money += enemy.dropMoney();
                save_exp += enemy.level * 3;
            }
            
        }
    }
    
    public synchronized static void count_enemy(){
        int count_animon = 0;
        for (int i = 0; i < listEnemy.length; i++) {
            if (listEnemy[i] != null){
                if (!listEnemy[i].dead_before){
                    count_animon ++;
                }
            }
        }
        count_enemy = count_animon;
    }
    
    public static boolean Deadall_enemy(){
        count_enemy();
        
        if (count_enemy > 0){
            for (int i = 0; i < count_enemy; i++) {
                setEnemy(listEnemy[i]);
            }
            return false;
        }
        return true;
    }

    public static boolean Deadall(Player player) {
        boolean deadall = false;

        for (int i = 0; i < player.getCountAnimals(); i++) {
            deadall = player.getAnimals()[i].dead;
            if (!deadall) {
                return deadall;
            }
        }
        System.out.println(deadall);
        return deadall;
    }

    public static void selected(MyController ct, Player player) {
        if (count_enemy == 0){
            end = true;
        }
        
        if (player.getAnimals()[num_animon].dead) {
            if (!Deadall(player)) {
                start = true;
                MyBattle.chose_animon = true;
            } else if (time == 0) {
                MyBattle.setVisible(false);
            }
        }

        if (capture_animon && time == 0) {
            if (ct.up) {
                MyBattle.setNum(num - 1);
                if (num < 0) {
                    num = 0;
                }
            } else if (ct.down) {
                MyBattle.setNum(num + 1);
                if (num > player.getCountAnimals() - 1) {
                    num = player.getCountAnimals() - 1;
                }
            }

            if (ct.button_z) {
                player.setAnimals(enemy, num);
                capture_animon = false;
                turn_player = false;
                enemy.dead = true;
                time = 4;
            }
            
            if (ct.button_x) {
                capture_animon = false;
                turn_player = false;
                enemy.dead = true;
                time = 4;
            }
        }

        if (end) {
            if (ct.button_x || ct.button_z && time == 0) {
                MyBattle.setVisible(false);
            }
        }

        if (MyBattle.chose_animon && time == 0) {
            if (ct.up) {
                MyBattle.setNum(num - 1);
                if (num < 0) {
                    num = 0;
                }
            } else if (ct.down) {
                MyBattle.setNum(num + 1);
                if (num > player.getCountAnimals() - 1) {
                    num = player.getCountAnimals() - 1;
                }
            }
            if (ct.button_z && start || ct.button_x) {
                num_animon = num;
                chose_animon = false;
                num = 0;
                time = 2;
                txt = "What the";
                txt2 = player.getAnimals()[num_animon].getName() + " Doing?";
                start = false;

            } else if (ct.button_z) {
                num_animon = num;
                chose_animon = false;
                num = 0;
                time = 2;

                turn_player = false;
            }

        }

        if (MyBattle.chose_fight && time == 0) {
            if (ct.down && num == 0) {
                MyBattle.setNum(2);
            } else if (ct.right && num == 0) {
                MyBattle.setNum(1);
            } else if (ct.down && num == 1) {
                MyBattle.setNum(3);
            } else if (ct.left && num == 1) {
                MyBattle.setNum(0);
            } else if (ct.up && num == 2) {
                MyBattle.setNum(0);
            } else if (ct.right && num == 2) {
                MyBattle.setNum(3);
            } else if (ct.down && num == 2) {
                MyBattle.setNum(4);
            } else if (ct.up && num == 3) {
                MyBattle.setNum(1);
            } else if (ct.left && num == 3) {
                MyBattle.setNum(2);
            } else if (ct.up && num == 4) {
                MyBattle.setNum(2);
            }

            if (ct.button_x) {
                chose_fight = false;
                num = 0;
            }
            
            if (time == 0) {
                if (ct.button_z && num == 0) {
                    turn_player = false;
                    player.getAnimals()[num_animon].nomalAttack(enemy);
                    txt = player.getAnimals()[num_animon].getName() + " use Normal Attack";
                    //fix call DMG
                    txt2 = "DMG " + (int) (player.getAnimals()[num_animon].baseAtk / 2);
                    chose_fight = false;
                    num = 0;
                    time = 8;
                    //System.out.println(player.getAnimals()[num_animon].exp+"/"+player.getAnimals()[num_animon].maxExp);
                } else if (ct.button_z && num == 1 && player.getAnimals()[num_animon].stamina >= (int) player.getAnimals()[num_animon].skill.get(player.getAnimals()[num_animon].getListSkill()[0]) * player.getAnimals()[num_animon].level) {
                    turn_player = false;
                    player.getAnimals()[num_animon].useskill(enemy, player.getAnimals()[num_animon].getListSkill()[0]);
                    txt = player.getAnimals()[num_animon].getName() + " use " + player.getAnimals()[num_animon].getListSkill()[0];
                    txt2 = "DMG " + (int) player.getAnimals()[num_animon].skill.get(player.getAnimals()[num_animon].getListSkill()[0]) * player.getAnimals()[num_animon].baseAtk;
                    chose_fight = false;
                    num = 0;
                    time = 8;
                } else if (ct.button_z && num == 2 && player.getAnimals()[num_animon].stamina >= (int) player.getAnimals()[num_animon].skill.get(player.getAnimals()[num_animon].getListSkill()[1]) * player.getAnimals()[num_animon].level) {
                    turn_player = false;
                    player.getAnimals()[num_animon].useskill(enemy, player.getAnimals()[num_animon].getListSkill()[1]);
                    txt = player.getAnimals()[num_animon].getName() + " use " + player.getAnimals()[num_animon].getListSkill()[1];
                    txt2 = "DMG " + (int) player.getAnimals()[num_animon].skill.get(player.getAnimals()[num_animon].getListSkill()[1]) * player.getAnimals()[num_animon].baseAtk;
                    chose_fight = false;
                    num = 0;
                    time = 8;
                } else if (ct.button_z && num == 3 && player.getAnimals()[num_animon].stamina >= (int) player.getAnimals()[num_animon].skill.get(player.getAnimals()[num_animon].getListSkill()[2]) * player.getAnimals()[num_animon].level) {
                    turn_player = false;
                    player.getAnimals()[num_animon].useskill(enemy, player.getAnimals()[num_animon].getListSkill()[2]);
                    txt = player.getAnimals()[num_animon].getName() + " use " + player.getAnimals()[num_animon].getListSkill()[2];
                    txt2 = "DMG " + (int) player.getAnimals()[num_animon].skill.get(player.getAnimals()[num_animon].getListSkill()[2]) * player.getAnimals()[num_animon].baseAtk;

                    chose_fight = false;
                    num = 0;
                    time = 8;
                } else if (ct.button_z && num == 4 && player.getAnimals()[num_animon].stamina >= (int) player.getAnimals()[num_animon].skill.get(player.getAnimals()[num_animon].getListSkill()[3]) * player.getAnimals()[num_animon].level) {
                    turn_player = false;
                    player.getAnimals()[num_animon].useskill(enemy, player.getAnimals()[num_animon].getListSkill()[3]);
                    txt = player.getAnimals()[num_animon].getName() + " use " + player.getAnimals()[num_animon].getListSkill()[3];
                    txt2 = "DMG " + (int) player.getAnimals()[num_animon].skill.get(player.getAnimals()[num_animon].getListSkill()[3]) * player.getAnimals()[num_animon].baseAtk;

                    chose_fight = false;
                    num = 0;
                    time = 8;
                }
                
                if (enemy.dead) {
                    time = 4;
                }
            }

        }

        if (MyBattle.chose_inventory && time == 0) {
            if (ct.down && num == 0) {
                MyBattle.setNum(2);
            } else if (ct.right && num == 0) {
                MyBattle.setNum(1);
            } else if (ct.down && num == 1) {
                MyBattle.setNum(3);
            } else if (ct.left && num == 1) {
                MyBattle.setNum(0);
            } else if (ct.up && num == 2) {
                MyBattle.setNum(0);
            } else if (ct.right && num == 2) {
                MyBattle.setNum(3);
            } else if (ct.down && num == 2) {
                MyBattle.setNum(4);
            } else if (ct.up && num == 3) {
                MyBattle.setNum(1);
            } else if (ct.left && num == 3) {
                MyBattle.setNum(2);
            } else if (ct.up && num == 4) {
                MyBattle.setNum(2);
            }

            if (ct.button_x) {
                chose_inventory = false;
                num = 1;
            }

            if (time == 0) {
                if (ct.button_z && num == 0 && player.getCount_potionHPS() > 0) {
                    turn_player = false;
                    player.getAnimals()[num_animon].useItem(player.getInventory()[0][player.getCount_potionHPS() - 1], player, null);

                    txt = player.getAnimals()[num_animon].getName() + " use Potion HP(S)";
                    txt2 = "Heals " + ((int) player.getAnimals()[num_animon].maxHp * 0.4);

                    chose_inventory = false;
                    num = 0;
                    time = 8;
                    //System.out.println(player.getAnimals()[num_animon].exp+"/"+player.getAnimals()[num_animon].maxExp);
                }
                if (ct.button_z && num == 1 && player.getCount_potionHPL() > 0) {
                    turn_player = false;
                    player.getAnimals()[num_animon].useItem(player.getInventory()[1][player.getCount_potionHPL() - 1], player, null);
                    txt = player.getAnimals()[num_animon].getName() + " use Potion HP(L)";
                    txt2 = "Heals " + ((int) player.getAnimals()[num_animon].maxHp * 0.7);

                    chose_inventory = false;
                    num = 0;
                    time = 8;
                }

                if (ct.button_z && num == 2 && player.getCount_potionSTS() > 0) {
                    turn_player = false;
                    player.getAnimals()[num_animon].useItem(player.getInventory()[2][player.getCount_potionSTS() - 1], player, null);
                    txt = player.getAnimals()[num_animon].getName() + " use Potion Stamina(S)";
                    txt2 = "Recover " + ((int) player.getAnimals()[num_animon].maxStamina * 0.4);

                    chose_inventory = false;
                    num = 0;
                    time = 8;
                }

                if (ct.button_z && num == 3 && player.getCount_potionSTL() > 0) {
                    turn_player = false;
                    player.getAnimals()[num_animon].useItem(player.getInventory()[3][player.getCount_potionSTL() - 1], player, null);
                    txt = player.getAnimals()[num_animon].getName() + " use Potion Stamina(L)";
                    txt2 = "Recover " + ((int) player.getAnimals()[num_animon].maxStamina * 0.7);

                    chose_inventory = false;
                    num = 0;
                    time = 8;
                }

                if (ct.button_z && num == 4 && player.getCount_aniball() > 0) {
                    turn_player = false;
                    if (player.getAnimals()[num_animon].useItem(player.getInventory()[4][player.getCount_aniball() - 1], player, enemy)) {
                        //I want to Change Name!!!
                        if (player.getCountAnimals() == 3) {
                            turn_player = true;
                            capture_animon = true;
                            enemy.dead();
                        } else {
                            player.setAnimals(enemy, player.getCountAnimals());
                            capture_animon = false;
                            enemy.dead();
                        }
                        txt = "Capture Successful";
                        txt2 = "";

                    } else {
                        txt = "Capture Failled Successful";
                        txt2 = "";
                    }

                    chose_inventory = false;
                    num = 0;
                    time = 8;
                }
            }

        }

        if ((!turn_player && time == 0) && !end) {
            EnemyAction(player);
        }

        //0 = Fight; 1 = Inventory; 2 = Animon; 3 = Run;
        if (!MyBattle.chose_animon && !MyBattle.chose_inventory && !MyBattle.chose_fight && !end && !capture_animon) {
            if (ct.down && num == 0) {
                MyBattle.setNum(2);
            }
            if (ct.right && num == 0) {
                MyBattle.setNum(1);
            }
            if (ct.down && num == 1) {
                MyBattle.setNum(3);
            }
            if (ct.left && num == 1) {
                MyBattle.setNum(0);
            }
            if (ct.up && num == 2) {
                MyBattle.setNum(0);
            }
            if (ct.right && num == 2) {
                MyBattle.setNum(3);
            }
            if (ct.up && num == 3) {
                MyBattle.setNum(1);
            }
            if (ct.left && num == 3) {
                MyBattle.setNum(2);
            }

            if (ct.button_z && num == 0 && time == 0) {
                chose_fight = true;
                num = 0;
                time = 2;
            }

            if (ct.button_z && num == 1 && time == 0) {
                chose_inventory = true;
                num = 0;
                time = 2;
            }

            if (ct.button_z && num == 2 && time == 0) {
                chose_animon = true;
                num = 0;
                time = 2;
            }
            if (ct.button_z && num == 3 && time == 0) {
                MyBattle.setVisible(false);
                enemy.setHp(enemy.maxHp);
                enemy.setStamina(enemy.maxStamina);
                MyJPanel.setTime_to_interact(30);
                num = 0;
                time = 2;
            }
        }

    }

    public static int getCount_enemy() {
        return count_enemy;
    }
    
    

}
