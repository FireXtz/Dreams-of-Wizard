package main;
public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }
    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

       double timePerFrame = 1000000000.0 / FPS_SET;
       long lastFrame = System.nanoTime();
       long now = System.nanoTime();
       int fps = 0;
       long lastCheck = 0;

       while (true) {
           now = System.nanoTime();
           if (System.nanoTime() - lastFrame >= timePerFrame) {
               gamePanel.repaint();
               lastFrame = now;
               fps++;
           }

           if (System.currentTimeMillis() - lastCheck >= 1000) {
               lastCheck = System.currentTimeMillis();
               System.out.println("FPS:" + fps);
               fps = 0;
           }
       }
    }
}
