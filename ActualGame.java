import java.awt.Color;
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

public class ActualGame extends JFrame implements KeyListener {

	Icon dinoImage = new ImageIcon("textures\\dinasourstand.png");
	ImageIcon logo = new ImageIcon("textures\\icon.png");
	JLabel dino = new JLabel();
	Icon cactusImage = new ImageIcon("textures\\cactus.png");
	JLabel cactus = new JLabel();
	JLabel scoreDisplay = new JLabel();
	JPanel panelCharacter = new JPanel();
	//ImageIcon background = new ImageIcon();
	Timer jumpTimer;
	Timer cactusTimer;
	Timer scoreTimer;

	boolean playing = false;
	boolean goingUp = true;
	int score = 0;

	ActualGame() {
		// general options
		this.setTitle("Dino Jump!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(850, 450);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

		// score
		scoreDisplay.setBounds(750, 25, 70, 20);
		scoreDisplay.setForeground(Color.WHITE);
		// dino
		dino.setIcon(dinoImage);
		dino.setBounds(35, 110, 360, 360);
		// cactus
		cactus.setIcon(cactusImage);
		cactus.setBounds(700, 360, 50, 50); // can be randomized
		// cactus.setBorder(new LineBorder(Color.BLACK)); //border for collision
		// purposes

		// background
		this.setContentPane(new JLabel(new ImageIcon("textures\\backgroundFin.png")));
		this.setIconImage(logo.getImage());
		this.addKeyListener(this);
		this.add(dino);
		scoreDisplay.setText("Score: 0");
		this.add(scoreDisplay);
		this.add(cactus);
		this.setVisible(true);

		jumpTimer = new Timer(10, new jumpTimerListener());
		cactusTimer = new Timer(10, new cactusListener());
		scoreTimer = new Timer(100, new scoreTimerListener());
	}

	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case ' ':
			if (!playing) {
				playing = true;
				cactusTimer.start();
				scoreTimer.start();
			} else {
				jumpTimer.start();
			}
			break;
		}

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 38:
			jumpTimer.start();
			break;
		case 40:
			System.out.println("Down");
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	private class jumpTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (goingUp) {
				dino.setLocation(dino.getX(), dino.getY() - 5);
			} else {
				dino.setLocation(dino.getX(), dino.getY() + 10);
			}
			if (dino.getY() <= -50) {
				goingUp = false;
			} else if (dino.getY() >= 110) {
				dino.setLocation(dino.getX(), 110);
				goingUp = true;
				jumpTimer.stop();
			}
		}
	}

	private class cactusListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cactus.setLocation(cactus.getX() - 5, cactus.getY());
			if (cactus.getX() < 0) {
				cactus.setLocation(700, cactus.getY());
			}
		}
	}
	
	private class scoreTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			score++;
			scoreDisplay.setText("Score: " + score);
		}
	}

}
