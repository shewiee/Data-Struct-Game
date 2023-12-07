import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class ActualGame extends JFrame implements KeyListener {

	Icon dinoImage = new ImageIcon("textures\\dinasourstand.png");
	ImageIcon logo = new ImageIcon("textures\\icon.png");
	JLabel dino = new JLabel();
	Icon cactusImage = new ImageIcon("textures\\cactus.png");
	JLabel cactus = new JLabel();
	JLabel scoreDisplay = new JLabel();
	JPanel panelCharacter = new JPanel();
	// ImageIcon background = new ImageIcon();
	Timer jumpTimer;
	Timer cactusTimer;
	Timer scoreTimer;
	Timer dinoRunAnimTimer;

	boolean playing = false;
	boolean crouch = false;
	boolean jump = false;
	boolean onAir = false;
	boolean walk = false;
	boolean collided = false;
	int score;

	// entity borders
	JLabel dinoBorder = new JLabel();
	JLabel cactusBorder = new JLabel();

	// variable values
	// dino
	int dinoX;
	int dinoY;
	int dinoStandBorderWidth;
	int dinoStandBorderHeight;
	int dinoCrouchY;
	int dinoCrouchBorderWidth;
	int dinoCrouchBorderHeight;
	int dinoJumpLimit;

	// cactus
	int cactusX;
	int cactusY;
	int cactusBorderX;
	int cactusBorderY;
	int cactusThroughLimit;
	int cactusBorderWidth;
	int cactusBorderHeight;

	// reminders
	// PLEASE PRESS JUMP TO PLAY
	ActualGame() {
		// general options
		this.setTitle("Dino Jump!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(850, 450);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

		// score
		scoreDisplay.setBounds(750, 0, 70, 20);
		scoreDisplay.setForeground(Color.WHITE);

		dino.setIcon(dinoImage);
		cactus.setIcon(cactusImage);
		initialPosition();

		// background
		this.setContentPane(new JLabel(new ImageIcon("textures\\backgroundFin.png")));
		this.setIconImage(logo.getImage());
		this.addKeyListener(this);

		// add
		this.add(dino);
		this.add(dinoBorder);
		scoreDisplay.setText("Score: 0");
		this.add(scoreDisplay);
		this.add(cactus);
		this.add(cactusBorder);
		this.setVisible(true);

		jumpTimer = new Timer(10, new jumpTimerListener());
		cactusTimer = new Timer(10, new cactusListener());
		scoreTimer = new Timer(100, new scoreTimerListener());
		dinoRunAnimTimer = new Timer(100, new dinoRunAnimTimerListener());
	}

	public void initialPosition() {
		score = 0;
		dinoX = 35;
		dinoY = 225;
		dinoStandBorderWidth = 56;
		dinoStandBorderHeight = 64;
		dinoCrouchY = dinoY + 25;
		dinoCrouchBorderWidth = 80;
		dinoCrouchBorderHeight = 34;
		dinoJumpLimit = 60; // Y-60 in jframe
		cactusX = 900;
		cactusY = 217;
		cactusBorderX = cactusX + 60;
		cactusBorderY = cactusY + 20;
		cactusThroughLimit = -70;
		cactusBorderWidth = 35;
		cactusBorderHeight = 55;

		// dino

		dino.setBounds(dinoX, dinoY, 94, 64); // 94 x 64 depends on the size of the image
		dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
		dinoBorder.setBorder(new LineBorder(Color.RED));

		// cactus

		cactus.setBounds(cactusX, cactusY, 94, 94); // can be randomized
		cactusBorder.setBounds(cactusBorderX, cactusBorderY, cactusBorderWidth, cactusBorderHeight);
		cactusBorder.setBorder(new LineBorder(Color.RED));
		// cactus.setBorder(new LineBorder(Color.BLACK)); //border for collision
		// purposes
	}

	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case ' ':
			if (!playing) {
				jumpTimer.stop();
				dino.setLocation(dino.getX(), dino.getY());
				dinoBorder.setLocation(dinoBorder.getX(), dinoBorder.getY());
				reset();
				
				playing = true;
				initialPosition();
				cactusTimer.start();
				scoreTimer.start();
				dinoRunAnimTimer.start();
			} else {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinasourstand.png"));
				jumpTimer.start();
				dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
			}
			break;
		}

	}

	public void keyPressed(KeyEvent e) {
		if (playing) {
			switch (e.getKeyCode()) {
			case 38:
				jumpTimer.start();
				dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
				break;
			case 40:
				if (!onAir || jump) {
					crouch = true;
					dinoBorder.setBounds(dinoX, dinoCrouchY, dinoCrouchBorderWidth, dinoCrouchBorderHeight);
					dino.setIcon(new javax.swing.ImageIcon("textures\\dinasourduckleft.png"));
				}
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		crouch = false;
		dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
	}

	private class jumpTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (collided) {
				//moved
			} else{
				if (!jump) {
					dinoBorder.setLocation(dino.getX(), dino.getY() - 5);
					dino.setLocation(dino.getX(), dino.getY() - 5);
					onAir = true;
				} else {
					dinoBorder.setLocation(dino.getX(), dino.getY() + 10);
					dino.setLocation(dino.getX(), dino.getY() + 10);
				}
				if (dino.getY() <= dinoJumpLimit) {
					jump = true;
				} else if (dino.getY() >= dinoY) {
					dinoBorder.setLocation(dino.getX(), dinoY);
					dino.setLocation(dino.getX(), dinoY);
					jump = false;
					onAir = false;
					jumpTimer.stop();
				}
			}

		}
	}

	private void reset() {
		playing = false;
		crouch = false;
		jump = false;
		onAir = false;
		walk = false;
		collided = false;
	}

	private class cactusListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cactus.setLocation(cactus.getX() - 5, cactus.getY());
			cactusBorder.setLocation(cactusBorder.getX() - 5, cactusBorder.getY());
			if (cactus.getX() < cactusThroughLimit) {
				cactus.setLocation(cactusX, cactus.getY());
				cactusBorder.setLocation(cactusBorderX, cactusBorder.getY());
			}

			// EXAMPLE OF COLLISION
			if (checkCollision(dinoBorder, cactusBorder)) {
				playing = false;
				collided = true;
				System.out.println("Collided");
				cactusTimer.stop();
				scoreTimer.stop();
				dinoRunAnimTimer.stop();
			}
		}
	}

	private boolean checkCollision(JComponent comp1, JComponent comp2) {
		Rectangle bounds1 = comp1.getBounds();
		Rectangle bounds2 = comp2.getBounds();
		return bounds1.intersects(bounds2);
	}

	private class scoreTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			score++;
			scoreDisplay.setText("Score: " + score);
		}
	}

	private class dinoRunAnimTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!walk && !jump && !crouch) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinasourstandleft.png"));
				walk = true;
			} else if (walk && !jump && !crouch) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinasourstandright.png"));
				walk = false;
			} else if (jump && onAir) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinasourstand.png"));
				walk = false;
			} else if (crouch && !walk && !onAir) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinasourduckleft.png"));
				walk = true;
			} else if (crouch && walk && !onAir) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinasourduckright.png"));
				walk = false;
			}
		}
	}

}
