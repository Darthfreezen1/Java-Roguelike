package roguelikeLibrary.mapUtils.terrain;

import asciiPanel.AsciiPanel;

/**
 * Created by fawf on 08/04/16.
 */
public class Boulder extends Terrain {

    public Boulder(){
        speed = 0;
        passable = false;
        display = 'o';
        colour = AsciiPanel.brightBlack;
        name = "Boulder";
    }
}
