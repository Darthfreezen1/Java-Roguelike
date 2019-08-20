package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;

import java.util.Random;

/**
 * Created by Garett on 5/26/2016.
 */
public class Skum extends Entity{
    Random r = new Random();
    public Skum(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 10 + (17-10)*r.nextDouble();
        dmg = r.nextInt(12-4)+1;
        armourclass = 15;
        speed = .9;
        threat = 2;
        xp = 10;
        display = 's';
        colour = AsciiPanel.magenta;
        inventory = new Inventory();
        name = "skum";
    }
}
