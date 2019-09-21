package com.bac.model;

import com.bac.gui.GameFrame;
import com.bac.until.ImageLoader;

import java.awt.*;

public class Map {
    private int x;
    private int y;
    private int bit;
    private Image[] images;


    public Map(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;
        images = new Image[9];

        images[0] = ImageLoader.getImage("sky.png", getClass());
        images[1] = ImageLoader.getImage("water.png", getClass());
        images[2] = ImageLoader.getImage("tree_1.png", getClass());
        images[3] = ImageLoader.getImage("tree_2.png", getClass());
        images[4] = ImageLoader.getImage("ground.png", getClass());
        images[5] = ImageLoader.getImage("under_1.png", getClass());
        images[6] = ImageLoader.getImage("under_2.png", getClass());
        images[7] = ImageLoader.getImage("bridge_1.png", getClass());
        images[8] = ImageLoader.getImage("bridge_2.png", getClass());
    }


    public void draw(Graphics2D g2d) {
        g2d.drawImage(images[bit], x, y, null);
    }

    public void move(int orient) {
        switch (orient) {
//            case Player.LEFT:
//            case Player.LEFT_DOWN:
//            case Player.LEFT_UP:
//                x++;
//                break;
            case Player.RIGHT:
            case Player.RIGHT_DOWN:
            case Player.RIGHT_UP:
                x--;
                break;
        }
    }

    public boolean checkTouchLeft(){
        if(x==0) return true;
        else return false;
    }

    public boolean checkTouchRight(){
        if(x== GameFrame.WIDTH) return true;
        else return false;
    }
}
