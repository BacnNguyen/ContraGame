package com.bac.model;

import com.bac.until.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private int x;
    private int y;
    private Image img;
    private boolean selected ;

    public Button(int x,int y,String type){
        this.x = x;
        this.y = y;
        if(type.equals("start"))  img = ImageLoader.getImage("button_start.png",getClass());
        else if(type.equals("exit"))  img = ImageLoader.getImage("button_exit.png",getClass());

    }

    public boolean getSelected(){
        return selected;
    }

    public void setSelected(boolean val){
         selected = val;
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(img,x,y,
                img.getWidth(null),
                img.getHeight(null),null);
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
    }
}
