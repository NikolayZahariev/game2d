package states;

import entities.enemies.EnemyMovement;
import entities.enemies.Slugger;
import main.GamePanel;
import main.State;
import tilemaps.Background;
import tilemaps.TileMap;
import entities.characters.*;

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
    private Lich lich;
    private Player player;
    private ArrayList<Slugger> enemies;
    private EnemyMovement enemyMovement;

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
        lich = new Lich(tileMap);
        if (CharState.character == "berserker") {
            player = new Player(tileMap, berserker.spriteSheet, berserker.character, berserker.movement);
            player.collision.characterMapPlacement.setPosition(100, 100);
        }
        if (CharState.character == "lich") {
            player = new Player(tileMap, lich.spriteSheet, lich.character, lich.movement);
            player.collision.characterMapPlacement.setPosition(100, 100);
        }
        enemies = new ArrayList<>();

        Slugger slugger;
        Point[] enemySpawnPoint = new Point[]{
                new Point(150, 100),
                new Point(200, 120)
        };
        for (Point spawnPoint : enemySpawnPoint) {
            slugger = new Slugger(tileMap);
            slugger.collision.characterMapPlacement.setPosition(spawnPoint.x, spawnPoint.y);
            enemies.add(slugger);
        }
    }

    @Override
    public void update() {
        player.update();
        player.checkDamageTaken(enemies);
        player.meleeAttack(enemies);
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.collision.characterMapPlacement.getx(), GamePanel.HEIGHT / 2 - player.collision.characterMapPlacement.gety());
        for (int i = 0; i < enemies.size(); i++) {
            Slugger slugger = enemies.get(i);
            enemyMovement = new EnemyMovement(player, slugger);
            enemyMovement.movement();
            slugger.update();
            if (slugger.enemy.isDead()) {
                enemies.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        background.draw(g);
        tileMap.draw(g);
        player.draw(g);
        for (Slugger enemy : enemies) {
            enemy.draw(g);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_A | k == KeyEvent.VK_LEFT) {
            player.moveSet.left = false;
        }
        if (k == KeyEvent.VK_D | k == KeyEvent.VK_RIGHT) {
            player.moveSet.right = false;
        }
        if (k == KeyEvent.VK_W | k == KeyEvent.VK_UP) {
            player.moveSet.jumping = false;
        }
        if (k == KeyEvent.VK_J | k == KeyEvent.VK_Z) {
            player.character.attacking = false;
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_A | k == KeyEvent.VK_LEFT) {
            player.moveSet.left = true;
        }
        if (k == KeyEvent.VK_D | k == KeyEvent.VK_RIGHT) {
            player.moveSet.right = true;
        }
        if (k == KeyEvent.VK_W | k == KeyEvent.VK_UP) {
            player.moveSet.jumping = true;
        }
        if (k == KeyEvent.VK_J | k == KeyEvent.VK_Z) {
            player.character.attacking = true;
        }
    }
}