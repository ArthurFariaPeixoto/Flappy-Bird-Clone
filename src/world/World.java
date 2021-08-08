package world;

import entities.*;
import main.Game;
import java.awt.*;


public class World {

    public static void Restart(){
        Game.score = 0;
        Game.player = new Player(Game.WIDTH/2 -100, Game.HEIGHT/2, 20, 20, Game.spritesheet.getSprite(0, 0, 20, 20));
        Game.entities.clear();
        Game.entities.add(Game.player);
        return;
    }
    public void render(Graphics g){

    }
}