package tilemaps;

import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {
    private BufferedImage image;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double moveScale;

    public Background(double moveScale) {
        this.moveScale = moveScale;
    }

    public void getResource(String resource) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, (int) x, (int) y, null);
        if (x < 0) {
            graphics.drawImage(image, (int) x + Panel.WIDTH, (int) y, null);
        }
        if (x > 0) {
            graphics.drawImage(image, (int) x - Panel.WIDTH, (int) y, null);
        }
    }
}