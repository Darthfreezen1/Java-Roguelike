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
public class Troll extends Entity {
    Random r = new Random();
    public Troll(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 42 + (68-48)*r.nextDouble();
        dmg = r.nextInt(12-1)+1;
        armourclass = 16;
        speed = .7;
        threat = 3;
        xp = 15;
        display = 'T';
        colour = AsciiPanel.trollColour;
        inventory = new Inventory();
        name = "troll";
    }

}
