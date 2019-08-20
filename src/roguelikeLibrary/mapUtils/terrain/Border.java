package roguelikeLibrary.mapUtils.terrain;

import asciiPanel.AsciiPanel;

/**
 * Created by fawf on 14/05/16.
 */
public class Border extends Terrain {

    public Border(){
        speed = 0;
        passable = false;
        display = '+';
        colour = AsciiPanel.black;
        name = "Border";
    }

}
