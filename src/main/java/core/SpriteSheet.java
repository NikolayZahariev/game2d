package core;

import entities.character.Character;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Denis Dimitrov <denis.k.dimitrov@gmail.com>.
 */
public class SpriteSheet {
    public String path;
    public int frameHeight;

    public SpriteSheet(String path, int frameHeight) {
        this.path = path;
        this.frameHeight = frameHeight;
    }

    public void getSpriteSheet(Character character, SpriteDimensions spriteDimensions) {
        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(path)
            );
            character.sprites = new ArrayList<>();
            for (int i = 0; i < frameHeight; i++) {
                BufferedImage[] bi = new BufferedImage[character.numFrames[i]];
                for (int j = 0; j < character.numFrames[i]; j++) {
                    bi[j] = spriteSheet.getSubimage(j * spriteDimensions.width, i * spriteDimensions.height, spriteDimensions.width, spriteDimensions.height);
                }
                character.sprites.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
