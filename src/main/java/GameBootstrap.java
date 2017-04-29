import main.GamePanel;

import javax.swing.*;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class GameBootstrap {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}