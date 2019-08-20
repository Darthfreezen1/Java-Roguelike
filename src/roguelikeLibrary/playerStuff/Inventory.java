package roguelikeLibrary.playerStuff;

import roguelikeLibrary.mapUtils.items.*;
import roguelikeLibrary.mapUtils.items.Consumables.Consumable;
import roguelikeLibrary.mapUtils.items.Munitions.Ammunition;
import roguelikeLibrary.mapUtils.items.Weapons.*;
import roguelikeLibrary.mapUtils.items.Armours.*;

import java.util.ArrayList;

/**
 * Created by klassenm on 4/22/2016.
 */
public class Inventory {
    private ArrayList itemNames = new ArrayList();
    private ArrayList itemAmts = new ArrayList();

    /**
     * Gets item names and amounts from Player class and adds them to the inventory
     */
    public void addItem(Item item){

        if(!itemNames.contains(item)){
            if(Weapon.class.isAssignableFrom(item.getClass())){
                itemNames.add(0, item);
                itemAmts.add(0, 1);
            } else if(Armour.class.isAssignableFrom(item.getClass())){
                itemNames.add(1, item);
                itemAmts.add(1, 1);
            } else if(Consumable.class.isAssignableFrom(item.getClass())){
                itemNames.add(2, item);
                itemAmts.add(2, 1);
            } else if(Ammunition.class.isAssignableFrom(item.getClass())){
                itemNames.add(3, item);
                itemAmts.add(3, 1);
            }
        }else{
            int i = Integer.parseInt(itemAmts.get(itemNames.indexOf(item)).toString());
            itemAmts.set(itemNames.indexOf(item), (i += 1));
        }


    }

    /**
     * Displays the inventory upon user input
     */
    public String displayInv(){
        /**
         * TODO have the player/entity return items from here to InventoryScreen
         */
        return "test";
    }

    public Object getItem(int i) {
        return itemNames.get(i);
    }

    /**
     * Handles the removal of items from the player's inventory
     */
    public void removeItem(){

    }
}
