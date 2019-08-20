package roguelikeLibrary;

import roguelikeLibrary.mapUtils.items.*;
import roguelikeLibrary.mapUtils.terrain.*;
import roguelikeLibrary.playerStuff.*;

import java.util.Iterator;

/**
 * Created by friesenga on 5/17/2016.
 *
 * basically, this save everything on the map
 * this is needed because whenever you go to a new screen, it will return a new class thus regenerating everything and resetting stuff
 *
 */
public class Init {

    Terrain[][] map = new Terrain[1000][1000];
    Entity[][] entityMap = new Entity[1000][1000];
    Item[][] itemMap = new Item[1000][1000];
    Player player = new Player(50, 50);
    protected int count = 0;
    boolean genOnce = false;
    boolean gotData = false;
    boolean pickup = false;

    public void setMap(Terrain[][] map){
        for(int i = 0; i < map.length; i++){
            for(int o = 0; o < map.length; o++){
                this.map[i][o] = map[i][o];
            }
        }
    }
    public void setEntityMap(Entity[][] entityMap){
        for (int i = 0; i < entityMap.length; i++){
            for (int u = 0; u < entityMap[0].length;u++){
                this.entityMap[i][u] = entityMap[i][u];
            }
        }
    }
    public void setItemMap(Item[][] itemMap){
        for (int i = 0; i < itemMap.length; i++){
            for (int u = 0; u < itemMap[0].length;u++){
                this.itemMap[i][u] = itemMap[i][u];
            }
        }
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public void setGenOnce(boolean genOnce){
        this.genOnce = genOnce;
    }

    public void write(){
        gotData = true;
    }

    public Terrain[][] getTerrain(){return map;}
    public Entity[][] getEntityMap(){return entityMap;}
    public Item[][] getItemMap(){return itemMap;}
    public Player getPlayer(){return player;}
    public boolean isGenOnce(){return genOnce;}
    public boolean isGotData(){return gotData;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
