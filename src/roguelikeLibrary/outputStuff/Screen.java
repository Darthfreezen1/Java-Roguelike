package roguelikeLibrary.outputStuff;

/**
 * Created by friesenga on 4/21/2016.
 *
 * this Interface allows use for multiple classes IE windows
 *
 * displayOutput() creates a new terminal to display stuff in
 * respondToUserInput() is a keylistener -> do stuff when button is pressed
 *
 */
import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public interface Screen {
    public void displayOutput(AsciiPanel terminal);
    public Screen respondToUserInput(KeyEvent key, AsciiPanel terminal);
}
