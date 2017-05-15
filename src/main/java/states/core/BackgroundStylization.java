package states.core;

import tilemaps.Background;

import java.awt.*;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class BackgroundStylization {
    public Background background = new Background(1);
    public Color titleColor;
    public Font titleFont;
    public Font font;

    public void loadBackgroundAndFonts() {
        background.getResource("/backgrounds/bg.jpg");
        titleColor = new Color(0, 173, 255);
        titleFont = new Font("Times New Roman", Font.PLAIN, 28);
        font = new Font("Arial Black", Font.PLAIN, 12);
    }
}
