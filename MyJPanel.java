/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.*;
import java.util.Random;
import javax.swing.*;

public class MyJPanel extends JPanel implements Runnable {

    private MyController ct;
    private Thread thread_main;
    private Thread thread_enemy_1, thread_enemy_2, thread_enemy_3, thread_enemy_4, thread_enemy_5, thread_enemy_6, thread_enemy_7, thread_enemy_8, thread_enemy_9, thread_enemy_10, thread_enemy_11, thread_enemy_12;
    private final int FPS = 60;
    private static MyJFrame frame;

    private MyPlayer player;
    private MyShop shop1, shop2, shop3, shop4;
    private Rectangle shopPanel, startPanel;
    private MyTile fence1, fence2, fence3, fence4;
    private MyHitbox map2_hitbox1, map2_hitbox2, map2_hitbox3, map2_hitbox4, map2_hitbox5,
            map2_hitbox6, map2_hitbox7, map2_hitbox8, map2_hitbox9, map2_hitbox10, map2_hitbox11,
            map2_hitbox12, map2_hitbox13, map2_hitbox14, map2_hitbox15, map2_hitbox16, map2_hitbox17,
            map2_hitbox18, map2_hitbox19, map2_hitbox20, map2_hitbox21, map2_hitbox22, map2_hitbox23,
            map2_hitbox24, map2_hitbox25, map2_hitbox26, map4_hitbox1, map4_hitbox2, map4_hitbox3,
            map4_hitbox4, map4_hitbox5, map4_hitbox6, map4_hitbox7, map4_hitbox8,
            map4_hitboxfish1, map4_hitboxfish2, map4_hitboxfish3, map4_hitboxfish4;

    private Rectangle hitbox;

    private Animon[] enemy = new Animon[3];
    private MyEnemy dog1, dog2, dog3, dog4, chick1, chick2, chick3, chick4, fish1, fish2, fish3, fish4;
    private int map = 1; //1 = Start map, 2 = Dog ##Tample; 3 = Chicken ##KFC; 4 = Fish; 5 = Battle ##UFC
    private static int time_to_interact = 0;
    private ImageIcon[] animation = {new ImageIcon("Player_image/down.png"), new ImageIcon("Player_image/left.png"),
        new ImageIcon("Player_image/right.png"), new ImageIcon("Player_image/up.png")};

    public MyJPanel(MyJFrame frame) throws ClassNotFoundException {
        MyJPanel.frame = frame;
        ct = new MyController();
        hitbox = new Rectangle();
        thread_main = new Thread(this);
        shopPanel = new Rectangle();
        startPanel = new Rectangle();
        player = new MyPlayer();

        try (BufferedInputStream file = new BufferedInputStream(new FileInputStream("save/save1.dat"))) {
            ObjectInputStream out = new ObjectInputStream(file);
            player = (MyPlayer) out.readObject();
            map = player.getMap();
            player.update();
            MyStart.setVisible(false);
        } catch (IOException ex) {
        }
        player.setAnimation(animation);

        shop1 = new MyShop(580, 285);
        shop2 = new MyShop(150, 260);
        shop3 = new MyShop(460, 590);
        shop4 = new MyShop(1110, 545);

        fence1 = new MyTile(new ImageIcon("Map/fence_1.png").getImage(), 0, 0);
        hitbox.x = fence1.getX() + 30;
        hitbox.y = fence1.getY() + 50;
        hitbox.width = 1360 / 2 - 135;
        hitbox.height = 50;
        fence1.setHitbox1(hitbox);
        hitbox.x = fence1.getX() + 30;
        hitbox.y = fence1.getY() + 50;
        hitbox.width = 16;
        hitbox.height = 768 / 2 - 120;
        fence1.setHitbox2(hitbox);

        fence2 = new MyTile(new ImageIcon("Map/fence_2.png").getImage(), 1360 / 2, 0);
        hitbox.x = fence2.getX() + 95;
        hitbox.y = fence2.getY() + 50;
        hitbox.width = 1360 / 2 - 135;
        hitbox.height = 50;
        fence2.setHitbox1(hitbox);
        hitbox.x = fence2.getX() + 95 + 1360 / 2 - 135 - 16;
        hitbox.y = fence2.getY() + 50;
        hitbox.width = 16;
        hitbox.height = 768 / 2 - 120;
        fence2.setHitbox2(hitbox);

        fence3 = new MyTile(new ImageIcon("Map/fence_3.png").getImage(), 0, 768 / 2);
        hitbox.x = fence3.getX() + 40;
        hitbox.y = fence3.getY() + 85 + 35 + 768 / 2 - 120 - 40;
        hitbox.width = 1360 / 2 - 135;
        hitbox.height = 50;
        fence3.setHitbox1(hitbox);
        hitbox.x = fence3.getX() + 34;
        hitbox.y = fence3.getY() + 85;
        hitbox.width = 16;
        hitbox.height = 768 / 2 - 120;
        fence3.setHitbox2(hitbox);

        fence4 = new MyTile(new ImageIcon("Map/fence_4.png").getImage(), 1360 / 2, 768 / 2);
        hitbox.x = fence4.getX() + 40 + 25;
        hitbox.y = fence4.getY() + 85 + 35 + 768 / 2 - 120 - 40;
        hitbox.width = 1360 / 2 - 120;
        hitbox.height = 50;
        fence4.setHitbox1(hitbox);
        hitbox.x = fence4.getX() + 34 + 25 + 1360 / 2 - 119;
        hitbox.y = fence4.getY() + 80;
        hitbox.width = 16;
        hitbox.height = 768 / 2 - 120;
        fence4.setHitbox2(hitbox);

        map2_hitbox1 = new MyHitbox(0, 68);
        hitbox.x = map2_hitbox1.getX();
        hitbox.y = map2_hitbox1.getY();
        hitbox.width = 1360 / 2 - 60;
        hitbox.height = 50;
        map2_hitbox1.setHitbox(hitbox);

        map2_hitbox2 = new MyHitbox(1360 / 2 - 65, 68);
        hitbox.x = map2_hitbox2.getX();
        hitbox.y = map2_hitbox2.getY();
        hitbox.width = 45;
        hitbox.height = 235;
        map2_hitbox2.setHitbox(hitbox);

        map2_hitbox3 = new MyHitbox(1360 / 2 - 65 + 45 - 10, 68 + 235 - 10 - 50);
        hitbox.x = map2_hitbox3.getX();
        hitbox.y = map2_hitbox3.getY();
        hitbox.width = 45 + 10;
        hitbox.height = 9 + 50;
        map2_hitbox3.setHitbox(hitbox);

        map2_hitbox4 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 1, 68 + 235 - 10 + 5 * 1 - 50);
        hitbox.x = map2_hitbox4.getX();
        hitbox.y = map2_hitbox4.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox4.setHitbox(hitbox);

        map2_hitbox5 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 2, 68 + 235 - 10 + 5 * 2 - 50);
        hitbox.x = map2_hitbox5.getX();
        hitbox.y = map2_hitbox5.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox5.setHitbox(hitbox);

        map2_hitbox6 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 3, 68 + 235 - 10 + 5 * 3 - 50);
        hitbox.x = map2_hitbox6.getX();
        hitbox.y = map2_hitbox6.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox6.setHitbox(hitbox);

        map2_hitbox7 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 4, 68 + 235 - 10 + 5 * 4 - 50);
        hitbox.x = map2_hitbox7.getX();
        hitbox.y = map2_hitbox7.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox7.setHitbox(hitbox);

        map2_hitbox8 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 5, 68 + 235 - 10 + 5 * 5 - 50);
        hitbox.x = map2_hitbox8.getX();
        hitbox.y = map2_hitbox8.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox8.setHitbox(hitbox);

        map2_hitbox9 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 6, 68 + 235 - 10 + 5 * 6 - 50);
        hitbox.x = map2_hitbox9.getX();
        hitbox.y = map2_hitbox9.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox9.setHitbox(hitbox);

        map2_hitbox10 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 7, 68 + 235 - 10 + 5 * 7 - 50);
        hitbox.x = map2_hitbox10.getX();
        hitbox.y = map2_hitbox10.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox10.setHitbox(hitbox);

        map2_hitbox11 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 8, 68 + 235 - 10 + 5 * 8 - 50);
        hitbox.x = map2_hitbox11.getX();
        hitbox.y = map2_hitbox11.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox11.setHitbox(hitbox);

        map2_hitbox12 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 9, 68 + 235 - 10 + 5 * 9 - 50);
        hitbox.x = map2_hitbox12.getX();
        hitbox.y = map2_hitbox12.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox12.setHitbox(hitbox);

        map2_hitbox13 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 9 + 10, 68 + 235 - 10 + 5 * 10);
        hitbox.x = map2_hitbox13.getX();
        hitbox.y = map2_hitbox13.getY();
        hitbox.width = 50;
        hitbox.height = 25;
        map2_hitbox13.setHitbox(hitbox);

        map2_hitbox14 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 9 + 10 + 45 - 2, 68 + 235 - 10 + 5 * 9 + 25);
        hitbox.x = map2_hitbox14.getX();
        hitbox.y = map2_hitbox14.getY();
        hitbox.width = 45;
        hitbox.height = 11;
        map2_hitbox14.setHitbox(hitbox);

        map2_hitbox15 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 10 + 10 + 45, 68 + 235 - 10 + 5 * 10 + 25 - 50);
        hitbox.x = map2_hitbox15.getX();
        hitbox.y = map2_hitbox15.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox15.setHitbox(hitbox);

        map2_hitbox16 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 11 + 10 + 45, 68 + 235 - 10 + 5 * 11 + 25 - 50);
        hitbox.x = map2_hitbox16.getX();
        hitbox.y = map2_hitbox16.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox16.setHitbox(hitbox);

        map2_hitbox17 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 12 + 10 + 45, 68 + 235 - 10 + 5 * 12 + 25 - 50);
        hitbox.x = map2_hitbox17.getX();
        hitbox.y = map2_hitbox17.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox17.setHitbox(hitbox);

        map2_hitbox18 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 13 + 10 + 45, 68 + 235 - 10 + 5 * 13 + 25 - 30);
        hitbox.x = map2_hitbox18.getX();
        hitbox.y = map2_hitbox18.getY();
        hitbox.width = 55;
        hitbox.height = 15 + 30;
        map2_hitbox18.setHitbox(hitbox);

        map2_hitbox19 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 12 + 10 + 45 + 55, 68 + 235 - 10 + 5 * 11 + 25 - 50);
        hitbox.x = map2_hitbox19.getX();
        hitbox.y = map2_hitbox19.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox19.setHitbox(hitbox);

        map2_hitbox20 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 13 + 10 + 45 + 55, 68 + 235 - 10 + 5 * 10 + 25 - 50);
        hitbox.x = map2_hitbox20.getX();
        hitbox.y = map2_hitbox20.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox20.setHitbox(hitbox);

        map2_hitbox21 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 14 + 10 + 45 + 55, 68 + 235 - 10 + 4 * 9 + 25 - 50);
        hitbox.x = map2_hitbox21.getX();
        hitbox.y = map2_hitbox21.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox21.setHitbox(hitbox);

        map2_hitbox22 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 15 + 10 + 45 + 55, 68 + 235 - 10 + 4 * 8 + 25 - 50);
        hitbox.x = map2_hitbox22.getX();
        hitbox.y = map2_hitbox22.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox22.setHitbox(hitbox);

        map2_hitbox23 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 16 + 10 + 45 + 55, 68 + 235 - 10 + 4 * 7 + 25 - 50);
        hitbox.x = map2_hitbox23.getX();
        hitbox.y = map2_hitbox23.getY();
        hitbox.width = 45;
        hitbox.height = 9 + 50;
        map2_hitbox23.setHitbox(hitbox);

        map2_hitbox24 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 17 + 10 + 45 + 55, 68 + 235 - 80 - 10 + 4 * 6 + 25);
        hitbox.x = map2_hitbox24.getX();
        hitbox.y = map2_hitbox24.getY();
        hitbox.width = 45;
        hitbox.height = 85;
        map2_hitbox24.setHitbox(hitbox);

        map2_hitbox25 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 18 + 10 + 45 + 55, 68 + 235 - 60 - 15 - 10 + 4 * 5 + 25 - 50);
        hitbox.x = map2_hitbox25.getX();
        hitbox.y = map2_hitbox25.getY();
        hitbox.width = 250;
        hitbox.height = 9 + 50;
        map2_hitbox25.setHitbox(hitbox);

        map2_hitbox26 = new MyHitbox(1360 / 2 - 65 + 45 + 20 * 18 + 10 + 45 + 55 + 175, 68 + 235 - 60 - 15 - 10 + 4 * 5 + 25);
        hitbox.x = map2_hitbox26.getX();
        hitbox.y = map2_hitbox26.getY();
        hitbox.width = 150;
        hitbox.height = 500;
        map2_hitbox26.setHitbox(hitbox);

        map4_hitbox1 = new MyHitbox(240, 230 - 20);
        hitbox.x = map4_hitbox1.getX();
        hitbox.y = map4_hitbox1.getY();
        hitbox.width = 586 - 250 + 50;
        hitbox.height = 30;
        map4_hitbox1.setHitbox(hitbox);

        map4_hitbox2 = new MyHitbox(236, 210);
        hitbox.x = map4_hitbox2.getX();
        hitbox.y = map4_hitbox2.getY();
        hitbox.width = 250 - 236;
        hitbox.height = 440 - 230 + 50;
        map4_hitbox2.setHitbox(hitbox);

        map4_hitbox3 = new MyHitbox(236, 440);
        hitbox.x = map4_hitbox3.getX();
        hitbox.y = map4_hitbox3.getY();
        hitbox.width = 586 - 236 + 30;
        hitbox.height = 40;
        map4_hitbox3.setHitbox(hitbox);

        map4_hitbox4 = new MyHitbox(586, 440);
        hitbox.x = map4_hitbox4.getX();
        hitbox.y = map4_hitbox4.getY();
        hitbox.width = 25;
        hitbox.height = 768 - 440;
        map4_hitbox4.setHitbox(hitbox);

        map4_hitbox5 = new MyHitbox(586, 0);
        hitbox.x = map4_hitbox5.getX();
        hitbox.y = map4_hitbox5.getY();
        hitbox.width = 20;
        hitbox.height = 236;
        map4_hitbox5.setHitbox(hitbox);

        map4_hitbox6 = new MyHitbox(1108, 160);
        hitbox.x = map4_hitbox6.getX();
        hitbox.y = map4_hitbox6.getY();
        hitbox.width = 90;
        hitbox.height = 45;
        map4_hitbox6.setHitbox(hitbox);

        map4_hitbox7 = new MyHitbox(1100, 205);
        hitbox.x = map4_hitbox7.getX();
        hitbox.y = map4_hitbox7.getY();
        hitbox.width = 110;
        hitbox.height = 20;
        map4_hitbox7.setHitbox(hitbox);

        map4_hitbox8 = new MyHitbox(1086, 225);
        hitbox.x = map4_hitbox8.getX();
        hitbox.y = map4_hitbox8.getY();
        hitbox.width = 1221 - 1086;
        hitbox.height = 15;
        map4_hitbox8.setHitbox(hitbox);

        map4_hitboxfish1 = new MyHitbox(470, 245 - 50);
        hitbox.x = map4_hitboxfish1.getX();
        hitbox.y = map4_hitboxfish1.getY();
        hitbox.width = 586 - 470;
        hitbox.height = 45 + 50;
        map4_hitboxfish1.setHitbox(hitbox);

        map4_hitboxfish2 = new MyHitbox(250 - 25, 245 - 25);
        hitbox.x = map4_hitboxfish2.getX();
        hitbox.y = map4_hitboxfish2.getY();
        hitbox.width = 366 - 250 + 25;
        hitbox.height = 295 - 245 + 25;
        map4_hitboxfish2.setHitbox(hitbox);

        map4_hitboxfish3 = new MyHitbox(250 - 25, 370);
        hitbox.x = map4_hitboxfish3.getX();
        hitbox.y = map4_hitboxfish3.getY();
        hitbox.width = 330 - 250 + 25;
        hitbox.height = 420 - 370 + 15 + 50;
        map4_hitboxfish3.setHitbox(hitbox);

        map4_hitboxfish4 = new MyHitbox(405, 420 - 30);
        hitbox.x = map4_hitboxfish4.getX();
        hitbox.y = map4_hitboxfish4.getY();
        hitbox.width = 566 - 405;
        hitbox.height = 15 + 30 + 75;
        map4_hitboxfish4.setHitbox(hitbox);

        dog1 = new MyEnemy(1360 / 2 - 150, 768 / 2, new Dogmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        dog2 = new MyEnemy(1360 / 2 + 250, 768 / 2 - 25, new Dogmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        dog3 = new MyEnemy(1360 / 2 - 50, 768 / 2 + 250, new Dogmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        dog4 = new MyEnemy(1360 / 2, 768 / 2 - 100, new Dogmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));

        chick1 = new MyEnemy(540, 93, new Birdmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        chick2 = new MyEnemy(845, 148, new Birdmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        chick3 = new MyEnemy(715, 213, new Birdmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        chick4 = new MyEnemy(470, 263, new Birdmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));

        fish1 = new MyEnemy(400, 30, new Fishmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        fish2 = new MyEnemy(100, 85, new Fishmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        fish3 = new MyEnemy(175, 590, new Fishmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));
        fish4 = new MyEnemy(430, 640, new Fishmon(new Random().nextInt(MyPlayer.getHighestLevel(), MyPlayer.getHighestLevel() + 4)));

        thread_enemy_1 = new Thread(dog1);
        thread_enemy_1.start();
        thread_enemy_2 = new Thread(dog2);
        thread_enemy_2.start();
        thread_enemy_3 = new Thread(dog3);
        thread_enemy_3.start();
        thread_enemy_4 = new Thread(dog4);
        thread_enemy_4.start();

        thread_enemy_5 = new Thread(chick1);
        thread_enemy_5.start();
        thread_enemy_6 = new Thread(chick2);
        thread_enemy_6.start();
        thread_enemy_7 = new Thread(chick3);
        thread_enemy_7.start();
        thread_enemy_8 = new Thread(chick4);
        thread_enemy_8.start();

        thread_enemy_9 = new Thread(fish1);
        thread_enemy_9.start();
        thread_enemy_10 = new Thread(fish2);
        thread_enemy_10.start();
        thread_enemy_11 = new Thread(fish3);
        thread_enemy_11.start();
        thread_enemy_12 = new Thread(fish4);
        thread_enemy_12.start();

        shopPanel.x = 1360 / 4;
        shopPanel.y = 768 / 4;
        shopPanel.width = 1360 / 2;
        shopPanel.height = 768 / 2;

        startPanel.x = 1360 / 4;
        startPanel.y = 768 / 4;
        startPanel.width = 1360 / 2;
        startPanel.height = 768 / 2;

        this.setPreferredSize(new Dimension(1360, 768));
        this.setBounds(0, 0, 1360, 768);
        this.setBackground(Color.black);
        this.setLayout(null);
        this.addKeyListener(ct);
        this.setFocusable(true);
        thread_main.start();
    }

    public void paint(Graphics g) {
        super.paint(g); //It will paint the background color
        Graphics2D g2D = (Graphics2D) g;

        if (map == 1) {
            g2D.drawImage(new ImageIcon("Map/bg_map1.png").getImage(), 0, 0, null);
            g2D.drawImage(new ImageIcon("Map/fence.png").getImage(), 0, 0, null);
            g2D.drawImage(fence1.getImage(), fence1.getX(), fence1.getY(), null);
            g2D.drawImage(fence2.getImage(), fence2.getX(), fence2.getY(), null);

            g2D.setPaint(Color.white);
            g2D.fillRect(0, 768 / 2, 50, 50); // Fish
            g2D.drawString("Fish", 0, 768 / 2);
            g2D.fillRect(1360 / 2, 0, 50, 50); // Bird
            g2D.drawString("Bird", 1360 / 2, 65);
            g2D.fillRect(1360 - 50, 768 / 2, 50, 50); // Dog
            g2D.drawString("Dog", 1360 - 50, 768 / 2);

            g2D.setPaint(Color.red);
            g2D.fillRect(shop1.getX(), shop1.getY(), 50, 50);

            g2D.setPaint(Color.white);
            g2D.drawString("Shop", shop1.getX() + 50 / 4 - 1, shop1.getY() - 2);
        }

        if (map == 2) {
            g2D.drawImage(new ImageIcon("Map/dog_bg.png").getImage(), 0, 0, null);
            g2D.drawImage(new ImageIcon("Map/dog_bg_tree.png").getImage(), 0, 0, null);
            //g2D.drawRect(map2_hitbox1.getHitbox().x, map2_hitbox1.getHitbox().y, map2_hitbox1.getHitbox().width, map2_hitbox1.getHitbox().height);g2D.drawRect(map2_hitbox2.getHitbox().x, map2_hitbox2.getHitbox().y, map2_hitbox2.getHitbox().width, map2_hitbox2.getHitbox().height);g2D.drawRect(map2_hitbox3.getHitbox().x, map2_hitbox3.getHitbox().y, map2_hitbox3.getHitbox().width, map2_hitbox3.getHitbox().height);g2D.drawRect(map2_hitbox4.getHitbox().x, map2_hitbox4.getHitbox().y, map2_hitbox4.getHitbox().width, map2_hitbox4.getHitbox().height);g2D.drawRect(map2_hitbox5.getHitbox().x, map2_hitbox5.getHitbox().y, map2_hitbox5.getHitbox().width, map2_hitbox5.getHitbox().height);g2D.drawRect(map2_hitbox6.getHitbox().x, map2_hitbox6.getHitbox().y, map2_hitbox6.getHitbox().width, map2_hitbox6.getHitbox().height);g2D.drawRect(map2_hitbox7.getHitbox().x, map2_hitbox7.getHitbox().y, map2_hitbox7.getHitbox().width, map2_hitbox7.getHitbox().height);g2D.drawRect(map2_hitbox8.getHitbox().x, map2_hitbox8.getHitbox().y, map2_hitbox8.getHitbox().width, map2_hitbox8.getHitbox().height);g2D.drawRect(map2_hitbox9.getHitbox().x, map2_hitbox9.getHitbox().y, map2_hitbox9.getHitbox().width, map2_hitbox9.getHitbox().height);g2D.drawRect(map2_hitbox10.getHitbox().x, map2_hitbox10.getHitbox().y, map2_hitbox10.getHitbox().width, map2_hitbox10.getHitbox().height);g2D.drawRect(map2_hitbox11.getHitbox().x, map2_hitbox11.getHitbox().y, map2_hitbox11.getHitbox().width, map2_hitbox11.getHitbox().height);g2D.drawRect(map2_hitbox12.getHitbox().x, map2_hitbox12.getHitbox().y, map2_hitbox12.getHitbox().width, map2_hitbox12.getHitbox().height);g2D.drawRect(map2_hitbox13.getHitbox().x, map2_hitbox13.getHitbox().y, map2_hitbox13.getHitbox().width, map2_hitbox13.getHitbox().height);g2D.drawRect(map2_hitbox14.getHitbox().x, map2_hitbox14.getHitbox().y, map2_hitbox14.getHitbox().width, map2_hitbox14.getHitbox().height);g2D.drawRect(map2_hitbox15.getHitbox().x, map2_hitbox15.getHitbox().y, map2_hitbox15.getHitbox().width, map2_hitbox15.getHitbox().height);g2D.drawRect(map2_hitbox16.getHitbox().x, map2_hitbox16.getHitbox().y, map2_hitbox16.getHitbox().width, map2_hitbox16.getHitbox().height);g2D.drawRect(map2_hitbox17.getHitbox().x, map2_hitbox17.getHitbox().y, map2_hitbox17.getHitbox().width, map2_hitbox17.getHitbox().height);g2D.drawRect(map2_hitbox18.getHitbox().x, map2_hitbox18.getHitbox().y, map2_hitbox18.getHitbox().width, map2_hitbox18.getHitbox().height);g2D.drawRect(map2_hitbox19.getHitbox().x, map2_hitbox19.getHitbox().y, map2_hitbox19.getHitbox().width, map2_hitbox19.getHitbox().height);g2D.drawRect(map2_hitbox20.getHitbox().x, map2_hitbox20.getHitbox().y, map2_hitbox20.getHitbox().width, map2_hitbox20.getHitbox().height);g2D.drawRect(map2_hitbox21.getHitbox().x, map2_hitbox21.getHitbox().y, map2_hitbox21.getHitbox().width, map2_hitbox21.getHitbox().height);g2D.drawRect(map2_hitbox22.getHitbox().x, map2_hitbox22.getHitbox().y, map2_hitbox22.getHitbox().width, map2_hitbox22.getHitbox().height);g2D.drawRect(map2_hitbox23.getHitbox().x, map2_hitbox23.getHitbox().y, map2_hitbox23.getHitbox().width, map2_hitbox23.getHitbox().height);g2D.drawRect(map2_hitbox24.getHitbox().x, map2_hitbox24.getHitbox().y, map2_hitbox24.getHitbox().width, map2_hitbox24.getHitbox().height);g2D.drawRect(map2_hitbox25.getHitbox().x, map2_hitbox25.getHitbox().y, map2_hitbox25.getHitbox().width, map2_hitbox25.getHitbox().height);g2D.drawRect(map2_hitbox26.getHitbox().x, map2_hitbox26.getHitbox().y, map2_hitbox26.getHitbox().width, map2_hitbox26.getHitbox().height);
            g2D.setPaint(Color.white);

            g2D.setPaint(Color.white);
            g2D.fillRect(0, 768 / 2, 50, 50);
            g2D.drawString("Back", 0, 768 / 2);

            g2D.setPaint(Color.red);
            g2D.fillRect(shop2.getX(), shop2.getY(), 50, 50);

            g2D.setPaint(Color.white);
            g2D.drawString("Shop", shop2.getX() + 50 / 4 - 1, shop2.getY() - 2);

            g2D.drawString("Level : " + dog1.getLevel(), dog1.getMy_x() + 4, dog1.getMy_y());
            g2D.drawString("Level : " + dog2.getLevel(), dog2.getMy_x() + 4, dog2.getMy_y());
            g2D.drawString("Level : " + dog3.getLevel(), dog3.getMy_x() + 4, dog3.getMy_y());
            g2D.drawString("Level : " + dog4.getLevel(), dog4.getMy_x() + 4, dog4.getMy_y());
            g2D.drawImage(dog1.getImage(), dog1.getMy_x(), dog1.getMy_y(), null);
            g2D.drawImage(dog2.getImage(), dog2.getMy_x(), dog2.getMy_y(), null);
            g2D.drawImage(dog3.getImage(), dog3.getMy_x(), dog3.getMy_y(), null);
            g2D.drawImage(dog4.getImage(), dog4.getMy_x(), dog4.getMy_y(), null);

            //g2D.drawRect(dog1.getHitbox().x, dog1.getHitbox().y, dog1.getHitbox().width, dog1.getHitbox().height);g2D.drawRect(dog2.getHitbox().x, dog2.getHitbox().y, dog2.getHitbox().width, dog2.getHitbox().height);g2D.drawRect(dog3.getHitbox().x, dog3.getHitbox().y, dog3.getHitbox().width, dog3.getHitbox().height);g2D.drawRect(dog4.getHitbox().x, dog4.getHitbox().y, dog4.getHitbox().width, dog4.getHitbox().height);
        } else {
            dog1.reposition(MyPlayer.getHighestLevel());
            dog2.reposition(MyPlayer.getHighestLevel());
            dog3.reposition(MyPlayer.getHighestLevel());
            dog4.reposition(MyPlayer.getHighestLevel());
        }

        if (map == 3) {

            g2D.setPaint(Color.white);
            g2D.fillRect(1360 / 2, 718, 50, 50);
            g2D.drawString("Back", 1360 / 2, 718);

            g2D.setPaint(Color.red);
            g2D.fillRect(shop3.getX(), shop3.getY(), 50, 50);

            g2D.setPaint(Color.white);
            g2D.drawString("Shop", shop3.getX() + 50 / 4 - 1, shop3.getY() - 2);
            g2D.drawString("Level : " + chick1.getLevel(), chick1.getMy_x() + 4, chick1.getMy_y());
            g2D.drawString("Level : " + chick2.getLevel(), chick2.getMy_x() + 4, chick2.getMy_y());
            g2D.drawString("Level : " + chick3.getLevel(), chick3.getMy_x() + 4, chick3.getMy_y());
            g2D.drawString("Level : " + chick4.getLevel(), chick4.getMy_x() + 4, chick4.getMy_y());
            g2D.drawImage(chick1.getImage(), chick1.getMy_x(), chick1.getMy_y(), null);
            g2D.drawImage(chick2.getImage(), chick2.getMy_x(), chick2.getMy_y(), null);
            g2D.drawImage(chick3.getImage(), chick3.getMy_x(), chick3.getMy_y(), null);
            g2D.drawImage(chick4.getImage(), chick4.getMy_x(), chick4.getMy_y(), null);
        } else {
            chick1.reposition(MyPlayer.getHighestLevel());
            chick2.reposition(MyPlayer.getHighestLevel());
            chick3.reposition(MyPlayer.getHighestLevel());
            chick4.reposition(MyPlayer.getHighestLevel());
        }

        if (map == 4) {
            g2D.drawImage(new ImageIcon("Map/fish_b.png").getImage(), 0, 0, null);
            g2D.drawImage(new ImageIcon("Map/fish_front.png").getImage(), 0, 0, null);

            g2D.setPaint(Color.white);
            //g2D.drawRect(map4_hitbox1.getHitbox().x, map4_hitbox1.getHitbox().y, map4_hitbox1.getHitbox().width, map4_hitbox1.getHitbox().height);g2D.drawRect(map4_hitbox2.getHitbox().x, map4_hitbox2.getHitbox().y, map4_hitbox2.getHitbox().width, map4_hitbox2.getHitbox().height);g2D.drawRect(map4_hitbox3.getHitbox().x, map4_hitbox3.getHitbox().y, map4_hitbox3.getHitbox().width, map4_hitbox3.getHitbox().height);g2D.drawRect(map4_hitbox4.getHitbox().x, map4_hitbox4.getHitbox().y, map4_hitbox4.getHitbox().width, map4_hitbox4.getHitbox().height);g2D.drawRect(map4_hitbox5.getHitbox().x, map4_hitbox5.getHitbox().y, map4_hitbox5.getHitbox().width, map4_hitbox5.getHitbox().height);g2D.drawRect(map4_hitbox6.getHitbox().x, map4_hitbox6.getHitbox().y, map4_hitbox6.getHitbox().width, map4_hitbox6.getHitbox().height);g2D.drawRect(map4_hitbox7.getHitbox().x, map4_hitbox7.getHitbox().y, map4_hitbox7.getHitbox().width, map4_hitbox7.getHitbox().height);g2D.drawRect(map4_hitbox8.getHitbox().x, map4_hitbox8.getHitbox().y, map4_hitbox8.getHitbox().width, map4_hitbox8.getHitbox().height);
            //g2D.drawRect(map4_hitboxfish1.getHitbox().x, map4_hitboxfish1.getHitbox().y, map4_hitboxfish1.getHitbox().width, map4_hitboxfish1.getHitbox().height);g2D.drawRect(map4_hitboxfish2.getHitbox().x, map4_hitboxfish2.getHitbox().y, map4_hitboxfish2.getHitbox().width, map4_hitboxfish2.getHitbox().height);g2D.drawRect(map4_hitboxfish3.getHitbox().x, map4_hitboxfish3.getHitbox().y, map4_hitboxfish3.getHitbox().width, map4_hitboxfish3.getHitbox().height);g2D.drawRect(map4_hitboxfish4.getHitbox().x, map4_hitboxfish4.getHitbox().y, map4_hitboxfish4.getHitbox().width, map4_hitboxfish4.getHitbox().height);

            g2D.fillRect(1360 - 50, 768 / 2, 50, 50);
            g2D.drawString("Back", 1360 - 50, 768 / 2);

            g2D.setPaint(Color.red);
            g2D.fillRect(shop4.getX(), shop4.getY(), 50, 50);

            g2D.setPaint(Color.white);
            g2D.drawString("Shop", shop4.getX() + 50 / 4 - 1, shop4.getY() - 2);
            g2D.drawString("Level : " + fish1.getLevel(), fish1.getMy_x() + 4, fish1.getMy_y());
            g2D.drawString("Level : " + fish2.getLevel(), fish2.getMy_x() + 4, fish2.getMy_y());
            g2D.drawString("Level : " + fish3.getLevel(), fish3.getMy_x() + 4, fish3.getMy_y());
            g2D.drawString("Level : " + fish4.getLevel(), fish4.getMy_x() + 4, fish4.getMy_y());
            g2D.drawImage(fish1.getImage(), fish1.getMy_x(), fish1.getMy_y(), null);
            g2D.drawImage(fish2.getImage(), fish2.getMy_x(), fish2.getMy_y(), null);
            g2D.drawImage(fish3.getImage(), fish3.getMy_x(), fish3.getMy_y(), null);
            g2D.drawImage(fish4.getImage(), fish4.getMy_x(), fish4.getMy_y(), null);
        } else {
            fish1.reposition(MyPlayer.getHighestLevel());
            fish2.reposition(MyPlayer.getHighestLevel());
            fish3.reposition(MyPlayer.getHighestLevel());
            fish4.reposition(MyPlayer.getHighestLevel());
        }

        g2D.drawImage(player.getImage(), player.getMy_x(), player.getMy_y(), null);
        g2D.drawRect(player.getHitbox().x, player.getHitbox().y, player.getHitbox().width, player.getHitbox().height);

        if (map == 1) {
            g2D.drawImage(fence3.getImage(), fence3.getX(), fence3.getY(), null);
            g2D.drawImage(fence4.getImage(), fence4.getX(), fence4.getY(), null);

        }

        if (map == 2) {
            g2D.drawImage(new ImageIcon("Map/dog_bg_tree2.png").getImage(), 0, 0, null);
        }

        if (map == 4) {
            g2D.drawImage(new ImageIcon("Map/fish_front2.png").getImage(), 0, 0, null);
        }

        if (MyShop.isVisible()) {
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

            g2D.drawString(shop1.getItemShop()[0].getName(), shopPanel.x + 10 + 5 + 10, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
            g2D.drawString(shop1.getItemShop()[0].getPrice() + "", shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 / 2 + 5);

            g2D.drawString(shop1.getItemShop()[1].getName(), shopPanel.x + 10 + 5 + 10, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 1 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
            g2D.drawString(shop1.getItemShop()[1].getPrice() + "", shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 1 + (768 / 2 - 20 - 10) / 5 / 2 + 5);

            g2D.drawString(shop1.getItemShop()[2].getName(), shopPanel.x + 10 + 5 + 10, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 2 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
            g2D.drawString(shop1.getItemShop()[2].getPrice() + "", shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 2 + (768 / 2 - 20 - 10) / 5 / 2 + 5);

            g2D.drawString(shop1.getItemShop()[3].getName(), shopPanel.x + 10 + 5 + 10, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 3 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
            g2D.drawString(shop1.getItemShop()[3].getPrice() + "", shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 3 + (768 / 2 - 20 - 10) / 5 / 2 + 5);

            g2D.drawString(shop1.getItemShop()[4].getName(), shopPanel.x + 10 + 5 + 10, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 4 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
            g2D.drawString(shop1.getItemShop()[4].getPrice() + "", shopPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) - 50, shopPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 5 * 4 + (768 / 2 - 20 - 10) / 5 / 2 + 5);
            g2D.setFont(new Font("Dialog", 12, 12));
        }

        if (MyStart.isVisible()) {
            g2D.setPaint(new Color(254, 147, 7));
            g2D.fillRoundRect(startPanel.x, startPanel.y, startPanel.width, startPanel.height, 10, 10);
            g2D.setPaint(new Color(128, 59, 0));
            g2D.fillRoundRect(startPanel.x + 5, startPanel.y + 5, startPanel.width - 10, startPanel.height - 10, 10, 10);
            g2D.setPaint(new Color(255, 210, 133));
            g2D.fillRoundRect(startPanel.x + 10, startPanel.y + 10, startPanel.width - 20, startPanel.height - 20, 10, 10);
            g2D.setPaint(new Color(128, 50, 11));

            g2D.setFont(new Font("Dialog", 12, 14));
            g2D.drawImage(new ImageIcon("Monster_image/dog.png").getImage(), startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25, startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6, null);
            g2D.drawString("HP : 12", startPanel.x + 10 + 10 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 - "HP : 10".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100);
            g2D.drawString("Stamina : 7", startPanel.x + 10 + 10 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 - "Stamina : 5".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 1);
            g2D.drawString("ATK : 2", startPanel.x + 10 + 10 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 - "ATK : 2".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 2);

            g2D.drawImage(new ImageIcon("Monster_image/chicken.png").getImage(), startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 1, startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6, null);
            g2D.drawString("HP : 7", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 1 - "HP : 10".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100);
            g2D.drawString("Stamina : 6", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 1 - "Stamina : 5".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 1);
            g2D.drawString("ATK : 4", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 1 - "ATK : 2".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 2);

            g2D.drawImage(new ImageIcon("Monster_image/fish.png").getImage(), startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 2, startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6, null);
            g2D.drawString("HP : 6", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 2 - "HP : 10".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100);
            g2D.drawString("Stamina : 5", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 2 - "Stamina : 5".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 1);
            g2D.drawString("ATK : 5", startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 / 2 - 25 + (1360 / 2 - 20 - 10) / 3 * 2 - "ATK : 2".length(), startPanel.y + 10 + 5 + (768 / 2 - 20 - 10) / 6 + 100 + 20 * 2);

            g2D.setFont(new Font("Dialog", 12, 12));

            if (MyStart.getNum() == 0) {
                g2D.drawRoundRect(startPanel.x + 10 + 5, startPanel.y + 10 + 5, (1360 / 2 - 20 - 10) / 3, (768 / 2 - 20 - 10), 10, 10);
            }
            if (MyStart.getNum() == 1) {
                g2D.drawRoundRect(startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 * 1, startPanel.y + 10 + 5, (1360 / 2 - 20 - 10) / 3, (768 / 2 - 20 - 10), 10, 10);
            }
            if (MyStart.getNum() == 2) {
                g2D.drawRoundRect(startPanel.x + 10 + 5 + (1360 / 2 - 20 - 10) / 3 * 2, startPanel.y + 10 + 5, (1360 / 2 - 20 - 10) / 3, (768 / 2 - 20 - 10), 10, 10);
            }

        }

        if (MyMenu.isVisible()) {
            MyMenu.paint(g, player);
        }

        if (MyBattle.isVisible()) {
            MyBattle.paint(g, player);
        }

        g2D.setPaint(Color.white);

        g2D.drawString("Money : " + player.getMoney(), 10, 20);

        g2D.drawString("z : Confirm", 1300, 20);
        g2D.drawString("x : Cancel", 1300, 40);
        //g2D.setPaint(Color.red);
        //g2D.drawRect(player.getHitbox().x, player.getHitbox().y, player.getHitbox().width, player.getHitbox().height); //Draw Hitbox
        //g2D.drawLine(0, 0, 1360, 768);
    }

    public void update() {
        //System.out.println(player.getHitbox().x+" "+player.getHitbox().y);
        switch (map) {
            case 1 -> {
                if (player.hitted(shop1.getHitbox())) {
                    MyShop.setVisible(true);
                }
                player.hitted(fence1.getHitbox()[0]);
                player.hitted(fence1.getHitbox()[1]);
                player.hitted(fence2.getHitbox()[0]);
                player.hitted(fence2.getHitbox()[1]);
                player.hitted(fence3.getHitbox()[0]);
                player.hitted(fence3.getHitbox()[1]);
                player.hitted(fence4.getHitbox()[0]);
                player.hitted(fence4.getHitbox()[1]);

            }
            case 2 -> {
                if (player.hitted(shop2.getHitbox())) {
                    MyShop.setVisible(true);

                }
                if (time_to_interact == 0) {
                    if (player.hitted(dog1.getHitbox())) {

                        enemy[0] = dog1.getAnimon();
                        MyBattle.setListEnemy(enemy);
                        if (!MyBattle.isVisible()) {
                            MyBattle.setVisible(true);
                        }

                    } else if (player.hitted(dog2.getHitbox())) {

                        enemy[0] = dog2.getAnimon();
                        MyBattle.setListEnemy(enemy);
                        if (!MyBattle.isVisible()) {
                            MyBattle.setVisible(true);
                        }

                    } else if (player.hitted(dog3.getHitbox())) {

                        enemy[0] = dog3.getAnimon();
                        MyBattle.setListEnemy(enemy);
                        if (!MyBattle.isVisible()) {
                            MyBattle.setVisible(true);
                        }

                    } else if (player.hitted(dog4.getHitbox())) {

                        enemy[0] = dog4.getAnimon();
                        MyBattle.setListEnemy(enemy);
                        if (!MyBattle.isVisible()) {
                            MyBattle.setVisible(true);
                        }

                    }
                }

                player.hitted(map2_hitbox1.getHitbox());
                player.hitted(map2_hitbox2.getHitbox());
                player.hitted(map2_hitbox3.getHitbox());
                player.hitted(map2_hitbox4.getHitbox());
                player.hitted(map2_hitbox5.getHitbox());
                player.hitted(map2_hitbox6.getHitbox());
                player.hitted(map2_hitbox7.getHitbox());
                player.hitted(map2_hitbox8.getHitbox());
                player.hitted(map2_hitbox9.getHitbox());
                player.hitted(map2_hitbox10.getHitbox());
                player.hitted(map2_hitbox11.getHitbox());
                player.hitted(map2_hitbox12.getHitbox());
                player.hitted(map2_hitbox13.getHitbox());
                player.hitted(map2_hitbox14.getHitbox());
                player.hitted(map2_hitbox15.getHitbox());
                player.hitted(map2_hitbox16.getHitbox());
                player.hitted(map2_hitbox17.getHitbox());
                player.hitted(map2_hitbox18.getHitbox());
                player.hitted(map2_hitbox19.getHitbox());
                player.hitted(map2_hitbox20.getHitbox());
                player.hitted(map2_hitbox21.getHitbox());
                player.hitted(map2_hitbox22.getHitbox());
                player.hitted(map2_hitbox23.getHitbox());
                player.hitted(map2_hitbox24.getHitbox());
                player.hitted(map2_hitbox25.getHitbox());
                player.hitted(map2_hitbox26.getHitbox());

                dog1.hitted(map2_hitbox1.getHitbox());
                dog1.hitted(map2_hitbox2.getHitbox());
                dog1.hitted(map2_hitbox3.getHitbox());
                dog1.hitted(map2_hitbox4.getHitbox());
                dog1.hitted(map2_hitbox5.getHitbox());
                dog1.hitted(map2_hitbox6.getHitbox());
                dog1.hitted(map2_hitbox7.getHitbox());
                dog1.hitted(map2_hitbox8.getHitbox());
                dog1.hitted(map2_hitbox9.getHitbox());
                dog1.hitted(map2_hitbox10.getHitbox());
                dog1.hitted(map2_hitbox11.getHitbox());
                dog1.hitted(map2_hitbox12.getHitbox());
                dog1.hitted(map2_hitbox13.getHitbox());
                dog1.hitted(map2_hitbox14.getHitbox());
                dog1.hitted(map2_hitbox15.getHitbox());
                dog1.hitted(map2_hitbox16.getHitbox());
                dog1.hitted(map2_hitbox17.getHitbox());
                dog1.hitted(map2_hitbox18.getHitbox());
                dog1.hitted(map2_hitbox19.getHitbox());
                dog1.hitted(map2_hitbox20.getHitbox());
                dog1.hitted(map2_hitbox21.getHitbox());
                dog1.hitted(map2_hitbox22.getHitbox());
                dog1.hitted(map2_hitbox23.getHitbox());
                dog1.hitted(map2_hitbox24.getHitbox());
                dog1.hitted(map2_hitbox25.getHitbox());
                dog1.hitted(map2_hitbox26.getHitbox());

                dog2.hitted(map2_hitbox1.getHitbox());
                dog2.hitted(map2_hitbox2.getHitbox());
                dog2.hitted(map2_hitbox3.getHitbox());
                dog2.hitted(map2_hitbox4.getHitbox());
                dog2.hitted(map2_hitbox5.getHitbox());
                dog2.hitted(map2_hitbox6.getHitbox());
                dog2.hitted(map2_hitbox7.getHitbox());
                dog2.hitted(map2_hitbox8.getHitbox());
                dog2.hitted(map2_hitbox9.getHitbox());
                dog2.hitted(map2_hitbox10.getHitbox());
                dog2.hitted(map2_hitbox11.getHitbox());
                dog2.hitted(map2_hitbox12.getHitbox());
                dog2.hitted(map2_hitbox13.getHitbox());
                dog2.hitted(map2_hitbox14.getHitbox());
                dog2.hitted(map2_hitbox15.getHitbox());
                dog2.hitted(map2_hitbox16.getHitbox());
                dog2.hitted(map2_hitbox17.getHitbox());
                dog2.hitted(map2_hitbox18.getHitbox());
                dog2.hitted(map2_hitbox19.getHitbox());
                dog2.hitted(map2_hitbox20.getHitbox());
                dog2.hitted(map2_hitbox21.getHitbox());
                dog2.hitted(map2_hitbox22.getHitbox());
                dog2.hitted(map2_hitbox23.getHitbox());
                dog2.hitted(map2_hitbox24.getHitbox());
                dog2.hitted(map2_hitbox25.getHitbox());
                dog2.hitted(map2_hitbox26.getHitbox());

                dog3.hitted(map2_hitbox1.getHitbox());
                dog3.hitted(map2_hitbox2.getHitbox());
                dog3.hitted(map2_hitbox3.getHitbox());
                dog3.hitted(map2_hitbox4.getHitbox());
                dog3.hitted(map2_hitbox5.getHitbox());
                dog3.hitted(map2_hitbox6.getHitbox());
                dog3.hitted(map2_hitbox7.getHitbox());
                dog3.hitted(map2_hitbox8.getHitbox());
                dog3.hitted(map2_hitbox9.getHitbox());
                dog3.hitted(map2_hitbox10.getHitbox());
                dog3.hitted(map2_hitbox11.getHitbox());
                dog3.hitted(map2_hitbox12.getHitbox());
                dog3.hitted(map2_hitbox13.getHitbox());
                dog3.hitted(map2_hitbox14.getHitbox());
                dog3.hitted(map2_hitbox15.getHitbox());
                dog3.hitted(map2_hitbox16.getHitbox());
                dog3.hitted(map2_hitbox17.getHitbox());
                dog3.hitted(map2_hitbox18.getHitbox());
                dog3.hitted(map2_hitbox19.getHitbox());
                dog3.hitted(map2_hitbox20.getHitbox());
                dog3.hitted(map2_hitbox21.getHitbox());
                dog3.hitted(map2_hitbox22.getHitbox());
                dog3.hitted(map2_hitbox23.getHitbox());
                dog3.hitted(map2_hitbox24.getHitbox());
                dog3.hitted(map2_hitbox25.getHitbox());
                dog3.hitted(map2_hitbox26.getHitbox());

                dog4.hitted(map2_hitbox1.getHitbox());
                dog4.hitted(map2_hitbox2.getHitbox());
                dog4.hitted(map2_hitbox3.getHitbox());
                dog4.hitted(map2_hitbox4.getHitbox());
                dog4.hitted(map2_hitbox5.getHitbox());
                dog4.hitted(map2_hitbox6.getHitbox());
                dog4.hitted(map2_hitbox7.getHitbox());
                dog4.hitted(map2_hitbox8.getHitbox());
                dog4.hitted(map2_hitbox9.getHitbox());
                dog4.hitted(map2_hitbox10.getHitbox());
                dog4.hitted(map2_hitbox11.getHitbox());
                dog4.hitted(map2_hitbox12.getHitbox());
                dog4.hitted(map2_hitbox13.getHitbox());
                dog4.hitted(map2_hitbox14.getHitbox());
                dog4.hitted(map2_hitbox15.getHitbox());
                dog4.hitted(map2_hitbox16.getHitbox());
                dog4.hitted(map2_hitbox17.getHitbox());
                dog4.hitted(map2_hitbox18.getHitbox());
                dog4.hitted(map2_hitbox19.getHitbox());
                dog4.hitted(map2_hitbox20.getHitbox());
                dog4.hitted(map2_hitbox21.getHitbox());
                dog4.hitted(map2_hitbox22.getHitbox());
                dog4.hitted(map2_hitbox23.getHitbox());
                dog4.hitted(map2_hitbox24.getHitbox());
                dog4.hitted(map2_hitbox25.getHitbox());
                dog4.hitted(map2_hitbox26.getHitbox());
            }
            case 3 -> {
                if (player.hitted(shop3.getHitbox())) {
                    MyShop.setVisible(true);
                }

            }
            case 4 -> {
                if (player.hitted(shop4.getHitbox())) {
                    MyShop.setVisible(true);
                }

                if (time_to_interact == 0) {
                    if (player.getHitbox().intersects(map4_hitboxfish1.getHitbox())) {
                        fish1.setMoveto_player(true);
                        fish1.move_to_player(player.getHitbox());
                        if (fish1.getHitbox().intersects(map4_hitboxfish1.getHitbox())) {
                            enemy[0] = fish1.getAnimon();
                            MyBattle.setListEnemy(enemy);
                            if (!MyBattle.isVisible()) {
                                MyBattle.setVisible(true);
                            }
                        }
                    } else {
                        if (fish1.isLeave_origin()) {
                            fish1.setMoveto_player(true);
                            fish1.move_to_player(fish1.getOrigin());
                        } else {
                            fish1.setMoveto_player(false);
                        }

                    }

                    if (player.getHitbox().intersects(map4_hitboxfish2.getHitbox())) {
                        fish2.setMoveto_player(true);
                        fish2.move_to_player(player.getHitbox());
                        if (fish2.getHitbox().intersects(map4_hitboxfish2.getHitbox())) {
                            enemy[0] = fish2.getAnimon();
                            MyBattle.setListEnemy(enemy);
                            if (!MyBattle.isVisible()) {
                                MyBattle.setVisible(true);
                            }
                        }
                    } else {
                        if (fish2.isLeave_origin()) {
                            fish2.setMoveto_player(true);
                            fish2.move_to_player(fish2.getOrigin());
                        } else {
                            fish2.setMoveto_player(false);
                        }

                    }

                    if (player.getHitbox().intersects(map4_hitboxfish3.getHitbox())) {
                        fish3.setMoveto_player(true);
                        fish3.move_to_player(player.getHitbox());

                        if (fish3.getHitbox().intersects(map4_hitboxfish3.getHitbox())) {
                            enemy[0] = fish3.getAnimon();
                            MyBattle.setListEnemy(enemy);
                            if (!MyBattle.isVisible()) {
                                MyBattle.setVisible(true);
                            }
                        }
                    } else {
                        if (fish3.isLeave_origin()) {
                            fish3.setMoveto_player(true);
                            fish3.move_to_player(fish3.getOrigin());
                        } else {
                            fish3.setMoveto_player(false);
                        }

                    }

                    if (player.getHitbox().intersects(map4_hitboxfish4.getHitbox())) {
                        fish4.setMoveto_player(true);
                        fish4.move_to_player(player.getHitbox());

                        if (fish4.getHitbox().intersects(map4_hitboxfish4.getHitbox())) {
                            enemy[0] = fish4.getAnimon();
                            MyBattle.setListEnemy(enemy);
                            if (!MyBattle.isVisible()) {
                                MyBattle.setVisible(true);
                            }
                        }
                    } else {
                        if (fish4.isLeave_origin()) {
                            fish4.setMoveto_player(true);
                            fish4.move_to_player(fish4.getOrigin());
                        } else {
                            fish4.setMoveto_player(false);
                        }

                    }
                }
                player.hitted(map4_hitbox1.getHitbox());
                player.hitted(map4_hitbox2.getHitbox());
                player.hitted(map4_hitbox3.getHitbox());
                player.hitted(map4_hitbox4.getHitbox());
                player.hitted(map4_hitbox5.getHitbox());
                player.hitted(map4_hitbox6.getHitbox());
                player.hitted(map4_hitbox7.getHitbox());
                player.hitted(map4_hitbox8.getHitbox());

                fish1.hitted(map4_hitbox1.getHitbox());
                fish1.hitted(map4_hitbox2.getHitbox());
                fish1.hitted(map4_hitbox3.getHitbox());
                fish1.hitted(map4_hitbox4.getHitbox());
                fish1.hitted(map4_hitbox5.getHitbox());
                fish1.hitted(map4_hitbox6.getHitbox());
                fish1.hitted(map4_hitbox7.getHitbox());
                fish1.hitted(map4_hitbox8.getHitbox());

                fish2.hitted(map4_hitbox1.getHitbox());
                fish2.hitted(map4_hitbox2.getHitbox());
                fish2.hitted(map4_hitbox3.getHitbox());
                fish2.hitted(map4_hitbox4.getHitbox());
                fish2.hitted(map4_hitbox5.getHitbox());
                fish2.hitted(map4_hitbox6.getHitbox());
                fish2.hitted(map4_hitbox7.getHitbox());
                fish2.hitted(map4_hitbox8.getHitbox());

                fish3.hitted(map4_hitbox1.getHitbox());
                fish3.hitted(map4_hitbox2.getHitbox());
                fish3.hitted(map4_hitbox3.getHitbox());
                fish3.hitted(map4_hitbox4.getHitbox());
                fish3.hitted(map4_hitbox5.getHitbox());
                fish3.hitted(map4_hitbox6.getHitbox());
                fish3.hitted(map4_hitbox7.getHitbox());
                fish3.hitted(map4_hitbox8.getHitbox());

                fish4.hitted(map4_hitbox1.getHitbox());
                fish4.hitted(map4_hitbox2.getHitbox());
                fish4.hitted(map4_hitbox3.getHitbox());
                fish4.hitted(map4_hitbox4.getHitbox());
                fish4.hitted(map4_hitbox5.getHitbox());
                fish4.hitted(map4_hitbox6.getHitbox());
                fish4.hitted(map4_hitbox7.getHitbox());
                fish4.hitted(map4_hitbox8.getHitbox());
            }
            default -> {
            }
        }

        if (!MyShop.isVisible() && !MyStart.isVisible() && !MyMenu.isVisible() && !MyBattle.isVisible()) {
            player.setSpeed(5);
            player.movement(ct);

            if (time_to_interact == 0 && ct.button_x && !MyMenu.isVisible()) {
                MyMenu.setVisible(true);
            }

        } else if (MyShop.isVisible()) {
            shop1.selected(ct, player);
            time_to_interact = 30;
        } else if (MyStart.isVisible()) {
            MyStart.selected(ct, player);
            time_to_interact = 30;
        } else if (MyMenu.isVisible()) {
            MyMenu.selected(ct, player);
            time_to_interact = 30;
        } else if (MyBattle.isVisible()) {
            MyBattle.selected(ct, player);
            time_to_interact = 30;
        }

        player.updateMap(map);

        // fix to hitbox
        if ((player.getMy_x() >= 1360 - player.getMy_Width() && player.getMy_x() <= 1360 - player.getMy_Width() + 10 && player.getMy_y() >= 768 / 2 - player.getMy_Height() && player.getMy_y() <= 768 / 2 + player.getMy_Height()) && map == 1) {
            map = 2;
            player.setMy_x(player.getMy_Width() / 2);
        } else if ((player.getMy_x() >= 0 && player.getMy_x() <= player.getMy_Width() / 2 - 10 && player.getMy_y() >= 768 / 2 - player.getMy_Height() && player.getMy_y() <= 768 / 2 + player.getMy_Height()) && map == 2) {
            map = 1;
            player.setMy_x(1360 - player.getMy_Width() * 2 - 5);
        } else if ((player.getMy_x() >= 1360 / 2 - player.getMy_Width() && player.getMy_x() <= 1360 / 2 + player.getMy_Width() && player.getMy_y() >= 0 && player.getMy_y() <= player.getMy_Height() / 2 && map == 1)) {
            map = 3;
            player.setMy_y(768 - player.getMy_Height() * 2);
        } else if ((player.getMy_x() >= 1360 / 2 - player.getMy_Width() && player.getMy_x() <= 1360 / 2 + player.getMy_Width() && player.getMy_y() >= 768 - player.getMy_Height() * 2 + 25 && player.getMy_y() <= 768) && map == 3) {
            map = 1;
            player.setMy_y(player.getMy_Height() + 5);
        } else if ((player.getMy_x() >= 0 && player.getMy_x() <= player.getMy_Width() / 2 - 10 && player.getMy_y() >= 768 / 2 - player.getMy_Height() && player.getMy_y() <= 768 / 2 + player.getMy_Height()) && map == 1) {
            map = 4;
            player.setMy_x(1360 - player.getMy_Width() * 2 - 5);
        } else if ((player.getMy_x() >= 1360 - player.getMy_Width() && player.getMy_x() <= 1360 - player.getMy_Width() + 10 && player.getMy_y() >= 768 / 2 - player.getMy_Height() && player.getMy_y() <= 768 / 2 + player.getMy_Height()) && map == 4) {
            map = 1;
            player.setMy_x(player.getMy_Width() / 2);
        }

        //player.setBounds(player.getMy_x(), player.getMy_y(), player.getMy_Width(), player.getMy_Height());
    }

    @Override
    public void run() {
        long drawTime = 1000 / FPS;
        long nextTime = System.currentTimeMillis() + drawTime;
        double drawing;
        while (thread_main != null) {

            if (time_to_interact != 0) {
                time_to_interact -= 1;
            }

            update();

            repaint();

            try {
                if (MyShop.isVisible() || MyStart.isVisible() || MyMenu.isVisible() || MyBattle.isVisible()) {
                    drawing = 90;
                    MyMenu.setTime(MyMenu.getTime() - 1);
                    MyBattle.setTime(MyBattle.getTime() - 1);
                } else {
                    drawTime = 1000 / FPS;
                    nextTime = System.currentTimeMillis() + drawTime;
                    drawing = nextTime - System.currentTimeMillis();
                }
                if (drawing < 0) {
                    drawing = 0;
                }

                thread_main.join((long) drawing);

                nextTime += drawTime;

            } catch (InterruptedException ex) {
            }
        }
    }

    public static MyJFrame getFrame() {
        return frame;
    }

    public static int getTime_to_interact() {
        return time_to_interact;
    }

    public static void setTime_to_interact(int time_to_interact) {
        MyJPanel.time_to_interact = time_to_interact;
    }

}
