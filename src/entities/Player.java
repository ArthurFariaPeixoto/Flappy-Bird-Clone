package entities;

import main.Game;
import main.Sound;
import world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entitie{


    public static boolean up=false;

    public Player(int x, int y, int width, int height, BufferedImage sprite){
        super(x, y, width, height, sprite);

    }
    public void tick(){
        if(!up){
            y+=1.5;
        }
        else{
            if(y > 0) {
               y -= 3;

            }
        }
        if(y >= Game.HEIGHT){
            Sound.hurt.play();
            World.Restart();
        }

        for(int i=0; i<Game.entities.size();i++){
            Entitie e = Game.entities.get(i);
            if(e != this){
                if(Entitie.isColliding(this, e)){
                    if(Game.highScore <=Game.score){
                        Game.highScore = Game.score;
                    }
                    Sound.hurt.play();
                    World.Restart();

                }
            }
        }
    }
    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(!up) {
            //g2.rotate(Math.toRadians(3), this.x, this.y);
            g2.drawImage(sprite, (int) this.x, (int) this.y, null);
            //g2.rotate(Math.toRadians(-3), this.x, this.y);
        }else{
            g2.rotate(Math.toRadians(-10), this.x, this.y);
            g2.drawImage(sprite, (int) this.x, (int) this.y, null);
            g2.rotate(Math.toRadians(10), this.x, this.y);
        }

    }
}