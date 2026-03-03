package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

// opcoes da tela 
final int originalTitleSize = 32; // 32x32 titulo
final int scale = 2;

final int titleSize = originalTitleSize * scale; // 64x64 titulo
 final int maxScreenCol = 16;
 final int maxScreenRow = 12;
 final int screenWidth = titleSize * maxScreenCol; // 1024 pixels
final int screenHeight = titleSize * maxScreenRow; // 768 pixels

// FPS

int FPS = 60;

KeyHandler KeyH = new KeyHandler();
Thread gameThread;

// SETAR AS CONFIGURACOES INICIAIS DO JOGO
int playerX = 100;
int playerY = 100;
int playerSpeed = 4;

public GamePanel() {

    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(KeyH);
    this.setFocusable(true);
}

public void startGameThread() {

    gameThread = new Thread(this);
    gameThread.start();
}


@Override
public void run() {
    double drawInterval = 1_000_000_000.0 / FPS; // nanos
    double nextDrawTime = System.nanoTime() + drawInterval;

    while (gameThread != null && !Thread.currentThread().isInterrupted()) {
        update1();
        repaint();

        try {
            double remainingTime = nextDrawTime - System.nanoTime();
            if (remainingTime < 0) remainingTime = 0;
            Thread.sleep((long) (remainingTime / 1_000_000.0));
            nextDrawTime += drawInterval;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public void update1() {
    if (KeyH.upPressed)    playerY -= playerSpeed; // removido o decremento duplicado
    if (KeyH.downPressed)  playerY += playerSpeed;
    if (KeyH.leftPressed)  playerX -= playerSpeed;
    if (KeyH.rightPressed) playerX += playerSpeed;

    // Clampar limites
    if (playerX < 0) playerX = 0;
    if (playerY < 0) playerY = 0;
    if (playerX > screenWidth - titleSize) playerX = screenWidth - titleSize;
    if (playerY > screenHeight - titleSize) playerY = screenHeight - titleSize;
}

public void paintComponent(Graphics g) {
    
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(Color.white);

    g2.fillRect(playerX, playerY, titleSize, titleSize);

    g2.dispose(); 


}
}
