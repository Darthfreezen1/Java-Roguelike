package roguelikeLibrary;

import javax.swing.*;

public class Main {
    /**
     * the default size for the window that the library uses is 80x24char [728x411res]
     */

    private Object[][][] map = new Object[10][10][2];

    public static void main(String[] args) {
        Init init = new Init();
        ApplicationMain app = new ApplicationMain(init);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        //debugging System.out.println(app.getSize());
        app.setVisible(true);


    }
}
