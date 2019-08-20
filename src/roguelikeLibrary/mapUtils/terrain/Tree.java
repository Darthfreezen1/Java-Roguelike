package roguelikeLibrary.mapUtils.terrain;

import asciiPanel.AsciiPanel;

/**
 * Created by fawf on 08/04/16.
 */
public class Tree extends Terrain{

    public Tree(){
        speed = 0;
        passable = false;
        display = 'O';
        colour = AsciiPanel.brightGreen;
        name = "Tree";
    }
}
