package roguelikeLibrary.outputStuff;

/**
 * Created by friesenga on 4/22/2016.
 *
 * menu screen instance
 *
 */
import java.awt.*;
import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import roguelikeLibrary.Init;

public class MainMenuScreen implements Screen{
    Init init = new Init();
    public MainMenuScreen(Init init){
        this.init = init;
    }

    public void displayOutput(AsciiPanel terminal){
        terminal.writeCenter("Roguelike Game", 7,AsciiPanel.brightWhite);
        terminal.writeCenter("Press [enter] to play ",11,AsciiPanel.brightWhite);

    }

    public Screen respondToUserInput(KeyEvent key, AsciiPanel terminal) {
        switch (key.getKeyCode()){
            case KeyEvent.VK_ENTER: return new InstructionsScreen(init);
        }
        return this;
    }


}
