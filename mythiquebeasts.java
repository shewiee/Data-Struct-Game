package mythiquebeasts;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class mythiquebeasts extends JFrame{
    public static void main(String[] args){
        mythiquebeasts mb = new mythiquebeasts();
    }

    public mythiquebeasts(){

        //Icon
        ImageIcon icon = new ImageIcon("textures\\icon\\icon.png");

        //Window
        this.setSize(640, 400);
        this.setTitle("MythiqueBeasts");
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        
        
        //Title Page
        JLabel titletext = new JLabel("MYTHIQUEBEASTS");
        this.add(titletext);
        titletext.setBounds(160, 20, 350, 100);
        titletext.setForeground(Color.WHITE);
        titletext.setFont(new Font("Serif", Font.BOLD, 32));
        JButton startbutton = new JButton("Start");
        this.add(startbutton);
        startbutton.setBounds(260, 200, 120, 30);
        
        /* 
        JLabel imagelogo = new JLabel();
        imagelogo.setIcon(new ImageIcon("textures\\icon\\icon.png"));
        Dimension imagelogosize = imagelogo.getPreferredSize();
        imagelogo.setBounds(20, 1, imagelogosize.width, imagelogosize.height);
        this.add(imagelogo);
        */

        this.setLayout(null);
    }
}
