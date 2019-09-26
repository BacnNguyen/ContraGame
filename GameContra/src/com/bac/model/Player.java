package com.bac.model;

import com.bac.gui.GameFrame;
import com.bac.until.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    public static final int LEFT =0;
    public static final int RIGHT =1;
    public static final int LEFT_UP =2;
    public static final int RIGHT_UP =3;
    public static final int LEFT_DOWN =4;
    public static final int RIGHT_DOWN =5;
    public static final int JUMP_LEFT =6;
    public static final int JUMP_RIGHT =7;
    public static final int FIRE_DOWN =8;
    public static final int FIRE_RIGHT = 9;
    public static final int FIRE_LEFT = 10;
    public static final int FIRE_STAND_LEFT = 11;
    public static final int FIRE_STAND_RIGHT = 12;
    public static final int FIRE_UP = 13;
    public static final int FIRE_LIE_LEFT = 14;
    public static final int FIRE_LIE_RIGHT = 15;

    protected int x;
    protected int y;
    protected int orient;
    protected int count;
    protected int index;
    protected int jumpStep;
    protected boolean isFall = false;
    protected int ground;

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


    Image fireRight [] = {
            ImageLoader.getImage("bill_run_fire_right_1.png",getClass()),
            ImageLoader.getImage("bill_run_fire_right_2.png",getClass()),
            ImageLoader.getImage("bill_run_fire_right_3.png",getClass())
    };

    Image fireLeft [] = {
            ImageLoader.getImage("bill_run_fire_left_1.png",getClass()),
            ImageLoader.getImage("bill_run_fire_left_2.png",getClass()),
            ImageLoader.getImage("bill_run_fire_left_3.png",getClass())
    };

    Image fireStandLeft [] = {
            ImageLoader.getImage("bill_run_fire_right_1.png",getClass())
    };

    Image fireStandRight [] = {
            ImageLoader.getImage("bill_run_fire_right_1.png",getClass())
    };

    Image fireUp [] = {
            ImageLoader.getImage("bill_fire_up_right.png",getClass())
    };

    Image fireLieRight [] = {
            ImageLoader.getImage("bill_lie_fire_right.png",getClass())
    };

    Image fireLieLeft [] = {
        ImageLoader.getImage("bill_lie_fire_left.png",getClass())
    };

    Image fireDown [] = {
            ImageLoader.getImage("bill_jump_right_1.png",getClass()),
            ImageLoader.getImage("bill_jump_right_2.png",getClass()),
            ImageLoader.getImage("bill_jump_right_3.png",getClass())
    };


    Image images [][] = {left,right,leftUp,rightUp,leftDown,rightDown,
            jumpLeft,jumpRight,fireDown,fireRight,fireLeft,fireStandLeft,
            fireStandRight,fireUp,fireLieLeft,fireLieRight};


    public void draw(Graphics2D g2d){
        g2d.drawImage(images[orient][index],x,y,null);
    }

    public void move(){
        switch (orient){
            case LEFT:
            case LEFT_UP:
            case LEFT_DOWN:
            case FIRE_LEFT:
                if(x>0) x--;
                break;
            case RIGHT:
            case RIGHT_UP:
            case RIGHT_DOWN:
            case FIRE_RIGHT:
                if(x<GameFrame.WIDTH-20) x++;
                break;
            case JUMP_RIGHT:
                x++;
                break;
            case JUMP_LEFT:
                x--;
                break;
            case FIRE_DOWN:
                jump();
                break;
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

    public Rectangle getRect() {
        int w= images[orient][index].getWidth(null);
        int h = images[orient][index].getHeight(null);
        Rectangle rect = new Rectangle(x+2,y+h-3,w-4,3);
        return rect;
    }

    public Rectangle getRectAllBody() {
        int w= images[orient][index].getWidth(null);
        int h = images[orient][index].getHeight(null);
        Rectangle rect = new Rectangle(x,y,w,h);
        return rect;
    }

    public int getX() {
        return x;
    }


    public boolean checkTouchMap(ArrayList<Map> arr){
        for (Map m:arr) {
            if(m.getBit()==4||m.getBit()==7||m.getBit()==8) {
                Rectangle rect = getRect().intersection(m.getRect());
                if(!rect.isEmpty()){
                    y = m.getY()-images[orient][index].getHeight(null);
                    return true;
                }
                else continue;
            }else continue;
        }
        return false;
    }

    public void jump (){
         if(!isFall) jumpStep = 50;
        ground = y;
    }


    public boolean checkDie(){
        if(y>=GameFrame.HEIGHT) return true;
        else return false;
    }


    public void fall(ArrayList<Map> arr){
        if(checkTouchMap(arr)==false&&jumpStep ==0){
            y++;
            isFall = true;
            if(y == ground+5) orient = RIGHT;
        }
        else if (jumpStep>0){
            y-=2;jumpStep--;
            isFall = true;
        }
        else {
            isFall = false;
        }
    }

    public boolean checkTouchBullet(ArrayList<Bullet> arr){
        for (Bullet b:arr) {
            Rectangle rect = getRectAllBody().intersection(b.getRect());
            if(!rect.isEmpty()) {
                arr.remove(b);
                return true;// if return true, player touched bullet
            }
        }
        return false;
    }

    public int getY() {
        return y;
    }

    public  int getOrient() {
        return orient;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
