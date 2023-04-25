
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
public class MyStart{

    private static int num = 0;
    private static boolean visible = true;
    
    public static void selected(MyController ct, Player player) {
        if (ct.button_z) {
            switch (num) {
                case 0 -> player.setAnimals(new Dogmon(1), player.getCountAnimals());
                case 1 -> player.setAnimals(new Birdmon(1), player.getCountAnimals());
                case 2 -> player.setAnimals(new Fishmon(1), player.getCountAnimals());
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

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        MyStart.num = num;
        if (MyStart.num < 0) {
            MyStart.num = 2;
        } else if (MyStart.num > 2) {
            MyStart.num = 0;
        }
    }

    public static boolean isVisible() {
        return visible;
    }

    public static void setVisible(boolean visible) {
        MyStart.visible = visible;
    }

}
