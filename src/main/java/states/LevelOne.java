package states;

import entities.enemies.TestEnemy;
import main.State;
import entities.*;
import main.GamePanel;
import tilemaps.Background;
import tilemaps.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class LevelOne implements State {
    private TileMap tileMap;
    private Background background = new Background(0.1);
    private Berserker berserker;
    private ArrayList<TestEnemy> enemies;

    public LevelOne() {
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.tileLoading.loadTiles("/tilesets/grasstileset.gif");
        tileMap.mapLoading.loadMap("/maps/level1-1.map");
        tileMap.setPosition(0, 0);
        background.getResource("/backgrounds/levelone.gif");
        berserker = new Berserker(tileMap);
        berserker.collision.characterMapPlacement.setPosition(100, 100);

        enemies = new ArrayList<>();

        TestEnemy testEnemy;
        Point[] points = new Point[]{
                new Point(150, 150),
        };
        for (Point point : points) {
            testEnemy = new TestEnemy(tileMap);
            testEnemy.collision.characterMapPlacement.setPosition(point.x, point.y);
            enemies.add(testEnemy);
        }
    }

    @Override
    public void update() {
        berserker.update();

        berserker.checkAttack(enemies);

        tileMap.setPosition(GamePanel.WIDTH / 2 - berserker.collision.characterMapPlacement.getx(), GamePanel.HEIGHT / 2 - berserker.collision.characterMapPlacement.gety());
        for (int i = 0; i < enemies.size(); i++) {
            TestEnemy e = enemies.get(i);
            e.update();
            if (e.enemy.isDead()) {
                enemies.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        background.draw(g);
        tileMap.draw(g);
        berserker.draw(g);
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            berserker.moveSet.left = false;
        }
        if (k == KeyEvent.VK_RIGHT) {
            berserker.moveSet.right = false;
        }
        if (k == KeyEvent.VK_W | k == KeyEvent.VK_UP) {
            berserker.moveSet.jumping = false;
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            berserker.moveSet.left = true;
        }
        if (k == KeyEvent.VK_RIGHT) {
            berserker.moveSet.right = true;
        }
        if (k == KeyEvent.VK_W | k == KeyEvent.VK_UP) {
            berserker.moveSet.jumping = true;
        }
    }
}