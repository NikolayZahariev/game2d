package core;

import java.awt.*;

/**
 * Created by Legion on 4/30/2017.
 */
public interface State {

    void init();

    void update();

    void draw(Graphics g);

    void keyPressed(int k);

    void keyReleased(int k);
}
