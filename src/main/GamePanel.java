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
import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        importImgs(new String[]{"res/Idle.png", "res/Run.png"});
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImgs(String[] imagePaths) {
        animations = new BufferedImage[imagePaths.length][];
        for (int i = 0; i < imagePaths.length; i++) {
            try (FileInputStream inputStream = new FileInputStream(imagePaths[i])) {
                BufferedImage img = ImageIO.read(inputStream);
                int frameCount = getSpriteAmount(i);
                animations[i] = new BufferedImage[frameCount];
                int frameWidth = img.getWidth() / frameCount;
                for (int j = 0; j < frameCount; j++) {
                    animations[i][j] = img.getSubimage(j * frameWidth, 0, frameWidth, img.getHeight());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Image file not found: " + imagePaths[i], e);
            } catch (IOException e) {
                throw new RuntimeException("Error reading image file: " + imagePaths[i], e);
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(800, 600);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        updateAnimationTick();
        setAnimation();
        updatePos();
        graphics.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 256,160,null);
    }

    private void updatePos() {
        if (moving){
            switch (playerDir) {
                case LEFT:
                    xDelta-= 5;
                    break;
                case UP:
                    yDelta-= 5;
                    break;
                case RIGHT:
                    xDelta+= 5;
                    break;
                case DOWN:
                    yDelta+= 5;
                    break;
            }
        }
    }

    public void setAnimation() {
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }
}
