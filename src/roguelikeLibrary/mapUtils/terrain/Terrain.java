package roguelikeLibrary.mapUtils.terrain;

/**
 * Created by fawf on 25/03/16.
 *
 * this allows for creation of multiple terrain types
 */
import asciiPanel.AsciiPanel;
import java.awt.Color;
public abstract class Terrain {

    protected double speed = 1;
    protected boolean passable;
    protected char display;
    protected Color colour;
    protected String name;

    public double getSpeed(){return speed;}
    public boolean isPassable(){return passable;}
    public char getDisplay(){return display;}
    public Color getColour(){return colour;}
    public void setPassable(boolean passable){this.passable = passable;}
    public String getName(){return name;}
}
