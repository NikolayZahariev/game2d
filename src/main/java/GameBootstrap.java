import main.GamePanel;

import javax.swing.*;

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