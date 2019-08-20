package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;

import java.util.Random;

/**
 * Created by Garett on 5/26/2016.
 */
public class Werewolf extends Entity{
    Random r = new Random();
    public Werewolf(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 16 + (23-16)*r.nextDouble();
        dmg = r.nextInt(8-4)+1;
        armourclass = 17;
        speed = 1;
        threat = 2;
        xp = 20;
        display = 'W';
        colour = AsciiPanel.brightWhite;
        inventory = new Inventory();
        name = "werewolf";
    }

}
