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
        JLabel titletext = new JLabel("MYTHIQUEBEASTS", 0);
        this.add(titletext);
        titletext.setForeground(Color.WHITE);
        titletext.setFont(new Font("Serif", Font.BOLD, 32));
    }
}
