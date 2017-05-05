package states;

import core.State;
import entities.Player;
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
    private Player player;

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
        player = new Player(tileMap);
        player.setPosition(100, 100);
    }

    @Override
    public void update() {
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
    }

    @Override
    public void draw(Graphics g) {
        background.draw(g);
        tileMap.draw(g);
        player.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if(k== KeyEvent.VK_LEFT){
            player.setLeft(false);
        }
        if(k== KeyEvent.VK_RIGHT){
            player.setRight(false);
        }
        if(k== KeyEvent.VK_UP){
            player.setUp(false);
        }
        if(k== KeyEvent.VK_DOWN){
            player.setDown(false);
        }
        if(k== KeyEvent.VK_W){
            player.setJumping(false);
        }
        if(k== KeyEvent.VK_F){
            player.setFiring();
        }
    }

    @Override
    public void keyReleased(int k) {
        if(k== KeyEvent.VK_LEFT){
            player.setLeft(true);
        }
        if(k== KeyEvent.VK_RIGHT){
            player.setRight(true);
        }
        if(k== KeyEvent.VK_UP){
            player.setUp(true);
        }
        if(k== KeyEvent.VK_DOWN){
            player.setDown(true);
        }
        if(k== KeyEvent.VK_W){
            player.setJumping(true);
        }
    }
}