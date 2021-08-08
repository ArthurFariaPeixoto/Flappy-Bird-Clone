package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Entitie{

    public static Random rand;
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    public BufferedImage sprite;
    protected int maskx, masky, maskwidht, maskheight;

    //public static BufferedImage LIFE_EN = Game.spritesheet.getSprite(96,0, 16, 16);

    public Entitie(int x, int y, int width, int height, BufferedImage sprite){
        rand = new Random();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;

        this.maskx = 0;
        this.masky = 0;
        this.maskwidht = width;
        this.maskheight = height;
    }

    public int getX(){
        return (int) this.x;
    }
    public int getY(){
        return (int) this.y;
    }

    public void tick(){

    }
    public static boolean isColliding(Entitie e1, Entitie e2){
        Rectangle e1Mask = new Rectangle(e1.getX()+e1.maskx-1, e1.getY()+e1.masky-4, e1.maskwidht-1, e1.maskheight-4);
        Rectangle e2Mask = new Rectangle(e2.getX()+e2.maskx, e2.getY()+e2.masky, e2.maskwidht, e2.maskheight);
        return e1Mask.intersects(e2Mask);
    }
    public void render(Graphics g){
        g.drawImage(sprite, getX(), getY(), null);
        /*Visualização de colisao

         */
        g.setColor(Color.blue);
        g.fillRect(getX()+maskx, getY()+masky, maskwidht, maskheight);
         /**/

    }

}