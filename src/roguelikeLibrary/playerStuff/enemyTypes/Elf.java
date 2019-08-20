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
public class Elf extends Entity{
    Random r = new Random();
    public Elf(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 4 + (8-4)*r.nextDouble();
        dmg = r.nextInt(8)+1;
        speed = 1.2;
        threat = 1;
        xp = 5;
        armourclass = 15;
        display = 'e';
        colour = AsciiPanel.brightGreen;
        inventory = new Inventory();
        name = "elf";
    }


}
