package roguelikeLibrary.mapUtils;

/**
 * Created by fawf on 08/04/16.
 *
 *
 */

import roguelikeLibrary.mapUtils.terrain.*;
import java.util.Random;
import roguelikeLibrary.playerStuff.*;
import roguelikeLibrary.playerStuff.enemyTypes.*;

public class MapGen {

    public Terrain[][] genMap(Terrain[][] map){

        map = genTerrain(map);
        map = genTexture(map);
        map = genBorder(map);
        map = roughEdge(map);
        map = genWater(map);
        map = genRivers(map);
        map = genFortress(map);

        return map;
    }

    private Terrain terrainSwitcher(){
        Random r = new Random();

        switch(r.nextInt(4)){
            case 1: Sand sand = new Sand();return sand;
            case 2: TallGrass tallGrass = new TallGrass(); return tallGrass;
            case 3: Stone stone = new Stone(); return stone;
            default: Earth earth = new Earth(); return earth;
        }

    }

    private Terrain textureSwitcher(int x){

        switch(x){
            case 1: Boulder boulder = new Boulder();return boulder;
            default: Tree tree = new Tree();return tree;
        }

    }

    public Entity enemySwitcher(int threat, int x, int y, Terrain[][] map){
        Random r = new Random();

        switch(threat){
            case 1:
                switch(r.nextInt(4)){
                    case 1: Elf elf = new Elf(map, x, y); return elf;
                    case 2: Goblin goblin = new Goblin(map, x, y); return goblin;
                    case 3: Kobold kobold = new Kobold(map, x, y); return kobold;
                    default: Skeleton skeleton = new Skeleton(map, x, y); return skeleton;
                }
            case 2:
                switch(r.nextInt(3)){
                    case 1: Skum skum = new Skum(map, x, y); return skum;
                    case 2: Werewolf werewolf = new Werewolf(map, x, y); return werewolf;
                    default: Ogre ogre = new Ogre(map, x, y); return ogre;
                }
            case 3:
                switch(r.nextInt(3)){
                    case 1: Phasm phasm = new Phasm(map, x, y); return phasm;
                    case 2: Demon demon = new Demon(map, x, y); return demon;
                    default: Troll troll = new Troll(map, x, y); return troll;
                }
            case 4:
                switch(r.nextInt(2)){
                    case 1: Giant giant = new Giant(map, x, y); return  giant;
                    default: Golem golem = new Golem(map, x, y); return golem;
                }
            default:
                switch(r.nextInt(1)){
                    default: Dragon dragon = new Dragon(map, x, y); return dragon;
                }
        }

    }

    private Terrain[][] genTerrain(Terrain[][] map){
        Random r = new Random();
        Nothin nothin = new Nothin();
        int prevy, prevx;

        Terrain test[] = new Terrain[map.length / 20];


        for(int i = 0; i < map.length; i++){
            for(int n = 0; n < map[0].length; n++){

                //sets test with placeholder objects
                for(int a = 0; a < test.length; a++){
                    test[a] = nothin;
                }

                //sets test with previous squares (x axis)
                for(int o = 0; o < test.length; o++){

                    try{
                        test[o] = map[i - o][n];
                    }catch(ArrayIndexOutOfBoundsException e){
                        test[o] = nothin;
                    }

                }

                prevx = count(test);

                //sets test with previous squares (y axis)
                for(int o = 0; o < test.length; o++){

                    try{
                        test[o] = map[i][n - o];
                    }catch(ArrayIndexOutOfBoundsException e){
                        test[o] = nothin;
                    }

                }

                prevy = count(test);

                if(i == 0 && n == 0){
                    map[i][n] = terrainSwitcher();
                }else if(prevx > 50 || prevy > 50){
                    map[i][n] = terrainSwitcher();
                }else if(prevx > 20 && prevx < 50 && prevy > 20 && prevy < 50){
                    if(r.nextInt(100) < 10){
                        map[i][n] = terrainSwitcher();
                    }else if(i > 0){
                        map[i][n] = map[i - 1][n];
                    }else{
                        map[i][n] = map[i][n - 1];
                    }
                }else if(prevx < 20){
                    if(i > 0){
                        map[i][n] = map[i - 1][n];
                    }else{
                        map[i][n] = map[i][n - 1];
                    }
                }else if(prevy < 20){
                    if(n > 0){
                        map[i][n] = map[i][n - 1];
                    }else{
                        map[i][n] = map[i - 1][n];
                    }
                }else{
                    map[i][n] = terrainSwitcher();
                }

            }
        }

        return map;
    }

    /**
     * generates randomly places objects such as trees and rocks
     */
    private Terrain[][] genTexture(Terrain[][] map){
        Random r = new Random();
        int num = (map.length * map[0].length) / 20;
        int coord1, coord2;

        for(int i = 0; i < num; i++){

            coord1 = r.nextInt(map.length);
            coord2 = r.nextInt(map[0].length);

            if(map[coord1][coord2].getDisplay() != '~' && map[coord1][coord2].getDisplay() != 'o' &&
                    map[coord1][coord2].getDisplay() != 'O'){
                map[coord1][coord2] = textureSwitcher(r.nextInt(2));
            }else{
                i -= 1;
            }

        }

        return map;
    }

    /**
     * sets the edges of the map as an impassable border
     */
    private Terrain[][] genBorder(Terrain[][] map){
        Border border = new Border();

        for(int i = 0; i < map.length; i++){
            map[i][0] = border;
            map[i][map[0].length - 1] = border;
            map[i][map[0].length - 2] = border;
        }

        for(int i = 0; i < map[0].length; i++){
            map[0][i] = border;
            map[map.length - 1][i] = border;
            map[map.length - 2][i] = border;
        }

        return map;
    }

    /**
     * counts the number of preceding tiles that are the same
     */
    public int count(Terrain[] test){
        int count = 0;
        Nothin nothin = new Nothin();

        for(int n = 2; n < test.length; n++){

            if(test[n] == test[1] && test[n] != nothin){
                count++;
            }else{
                break;
            }

        }

        return count;

    }

    /**
     * roughens smooth edges on terrain borders
     */
    public Terrain[][] roughEdge(Terrain[][] map){
        Random r = new Random();

        for(int i = 0; i < map.length; i++){
            for(int n = 0; n < map.length; n++){

                if(i > 0 && map[i][n] != map[i - 1][n] && map[i - 1][n].isPassable()){
                    if(r.nextInt(100) < 50){
                        map[i][n] = map[i - 1][n];
                    }
                }

                if(n > 0 && map[i][n] != map[i][n - 1] && map[i][n - 1].isPassable()){
                    if(r.nextInt(100) < 50){
                        map[i][n] = map[i][n - 1];
                    }
                }

            }
        }

        return map;
    }

    /**
     * generates an ocean of random width on one of the four sides of the map
     */
    public Terrain[][] genWater(Terrain[][] map){
        Random r = new Random();
        Water water = new Water();
        int minWater = r.nextInt(5) + 2;

        switch(r.nextInt(4)){
            case 1:
                //generates random amount of water on one edge of the map
                for(int i = 0; i < map.length; i++){
                    for(int n = 0; n < r.nextInt(10) + minWater; n++){
                        map[i][n] = water;
                    }
                }
                break;
            case 2:
                for(int i = 0; i < map.length; i++){
                    for(int n = 1; n < r.nextInt(10) + minWater; n++){
                        map[i][map[0].length - n] = water;
                    }
                }
                break;
            case 3:
                for(int i = 0; i < map[0].length; i++){
                    for(int n = 0; n < r.nextInt(10) + minWater; n++){
                        map[n][i] = water;
                    }
                }
                break;
            default:
                for(int i = 0; i < map.length; i++){
                    for(int n = 1; n < r.nextInt(10) + minWater; n++){
                        map[map.length - n][i] = water;
                    }
                }
                break;
        }

        return map;
    }

    /**
     * generates rivers at random points on both axes
     */
    public Terrain[][] genRivers(Terrain[][] map){
        Random r = new Random();
        Water water = new Water();
        Sand sand = new Sand();
        int x;
        int variance = 3;

        for(int o = 0; o < r.nextInt(3); o++) {

            x = r.nextInt(map.length);

            for (int i = 0; i < map[0].length; i++) {
                for (int n = -r.nextInt(variance); n < r.nextInt(variance) + 5; n++) {
                    map[(x + n > 0 && x + n < 1000 ? x + n : 1)][i] = water;
                }

                if(r.nextInt(100) > 50){
                    x += variance;
                }else{
                    x -= variance;
                }
            }
        }

        for(int o = 0; o < r.nextInt(3); o++) {

            x = r.nextInt(map[0].length);

            for (int i = 0; i < map.length; i++) {
                for (int n = -r.nextInt(variance); n < r.nextInt(variance) + 5; n++) {
                    map[i][(x + n > 0 && x + n < 1000 ? x + n : 1)] = water;
                }

                if(r.nextInt(100) > 50){
                    x += variance;
                }else{
                    x -= variance;
                }
            }
        }


        return map;
    }

    /**
     * generates a fortress near the bottom right-hand corner of the map
     * @param map
     * @return
     */
    public Terrain[][] genFortress(Terrain[][] map){
        Wall wall = new Wall();
        Stone stone = new Stone();
        int x = 950;
        int y = 950;

        for(int i = 0; i < 20; i++){
            for(int n = 0; n < 20; n++){
                map[x + i][y + n] = stone;
            }
        }

        for(int i = 0; i < 20; i++){
            map[x + i][y] = wall;
            map[x][y + i] = wall;
            map[x + 20][y + i] = wall;
            map[x + i][y + 20] = wall;
        }

        map[970][970] = wall;
        map[960][950] = stone;

        return map;
    }

    /**
     * spawns enemies in random locations on the map
     */
    public Entity[][] genEnemies(Entity[][] entityMap, int playerX, int playerY, Terrain[][] map){
        Random r = new Random();
        int threat, jej, x, y;
        int total = 1000;

        do {
            do {
                x = r.nextInt(map.length);

                if (x == 0) {
                    x++;
                }
                if (x == 999) {
                    x--;
                }
                y = r.nextInt(map[0].length);
                if (y == 0) {
                    y++;
                }
                if (y == 999) {
                    y--;
                }
            }while(!map[x][y].isPassable());

            jej = x + y;

            if (jej - (playerX + playerY) < ((entityMap.length + entityMap[0].length) / 10)) {
                threat = 1;
            } else if (jej - (playerX + playerY) < ((entityMap.length + entityMap[0].length) / 5)) {
                threat = 2;
            } else if (jej - (playerX + playerY) < ((entityMap.length + entityMap[0].length) / 2.5)) {
                threat = 3;
            } else if (jej - (playerX + playerY) < ((entityMap.length + entityMap[0].length) / 1.75)) {
                threat = 4;
            } else if (jej - (playerX + playerY) < ((entityMap.length + entityMap[0].length) / 1.1)){
                threat = 5;
            }else{
                threat = 5;
            }

            entityMap[x][y] = enemySwitcher(threat, x, y, map);
            total --;

        }while(total > 0);

        return entityMap;
    }

    /**
     * places the boss in the fortress
     * @param entityMap
     * @param map
     * @return
     */
    public Entity[][] genBossman(Entity[][] entityMap, Terrain[][] map){

        entityMap[970][970] = new Bossman(map, 970, 970);

        return entityMap;
    }

}
