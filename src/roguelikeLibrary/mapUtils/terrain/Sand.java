package roguelikeLibrary.mapUtils.terrain;

import asciiPanel.AsciiPanel;

/**
 * Created by fawf on 08/04/16.
 */
public class Sand extends Terrain{

    public Sand(){
        speed = 0.75;
        passable = true;
        display = ',';
        colour = AsciiPanel.yellow;
        name = "Sand";
    }

}
