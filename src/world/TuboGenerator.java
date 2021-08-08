package world;

import entities.Entitie;
import entities.Tubo;
import main.Game;

public class TuboGenerator{

    public int time = 0;
    public int alturacima, alturabaixo;
    public int targetTime =  90;

    public void tick(){
        time++;
        if(time == targetTime){
            alturacima = Entitie.rand.nextInt(73 - 30) + 30;
            Tubo tubocima = new Tubo(Game.WIDTH,0,20,alturacima,Game.spritesheet.getSprite(40, 0, 20, 40));

            alturabaixo = Entitie.rand.nextInt(76 - 30) + 30;
            Tubo tubobaixo = new Tubo(Game.WIDTH,Game.HEIGHT-alturabaixo,20,alturabaixo,Game.spritesheet.getSprite(20, 0, 20, 40));

            Game.entities.add(tubocima);
            Game.entities.add(tubobaixo);
            time=0;
        }
    }
}
