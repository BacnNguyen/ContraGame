package com.bac.manager;

import com.bac.gui.GameFrame;
import com.bac.model.Bullet;
import com.bac.model.Map;
import com.bac.model.Player;

import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    private Player player;
    private ArrayList<Map> maps;
    private ArrayList<Bullet> bullets;
    private int fireCount;

    public void gameInit() {
        player = new Player(200, 0);
        maps = MapManager.getMap("map1.txt");
        bullets = new ArrayList<>();
    }

    public void fire(int orient) {
        fireCount++;
        if (fireCount == 20) {
            switch (orient) {
                case Player.RIGHT:
                    Bullet bullet = new Bullet(player.getX() + 20, player.getY()+8, orient);
                    bullets.add(bullet);
                    break;
                case Player.LEFT:
                    Bullet bullet1 = new Bullet(player.getX() -3, player.getY() +8, orient);
                    bullets.add(bullet1);
                    break;
                case Player.RIGHT_UP:
                    Bullet bullet2 = new Bullet(player.getX() + 20, player.getY()-2, orient);
                    bullets.add(bullet2);
                    break;
                case Player.RIGHT_DOWN:
                    Bullet bullet3 = new Bullet(player.getX() + 20, player.getY() + 15, orient);
                    bullets.add(bullet3);
                    break;
                case Player.LEFT_UP:
                    Bullet bullet4 = new Bullet(player.getX() -3, player.getY() -2, orient);
                    bullets.add(bullet4);
                    break;
                case Player.LEFT_DOWN:
                    Bullet bullet5 = new Bullet(player.getX()-5, player.getY() +15, orient);
                    bullets.add(bullet5);
                    break;
                case Player.FIRE_UP:
                    Bullet bullet6 = new Bullet(player.getX()+7, player.getY() -3, orient);
                    bullets.add(bullet6);
                    break;
                case Player.FIRE_DOWN:
                    Bullet bullet7 = new Bullet(player.getX()+7, player.getY() +3, orient);
                    bullets.add(bullet7);
                    break;
            }

            fireCount = 0;
        }
    }

    public void moveBullet() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
            if (bullets.get(i).checkTouch()) bullets.remove(i);
        }
    }

    public void draw(Graphics2D g2d) {
        for (Map map : maps) {
            map.draw(g2d);
//            if (map.getBit() == 4) map.drawRect(g2d);
        }
        player.draw(g2d);
        for (Bullet b : bullets) {
            b.draw(g2d);
        }
    }

    public void playerMove(int newOrient) {
        player.changeOrient(newOrient);
        player.changeImage();
        player.move();
    }

    public void playerMove1(int newOrient) {
        player.changeOrient(newOrient);
        player.changeImage();
        if(newOrient == Player.FIRE_LIE_RIGHT||newOrient == Player.FIRE_LIE_LEFT){
            if(player.getOrient()== Player.RIGHT) newOrient = Player.FIRE_LIE_RIGHT;
            else newOrient = Player.FIRE_LIE_LEFT;
        }
    }


    public void mapMove(int newOrient) {
        for (int i = 0; i < maps.size(); i++) {
            maps.get(i).move(newOrient);
        }
        System.out.println();
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
            }
        }
    }

    public void playerJump() {
        playerMove1(Player.JUMP_RIGHT);
        player.jump();
    }


    public boolean AI() {
        moveBullet();
        player.fall(maps);
        if (player.checkDie()) return false;
        else return true;
    }
}
