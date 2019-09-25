package com.bac.model;

import com.bac.until.ImageLoader;

import java.awt.*;

public class Life {
    private int x,y;
    private Image img;

    public Life(int x, int y) {
        this.x = x;
        this.y = y;
        img = ImageLoader.getImage("star.png",getClass());
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(img,x,y,img.getWidth(null),img.getHeight(null), null);
    }
}
