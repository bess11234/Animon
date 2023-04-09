import javax.swing.*;
import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
public class ActionFrame extends JPanel implements Runnable{
    public Thread thread_main;
    public MyController ct;
    private final int FPS = 60;
    public ActionFrame(){
        this.setLayout(new GridLayout(3, 1));
        ct = new MyController();
        thread_main = new Thread(this);
        this.setPreferredSize(new Dimension(1360, 768));
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(ct);
        thread_main.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        //draw big GUI
        g2d.setPaint(new Color(37, 53, 67));
        g2d.fillRect(0, 768-768/3, 1360, 768);
        g2d.setPaint(new Color(201, 179, 37));
        g2d.fillRoundRect(0+5, 768-768/3+5, 1360-10, 768/3-10, 20, 20);
        g2d.setPaint(Color.white);
        g2d.fillRoundRect(0+15, 768-768/3+15, 1360-30, 768/3-30, 10, 10);
        g2d.setPaint(new Color(51, 102, 153));
        g2d.fillRoundRect(0+20, 768-768/3+20, 1360-40, 768/3-40, 10, 10);
        //draw half GUI
        g2d.setPaint(new Color(37, 53, 67));
        g2d.fillRoundRect(1360/2, 768-768/3, 1360, 768, 20, 20);
        g2d.setPaint(new Color(135, 135, 212));
        g2d.fillRoundRect(1360/2+5, 768-768/3+5, 1360/2-10, 768/3-10, 10, 10);
        g2d.setPaint(new Color(92, 92, 125));
        g2d.fillRect(1360/2+10, 768-768/3+10, 1360/2-20, 768/3-20);
        g2d.setPaint(Color.white);
        g2d.fillRoundRect(1360/2+15, 768-768/3+15, 1360/2-30, 768/3-30, 10, 10);
        
        
        
        //draw select
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(5));
        if(MyBattle.getNum() == 0){g2d.drawRoundRect(1360/2+20, 768-768/3+20, 340-40, 128-40, 10, 10);}
        if(MyBattle.getNum() == 1){g2d.drawRoundRect(1360/2+20+340, 768-768/3+20, 340-40, 128-40, 10, 10);}
        if(MyBattle.getNum() == 2){g2d.drawRoundRect(1360/2+20, 768-768/3+20+128, 340-40, 128-40, 10, 10);}
        if(MyBattle.getNum() == 3){g2d.drawRoundRect(1360/2+20+340, 768-768/3+20+128, 340-40, 128-40, 10, 10);}
    }
    public void update(){
        MyBattle.selected(ct);
    }
    @Override
    public void run() {
        long drawTime = 1000 / FPS;
        long nextTime = System.currentTimeMillis() + drawTime;
        double drawing;
        while (thread_main != null) {
            
            update();
            
            repaint();
            
            try {
                drawTime = 1000 / FPS;
                nextTime = System.currentTimeMillis() + drawTime;
                drawing = nextTime - System.currentTimeMillis();
                
                if (drawing < 0) {
                    drawing = 0;
                }
                
                thread_main.join((long) drawing);
                
                nextTime += drawTime;
                
            } catch (InterruptedException ex) {
            }
        }
    }
}
