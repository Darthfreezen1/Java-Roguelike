package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.items.Armours.*;
import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.Weapons.*;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;

import java.util.Random;

/**
 * Created by friesenga on 5/25/2016.
 */
public class Ogre extends Entity {
    Random r = new Random();
    public Ogre(Terrain[][] map, int x, int y){
        super(map, x, y);
        double healthmin = 19, healthmax = 29;
        health = healthmin + (healthmax-healthmin)*r.nextDouble();
        speed = .7;
        armourclass = 16;
        dmg = r.nextInt(27-9)+1;
        threat = 2;
        xp = 10;
        display = 'O';
        colour = AsciiPanel.ogreColour;
        inventory = new Inventory();
        name = "ogre";
    }

}
