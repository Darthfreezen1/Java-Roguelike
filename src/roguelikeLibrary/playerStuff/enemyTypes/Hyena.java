package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;

import java.util.Random;

/**
 * Created by Garett on 5/26/2016.
 */
public class Hyena extends Entity{
    Random r= new Random();
    public Hyena(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 3 + (5-3)*r.nextDouble();
        dmg = r.nextInt(3-1)+1;
        armourclass = 10;
        speed = 1;
        threat = 1;
        xp = 3;
        display = 'h';
        colour = AsciiPanel.trollColour;
        inventory = new Inventory();
        name = "hyena";
    }
}
