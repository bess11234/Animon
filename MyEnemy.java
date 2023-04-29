/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
import java.awt.Image;
import java.awt.Rectangle;
import java.util.*;

public class MyEnemy implements Runnable {

    private final int origin_x;
    private final int origin_y;
    private Rectangle hitbox = new Rectangle(), rect_up = new Rectangle(), rect_down = new Rectangle(), rect_left = new Rectangle(), rect_right = new Rectangle();
    private int my_x;
    private int my_y;
    private Animon animon;
    private ArrayList<Integer> moveset1, moveset2, moveset3, moveset4, moveset5, moveset6, moveset7, moveset8, moveset0;
    private int my_width;
    private int my_height;
    private final int FPS = 60;
    private int speed;
    private String[] animation = new String[4];
    private Rectangle origin;
    private boolean re_animon;
    private boolean leave_origin;
    private boolean moveto_player;

    private int move;
    // It need to be in class Animon
    //private Animal animon; !!Keep animal!!

    public Animon getAnimon() {
        return animon;
    }

    public MyEnemy(int x, int y, Animon animon) {
        speed = 3;
        my_width = 50;
        my_height = 50;
        re_animon = true;
        origin = new Rectangle();

        moveset1 = new ArrayList<Integer>();
        moveset1.add(0);
        moveset1.add(1);
        moveset1.add(5);
        moveset1.add(7);
        moveset2 = new ArrayList<Integer>();
        moveset2.add(0);
        moveset2.add(2);
        moveset2.add(6);
        moveset2.add(8);
        moveset3 = new ArrayList<Integer>();
        moveset3.add(0);
        moveset3.add(3);
        moveset3.add(5);
        moveset3.add(6);
        moveset4 = new ArrayList<Integer>();
        moveset4.add(0);
        moveset4.add(4);
        moveset4.add(7);
        moveset4.add(8);

        moveset5 = new ArrayList<Integer>();
        moveset5.add(0);
        moveset5.add(5);
        moveset5.add(1);
        moveset5.add(3);
        moveset6 = new ArrayList<Integer>();
        moveset6.add(0);
        moveset6.add(6);
        moveset6.add(2);
        moveset6.add(3);
        moveset7 = new ArrayList<Integer>();
        moveset7.add(0);
        moveset7.add(7);
        moveset7.add(1);
        moveset7.add(4);
        moveset8 = new ArrayList<Integer>();
        moveset8.add(0);
        moveset8.add(8);
        moveset8.add(2);
        moveset8.add(4);
        this.move = 8;
        my_x = x;
        my_y = y;
        origin_x = x;
        origin_y = y;
        this.animon = animon;

        origin.x = x - 50;
        origin.y = y - 50;
        origin.width = 125;
        origin.height = 125;

        hitbox.x = my_x + 16;
        hitbox.y = my_y + 16 * 2;
        hitbox.width = 16;
        hitbox.height = 16;
        //this.animon = animon;
        //this.setBackground(Color.red);
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
            this.setMy_y(this.getMy_y() - speed);
            return true;
        } else if (hitbox.intersects(rect_right)) {
            this.setMy_x(this.getMy_x() + speed);
            return true;
        } else if (hitbox.intersects(rect_left)) {
            this.setMy_x(this.getMy_x() - speed);
            return true;
        } else if (hitbox.intersects(rect_down)) {
            this.setMy_y(this.getMy_y() + speed);
            return true;
        }
        return false;
    }

    public void move_to_player(Rectangle player) {
        ArrayList<Integer> move_left_down = new ArrayList<Integer>();
        move_left_down.add(1);
        move_left_down.add(7);
        move_left_down.add(4);
        ArrayList<Integer> move_right_down = new ArrayList<Integer>();
        move_right_down.add(2);
        move_right_down.add(4);
        move_right_down.add(8);
        ArrayList<Integer> move_left_up = new ArrayList<Integer>();
        move_left_up.add(1);
        move_left_up.add(3);
        move_left_up.add(5);
        ArrayList<Integer> move_right_up = new ArrayList<Integer>();
        move_right_up.add(3);
        move_right_up.add(6);
        move_right_up.add(2);

        if ((player.x - 5 > my_x || player.x + 5 > my_x) && (player.y - 5 > my_y || player.y + 5 > my_y)) {
            move = move_right_down.get(new Random().nextInt(move_right_down.size()));
        } else if ((player.x - 5 > my_x || player.x + 5 > my_x) && (player.y - 5 < my_y || player.y + 5 < my_y)) {
            move = move_right_up.get(new Random().nextInt(move_right_up.size()));
        } else if ((player.x - 5 < my_x || player.x + 5 > my_x) && (player.y - 5 < my_y || player.y + 5 < my_y)) {
            move = move_left_up.get(new Random().nextInt(move_left_up.size()));
        } else if ((player.x - 5 < my_x || player.x + 5 > my_x) && (player.y - 5 > my_y || player.y + 5 > my_y)) {
            move = move_left_down.get(new Random().nextInt(move_left_down.size()));
        } else if (player.x - 5 > my_x || player.x + 5 > my_x) {
            move = 2;
        } else if (player.x - 5 < my_x || player.x + 5 < my_x) {
            move = 1;
        } else if (player.y - 5 < my_y || player.y + 5 < my_y) {
            move = 3;
        } else if (player.y - 5 > my_y || player.y + 5 > my_y) {
            move = 4;
        } else {
            move = 0;
        }
    }

    public void random_move() {
        if (move == 0) {
            speed = 0;
            move = new Random().nextInt(0, 9);
        } else {
            speed = 3;
        }

        if (move == 1) {
            move = moveset1.get(new Random().nextInt(moveset1.size()));
        } else if (move == 2) {
            move = moveset2.get(new Random().nextInt(moveset2.size()));
        } else if (move == 3) {
            move = moveset3.get(new Random().nextInt(moveset3.size()));
        } else if (move == 4) {
            move = moveset4.get(new Random().nextInt(moveset4.size()));
        } else if (move == 5) {
            move = moveset5.get(new Random().nextInt(moveset5.size()));
        } else if (move == 6) {
            move = moveset6.get(new Random().nextInt(moveset6.size()));
        } else if (move == 7) {
            move = moveset7.get(new Random().nextInt(moveset7.size()));
        } else if (move == 8) {
            move = moveset8.get(new Random().nextInt(moveset8.size()));
        }
    }

    public void movement() {

        if (!hitbox.intersects(origin)) {
            leave_origin = true;
        } else {
            leave_origin = false;
        }
        if (isDead()) {
            speed = 0;
            my_x = 9999;
            my_y = 9999;
        }
        if (move == 1 || move == 5 || move == 7) {
            this.setMy_x(my_x - speed);
        }
        if (move == 2 || move == 6 || move == 8) {
            this.setMy_x(my_x + speed);
        }
        if (move == 3 || move == 5 || move == 6) {
            this.setMy_y(my_y - speed);
        }
        if (move == 4 || move == 7 || move == 8) {
            this.setMy_y(my_y + speed);
        }

        if (!leave_origin) {
            random_move();
        }

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLeave_origin() {
        return leave_origin;
    }

    public Rectangle getOrigin() {
        return origin;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void reposition(int maxLevel) {
        int limit = maxLevel - 2;
        if (limit < 1) {
            limit = 1;
        }
        if (animon instanceof Dogmon) {
            animon = new Dogmon(new Random().nextInt(limit, maxLevel + 2));
        } else if (animon instanceof Fishmon) {
            animon = new Fishmon(new Random().nextInt(limit, maxLevel + 2));
        } else if (animon instanceof Birdmon) {
            animon = new Birdmon(new Random().nextInt(limit, maxLevel + 2));
        }

        this.my_x = origin_x;
        this.my_y = origin_y;
    }

    public void setMy_x(int x) {
        my_x = x;
        if (my_x + my_width > origin_x + my_width + 50 && !isDead() && !moveto_player && !leave_origin) {
            my_x = origin_x + my_width + 50 - my_width;
        } else if (my_x < origin_x - 50 && !isDead() && !moveto_player && !leave_origin) {
            my_x = origin_x - 50;
        }

        if (my_x < 0 && !isDead() && !moveto_player) {
            my_x = 0;
        } else if (my_x + my_width > 1360 && !isDead() && !moveto_player && !leave_origin) {
            my_x = 1360 - my_width;
        }

    }

    public int getMy_x() {
        return my_x;
    }

    public void setMy_y(int y) {
        my_y = y;
        if (my_y + my_height > origin_y + my_height + 50 && !isDead() && !moveto_player && !leave_origin) {
            my_y = origin_y + my_height + 50 - my_height;
        } else if (my_y < origin_y - 50 && !isDead() && !moveto_player && !leave_origin) {
            my_y = origin_y - 50;
        }

        if (my_y < 0 && !isDead() && !moveto_player) {
            my_y = 0;
        } else if (my_y + my_height > 768 && !isDead() && !moveto_player && !leave_origin) {
            my_y = 768 - my_height;
        }

    }

    public boolean isMoveto_player() {
        return moveto_player;
    }

    public void setMoveto_player(boolean moveto_player) {
        this.moveto_player = moveto_player;
        if (moveto_player){
            speed = 5;
        }else{
            speed = 3;
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

    public int getLevel() {
        return animon.level;
    }

    public Image getImage() {
        return animon.getImage();
    }

    public boolean isDead() {
        return animon.dead_before;
    }

    public void run() {
        long drawTime = 1000 / FPS;
        long nextTime = System.currentTimeMillis() + drawTime;
        while (Thread.currentThread() != null) {
            double drawing = 50;
            movement();

            try {
                //double drawing = nextTime - System.currentTimeMillis();
                if (!MyMenu.isVisible() && !MyShop.isVisible() && !MyBattle.isVisible()) {
                    drawing = 50;
                    speed = 3;
                } else {
                    speed = 0;
                }

                if (drawing < 0) {
                    drawing = 0;
                }

                if (move == 0) {
                    drawing = 500;
                }

                if (isDead()) {
                    drawing = 5000;
                }
                Thread.currentThread().sleep((long) drawing);

                if (isDead()) {
                    re_animon = !re_animon;

                    if (re_animon) {
                        this.reposition(Player.getHighestLevel());
                    }
                }

                nextTime += drawTime;

            } catch (InterruptedException ex) {
            }
        }
    }
}
