package roguelikeLibrary.mapUtils.terrain;

import asciiPanel.AsciiPanel;

/**
 * Created by fawf on 26/05/16.
 */
public class Wall extends Terrain {

    public Wall(){
        passable = false;
        speed = 0;
        display = '#';
        colour = AsciiPanel.brightBlack;
        name = "Wall";
    }

}
