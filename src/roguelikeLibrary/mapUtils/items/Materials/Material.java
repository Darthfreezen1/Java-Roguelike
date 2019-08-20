package roguelikeLibrary.mapUtils.items.Materials;

import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.ItemType;

/**
 * Created by klassenm on 5/9/2016.
 */
public abstract class Material extends Item {

    public Material(){
        type = ItemType.MATERIAL;
    }

}
