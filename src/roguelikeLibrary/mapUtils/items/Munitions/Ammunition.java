package roguelikeLibrary.mapUtils.items.Munitions;

import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.ItemType;

/**
 * Created by klassenm on 5/9/2016.
 */
public abstract class Ammunition extends Item {

    public Ammunition(){
        type = ItemType.AMMUNITION;
        display = '↕';
    }

}
