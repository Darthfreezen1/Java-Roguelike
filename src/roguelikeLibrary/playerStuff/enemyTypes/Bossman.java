package roguelikeLibrary.playerStuff.enemyTypes;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.Weapons.Rectifier;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import roguelikeLibrary.playerStuff.Entity;
import roguelikeLibrary.playerStuff.Inventory;
import java.util.Random;

/**
 * Created by fawf on 26/05/16.
 */
public class Bossman extends Entity {
    Random r = new Random();
    public Bossman(Terrain[][] map, int x, int y){
        super(map, x, y);
        health = 100 + (200 - 100)*r.nextDouble();
        dmg = r.nextInt(100) + 1;
        armourclass = 30;
        speed = 0.8;
        threat = 6;
        xp = 50;
        display = '@';
        colour = AsciiPanel.dragonColour;
        inventory = new Inventory();
        name = "Devil";
    }

    @Override
    public Item drop(){
        return new Rectifier();
    }
}
