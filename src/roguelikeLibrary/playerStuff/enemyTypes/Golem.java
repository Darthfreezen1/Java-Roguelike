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
public class Golem extends Entity {
    Random r = new Random();
    public Golem(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 30 + (140-30)*r.nextDouble();
        dmg = r.nextInt(27-1)+1;
        armourclass = 22;
        speed = .6;
        threat = 4;
        xp = 20;
        display = 'G';
        colour = AsciiPanel.golemColour;
        inventory = new Inventory();
        name = "golem";
    }


}
