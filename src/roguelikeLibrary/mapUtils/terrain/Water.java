package roguelikeLibrary.mapUtils.terrain;

import asciiPanel.AsciiPanel;

/**
 * Created by fawf on 08/04/16.
 */
public class Water extends Terrain{

    public Water(){
        speed = 0.5;
        passable = true;
        display = '~';
        colour = AsciiPanel.brightCyan;
        name = "Water";
    }

}
