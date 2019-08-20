package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;

import java.util.Random;

/**
 * Created by Garett on 5/26/2016.
 */
public class Phasm extends Entity{
    Random r = new Random();
    public Phasm(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 20 + (60-20)*r.nextDouble();
        dmg = r.nextInt(12-5)+1;
        armourclass = 17;
        speed = 1;
        threat = 3;
        xp = 20;
        display = 'p';
        colour = AsciiPanel.brightBlue;
        inventory = new Inventory();
        name = "phasm";
    }
}
