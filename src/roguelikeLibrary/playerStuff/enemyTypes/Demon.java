package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;

import java.util.Random;

/**
 * Created by Garett on 5/26/2016.
 */
public class Demon extends Entity{
    Random r = new Random();
    public Demon(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 30 + (60-30)*r.nextDouble();
        dmg = r.nextInt(12-7)+1;
        armourclass = 18;
        speed = .7;
        threat = 3;
        xp = 20;
        display = 'D';
        colour = AsciiPanel.red;
        inventory = new Inventory();
        name = "demon";
    }
}
