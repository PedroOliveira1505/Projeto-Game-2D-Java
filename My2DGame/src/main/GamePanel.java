package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class GamePanel extends JPanel{

// opcoes da tela 
final int originalTitleSize = 32; // 32x32 titulo
final int scale = 2;

final int titleSize = originalTitleSize * scale; // 64x64 titulo
 final int maxScreenCol = 16;
 final int maxScreenRow = 12;
 final int screenWidth = titleSize * maxScreenCol; // 1024 pixels
final int screenHeight = titleSize * maxScreenRow; // 768 pixels


public GamePanel() {

    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);

}

}

