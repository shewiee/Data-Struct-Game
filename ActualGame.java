import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ActualGame extends JFrame implements KeyListener{
	
	Icon imageCharacter = new ImageIcon("textures\\dino.png");
	ImageIcon logo = new ImageIcon("textures\\icon.png");
	JLabel dino = new JLabel();
	Icon cactusImage = new ImageIcon("textures\\cactus.png");
	JLabel cactus = new JLabel();
	JPanel panelCharacter = new JPanel();
	ImageIcon background = new ImageIcon();
	Timer jumpTimer;
	Timer cactusTimer;
	
	boolean goingUp = true;
	
	ActualGame(){
		dino.setIcon(imageCharacter);
		dino.setBounds(-20,105,360,360);
		cactus.setIcon(cactusImage);
		cactus.setBounds(700 ,360, 50, 50);
		this.setTitle("Dino Jump!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(850,450);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setContentPane(new JLabel(new ImageIcon("textures\\background.jpg")));
		this.setIconImage(logo.getImage());
		this.addKeyListener(this);
		this.add(dino);
		this.add(cactus);
		this.setVisible(true);
		
		jumpTimer = new Timer(10, new jumpTimerListener());
		cactusTimer = new Timer(10, new cactusListener());
		cactusTimer.start();
	}
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
		
		case 'a':
				dino.setLocation(dino.getX()-10,dino.getY());
				
			break;
		case 'w':
				dino.setLocation(dino.getX(),dino.getY()-10);
			break;
		case 's':
				dino.setLocation(dino.getX(),dino.getY()+10);
			break;
		case 'd':
				dino.setLocation(dino.getX()+10,dino.getY());
			break;
		case ' ':
			jumpTimer.start();
			break;
		}
		
	}
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		
		case 37:
				dino.setLocation(dino.getX()-10,dino.getY());
			break;
		case 38:
				dino.setLocation(dino.getX(),dino.getY()-10);
			break;
		case 40:
				dino.setLocation(dino.getX(),dino.getY()+10);
			break;
		case 39:
				dino.setLocation(dino.getX()+10,dino.getY());
			break;
		}
	}
		
	public void keyReleased(KeyEvent e) {
	System.out.print(e.getKeyChar());
	System.out.print(e.getKeyCode());
	}
	
	private class jumpTimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(goingUp) {
				dino.setLocation(dino.getX(),dino.getY() - 5);
			}
			else {
				dino.setLocation(dino.getX(), dino.getY() + 15);
			}
			if(dino.getY() <= -105) {
				goingUp = false;
			}
			else if(dino.getY() >= 105){
				dino.setLocation(dino.getX(), 105);
				goingUp = true;
				jumpTimer.stop();
			}
		}
	}
	
	private class cactusListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			cactus.setLocation(cactus.getX() - 5, cactus.getY());
			if(cactus.getX() < 0) {
				cactus.setLocation(700, cactus.getY());
			}
		}
	}
	
}
