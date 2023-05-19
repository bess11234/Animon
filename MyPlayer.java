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
import javax.swing.*;

public class MyPlayer extends Player {

    private int my_x = 1360 / 2;
    private int my_y = 768 / 2;
    private int my_width = 75;
    private int my_height = 75;
    private int speed = 5;
    private int map = 1;
    private Rectangle hitbox = new Rectangle(), rect_up = new Rectangle(), rect_down = new Rectangle(), rect_left = new Rectangle(), rect_right = new Rectangle();
    private transient ImageIcon[][] animation;
    private transient ImageIcon image;
    private int time_animation = 15;
    private boolean left;
    private int move = 1;
    private boolean right;

    public int getSpeed() {
        return speed;
    }

    public ImageIcon[][] getAnimation() {
        return animation;
    }

    public void setAnimation(ImageIcon[][] animation) {
        this.animation = animation;
        this.image = animation[0][0];
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void updateMap(int map) {
        this.map = map;
    }

    public int getMap() {
        return map;
    }

    public boolean hitted(Rectangle rect) {
        hitbox.x = my_x + 16 * 2 - 3;
        hitbox.y = my_y + 16 * 2 + 10;
        hitbox.width = 16 +5;
        hitbox.height = 16 * 2; 

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

    public void movement(MyController ct) {
//        System.out.println(my_x + " " + my_y);
        boolean move = false;
        if (ct.left) {
            if (this.move == 1 && time_animation == 0) {
                this.setImage(animation[1][1]);
                time_animation = 7;
                this.move = 2;
            } else if (this.move == 2 && time_animation == 0) {
                this.setImage(animation[1][0]);
                time_animation = 7;
                this.move = 3;
            } else if (this.move == 3 && time_animation == 0) {
                this.setImage(animation[1][2]);
                time_animation = 7;
                this.move = 4;
            } else if (this.move == 4 && time_animation == 0) {
                this.setImage(animation[1][0]);
                time_animation = 7;
                this.move = 1;
            }

            this.setMy_x(my_x - speed);
            move = true;
        }

        if (ct.right) {
            if (this.move == 1 && time_animation == 0) {
                this.setImage(animation[2][1]);
                time_animation = 7;
                this.move = 2;
            } else if (this.move == 2 && time_animation == 0) {
                this.setImage(animation[2][0]);
                time_animation = 7;
                this.move = 3;
            } else if (this.move == 3 && time_animation == 0) {
                this.setImage(animation[2][2]);
                time_animation = 7;
                this.move = 4;
            } else if (this.move == 4 && time_animation == 0) {
                this.setImage(animation[2][0]);
                time_animation = 7;
                this.move = 1;
            }

            this.setMy_x(my_x + speed);
            move = true;
        }

        if (ct.down) {
            if (this.image == animation[0][0] && time_animation == 0) {
                this.setImage(animation[0][1]);
                time_animation = 10;
            } else if (this.image == animation[0][1] && time_animation == 0) {
                this.setImage(animation[0][2]);
                time_animation = 10;
            } else if (this.image != animation[0][0] && time_animation == 0) {
                this.setImage(animation[0][0]);
                time_animation = 3;
            }

            this.setMy_y(my_y + speed);
            move = true;
        }

        if (ct.up) {
            if (this.image == animation[3][0] && time_animation == 0) {
                this.setImage(animation[3][1]);
                time_animation = 10;
            } else if (this.image == animation[3][1] && time_animation == 0) {
                this.setImage(animation[3][2]);
                time_animation = 10;
            } else if (this.image != animation[3][0] && time_animation == 0) {
                this.setImage(animation[3][0]);
                time_animation = 3;
            }

            this.setMy_y(my_y - speed);
            move = true;
        }

        if (move == false && time_animation == 0) {
            if (ct.passed_before.equals("down") && !ct.down && !ct.left && !ct.right && !ct.up) {
                this.image = animation[0][0];
                time_animation = 3;
            }

            if (ct.passed_before.equals("up") && !ct.down && !ct.left && !ct.right && !ct.up) {
                this.image = animation[3][0];
                time_animation = 3;
            }

            if (ct.passed_before.equals("left") && !ct.down && !ct.left && !ct.right && !ct.up) {
                this.image = animation[1][0];
                time_animation = 3;
                this.move = 1;
            }

            if (ct.passed_before.equals("right") && !ct.down && !ct.left && !ct.right && !ct.up) {
                this.image = animation[2][0];
                time_animation = 3;
                this.move = 1;
            }

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

    public int getTime_animation() {
        return time_animation;
    }

    public void setTime_animation(int time_animation) {
        this.time_animation = time_animation;
        if (this.time_animation < 0) {
            this.time_animation = 0;
        }
    }
}
