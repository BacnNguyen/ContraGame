package com.bac.manager;

import com.bac.model.Map;
import com.bac.until.ImageLoader;

import java.io.*;
import java.util.ArrayList;

public class MapManager {
    public static int w = 32;
    public static int h = 16;

    public static ArrayList<Map> getMap(String name) {
        ArrayList<Map> maps = new ArrayList<>();
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
                Map map = new Map(j * w, i * h, bit);
                maps.add(map);
            }
            System.out.println();
            i++;
            try {
                line = buff.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return maps;
    }
}
