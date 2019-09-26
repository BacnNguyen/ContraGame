package com.bac.manager;

import com.bac.model.Boss;
import com.bac.model.Map;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MapManager {
    public static int w = 32;
    public static int h = 16;
    private ArrayList<Map> maps;
    private ArrayList<Boss> arrBoss;

    public MapManager(String name) {
        maps = new ArrayList<>();
        arrBoss = new ArrayList<Boss>();
        File file = new File("src/map/" + name);
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        String line = null;
        try {
            line = buff.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (line != null) {
            for (int j = 0; j < line.length(); j++) {
                int bit = Integer.parseInt(line.charAt(j) + "");
                if (bit == 9) {
                    Boss b = new Boss(j * w, i * h-15, new Random().nextInt(4));
                    arrBoss.add(b);
                }
                Map map = new Map(j * w, i * h, bit);
                maps.add(map);
            }
            i++;
            try {
                line = buff.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Map> getMap() {
        return maps;
    }

    public ArrayList<Boss> getBoss() {
        return arrBoss;
    }
}
