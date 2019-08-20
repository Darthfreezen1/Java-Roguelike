package roguelikeLibrary.playerStuff;

import asciiPanel.AsciiPanel;
import roguelikeLibrary.mapUtils.terrain.Terrain;

/**
 * Created by fawf on 14/05/16.
 *
 * created to prevent null pointers
 */
public class Holder extends Entity {

    public Holder(Terrain[][] map, int x, int y){
        super(map, x, y);
        speed = 0;
        health = 999;
        count = 0;
        display = ' ';
        colour = AsciiPanel.white;
        real = false;
    }

    @Override
    public int movement(int x, int y){return 0;}

}
