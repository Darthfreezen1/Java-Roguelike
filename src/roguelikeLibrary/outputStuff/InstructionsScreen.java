package roguelikeLibrary.outputStuff;

import roguelikeLibrary.AsciiPanel;
import roguelikeLibrary.Init;

import java.awt.event.KeyEvent;

/**
 * Created by friesenga on 5/20/2016.
 *
 * this screen is called after the mainmenu screen
 * displays info for the user
 *
 *
 */
public class InstructionsScreen implements Screen {
    Init init = new Init();
    public InstructionsScreen(Init init){this.init = init;}

    public void displayOutput(asciiPanel.AsciiPanel terminal) {
        terminal.writeCenter("INSTRUCTIONS", 1, roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.writeCenter("To Move: ", 3, roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.write("NUMPAD: ",(80/2)-12,4, roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.writeCenter("7, 8, 9", 4,roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.writeCenter("4, 5, 6",5,roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.writeCenter("1, 2, 3",6,roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.writeCenter("comma = pickup item", 7, roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.writeCenter("period = use potion", 8, roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.writeCenter("m = scavenge fallen weapons & armour - having 3 parts will upgrade your gear",
                9, AsciiPanel.brightWhite);
        terminal.writeCenter("Enemies appear as coloured letters",11,roguelikeLibrary.AsciiPanel.brightWhite);
        char k = 'k', z = 'T', d = 'D', a = 'A';
        terminal.write("Ex: ",(80/2)-3,12,roguelikeLibrary.AsciiPanel.brightWhite);
        terminal.write(k,(80/2)+1,12,roguelikeLibrary.AsciiPanel.brightMagenta);
        terminal.write(z,(80/2)+3,12,roguelikeLibrary.AsciiPanel.trollColour);
        terminal.write(d,(80/2)+5,12,roguelikeLibrary.AsciiPanel.dragonColour);
        terminal.writeCenter("Press [enter] to continue...",15,roguelikeLibrary.AsciiPanel.brightWhite);
    }

    public Screen respondToUserInput(KeyEvent key, asciiPanel.AsciiPanel terminal) {
        switch (key.getKeyCode()){
            case KeyEvent.VK_ENTER: return new PlayScreen(init);
        }
        return this;
    }


}
