package com.bac.gui;

import javax.swing.*;

public class GameFrame extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public GameFrame(){
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("GameContra");
        setResizable(false);
        setLocationRelativeTo(null);
        GamePanel panel = new GamePanel();
        add(panel);
    }
}
