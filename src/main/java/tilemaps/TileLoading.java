package tilemaps;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class TileLoading {
    private Tile tile;
    private BufferedImage bufferedImage;
    public int numTilesAcross;
    public Tile[][] tiles;
    public int tileSize;

    public TileLoading(int tileSize){
        this.tileSize = tileSize;
    }

    public void loadTiles(String s) {
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream(s));
            numTilesAcross = bufferedImage.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];
            BufferedImage subimage;
            for (int col = 0; col < numTilesAcross; col++) {
                subimage = bufferedImage.getSubimage(col * tileSize, 0, tileSize, tileSize);
                tile = new Tile(subimage, 1);
                tiles[0][col] = new Tile(subimage, tile.NORMAL);
                subimage = bufferedImage.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
                tiles[1][col] = new Tile(subimage, tile.BLOCKED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}