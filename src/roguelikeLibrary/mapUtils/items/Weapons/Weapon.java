package roguelikeLibrary.mapUtils.items.Weapons;

import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.ItemType;

import java.util.Random;

/**
 * Created by klassenm on 5/9/2016.
 */
public abstract class Weapon extends Item {
    Random r = new Random();
    protected int dmg, toHit;
    protected double weight;

    public Weapon(){
        type = ItemType.WEAPON;
        display = '/';
    }

    public int dmgRoll() {
        return r.nextInt(dmg);
    }

    public int getDmg(){return dmg;}

    public int getToHit(){return toHit;}

    public void upgradeWeapon(){toHit++;}
}
