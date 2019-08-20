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
public class Goblin extends Entity {
    Random r = new Random();
    public Goblin(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 5 + (8-5)*r.nextDouble();
        dmg = r.nextInt(6-1)+1;
        armourclass = 15;
        speed = 1.1;
        threat = 1;
        xp = 5;
        display = 'g';
        colour = AsciiPanel.green;
        inventory = new Inventory();
        name = "goblin";
    }
}
