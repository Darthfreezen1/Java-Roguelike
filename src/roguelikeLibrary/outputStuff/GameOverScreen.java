package roguelikeLibrary.outputStuff;


import asciiPanel.AsciiPanel;
import roguelikeLibrary.Init;

import java.awt.event.KeyEvent;

/**
 * Created by PedricElSapo on 2016-05-26.
 *
 * called when your player HP reaches zero
 * displays user's progress
 *
 */
public class GameOverScreen implements Screen {
    Init init = new Init();

    public GameOverScreen(Init init){this.init = init;}

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("Game Over", 7,AsciiPanel.brightWhite);
        terminal.writeCenter("You survived for " + init.getCount() + " turns", 8,AsciiPanel.brightWhite);
        terminal.writeCenter("Your earned " + init.getPlayer().getXp() + " experience and killed " +
                init.getPlayer().getKills() + " enemies", 9,AsciiPanel.brightWhite);
        terminal.writeCenter("Press [enter] to try again, [esc] to exit",11,AsciiPanel.brightWhite);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key, AsciiPanel terminal) {
        switch (key.getKeyCode()){
            case KeyEvent.VK_ENTER: return new InstructionsScreen(new Init());
            case KeyEvent.VK_ESCAPE: System.exit(0);
        }
        return this;
    }
}
