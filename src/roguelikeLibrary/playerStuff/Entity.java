package roguelikeLibrary.playerStuff;

import asciiPanel.AsciiPanel;
import roguelikeLibrary.mapUtils.items.Armours.Armour;
import roguelikeLibrary.mapUtils.items.Armours.ArmourList;
import roguelikeLibrary.mapUtils.items.Consumables.Potion;
import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.Weapons.Weapon;
import roguelikeLibrary.mapUtils.items.Weapons.WeaponList;
import roguelikeLibrary.mapUtils.terrain.Terrain;
import java.awt.*;
import java.util.Random;

/**
 * Created by lopez-gasconp on 5/9/2016.
 *
 * this abstract class allows for use in creating the enemies
 *
 */
public abstract class Entity {
    protected int x, y, threat, armourclass, dmg, xp, toHit;
    protected double speed, health;
    protected double count = 0;
    protected Inventory inventory;
    protected char display;
    protected Color colour;
    protected boolean real = true;
    protected Terrain[][] pos;
    protected int xAxis = 0;
    protected int yAxis = 0;
    protected boolean player = false;
    protected Weapon weapon;
    protected Armour armour;
    protected String name;
    protected boolean moved = false;

    public Entity(Terrain[][] map, int x, int y){
        pos = map.clone();
        this.x = x;
        this.y = y;
        toHit = 0;
    }

    public Entity(int x, int y){
        colour = AsciiPanel.brightWhite;
        display = '@';
        health = 100;
        count = 1;
        this.x = x;
        this.y = y;
        real = true;
        toHit = 0;
    }

    /**
     * this method is called from 'move'
     * determines the player's position and draws a path to it
     * detects objects in the way and calculates a way around it
     *
     * @param playerX
     * @param playerY
     */
    public void path(int playerX, int playerY) {
        boolean move;

        if (playerX < x) {
            xAxis = -1;
        } else if (playerX > x) {
            xAxis = 1;
        } else if (playerX == x) {
            xAxis = 0;
        }

        if (playerY < y) {
            yAxis = -1;
        } else if(playerY > y){
            yAxis = 1;
        } else if (playerY == y) {
            yAxis = 0;
        }

        if (pos[x + xAxis][y + yAxis].isPassable()) {
            move = true;
        } else {
            move = false;
        }

        int c = 0;
        while (!move) {
            if (xAxis == 0) {
                for (int i = -1; i <= 1; i++) {
                    if (!pos[x + i][y + yAxis].isPassable()) {
                        c++;
                    } else {
                        move = true;
                        xAxis = i;
                        break;
                    }
                }
            }
            if(pos[x + xAxis][y + yAxis].isPassable()){
                move = true;
                break;
            }
            for (int i = -1; i <= 1; i++) {
                if (!pos[x + xAxis][y + i].isPassable()) {
                    c++;
                } else {
                    move = true;
                    yAxis = i;
                    break;
                }
                if (c == 3) {
                    xAxis = 0;
                }
            }
            if(pos[x + xAxis][y + yAxis].isPassable()){
                move = true;
                break;
            }
            c = 0;
            for (int i = -1; i <= 1; i++) {
                if (!pos[x + i][y + yAxis].isPassable()) {
                    c++;
                } else {
                    move = true;
                    xAxis = i;
                    break;
                }
                if (c == 3) {
                    yAxis = 0;
                }
            }

            pos[x][y].setPassable(false);

            if (playerX < x) {
                xAxis = -1;
            } else if (playerX > x) {
                xAxis = 1;
            } else if(playerX == x){
                xAxis = 0;
            }


            if (playerY < y) {
                yAxis = -1;
            } else if(playerY > y){
                yAxis = 1;
            }  else if (playerY == y) {
                yAxis = 0;
            }

            c = 0;
            for (int i = -1; i <= 1; i++) {
                if (!pos[x-xAxis][y + i].isPassable()) {
                    c++;
                } else {
                    move = true;
                    yAxis = i;
                    xAxis = -xAxis;
                    break;
                }
                if (c == 3) {
                    xAxis = 0;
                }
            }
            for (int i = -1; i <= 1; i++) {
                if (!pos[x + i][y - yAxis].isPassable()) {
                    c++;
                } else {
                    move = true;
                    xAxis = i;
                    yAxis = -yAxis;
                    break;
                }
                if (c == 3) {
                    yAxis = 0;
                }
            }
        }
    }

    /**
     * this method calls 'path'
     * moves the player along the path to the player, putting the enemies speed into consideration
     * @param playerX
     * @param playerY
     * @return
     */
    public int movement(int playerX, int playerY) {
        count += speed * pos[x][y].getSpeed();
        do {
            //System.out.println(this.getName() + " " + count + " " + (speed * pos[x][y].getSpeed()) + " " + speed + " " + pos[x][y].getSpeed());
            path(playerX, playerY);
            if (count >= 1.0) {
                if (x + xAxis == playerX && y + yAxis == playerY){
                    return 1;
                } else {
                    x = x + xAxis;
                    y = y + yAxis;
                    count --;
                }
            } else {
                moved = true;
                return 0;
            }
        }while(true);
    }

    /**
     * this deals damage to the player based off the enemies stats (ArmourClass, toHitMod, etc)
     *
     * determines if the enemy is attacking the player, then:
     *      -rolls a random number from 1-20
     *      -if the roll is higher than the players ArmourClass, then hit player
     *          -else no hit
     * player does the same to the enemy it is hitting
     * @param e
     * @return
     */
    public Entity attack (Entity e){
        Random r = new Random();

        double test = r.nextDouble() * (isPlayer() ? (20 + weapon.getToHit()) : 20);
        while(count >= 1){
            if (test > (e.isPlayer() ? (e.getArmourclass() + e.armour.getAc()) : e.getArmourclass())) {
                e.setHealth(e.getHealth() - (isPlayer() ? (getDmg() + weapon.dmgRoll()) : getDmg()));
            }
            count--;
        }

        return e;
    }

    /**
     * getters and setters
     * @param health
     */
    public void setHealth(double health){
        this.health = health;
    }

    public double getSpeed(){return speed;}

    public double getCount(){return count;}

    public double getHealth(){return health;}

    public char getDisplay(){return display;}

    public Color getColour(){return colour;}

    public int getX(){return x;}

    public int getY(){return y;}

    public boolean isReal(){return real;}

    public boolean isPlayer(){return player;}

    public void setX(int x){this.x = x;}

    public void setY(int y){this.y = y;}

    public int getDmg(){return dmg;}

    public int getArmourclass() {
        return armourclass;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getXp(){return xp;}

    /**
     * creates lists of armour and weapons
     * then:
     *  takes the enemies threat level into effect
     *  rolls a random number to determine what to drop
     * @return
     */
    public Item drop(){
        Random r = new Random();
        WeaponList weaponList = new WeaponList();
        ArmourList armourList = new ArmourList();

        switch(threat){
            case 1:
                switch(r.nextInt(3)){
                    case 1: return armourList.getTierOne()[r.nextInt(armourList.getTierOne().length)];
                    case 2: return weaponList.getTierOne()[r.nextInt(weaponList.getTierOne().length)];
                    default: return new Potion();
                }
            case 2:
                switch(r.nextInt(3)){
                    case 1: return armourList.getTierTwo()[r.nextInt(armourList.getTierTwo().length)];
                    case 2: return weaponList.getTierTwo()[r.nextInt(weaponList.getTierTwo().length)];
                    default: return new Potion();
                }
            case 3:
                switch(r.nextInt(3)){
                    case 1: return armourList.getTierThree()[r.nextInt(armourList.getTierThree().length)];
                    case 2: return weaponList.getTierThree()[r.nextInt(weaponList.getTierThree().length)];
                    default: return new Potion();
                }
            case 4:
                switch(r.nextInt(3)){
                    case 1: return armourList.getTierFour()[r.nextInt(armourList.getTierFour().length)];
                    case 2: return weaponList.getTierFour()[r.nextInt(weaponList.getTierFour().length)];
                    default: return new Potion();
                }
            case 5:
                switch(r.nextInt(3)){
                    case 1: return armourList.getTierFive()[r.nextInt(armourList.getTierFive().length)];
                    case 2: return weaponList.getTierFive()[r.nextInt(weaponList.getTierFive().length)];
                    default: return new Potion();
                }
            default:return new Potion();
        }

    }
    //more getters
    public Armour getArmour(){
        return armour;
    }
    public Weapon getWeapon(){
        return weapon;
    }

    public String getName(){return name;}

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isMoved() {
        return moved;
    }
}
