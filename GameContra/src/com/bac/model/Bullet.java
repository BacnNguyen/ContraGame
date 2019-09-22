package com.bac.model;

import com.bac.gui.GameFrame;
import com.bac.until.ImageLoader;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private Image[] img;
    private int orient;

    public Bullet(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        img = new Image[3];
        img[0] = ImageLoader.getImage("bullet_1.png", getClass());
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(img[0], x, y, null);
    }


    public void move() {
        switch (orient) {
            case Player.RIGHT:
                x++;
                break;
            case Player.LEFT:
                x--;
                break;
            case Player.LEFT_DOWN:
                x--;
                y++;
                break;
            case Player.LEFT_UP:
                x--;
                y--;
                break;
            case Player.RIGHT_DOWN:
                x++;
                y++;
                break;
            case Player.RIGHT_UP:
                x++;
                y--;
                break;
            case Player.FIRE_UP:
                y--;
                break;
            case Player.FIRE_DOWN:
                y++;
                break;

        }
    }

    public boolean checkTouch() {
        if (x <= 0 || x >= GameFrame.WIDTH || y <= 0 || y >= GameFrame.HEIGHT) return true;
        else return false;
    }
}
