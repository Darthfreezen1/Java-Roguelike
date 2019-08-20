package roguelikeLibrary.mapUtils.items.Consumables;

import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.ItemType;

/**
 * Created by klassenm on 5/9/2016.
 */
public abstract class Consumable extends Item {

    public Consumable(){
        type = ItemType.CONSUMABLE;
    }

}
