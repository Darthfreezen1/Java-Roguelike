package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;

import java.util.Random;

/**
 * Created by Garett on 5/26/2016.
 */
public class Giant extends Entity{
    Random r = new Random();
    public Giant(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 20 + (140-20)*r.nextDouble();
        dmg = r.nextInt(25-5)+1;
        armourclass = 21;
        speed = .4;
        threat = 4;
        xp = 19;
        display = 'G';
        colour = AsciiPanel.giantColour;
        inventory = new Inventory();
        name = "giant";
    }

}
