package states;

import main.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class StateManager {
    public final int MENUSTATE = 0;
    public final int CHARSTATE = 1;
    public final int OPTIONSSTATE = 2;
    public final int HELPSTATE = 3;
    public final int LEVEL1STATE = 4;
    private ArrayList<State> states = new ArrayList<>(8);
    private int currentState;

    public StateManager() {
        currentState = MENUSTATE;
        states.add(new MenuState());
        states.add(null);
        states.add(null);
        states.add(null);
        states.add(null);
    }

    public void setState(int state) {
        switch (state) {
            case 0:
                break;
            case 1:
                states.set(state, new CharState());
                break;
            case 2:
                states.set(state, new SettingsState());
                break;
            case 3:
                states.set(state, new HelpState());
                break;
            case 4:
                states.set(state, new LevelOne());
                break;
        }
        currentState = state;
        states.get(state);
    }

    public void update() {
        states.get(currentState).update();
    }

    public void draw(Graphics graphics) {
        states.get(currentState).draw(graphics);
    }

    public void keyPressed(int k) {
        states.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        states.get(currentState).keyReleased(k);
    }
}
