import java.awt.*;

import javax.swing.*;

public class MyFrame extends JFrame{
	
	MyPanel panel;
	
	MyFrame(){
      MyKeyListener keyListener = new MyKeyListener();
		
		panel = new MyPanel();
		
      addKeyListener(keyListener);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
      this.setResizable(false);
      this.setTitle("Dino Jump");
	}  
}