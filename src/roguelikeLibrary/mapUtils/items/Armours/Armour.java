package roguelikeLibrary.mapUtils.items.Armours;

import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.ItemType;

/**
 * Created by klassenm on 5/9/2016.
 */
public abstract class Armour extends Item {

    protected double ac;
    protected int slot;
    protected double weight;
    protected int swag;

    public Armour(){
        type = ItemType.ARMOUR;
        display = '[';
    }

    public double getAc() {
        return ac;
    }

    public void upgrade(){ac++;}
}
