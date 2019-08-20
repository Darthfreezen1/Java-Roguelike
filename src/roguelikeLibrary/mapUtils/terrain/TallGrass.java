package roguelikeLibrary.mapUtils.terrain;

import roguelikeLibrary.AsciiPanel;

import java.awt.*;

/**
 * Created by friesenga on 5/20/2016.
 */
public class TallGrass extends Terrain{

    public TallGrass(){
        speed = .75;
        passable = true;
        display = '"';
        colour = AsciiPanel.grassColour;
        name = "Tall Grass";
    }

}
