package roguelikeLibrary.playerStuff;

import asciiPanel.AsciiPanel;
import roguelikeLibrary.mapUtils.items.Armours.*;
import roguelikeLibrary.mapUtils.items.Blank;
import roguelikeLibrary.mapUtils.items.Consumables.Consumable;
import roguelikeLibrary.mapUtils.items.Item;
import roguelikeLibrary.mapUtils.items.ItemType;
import roguelikeLibrary.mapUtils.items.Weapons.*;
import roguelikeLibrary.mapUtils.terrain.Terrain;

import java.awt.event.KeyEvent;

/**
 * Created by klassenm on 4/22/2016.
 */
public class Player extends Entity{
    private int exp, toNext, level, potions, kills, lastDmg, wepFrags, armFrags;
    private double maxHealth;
    private boolean attacked;
    private String targetName;

    public Player(int x, int y){
        super(x,y);
        player = true;
        colour = AsciiPanel.brightWhite;
        inventory = new Inventory();
        inventory.addItem(new ArmingSword());
        weapon = new ArmingSword();
        inventory.addItem(new Fur());
        armour = new Fur();
        xp = 0;
        exp = 0;
        dmg = 5;
        level = 1;
        toNext = 30;
        maxHealth = health;
        potions = 3;
        lastDmg = 0;
        wepFrags = 0;
        armFrags = 0;
    }

    public char getDisplay() {
        return super.getDisplay();
    }

    public double getHealth() {
        return super.getHealth();
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    @Override
    public int movement(int playerX, int playerY){return 0;}

    /**
     * gets a KeyEvent from PlayScreen()
     * uses that to move the player
     * @param key
     */
    public Entity[][] move(KeyEvent key, Terrain[][] map, Entity[][] entityMap){
        attacked = false;
        lastDmg = 0;

        try {
            switch (key.getKeyCode()) {
                case KeyEvent.VK_NUMPAD8:
                    if (map[x][y - 1].isPassable() && !entityMap[x][y - 1].isReal()) {
                        y--;
                    } else if (entityMap[x][y - 1].isReal()) {
                        entityMap = attack(entityMap, x, y - 1);
                    }
                    break;
                case KeyEvent.VK_NUMPAD2:
                    if (map[x][y + 1].isPassable() && !entityMap[x][y + 1].isReal()) {
                        y++;
                    } else if (entityMap[x][y + 1].isReal()) {
                        entityMap = attack(entityMap, x, y + 1);
                    }
                    break;
                case KeyEvent.VK_NUMPAD4:
                    if (map[x - 1][y].isPassable() && !entityMap[x - 1][y].isReal()) {
                        x--;
                    } else if (entityMap[x - 1][y].isReal()) {
                        entityMap = attack(entityMap, x - 1, y);
                    }
                    break;
                case KeyEvent.VK_NUMPAD6:
                    if (map[x + 1][y].isPassable() && !entityMap[x + 1][y].isReal()) {
                        x++;
                    } else if (entityMap[x + 1][y].isReal()) {
                        entityMap = attack(entityMap, x + 1, y);
                    }
                    break;
                case KeyEvent.VK_NUMPAD7:
                    if (map[x - 1][y - 1].isPassable() && !entityMap[x - 1][y - 1].isReal()) {
                        x--;
                        y--;
                    } else if (entityMap[x - 1][y - 1].isReal()) {
                        entityMap = attack(entityMap, x - 1, y - 1);
                    }
                    break;
                case KeyEvent.VK_NUMPAD1:
                    if (map[x - 1][y + 1].isPassable() && !entityMap[x - 1][y + 1].isReal()) {
                        x--;
                        y++;
                    } else if (entityMap[x - 1][y + 1].isReal()) {
                        entityMap = attack(entityMap, x - 1, y + 1);
                    }
                    break;
                case KeyEvent.VK_NUMPAD3:
                    if (map[x + 1][y + 1].isPassable() && !entityMap[x + 1][y + 1].isReal()) {
                        x++;
                        y++;
                    } else if (entityMap[x + 1][y + 1].isReal()) {
                        entityMap = attack(entityMap, x + 1, y + 1);
                    }
                    break;
                case KeyEvent.VK_NUMPAD9:
                    if (map[x + 1][y - 1].isPassable() && !entityMap[x + 1][y - 1].isReal()) {
                        x++;
                        y--;
                    } else if (entityMap[x + 1][y - 1].isReal()) {
                        entityMap = attack(entityMap, x + 1, y - 1);
                    }
                    break;
                case KeyEvent.VK_NUMPAD5:
                    break;
                case KeyEvent.VK_COMMA:
                    break;
                case KeyEvent.VK_PERIOD:
                    break;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        return entityMap;
    }

    /**
     * Handles the pickup item names, returns them to Inventory class
     * @return
     */
    public Item[][] pickupItem(Item[][] itemMap){
        Item temp;

        if(itemMap[x][y].isReal()){

            if(itemMap[x][y].getType() == ItemType.WEAPON){
                temp = weapon;
                weapon = (Weapon)itemMap[x][y];
                if(weapon != temp){wepFrags = 0;}
            }else if(itemMap[x][y].getType() == ItemType.ARMOUR){
                temp = armour;
                armour = (Armour)itemMap[x][y];
                if(armour != temp){armFrags = 0;}
            }else if(itemMap[x][y].getType() == ItemType.CONSUMABLE){
                temp = new Blank();
                potions++;
            }else{
                temp = itemMap[x][y];
            }

            itemMap[x][y] = temp;

        }

        return itemMap;

    }

    /**
     * essentially the same thing as the enemies 'attack' method
     * this one has to determine what enemy and where it is, though.
     * @param entityMap
     * @param i
     * @param n
     * @return
     */

    public Entity[][] attack(Entity[][] entityMap, int i, int n){
        attacked = true;
        int temp = (int)entityMap[i][n].getHealth();
        targetName = entityMap[i][n].getName();
        this.count = 1;
        entityMap[i][n] = attack(entityMap[i][n]);

        lastDmg = (int)(temp - entityMap[i][n].getHealth());

        if(entityMap[i][n].getHealth() <= 0){
            exp += entityMap[i][n].getXp();
            xp += entityMap[i][n].getXp();
        }

        return entityMap;

    }

    /**
     * if the weapon equipped is the same as the weapon dropped, press 'm' to scavenge said weapon
     * scavenging 3 will upgrade your weaponn
     * @param itemMap
     * @return
     */
    public Item[][] scavenge(Item[][] itemMap){

        if(itemMap[x][y].getName() == weapon.getName()){
            wepFrags++;
            itemMap[x][y] = new Blank();
        }else if(itemMap[x][y].getName() == armour.getName()){
            armFrags++;
            itemMap[x][y] = new Blank();
        }

        if(wepFrags >= 3){
            weapon.upgradeWeapon();
            wepFrags = 0;
        }

        if(armFrags >= 3){
            armour.upgrade();
            armFrags = 0;
        }

       return itemMap;
    }

    //getters
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    public int getExp(){return exp;}
    public int getToNext(){return toNext;}

    public void setExp(int exp){this.exp = exp;}
    public void setToNext(int toNext){this.toNext = toNext;}

    /**
     * takes the players XP into account and upgrades your health and base damage accordingly
     */
    public void levelUp(){
        level ++;
        toNext = (int)(toNext * 1.75);

        maxHealth += maxHealth * 0.25;
        health += maxHealth * 0.25;

        dmg += dmg + (int)(dmg * 0.05);
        toHit += toHit + (int)(toHit * 0.5);
    }

    /**
     * uses a potion
     */
    public void usePotion(){
        if(potions > 0) {
            potions--;
            health = (health <= (maxHealth - 20) ? (health + 20) : ( maxHealth));
        }
    }

    //more getters...
    public int getPotions(){return potions;}
    public int getLevel(){return level;}

    public int getKills(){return kills;}
    public void addKills(){kills++;}

    public int getLastDmg(){return lastDmg;}
    public boolean isAttacked(){return attacked;}

    public int getWepFrags(){return wepFrags;}
    public int getArmFrags(){return armFrags;}

    public String getTargetName(){return targetName;}
}
