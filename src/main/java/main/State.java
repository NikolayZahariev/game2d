package main;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public interface State {

    void init();

    void update();

    void draw(Graphics g);

    void keyPressed(int k);

    void keyReleased(int k);
}
