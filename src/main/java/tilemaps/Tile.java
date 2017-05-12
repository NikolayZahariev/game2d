package tilemaps;

import java.awt.image.BufferedImage;

public class Tile {
    public final int NORMAL = 0;
    private BufferedImage image;
    public static final int BLOCKED = 1;
    private int type;

    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

}