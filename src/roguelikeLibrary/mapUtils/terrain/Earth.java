package roguelikeLibrary.mapUtils.terrain;

import asciiPanel.AsciiPanel;

import java.awt.*;
import java.util.Random;

/**
 * Created by fawf on 08/04/16.
 */
public class Earth  extends Terrain{

    public Earth(){
        Color color = new Color(12, 68,0);
        speed = 1;
        passable = true;
        colour = AsciiPanel.magenta = color;
        display = '.';
        name = "Earth";
    }

}
