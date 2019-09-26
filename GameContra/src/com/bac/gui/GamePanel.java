package com.bac.gui;

import com.bac.manager.GameManager;
import com.bac.model.Button;
import com.bac.model.Player;
import com.bac.until.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private GameManager manager = new GameManager();
    private boolean[] flags = new boolean[256];
    private boolean isPlaying = false;
    private ArrayList<Button> arrButton;
    Button startButton;
    Button exitButton;

    public GamePanel() {
        setBackground(Color.BLACK);
        arrButton = new ArrayList<>();
        startButton = new Button(100, 300, "start");
        exitButton = new Button(100, 350, "exit");
        startButton.setSelected(true);
        exitButton.setSelected(false);
//        manager.gameInit();
        setFocusable(true);
        addKeyListener(this);
        Thread th = new Thread(this);
        th.start();

    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        if (!isPlaying) {
            g2d.drawImage(ImageLoader.getImage("menu.jpg", getClass()), 0, 0, null);
            g2d.setStroke(new BasicStroke(5));
            startButton.draw(g2d);
            exitButton.draw(g2d);
            g2d.setColor(Color.white);
            if (startButton.getSelected()) {
                Rectangle rect = startButton.getRect();
                g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
            } else if (exitButton.getSelected()) {
                Rectangle rect = exitButton.getRect();
                g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
            }
        } else manager.draw(g2d);
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
            if (isPlaying) {
                // các lệnh ba phím
                if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_RIGHT] && flags[KeyEvent.VK_UP]) {
                    manager.playerMove(Player.RIGHT_UP);
                    manager.fire();
                } else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_RIGHT] && flags[KeyEvent.VK_DOWN]) {
                    manager.playerMove(Player.RIGHT_DOWN);
                    manager.fire();
                } else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_LEFT] && flags[KeyEvent.VK_UP]) {
                    manager.playerMove(Player.LEFT_UP);
                    manager.fire();
                } else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_LEFT] && flags[KeyEvent.VK_DOWN]) {
                    manager.playerMove(Player.LEFT_DOWN);
                    manager.fire();
                }

                //            //cac lenh đúp phím
                else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_LEFT]) {
                    manager.playerMove(Player.FIRE_LEFT);
                    manager.fire();
                } else if (flags[KeyEvent.VK_S] && flags[KeyEvent.VK_RIGHT]) {
                    manager.playerMove(Player.FIRE_RIGHT);
                    manager.fire();
                }
                else if (flags[KeyEvent.VK_SPACE] && flags[KeyEvent.VK_S]) {
                    manager.playerMove(Player.FIRE_DOWN);
                    manager.fire();
                }
                else if (flags[KeyEvent.VK_UP] && flags[KeyEvent.VK_S]) {
                    manager.playerMove(Player.FIRE_UP);
                    manager.fire();
                }
                else if (flags[KeyEvent.VK_LEFT] && flags[KeyEvent.VK_UP]) manager.move(Player.LEFT_UP);
                else if (flags[KeyEvent.VK_RIGHT] && flags[KeyEvent.VK_UP]) manager.move(Player.RIGHT_UP);
                else if (flags[KeyEvent.VK_LEFT] && flags[KeyEvent.VK_DOWN]) manager.move(Player.LEFT_DOWN);
                else if (flags[KeyEvent.VK_RIGHT] && flags[KeyEvent.VK_DOWN]) manager.move(Player.RIGHT_DOWN);
                else if (flags[KeyEvent.VK_LEFT]) manager.move(Player.LEFT);
                else if (flags[KeyEvent.VK_RIGHT]) manager.move(Player.RIGHT);
                else if (flags[KeyEvent.VK_DOWN]) manager.playerMove1(Player.FIRE_LIE_RIGHT);
                else {
                    if (flags[KeyEvent.VK_S]) {
                        manager.fire();
                    }
                    if (flags[KeyEvent.VK_SPACE]) {
                        manager.playerJump();
                    }
                }


//            else if (flags[KeyEvent.VK_SPACE] && flags[KeyEvent.VK_RIGHT]) {
//                manager.move(Player.JUMP_RIGHT);
//                manager.playerJump();
//            } else if (flags[KeyEvent.VK_SPACE] && flags[KeyEvent.VK_LEFT]) {
//                manager.move(Player.JUMP_LEFT);
//                manager.playerJump();
//            }
//

//
//            //cac lenh đơn phím
//            else if (flags[KeyEvent.VK_SPACE]) manager.playerJump();

//            else if(flags[KeyEvent.VK_UP]){
//                manager.playerMove1(Player.FIRE_UP);
//            }


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
            }
            else {
                if (flags[KeyEvent.VK_DOWN] || flags[KeyEvent.VK_UP]) {
                    boolean tmp = startButton.getSelected();
                    startButton.setSelected(exitButton.getSelected());
                    exitButton.setSelected(tmp);
                }
                if (flags[KeyEvent.VK_ENTER] && startButton.getSelected()) {
                    manager.gameInit();
                    isPlaying = true;
                } else if (flags[KeyEvent.VK_ENTER] && exitButton.getSelected()) {
                    System.exit(0);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            repaint();
        }
    }

}
