package main;

import javax.swing.*;
public class GameWindow extends JFrame {
    private JFrame jFrame;
    public GameWindow(GamePanel gamePanel){
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
