package com.bac;

import com.bac.model.Player;

import java.awt.*;

public class GameManager {
   private Player player;

   public void gameInit(){
       player = new Player(200,200);
   }

   public void draw(Graphics2D g2d){
       player.draw(g2d);
   }

   public void playerMove(int newOrient){
       player.changeOrient(newOrient);
       player.changeImage();
       player.move();
   }
}
