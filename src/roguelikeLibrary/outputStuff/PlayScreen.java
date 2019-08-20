package roguelikeLibrary.outputStuff;

import asciiPanel.AsciiPanel;
import roguelikeLibrary.Init;
import roguelikeLibrary.mapUtils.MapGen;
import roguelikeLibrary.mapUtils.items.*;
import roguelikeLibrary.mapUtils.items.Armours.Armour;
import roguelikeLibrary.mapUtils.items.Weapons.Weapon;
import roguelikeLibrary.mapUtils.terrain.*;
import roguelikeLibrary.playerStuff.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by friesenga on 4/22/2016.
 *
 * 'playscreen' should be the screen where the map is drawn
 *  also where player input is
 *
 */

public class PlayScreen implements Screen{
    boolean genOnce = false;
    boolean isClear = false;
    Terrain [][] map = new Terrain[1000][1000];
    Entity[][] entityMap = new Entity[1000][1000];
    Item[][] itemMap = new Item[1000][1000];
    char[][] mape = new char[58][24];
    Color[][] mapeColour = new Color[58][24];
    int[][][] mapeCoord = new int[58][24][2];
    Player player = new Player(50, 50);
    Init init = new Init();
    String action = "";
    String actionOut, tempWep, tempArm, targetName;
    int margin, tempWepFrags, tempArmFrags, tempToHit;
    double tempHp, tempLvl, tempDmg, tempAc;

    public PlayScreen(Init init){
        this.init = init;
    }

    /**
     * this method is taken from the Interface 'Screen'
     *
     * this does all the display stuff for this screen:
     *  -outputting the player
     *  -outputting the enemies
     *  -etc.
     *  also displays info on the side of the screen
     * @param terminal
     */
    public void displayOutput(AsciiPanel terminal) {

        actionOut = "";
        if(init.isGotData()){
            loadInit();
        }else if(!genOnce){
            initMaps();
            genMap();
            entityMap[player.getX()][player.getY()] = player;
            genOnce = true;
        }


        //gets map information in a 58 by 24 square around the player to display
        for(int i = 0; i < 58; i++){
            for(int n = 0; n < 24; n++){
                try {
                    if (map[player.getX() + (-29 + i)][player.getY() + (-12 + n)] != null) {
                        mape[i][n] = map[player.getX() + (-29 + i)][player.getY() + (-12 + n)].getDisplay();
                        mapeColour[i][n] = map[player.getX() + (-29 + i)][player.getY() + (-12 + n)].getColour();
                        mapeCoord[i][n][0] = player.getX() + (-29 + i);
                        mapeCoord[i][n][1] = player.getY() + (-12 + n);
                    } else {
                        mape[i][n] = '+';
                        mapeColour[i][n] = AsciiPanel.black;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    mape[i][n] = '+';
                    mapeColour[i][n] = AsciiPanel.black;
                }
            }
        }

        //writes to terminal, with entities as top priority, followed by items and then terrain
        for (int i = 0; i < mape.length; i++){
            for (int u = 0; u< mape[0].length; u++){

                terminal.write(mape[i][u], i, u, mapeColour[i][u]);

                try {
                    if (itemMap[mapeCoord[i][u][0]][mapeCoord[i][u][1]].isReal()) {
                        terminal.write(itemMap[mapeCoord[i][u][0]][mapeCoord[i][u][1]].getDisplay(), i, u,
                                itemMap[mapeCoord[i][u][0]][mapeCoord[i][u][1]].getColour());
                    }

                    if (entityMap[mapeCoord[i][u][0]][mapeCoord[i][u][1]].isReal()) {
                        terminal.write(entityMap[mapeCoord[i][u][0]][mapeCoord[i][u][1]].getDisplay(), i, u,
                                entityMap[mapeCoord[i][u][0]][mapeCoord[i][u][1]].getColour());
                    }
                }catch(NullPointerException e){
                    e.printStackTrace();
                }

            }
        }
        terminal.write("[L] to view cntrls!",60,1,AsciiPanel.brightWhite);
        char tree = 'O', boulder = 'o', water = '~', grass = '.', sand = ',', tgrass = '"', playerChar = '@';
        terminal.write(tree,60,2,AsciiPanel.brightGreen);terminal.write(" = tree", 61,2,AsciiPanel.brightWhite);
        terminal.write(boulder,60,3,AsciiPanel.brightBlack);terminal.write(" = boulder", 61,3,AsciiPanel.brightWhite);
        terminal.write(water,60,4,AsciiPanel.brightCyan);terminal.write(" = water", 61,4,AsciiPanel.brightWhite);
        terminal.write(grass,60,5,AsciiPanel.green);terminal.write(" = grass",61,5,AsciiPanel.brightWhite);
        terminal.write(sand,60,6,AsciiPanel.yellow);terminal.write(" = sand", 61,6,AsciiPanel.brightWhite);
        terminal.write(tgrass,60,7,AsciiPanel.green);terminal.write(" = tall grass", 61,7,AsciiPanel.brightWhite);
        terminal.write(grass,60,8,AsciiPanel.brightBlack);terminal.write(" = stone", 61,8,AsciiPanel.brightWhite);
        terminal.write(playerChar, 60, 9, AsciiPanel.brightWhite); terminal.write(" = player", 61, 9, AsciiPanel.brightWhite);
        terminal.write("Player Health = " + (int)player.getHealth(), 60, 11, AsciiPanel.brightWhite);
        terminal.write("Player Level: " + player.getLevel(), 60, 12, AsciiPanel.brightWhite);
        terminal.write("XP: " + player.getExp() + "/" + player.getToNext(), 60, 13, AsciiPanel.brightWhite);
        terminal.write("Armour Class: " + (int)player.getArmour().getAc(), 60, 14,AsciiPanel.brightWhite);
        terminal.write("Dmg/ToHit: "+player.getWeapon().getDmg() + "/" +
                player.getWeapon().getToHit(), 60, 16, AsciiPanel.brightWhite);
        terminal.write("WParts: " + player.getWepFrags() + " AParts: " + player.getArmFrags(), 60, 17, AsciiPanel.brightWhite);
        terminal.write("Potions: " + player.getPotions(), 60, 19, AsciiPanel.brightWhite);
        terminal.write(player.getArmour().getName(), 60, 20, AsciiPanel.brightWhite);
        terminal.write(player.getWeapon().getName(), 60, 21, AsciiPanel.brightWhite);
        terminal.write("Ground:" , 60, 23, AsciiPanel.brightWhite);
        if(itemMap[player.getX()][player.getY()].isReal()){
            if(itemMap[player.getX()][player.getY()].getType() == ItemType.WEAPON) {
                Weapon temp = (Weapon) itemMap[player.getX()][player.getY()];
                terminal.write(temp.getName() + "(" + temp.getDmg() + "/" + temp.getToHit() + ")", 60, 23,
                        AsciiPanel.brightWhite);
            }else if(itemMap[player.getX()][player.getY()].getType() == ItemType.ARMOUR){
                Armour temp = (Armour)itemMap[player.getX()][player.getY()];
                terminal.write(temp.getName() + "(" + (int)temp.getAc() + ")", 60, 23, AsciiPanel.brightWhite);
            }else{
                terminal.write(itemMap[player.getX()][player.getY()].getName(), 60, 23, AsciiPanel.brightWhite);
            }
        }else{
            terminal.write(map[player.getX()][player.getY()].getName(), 68, 23, AsciiPanel.brightWhite);
        }

        margin = (58 - action.length()) / 2;
        for(int i = 0; i < margin; i++){
            actionOut += " ";
        }
        actionOut += action;
        for(int i = 0; i < margin; i++){
            actionOut += " ";
        }

        if(action != ""){
            terminal.write(actionOut, 0, 23, AsciiPanel.brightWhite);
            action = "";
        }

    }

    /**
     * respondToUserInput:
     *  is taken from the Interface 'Screen'
     *  does what the name implies:
     *      this method is called everytime a button is pressed
     *      calls tick after every press AKA turn
     *   bound keys:
     *      numpad 8,7,9,4,6,1,2,3 - movement
     *      numpad 5 - passing your 'turn' AKA not moving
     *      comma - picking up items
     *      period - using potions
     *      L - viewing instructions
     *      M - using armour/weapon shards
     *
     * @param key
     * @param terminal
     * @return
     */
    public Screen respondToUserInput(KeyEvent key, AsciiPanel terminal) {
        tempHp = player.getHealth();
        tempLvl = player.getLevel();
        tempDmg = player.getLastDmg();
        tempArmFrags = player.getArmFrags();
        tempWepFrags = player.getWepFrags();
        tempAc = player.getArmour().getAc();
        tempToHit = player.getWeapon().getToHit();
        tempWep = player.getWeapon().getName();
        tempArm = player.getArmour().getName();

        Holder holder = new Holder(map, 0, 0);
        entityMap[player.getX()][player.getY()] = holder;

        switch (key.getKeyCode()){
            case KeyEvent.VK_L: writeInit(); return new InstructionsScreen(init);
            //moves player hor/vert:
            case KeyEvent.VK_NUMPAD8: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);tick();break;
            case KeyEvent.VK_NUMPAD2: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);tick();break;
            case KeyEvent.VK_NUMPAD4: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);tick();break;
            case KeyEvent.VK_NUMPAD6: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);tick();break;
            //moves diagonally
            case KeyEvent.VK_NUMPAD7: entityMap[player.getX()][player.getY()] = holder;
                entityMap =  player.move(key, map, entityMap);tick();break;
            case KeyEvent.VK_NUMPAD1: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);tick();break;
            case KeyEvent.VK_NUMPAD3: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);tick();break;
            case KeyEvent.VK_NUMPAD9: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);tick();break;
            //waits a turn if 5 is pressed
            case KeyEvent.VK_NUMPAD5: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);tick();break;
            case KeyEvent.VK_COMMA: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);
                action += (itemMap[player.getX()][player.getY()].isReal() ?
                        ">You picked up " + itemMap[player.getX()][player.getY()].getName() + "<" : "");
                itemMap = player.pickupItem(itemMap);tick();break;
            case KeyEvent.VK_PERIOD: entityMap[player.getX()][player.getY()] = holder;
                entityMap = player.move(key, map, entityMap);player.usePotion();tick();break;
            case KeyEvent.VK_M: entityMap[player.getX()][player.getY()] = holder; itemMap = player.scavenge(itemMap);
                tick();break;
        }

        if (player.getHealth() < 1){
            writeInit();
            return new GameOverScreen(init);
        }else {
            return this;
        }
    }

    public void genMap(){
        MapGen m = new MapGen();
        map = m.genMap(map);
        entityMap = m.genEnemies(entityMap, player.getX(), player.getY(), map);
        entityMap = m.genBossman(entityMap, map);
    }

    /**
     * tick:
     *  this method sets the x/y coords of everything moving per turn
     *  calls the attack method/movement method for enemies
     *  updates and writes the player player location to entityMap
     *  writes out action messages
     */
    public void tick(){
        init.setCount(init.getCount()+1);
        Holder holder = new Holder(map, 0, 0);
        int x, y, z;

        for(int i = 0; i < mape.length; i++){
            for(int n = 0; n < mape[0].length; n++){

                //entityMap coords the MapeCoord of the entityMap in the mapeCoord
                if(entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].isReal() &&
                        !entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].isPlayer() && !entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].isMoved()){
                    x = entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].getX();
                    y = entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].getY();
                    z = entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].movement(player.getX(), player.getY());

                    entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].setMoved(true);

                    if (z != 0) {
                        entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].attack(player);
                    }

                    if(!entityMap[entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].getX()]
                            [entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].getY()].isReal()){

                        entityMap[entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].getX()]
                                [entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].getY()] =
                                entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]];
                        entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]] = holder;
                    }else{
                        entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].setX(x);
                        entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].setY(y);
                    }

                }

                //removes enemies if they are killed and drops their stuff
                if(entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].getHealth() <= 0) {
                    itemMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]] = entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].drop();
                    entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]] = holder;
                    player.addKills();
                }

            }
        }

        //Resets the "moved" state of all entities
        for(int i = 0; i < mape.length; i++) {
            for (int n = 0; n < mape[0].length; n++) {
                if (entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].isReal() &&
                        !entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].isPlayer()) {
                    entityMap[mapeCoord[i][n][0]][mapeCoord[i][n][1]].setMoved(false);
                }
            }
        }

        if(player.getExp() >= player.getToNext()){
            player.setExp(player.getExp() - player.getToNext());
            player.levelUp();
        }

        if(player.getLastDmg() != tempDmg && player.getLastDmg() != 0){
            action += ">You dealt " + player.getLastDmg() + " damage to the " + player.getTargetName() + "<";
        }else if(player.isAttacked()){
            action += ">You missed your foe.<";
        }

        if(tempArmFrags != player.getArmFrags() && tempArm == player.getArmour().getName()){
            action += ">Scavenged an armour part<";
        }

        if(tempWepFrags != player.getWepFrags() && tempWep == player.getWeapon().getName()){
            action += ">Scavenged a weapon part<";
        }

        if(tempToHit != player.getWeapon().getToHit() && tempWep == player.getWeapon().getName()){
            action += ">Weapon Upgraded<";
        }

        if(tempAc != player.getArmour().getAc() && tempArm == player.getArmour().getName()){
            action += ">Armour Upgraded<";
        }

        if(tempLvl != player.getLevel()){
            action += ">Leveled Up<";
        }else {
            if (player.getHealth() < tempHp) {
                action += ">Lost " + (int) (tempHp - player.getHealth()) + " health<";
            }
            if (player.getHealth() > tempHp) {
                action += ">Healed for " + (int) (player.getHealth() - tempHp) + " health<";
            }
        }


        entityMap[player.getX()][player.getY()] = player;
    }

    /**
     *fills entityMap and itemMap with placeholder entities and items to prevent null pointer errors
     */
    public void initMaps(){
        Holder holder = new Holder(map, 0, 0);
        Blank blank = new Blank();
        for(int i = 0; i < map.length; i++){
            for(int n = 0; n < map[0].length; n++){
                entityMap[i][n] = holder;
                itemMap[i][n] = blank;
            }
        }
    }

    public void loadInit(){
        player = init.getPlayer();
        map = init.getTerrain();
        entityMap = init.getEntityMap();
        itemMap = init.getItemMap();
        genOnce = init.isGenOnce();
    }

    public void writeInit(){
        init.setEntityMap(entityMap);
        init.setGenOnce(genOnce);
        init.setItemMap(itemMap);
        init.setPlayer(player);
        init.setMap(map);
        init.write();
    }

}
