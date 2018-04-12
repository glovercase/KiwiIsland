package KiwiIsland;

import java.awt.image.BufferedImage;
/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class SpriteSheet {
    // 2d Array to represent a sheet of different sprite images all of the same
    // width and height.
    private BufferedImage[][] frames;
    private BufferedImage     sheet;
    
    /**
     * 
     * @param sheet
     * @param width
     * @param height 
     */
    public SpriteSheet(BufferedImage sheet, int width, int height) {
        this.sheet = sheet;
        
        if (sheet.getWidth() % width != 0 || sheet.getHeight() % height != 0) {
            System.out.println("SpriteSheet: Width or height does not match.");
            return;
        }
        int rows = sheet.getHeight() / height;
        int cols = sheet.getWidth() / width;
        this.frames = new BufferedImage[rows][cols];
        
        loadFrames(width, height);
    }
    
    /**
     * 
     * @param width
     * @param height 
     */
    public void loadFrames(int width, int height) {
        int x = 0;
        int y = 0;
        
        // Go through the sprite sheet image and cut it up into equal sized frames.
        for (int i = 0; i < frames.length; i++) {
            for (int j = 0; j < frames[i].length; j++) {
                this.frames[i][j] = this.sheet.getSubimage(x, y, width, height);
                x += width;
            }
            x = 0;
            y += height;
        }
    }
    
    /**
     * 
     * @param row
     * @param n
     * @return 
     */
    public BufferedImage[] getRow(int row, int n) {
        BufferedImage[] frames = new BufferedImage[n];
        for (int i = 0; i < n; i++)
            frames[i] = this.frames[row][i];
        return frames;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public BufferedImage getImage(int x, int y) {
        if (x > this.frames.length || y > frames[x].length) {
            System.out.println("SpriteSheet: getImage: getting out of bounds image.");
            return null;
        }
        
        return this.frames[x][y];
    }
}
