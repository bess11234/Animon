
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class MyBattle {
    private static boolean visible = false;
    private static int num = 0;

    public static boolean isVisible() {
        return visible;
    }

    public static void setVisible(boolean visible) {
        MyBattle.visible = visible;
    }
    
    
    public static void paint(Graphics g, Player player, Animon enemy){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(new Color(230, 230, 230));
        g2D.fillRect(0, 0, 1360, 768);
        //draw big GUI
        g2D.setPaint(new Color(37, 53, 67));
        g2D.fillRect(0, 768-768/3, 1360, 768);
        g2D.setPaint(new Color(201, 179, 37));
        g2D.fillRoundRect(0+5, 768-768/3+5, 1360-10, 768/3-10, 20, 20);
        g2D.setPaint(Color.white);
        g2D.fillRoundRect(0+15, 768-768/3+15, 1360-30, 768/3-30, 10, 10);
        g2D.setPaint(new Color(51, 102, 153));
        g2D.fillRoundRect(0+20, 768-768/3+20, 1360-40, 768/3-40, 10, 10);
        //draw half GUI
        g2D.setPaint(new Color(37, 53, 67));
        g2D.fillRoundRect(1360/2, 768-768/3, 1360, 768, 20, 20);
        g2D.setPaint(new Color(135, 135, 212));
        g2D.fillRoundRect(1360/2+5, 768-768/3+5, 1360/2-10, 768/3-10, 10, 10);
        g2D.setPaint(new Color(92, 92, 125));
        g2D.fillRect(1360/2+10, 768-768/3+10, 1360/2-20, 768/3-20);
        g2D.setPaint(new Color(250, 250, 250));
        g2D.fillRoundRect(1360/2+15, 768-768/3+15, 1360/2-30, 768/3-30, 10, 10);
        
        //draw select
        g2D.setPaint(Color.black);
        
        g2D.setFont(new Font("Dialog", 12, 40));
        g2D.drawString("FIGHT", (1360/2+20)+(340-40)/2 - 50, (768-768/3+20)+(128-40)/2 + 10);
        g2D.drawString("INVENTORY", (1360/2+20+340)+(340-40)/2 - 110, (768-768/3+20)+(128-40)/2 + 10);
        g2D.drawString("ANIMON", (1360/2+20)+(340-40)/2 - 70, (768-768/3+20+128)+(128-40)/2 + 10);
        g2D.drawString("RUN", (1360/2+20+340)+(340-40)/2 - 45, (768-768/3+20+128)+(128-40)/2 + 10);
        
        g2D.setFont(new Font("Dialog", 12, 12));
        
        g2D.setStroke(new BasicStroke(5));
        if(MyBattle.getNum() == 0){g2D.drawRoundRect(1360/2+20, 768-768/3+20, (340-40), 128-40, 10, 10);}
        if(MyBattle.getNum() == 1){g2D.drawRoundRect(1360/2+20+340, 768-768/3+20, (340-40), 128-40, 10, 10);}
        if(MyBattle.getNum() == 2){g2D.drawRoundRect(1360/2+20, 768-768/3+20+128, (340-40), 128-40, 10, 10);}
        if(MyBattle.getNum() == 3){g2D.drawRoundRect(1360/2+20+340, 768-768/3+20+128, (340-40), 128-40, 10, 10);}
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        MyBattle.num = num;
    }
    public static void selected(MyController ct){
        if(ct.down && num == 0){MyBattle.setNum(2);}
        if(ct.right && num == 0){MyBattle.setNum(1);}
        if(ct.down && num == 1){MyBattle.setNum(3);}
        if(ct.left && num == 1){MyBattle.setNum(0);}
        if(ct.up && num == 2){MyBattle.setNum(0);}
        if(ct.right && num == 2){MyBattle.setNum(3);}
        if(ct.up && num == 3){MyBattle.setNum(1);}
        if(ct.left && num == 3){MyBattle.setNum(2);}
        if(ct.button_x){MyBattle.visible = false;}
    }
}
