package com.bac.manager;

import com.bac.gui.GameFrame;
import com.bac.model.Map;
import com.bac.model.Player;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class GameManager {
    private Player player;
    private ArrayList<Map> maps;

    public void gameInit() {
        player = new Player(200, 200);
        maps = MapManager.getMap("map1.txt");
    }

    public void draw(Graphics2D g2d) {
        for (Map map : maps) {
            map.draw(g2d);
        }
        player.draw(g2d);
    }

    public void playerMove(int newOrient) {
        player.changeOrient(newOrient);
        player.changeImage();
        player.move();
    }

    public void playerMove1(int newOrient) {
        player.changeOrient(newOrient);
        player.changeImage();
    }


    public void mapMove(int newOrient) {
        for (int i = 0; i < maps.size(); i++) {
            maps.get(i).move(newOrient);
        }
        System.out.println();
    }

    public void move(int newOrient) {
        Map map = maps.get(Map.step);
        if(!map.checkTouchLeft()) Map.step++;
        Map map1 = maps.get(maps.size() - 1);

        if (map.checkTouchLeft() &&
                (newOrient == Player.LEFT || newOrient == Player.LEFT_UP || newOrient == Player.LEFT_DOWN)) {
            playerMove(newOrient);
        } else if (map1.checkTouchRight() &&
                (newOrient == Player.RIGHT_DOWN || newOrient == Player.RIGHT || newOrient == Player.RIGHT_UP)) {
            playerMove(newOrient);
        } else {
            if(player.getX()<=GameFrame.WIDTH/3) playerMove(newOrient);
            else if(player.getX()>=2*GameFrame.WIDTH/3) playerMove(newOrient);
            else {
                playerMove1(newOrient);
                mapMove(newOrient);
            }
        }
    }
}
