package roguelikeLibrary.outputStuff;

import asciiPanel.AsciiPanel;
import roguelikeLibrary.mapUtils.*;
import roguelikeLibrary.mapUtils.terrain.*;
import java.awt.event.KeyEvent;

/**
 * Created by fawf on 02/05/16.
 */
public class MapGenTestScreen implements Screen {

    public void displayOutput(AsciiPanel terminal){
        Terrain[][] map = new Terrain[10][10];
        MapGen mapGen = new MapGen();

        mapGen.genMap(map);

        for(int i = 0; i < map.length; i++){
            for(int n = 0; n < map[0].length; n++){
                terminal.write(map[i][n].getDisplay());
            }
        }

    }

    public Screen respondToUserInput(KeyEvent key, AsciiPanel terminal){
        return this;
    }

}
