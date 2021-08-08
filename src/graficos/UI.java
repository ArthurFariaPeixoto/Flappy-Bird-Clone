package graficos;

import main.Game;

import java.awt.*;


public class UI {


    public void render(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.BOLD,18));
        g.drawString("Score: "+(int)Game.score, 20, 20);
        g.drawString("High Score: "+(int)Game.highScore, Game.WIDTH*Game.SCALE-150, 20);

    }
}
