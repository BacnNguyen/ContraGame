package com.bac.gui;

import com.bac.manager.GameManager;
import com.bac.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel  extends JPanel implements Runnable, KeyListener {
    private GameManager manager = new GameManager();
    private boolean [] flags = new boolean[256];


    public GamePanel(){
        setBackground(Color.BLACK);
        manager.gameInit();
        setFocusable(true);
        addKeyListener(this);
        Thread th = new Thread(this);
        th.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        manager.draw(g2d);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        flags[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        flags[e.getKeyCode()] =false;
    }

    @Override
    public void run() {
        while (true){
            if(flags[KeyEvent.VK_LEFT]&&flags[KeyEvent.VK_UP]) manager.move(Player.LEFT_UP);
            else if(flags[KeyEvent.VK_RIGHT]&&flags[KeyEvent.VK_UP])  manager.move(Player.RIGHT_UP);
            else if(flags[KeyEvent.VK_LEFT]&&flags[KeyEvent.VK_DOWN])  manager.move(Player.LEFT_DOWN);
            else if(flags[KeyEvent.VK_RIGHT]&&flags[KeyEvent.VK_DOWN])  manager.move(Player.RIGHT_DOWN);
            else if(flags[KeyEvent.VK_LEFT]) manager.move(Player.LEFT);
            else if(flags[KeyEvent.VK_RIGHT]) manager.move(Player.RIGHT);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
