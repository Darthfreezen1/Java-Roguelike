package roguelikeLibrary.mapUtils.terrain;

import asciiPanel.AsciiPanel;

/**
 * Created by friesenga on 5/20/2016.
 */
public class Stone extends Terrain {

    public Stone(){
        speed = .75;
        passable = true;
        display = '.';
        colour = AsciiPanel.brightBlack;
        name = "Stone";
    }
}
