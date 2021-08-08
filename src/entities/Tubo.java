package entities;

import main.Game;
import main.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tubo extends Entitie{

    public Tubo(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public void tick(){
        x--;
        if(x+width <= 0){
            Game.score+=0.5;
            if(Game.score>0){
                Sound.point.play();
            }
            Game.entities.remove(this);
            if(Game.entities.remove(this)){
                System.out.println("Removido");
            }
            return;
        }
    }

    public void render(Graphics g){
        if(sprite != null) {
            g.drawImage(sprite, this.getX(),this.getY(),width, height, null);
        }
        else{
            g.setColor(Color.green);
            g.fillRect((int) x, (int) y, width, height);
        }
    }
}
