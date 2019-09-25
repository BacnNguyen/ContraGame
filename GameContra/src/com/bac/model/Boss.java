package com.bac.model;

import com.bac.until.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

public class Boss extends Player {
    public int step;
    public static int bullet;
    public Boss(int x, int y,int orient) {
        super(x, y);
        this.orient = orient;
    }
    Image left[]={
            ImageLoader.getImage("bill_run_left_1.png",getClass())
    };

    Image right[] ={
            ImageLoader.getImage("bill_run_right_1.png",getClass())
    };

    Image left_up [] ={
            ImageLoader.getImage("bill_lie_fire_left.png",getClass())
    };

    Image right_up [] ={
            ImageLoader.getImage("bill_lie_fire_right.png",getClass())
    };

    Image[][] images ={ left,right,left_up,right_up};

    public Rectangle getRect(){
        return new Rectangle(x,y,
                images[orient][0].getWidth(null),
                images[orient][0].getWidth(null));
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(images[orient][0],x,y,
                images[orient][0].getWidth(null),
                images[orient][0].getHeight(null),null);
    }

    public void move(int newOrient){
        if(newOrient==RIGHT||newOrient==RIGHT_UP||newOrient==RIGHT_DOWN){
            x--;
        }
    }

    public boolean checkTouchBullet(ArrayList<Bullet> bullets){
        for (int i=bullets.size()-1;i>=0;i--) {
            Rectangle rect = getRect().intersection(bullets.get(i).getRect());
            if(!rect.isEmpty()) {
                bullets.remove(i);
                return true;
            }
        }
        return false;
    }
}
