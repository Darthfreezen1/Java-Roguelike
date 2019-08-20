package roguelikeLibrary.playerStuff.enemyTypes;

import asciiPanel.AsciiPanel;
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
public class Skeleton extends Entity {
    Random r = new Random();
    //HUMAN
    public Skeleton(Terrain[][] map, int x, int y){
        super(map,x,y);
        double healthmin = 12, healthmax = 1;
        health = healthmin + (healthmax-healthmin)*r.nextDouble();
        dmg = r.nextInt(5)+1;
        threat = 1;
        xp = 5;
        armourclass = 15;
        display = 's';
        speed = 1;
        colour = AsciiPanel.brightWhite;
        inventory = new Inventory();
        name = "skeleton";
    }


}
