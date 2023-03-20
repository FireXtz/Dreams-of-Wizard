package main;

import input.KeyboardInputs;
import input.MouseInputs;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage bufferedImage;
    private BufferedImage bufferedSubImage;
    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    private void importImg() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("res/Idle.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setPanelSize() {
        Dimension size = new Dimension(800,600);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    public void setRectPosition(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }
    public void changeXDelta(int value){
        this.xDelta += value;
    }
    public void changeYDelta(int value){
        this.yDelta += value;
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        bufferedSubImage = bufferedImage.getSubimage(0, 0, 100, 128);
        graphics.drawImage(bufferedSubImage,(int)xDelta,(int)yDelta,null);
    }
}
