package states;

import core.State;

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
    private ArrayList<State> states = new ArrayList<>();
    private int currentState;

    public StateManager() {
        currentState = MENUSTATE;
        states.add(new MenuState());
        states.add(new CharState());
        states.add(new SettingsState());
        states.add(new HelpState());
        states.add(new LevelOne());
    }

    public void setState(int state){
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
