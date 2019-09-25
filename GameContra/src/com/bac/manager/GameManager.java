package com.bac.manager;

import com.bac.gui.GameFrame;
import com.bac.model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private ArrayList<Life> lifes;
    private Player player;
    private ArrayList<Map> maps;
    private ArrayList<Bullet> bulletsPlayer;
    private ArrayList<Bullet> bulletsBoss;
    private ArrayList<Boss> arrBoss ;
    MapManager mapManager ;
    private int fireCount;
    private int score;

    public void gameInit() {
        mapManager = new MapManager("map1.txt");
        player = new Player(200, 0);
        maps = mapManager.getMap();
        bulletsPlayer = new ArrayList<>();
        arrBoss = mapManager.getBoss();
        bulletsBoss = new ArrayList<>();
        lifes = new ArrayList<>();
        initLife();
        score =0;
    }

    private void initLife() {
        lifes.add(new Life(20,10));
        lifes.add(new Life(50,10));
        lifes.add(new Life(80,10));
    }


    public void bossFire() {
        Boss.bullet++;
        if(Boss.bullet>=100) {
            for (Boss b:arrBoss) {
                Bullet bullet = new Bullet(b.getX(), b.getY(), b.getOrient());
                bulletsBoss.add(bullet);
            }
            Boss.bullet=0;
        }
    }

//    public void bossFall() {
//        for (Boss b : arrBoss) {
//            b.fall(maps);
//        }
//    }


    public void fire(int orient) {
        fireCount++;
        if (fireCount >= 20) {
            switch (orient) {
                case Player.RIGHT:
                    Bullet bullet = new Bullet(player.getX() + 20, player.getY() + 8, orient);
                    bulletsPlayer.add(bullet);
                    break;
                case Player.LEFT:
                    Bullet bullet1 = new Bullet(player.getX() - 3, player.getY() + 8, orient);
                    bulletsPlayer.add(bullet1);
                    break;
                case Player.RIGHT_UP:
                    Bullet bullet2 = new Bullet(player.getX() + 20, player.getY() - 2, orient);
                    bulletsPlayer.add(bullet2);
                    break;
                case Player.RIGHT_DOWN:
                    Bullet bullet3 = new Bullet(player.getX() + 20, player.getY() + 15, orient);
                    bulletsPlayer.add(bullet3);
                    break;
                case Player.LEFT_UP:
                    Bullet bullet4 = new Bullet(player.getX() - 3, player.getY() - 2, orient);
                    bulletsPlayer.add(bullet4);
                    break;
                case Player.LEFT_DOWN:
                    Bullet bullet5 = new Bullet(player.getX() - 5, player.getY() + 15, orient);
                    bulletsPlayer.add(bullet5);
                    break;
                case Player.FIRE_UP:
                    Bullet bullet6 = new Bullet(player.getX() + 7, player.getY() - 3, orient);
                    bulletsPlayer.add(bullet6);
                    break;
                case Player.FIRE_DOWN:
                    Bullet bullet7 = new Bullet(player.getX() + 7, player.getY() + 3, orient);
                    bulletsPlayer.add(bullet7);
                    break;
            }

            fireCount = 0;
        }
    }

    public void moveBullet(int newOrient) {
        if(newOrient== Player.RIGHT) {
            for (int i = 0; i < bulletsPlayer.size(); i++) {
                bulletsPlayer.get(i).move(newOrient);
                if (bulletsPlayer.get(i).checkTouch()) bulletsPlayer.remove(i);
            }

            for (int i = 0; i < bulletsBoss.size(); i++) {
                bulletsBoss.get(i).move(newOrient);
                if (bulletsBoss.get(i).checkTouch()) bulletsBoss.remove(i);
            }
        }
    }

    public void moveBullet(){
        for (int i = 0; i < bulletsPlayer.size(); i++) {
            bulletsPlayer.get(i).move();
            if (bulletsPlayer.get(i).checkTouch()) bulletsPlayer.remove(i);
        }

        for (int i = 0; i < bulletsBoss.size(); i++) {
            bulletsBoss.get(i).move();
            if (bulletsBoss.get(i).checkTouch()) bulletsBoss.remove(i);
        }
    }

    public void draw(Graphics2D g2d) {

        for (Map map : maps) {
            map.draw(g2d);
        }
        player.draw(g2d);
        for (Bullet b : bulletsPlayer) {
            b.draw(g2d);
        }

        for (Bullet b:bulletsBoss) {
            b.draw(g2d);
        }
        for (int i = 0; i <arrBoss.size() ; i++) {
            arrBoss.get(i).draw(g2d);
        }
        for (Life l: lifes) {
            l.draw(g2d);
        }
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Arial",Font.BOLD,20));
        g2d.drawString("SCORE :"+score,120,30);
    }

    public void playerMove(int newOrient) {
        player.changeOrient(newOrient);
        player.changeImage();
        player.move();
    }

    public void playerMove1(int newOrient) {
        player.changeOrient(newOrient);
        player.changeImage();
        if (newOrient == Player.FIRE_LIE_RIGHT || newOrient == Player.FIRE_LIE_LEFT) {
            if (player.getOrient() == Player.RIGHT) newOrient = Player.FIRE_LIE_RIGHT;
            else newOrient = Player.FIRE_LIE_LEFT;
        }
    }


    public void mapMove(int newOrient) {
        for (int i = 0; i < maps.size(); i++) {
            maps.get(i).move(newOrient);
        }
    }


    public void move(int newOrient) {
        Map map = maps.get(Map.step);
        if (!map.checkTouchLeft()) Map.step++;
        Map map1 = maps.get(maps.size() - 1);

        if (map.checkTouchLeft() &&
                (newOrient == Player.LEFT || newOrient == Player.LEFT_UP || newOrient == Player.LEFT_DOWN)) {
            playerMove(newOrient);
        } else if (map1.checkTouchRight() &&
                (newOrient == Player.RIGHT_DOWN || newOrient == Player.RIGHT || newOrient == Player.RIGHT_UP)) {
            playerMove(newOrient);
        } else {
            if (player.getX() <= GameFrame.WIDTH / 3) playerMove(newOrient);
            else if (player.getX() >= 2 * GameFrame.WIDTH / 3) playerMove(newOrient);
            else {
                playerMove1(newOrient);
                mapMove(newOrient);
                for (Boss b:arrBoss) {
                    b.move(newOrient);
                }
                moveBullet(newOrient);
            }
        }
    }

    public void playerJump() {
        playerMove1(Player.JUMP_RIGHT);
        player.jump();
    }


    public boolean AI() {
        bossFire();
        moveBullet();
        player.fall(maps);
        if (player.checkTouchBullet(bulletsBoss)||player.checkDie()) {
            //if return false, game over
            lifes.remove(lifes.size()-1);
            player.setXY(player.getX(),0);
        }

        if(arrBoss.size()>0){
            for (Boss b:arrBoss) {
                if(b.checkTouchBullet(bulletsPlayer)) {
                    arrBoss.remove(b);
                    score++;
                    break;
                }
            }
        }
        if(lifes.size() ==0) return false;
        else return true;
    }
}
