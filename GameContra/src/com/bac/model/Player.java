package com.bac.model;

import com.bac.gui.GameFrame;
import com.bac.until.ImageLoader;

import java.awt.*;

public class Player {
    public static final int LEFT =0;
    public static final int RIGHT =1;
    public static final int LEFT_UP =2;
    public static final int RIGHT_UP =3;
    public static final int LEFT_DOWN =4;
    public static final int RIGHT_DOWN =5;
    public static final int JUMP =6;
    public static final int JUMP_LEFT =7;
    public static final int JUMP_RIGHT =7;

    private int x;
    private int y;
    private int orient;
    private int count;
    private int index;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        orient = RIGHT;
    }


    Image left [] ={
            ImageLoader.getImage("bill_run_left_1.png",getClass()),
            ImageLoader.getImage("bill_run_left_2.png",getClass()),
            ImageLoader.getImage("bill_run_left_3.png",getClass()),
            ImageLoader.getImage("bill_run_left_4.png",getClass()),
            ImageLoader.getImage("bill_run_left_5.png",getClass()),
            ImageLoader.getImage("bill_run_left_6.png",getClass())
    };

    Image right[] ={
            ImageLoader.getImage("bill_run_right_1.png",getClass()),
            ImageLoader.getImage("bill_run_right_2.png",getClass()),
            ImageLoader.getImage("bill_run_right_3.png",getClass()),
            ImageLoader.getImage("bill_run_right_4.png",getClass()),
            ImageLoader.getImage("bill_run_right_5.png",getClass()),
            ImageLoader.getImage("bill_run_right_6.png",getClass())
    };

    Image leftUp [] ={
            ImageLoader.getImage("bill_run_up_left_1.png",getClass()),
            ImageLoader.getImage("bill_run_up_left_2.png",getClass()),
            ImageLoader.getImage("bill_run_up_left_3.png",getClass())
    };

    Image rightUp [] ={
            ImageLoader.getImage("bill_run_up_right_1.png",getClass()),
            ImageLoader.getImage("bill_run_up_right_2.png",getClass()),
            ImageLoader.getImage("bill_run_up_right_3.png",getClass())
    };

    Image leftDown [] = {
            ImageLoader.getImage("bill_run_down_left_1.png",getClass()),
            ImageLoader.getImage("bill_run_down_left_2.png",getClass()),
            ImageLoader.getImage("bill_run_down_left_3.png",getClass())
    };

    Image rightDown [] ={
            ImageLoader.getImage("bill_run_down_right_1.png",getClass()),
            ImageLoader.getImage("bill_run_down_right_2.png",getClass()),
            ImageLoader.getImage("bill_run_down_right_3.png",getClass())
    };

    Image jumpRight [] = {
            ImageLoader.getImage("bill_jump_right_1.png",getClass()),
            ImageLoader.getImage("bill_jump_right_2.png",getClass()),
            ImageLoader.getImage("bill_jump_right_3.png",getClass()),
            ImageLoader.getImage("bill_jump_right_4.png",getClass())
    };

    Image jumpLeft [] = {
            ImageLoader.getImage("bill_jump_left_1.png",getClass()),
            ImageLoader.getImage("bill_jump_left_2.png",getClass()),
            ImageLoader.getImage("bill_jump_left_3.png",getClass()),
            ImageLoader.getImage("bill_jump_left_4.png",getClass())
    };




    Image images [][] = {left,right,leftUp,rightUp,leftDown,rightDown,jumpLeft,jumpRight};


    public void draw(Graphics2D g2d){
        g2d.drawImage(images[orient][index],x,y,null);
    }

    public void move(){
        switch (orient){
            case LEFT:
            case LEFT_UP:
            case LEFT_DOWN:
                if(x>0) x--;
                break;
            case RIGHT:
            case RIGHT_UP:
            case RIGHT_DOWN:
                if(x<GameFrame.WIDTH-20) x++;
                break;
            case JUMP_LEFT:
                y--;
        }
    }


    public void changeOrient(int newOrient){
        if(orient!=newOrient){
            orient = newOrient;
            index =0;
            count =0;
        }
    }

    public void changeImage(){
        count++;
        if(count>=15){
            index++;
            if(index>=images[orient].length){
                index =0;
            }
            count =0;
        }
    }

    public int getX() {
        return x;
    }
}
