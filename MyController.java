
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bess1123
 */
public class MyController implements KeyListener {

    public boolean up, down, left, right, button_z, button_x;
    public String passed_before = "";

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
//        System.out.println(ke.getKeyCode());

        if (ke.getKeyCode() == 90 || ke.getKeyCode() == 10){
            button_z = true;
        }
        if (ke.getKeyCode() == 88 || ke.getKeyCode() == 27){
            button_x = true;
        }
        if (ke.getKeyCode() == 37) {
            left = true;
            passed_before = "left";
        }
        if (ke.getKeyCode() == 38) {
            up = true;
            passed_before = "up";
        }
        if (ke.getKeyCode() == 39) {
            right = true;
            passed_before = "right";
        }
        if (ke.getKeyCode() == 40) {
            down = true;
            passed_before = "down";
        }
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == 90 || ke.getKeyCode() == 10){
            button_z = false;
        }
        if (ke.getKeyCode() == 88 || ke.getKeyCode() == 27){
            button_x = false;
        }
        if (ke.getKeyCode() == 37) {
            left = false;
        }
        if (ke.getKeyCode() == 38) {
            up = false;
        }
        if (ke.getKeyCode() == 39) {
            right = false;
        }
        if (ke.getKeyCode() == 40) {
            down = false;
        }
    }

}
