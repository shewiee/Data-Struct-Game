import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener{
	final int PANEL_WIDTH = 500;
	final int PANEL_HEIGHT = 500;
	Image dino;
	Image backgroundImage;
	Timer timer;
   
	int xVelocity = 5;
	int yVelocity = 1;
	int x = -20;
	int y = 150;
   boolean jump = false;
	
	MyPanel(){
		this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		this.setBackground(Color.white);
		dino = new ImageIcon("textures\\dino.png").getImage();	
		backgroundImage = new ImageIcon("textures\\background.jpg").getImage();
      jump();
	}

	public void paint(Graphics g) {
		
		super.paint(g); // paint background
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.drawImage(backgroundImage, 0, 0, null);
		g2D.drawImage(dino, x, y, null);
	}
   
   public void jump(){
		timer = new Timer(10, this);
      timer.start();
   }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(y + yVelocity > y) {
         //going down
         yVelocity = 15;
		}
      else{
         //going up
         yVelocity = -10;
      }
		
		if(y < -200 || y > 157) {
			yVelocity = yVelocity * -1;
         jump = false;
         //(PANEL_HEIGHT + 150)-enemy.getHeight(null)
		}
		y = y + yVelocity;
		repaint();
	}
}

