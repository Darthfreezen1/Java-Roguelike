package roguelikeLibrary.mapUtils.items;

import java.awt.*;

/**
 * Created by fawf on 25/03/16.
 *
 *
 * this allows for creation of multiple item types
 *
 */
public abstract class Item {

    protected boolean stat;
    protected String name;
    protected double value, weight;
    protected char display;
    protected Color colour;
    protected ItemType type;
    protected boolean real = true;

    public double getValue(){return value;}
    public double getWeight(){return weight;}
    public ItemType getType(){return type;}
    public boolean isReal(){return real;}
    public Color getColour(){return colour;}
    public char getDisplay(){return display;}
    public String getName(){return name;}

}
