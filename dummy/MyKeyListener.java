import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener{
    int i = 0;
    MyPanel p;
    @Override
    public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_SPACE){
            i++;
            System.out.println("Jumped! " + i);
            p = new MyPanel();
            p.jump = true;
            p.jump();
         }
         if (e.getKeyCode() == KeyEvent.VK_N){
            System.out.println("Hello!");
         }
    }
    @Override
    public void keyTyped(KeyEvent e) { 
    }

    @Override
    public void keyReleased(KeyEvent e) {     
    }
}