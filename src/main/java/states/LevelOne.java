package states;

import core.State;
import entities.Berserker;
import main.GamePanel;
import tilemaps.Background;
import tilemaps.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class LevelOne implements State {
    private TileMap tileMap;
    private Background background = new Background(0.1);
    private Berserker berserker;

    public LevelOne() {
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/tilesets/grasstileset.gif");
        tileMap.loadMap("/maps/level1-1.map");
        tileMap.setPosition(0, 0);
        background.getResource("/backgrounds/levelone.gif");
        berserker = new Berserker(tileMap);
        berserker.collision.characterMapPlacement.setPosition(100, 100);
    }

    @Override
    public void update() {
        berserker.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - berserker.collision.characterMapPlacement.getx(), GamePanel.HEIGHT / 2 - berserker.collision.characterMapPlacement.gety());
    }

    @Override
    public void draw(Graphics g) {
        background.draw(g);
        tileMap.draw(g);
        berserker.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if(k== KeyEvent.VK_LEFT){
            berserker.moveSet.setLeft(false);
        }
        if(k== KeyEvent.VK_RIGHT){
            berserker.moveSet.setRight(false);
        }
        if(k== KeyEvent.VK_UP){
            berserker.moveSet.setUp(false);
        }
        if(k== KeyEvent.VK_DOWN){
            berserker.moveSet.setDown(false);
        }
        if(k== KeyEvent.VK_W){
            berserker.moveSet.setJumping(false);
        }
    }

    @Override
    public void keyReleased(int k) {
        if(k== KeyEvent.VK_LEFT){
            berserker.moveSet.setLeft(true);
        }
        if(k== KeyEvent.VK_RIGHT){
            berserker.moveSet.setRight(true);
        }
        if(k== KeyEvent.VK_UP){
            berserker.moveSet.setUp(true);
        }
        if(k== KeyEvent.VK_DOWN){
            berserker.moveSet.setDown(true);
        }
        if(k== KeyEvent.VK_W){
            berserker.moveSet.setJumping(true);
        }
    }
}