package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.items.Armours.*;
import roguelikeLibrary.mapUtils.items.Weapons.*;
import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;

import java.util.Random;

/**
 * Created by fawf on 26/05/16.
 */
public class Dragon extends Entity {
    Random r = new Random();
    public Dragon(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 39 + (156 - 53)*r.nextDouble();
        dmg = r.nextInt(60) + 1;
        armourclass = 25;
        speed = 0.8;
        threat = 5;
        xp = 25;
        display = 'D';
        colour = AsciiPanel.dragonColour;
        inventory = new Inventory();
        name = "dragon";
    }

}
