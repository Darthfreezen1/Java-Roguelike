package roguelikeLibrary.playerStuff.enemyTypes;

import asciiPanel.AsciiPanel;
import roguelikeLibrary.mapUtils.items.Armours.*;
import roguelikeLibrary.mapUtils.items.Weapons.*;
import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.*;

import java.awt.*;
import java.util.Random;

/**
 * Created by lopez-gasconp on 5/18/2016.
 */
public class Kobold extends Entity {
    Random r = new Random();
    public Kobold(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 1 + (4-1)*r.nextDouble();
        speed = 1;
        armourclass = 15;
        dmg = r.nextInt(6-1)*3+1;
        threat = 1;
        xp = 5;
        display = 'k';
        colour = AsciiPanel.brightMagenta;
        inventory = new Inventory();
        name = "kobold";
    }
}
