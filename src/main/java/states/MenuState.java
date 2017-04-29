package states;

import tilemaps.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
    private Background background = new Background(1);
    private int currentChoice = 0;
    private Color titleColor;
    private Font titleFont;
    private Font font;
    private String[] options = {
            "Start",
            "Options",
            "Help",
            "Quit"
    };

    public MenuState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        background.getResource("/backgrounds/bg.jpg");
        titleColor = new Color(0, 173, 255);
        titleFont = new Font("Times New Roman", Font.PLAIN, 28);
        font = new Font("Arial Black", Font.PLAIN, 12);
    }

    public void init() {
    }

    public void update() {
        background.update();
    }

    public void draw(Graphics graphics) {
        background.draw(graphics);
        graphics.setColor(titleColor);
        graphics.setFont(titleFont);
        graphics.drawString("Scrub Lords", 100, 70);
        graphics.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                graphics.setColor(Color.GRAY);
            } else {
                graphics.setColor(Color.WHITE);
            }
            graphics.drawString(options[i], 30, 140 + i * 15);
        }
    }

    private void select() {
        if (currentChoice == 0) {
        }
        if (currentChoice == 1) {
        }
        if (currentChoice == 2) {
        }
        if (currentChoice == 3) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    public void keyReleased(int k) {
    }

}