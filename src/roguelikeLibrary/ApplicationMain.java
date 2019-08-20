package roguelikeLibrary;

import asciiPanel.AsciiPanel;
import roguelikeLibrary.outputStuff.MainMenuScreen;
import roguelikeLibrary.outputStuff.Screen;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by friesenga on 4/22/2016.
 *
 * this handles the creation/destruction/clearing of all windows and keylisteners
 *
 */
public class ApplicationMain extends JFrame implements KeyListener{
    private Screen screen;
    private AsciiPanel terminal;
    private Init init = new Init();

    public ApplicationMain(Init init){
        super();
        this.init = init;
        terminal = new AsciiPanel();
        add(terminal);
        pack();
        screen = new MainMenuScreen(init);
        addKeyListener(this);
        repaint();
    }

    public void repaint(){
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }
    public void keyPressed(KeyEvent e){
        screen = screen.respondToUserInput(e, terminal);
        repaint();
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}
