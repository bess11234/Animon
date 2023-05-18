/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bess1123
 */
import javax.swing.JFrame;

public class MyJFrame extends JFrame{

    private MyJPanel panel;

    public MyJFrame(){
        super("Animon");
        try{
            panel = new MyJPanel(this);
        }catch(ClassNotFoundException ex){ex.printStackTrace();}
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(1360, 768);
        this.add(panel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
