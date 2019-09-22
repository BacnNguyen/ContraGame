package com.bac.gui;

import com.bac.manager.GameManager;
import com.bac.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private GameManager manager = new GameManager();
    private boolean[] flags = new boolean[256];


    public GamePanel() {
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
        flags[e.getKeyCode()] = false;
    }

    @Override
    public void run() {
        while (true) {

            // các lệnh ba phím
            if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_RIGHT] && flags[KeyEvent.VK_UP]) {
                manager.playerMove1(Player.RIGHT_UP);
                manager.fire(Player.RIGHT_UP);
            } else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_RIGHT] && flags[KeyEvent.VK_DOWN]) {
                manager.playerMove1(Player.RIGHT_DOWN);
                manager.fire(Player.RIGHT_DOWN);
            } else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_LEFT] && flags[KeyEvent.VK_UP]) {
                manager.playerMove1(Player.LEFT_UP);
                manager.fire(Player.LEFT_UP);
            } else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_LEFT] && flags[KeyEvent.VK_DOWN]) {
                manager.playerMove1(Player.LEFT_DOWN);
                manager.fire(Player.LEFT_DOWN);
            }

            //cac lenh đúp phím
            else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_LEFT]) {
                manager.fire(Player.LEFT);
                manager.playerMove(Player.FIRE_LEFT);
            }
            else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_RIGHT]) {
                manager.fire(Player.RIGHT);
                manager.playerMove(Player.FIRE_RIGHT);
            }
            else if (flags[KeyEvent.VK_LEFT] && flags[KeyEvent.VK_UP]) manager.move(Player.LEFT_UP);
            else if (flags[KeyEvent.VK_RIGHT] && flags[KeyEvent.VK_UP]) manager.move(Player.RIGHT_UP);
            else if (flags[KeyEvent.VK_LEFT] && flags[KeyEvent.VK_DOWN]) manager.move(Player.LEFT_DOWN);
            else if (flags[KeyEvent.VK_RIGHT] && flags[KeyEvent.VK_DOWN]) manager.move(Player.RIGHT_DOWN);
            else if (flags[KeyEvent.VK_SPACE] && flags[KeyEvent.VK_RIGHT]) {
                manager.move(Player.JUMP_RIGHT);
                manager.playerJump();
            } else if (flags[KeyEvent.VK_SPACE] && flags[KeyEvent.VK_LEFT]) {
                manager.move(Player.JUMP_LEFT);
                manager.playerJump();
            }
            else if(flags[KeyEvent.VK_S]&&flags[KeyEvent.VK_UP]){
                manager.playerMove1(Player.FIRE_UP);
                manager.fire(Player.FIRE_UP);
            }
            else if (flags[KeyEvent.VK_DOWN]&&flags[KeyEvent.VK_S]) {
                manager.playerMove(Player.FIRE_DOWN);
                manager.fire(Player.FIRE_DOWN);
            }

            //cac lenh đơn phím
            else if (flags[KeyEvent.VK_SPACE]) manager.playerJump();
            else if (flags[KeyEvent.VK_LEFT]) manager.move(Player.LEFT);
            else if (flags[KeyEvent.VK_RIGHT]) manager.move(Player.RIGHT);
            else if (flags[KeyEvent.VK_S]) {
                manager.playerMove1(Player.FIRE_RIGHT);
                manager.fire(Player.RIGHT);
            }
//            else if(flags[KeyEvent.VK_SPACE]) manager.move(Player.JUMP_RIGHT);
//            if(flags[KeyEvent.VK_SPACE]) manager.playerJump();
            if (!manager.AI()) {
                int ans = JOptionPane.showConfirmDialog(null,
                        "Do you want play again?",
                        "Game Over",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (ans == JOptionPane.YES_OPTION) {
                    manager.gameInit();
                    flags = new boolean[256];
                } else System.exit(0);
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
