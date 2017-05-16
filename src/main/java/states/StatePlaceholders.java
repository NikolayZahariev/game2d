package states;

import main.State;

import java.util.ArrayList;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class StatePlaceholders {
    public ArrayList<State> states = new ArrayList<>();
    private MenuState menuState;
    private CharState charState;
    private SettingsState settingsState;
    private LevelOne levelOne;
    private HelpState helpState;

    public StatePlaceholders(MenuState menuState, CharState charState, HelpState helpState, SettingsState settingsState, LevelOne levelOne) {
        this.menuState = menuState;
        this.charState = charState;
        this.settingsState = settingsState;
        this.helpState = helpState;
        this.levelOne = levelOne;
        populateStatesArray();
    }

    private void populateStatesArray() {
        states.add(menuState);
        states.add(charState);
        states.add(settingsState);
        states.add(helpState);
        states.add(levelOne);
    }
}
