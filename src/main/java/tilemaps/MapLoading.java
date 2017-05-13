package tilemaps;

import main.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * @author Nikolay Zahariev <nikolay.g.zahariev@gmail.com>.
 */
public class MapLoading {
    public int numRows;
    public int numCols;
    private int width;
    private int height;
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    public int tileSize;
    public int[][] map;
    public double x;
    public double y;

    public MapLoading(int tileSize){
        this.tileSize = tileSize;
    }

    public void loadMap(String s) {
        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;
            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymax = 0;
            String delims = "\\s+";
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void fixBounds() {
        if (x < xmin) {
            x = xmin;
        }
        if (y < ymin) {
            y = ymin;
        }
        if (x > xmax) {
            x = xmax;
        }
        if (y > ymax) {
            y = ymax;
        }
    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }
}