package KiwiIsland;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class Predator extends Fauna {
    // DATA FIELDS
    // gridX and gridY correspond to indices in the world grid.
    private int gridX, gridY;
    
    // worldX and worldY correspond to absolute x and y positions on the frame.
    private int worldX, worldY;
    
    private Animation curAnimation;
    
    private BufferedImage spriteSheet;
    
    /**
     * 
     * @param x
     * @param y
     * @param name
     * @param description 
     */
    public Predator(int x, int y, String name, String description) {
        super(name, description);
        try {
            this.spriteSheet = ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/"+name+".png"));
           
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        this.gridX = x;
        this.gridY = y;
        
        this.curAnimation  = new Animation(new SpriteSheet(spriteSheet, 64, 64).getRow(0, 10), Animation.DEFAULT_UPDATE_TIME, false);
        this.curAnimation.start();
    }
    
    /**
     * 
     */
    public void update() {}
    
    /**
     * Get middle of X axis on the grid.
     * 
     * @param x
     * @return 
     */
    public int getMiddleX(int x) {
        int middle = Game.TILE_SIZE / 2 - curAnimation.getCurFrame().getWidth() / 2;
        return middle + x*Game.TILE_SIZE;
    }
    
    /**
     * Get middle of Y axis on the grid.
     * 
     * @param y
     * @return 
     */
    public int getMiddleY(int y) {
        int middle = Game.TILE_SIZE / 2 - curAnimation.getCurFrame().getHeight() / 2;
        return middle + y*Game.TILE_SIZE;
    }
    
    /**
     * 
     * @return 
     */
    public int getWorldX() {
        return this.worldX;
    }
    
    /**
     * 
     * @return 
     */
    public int getWorldY() {
        return this.worldY;
    }
    
    /**
     * 
     * @return 
     */
    public int getGridX() {
        return this.gridX;
    }
    
    /**
     * 
     * @return 
     */
    public int getGridY() {
        return this.gridY;
    }
    
    /**
     * 
     * @return 
     */
    public Image getImg() {
        return (Image) this.curAnimation.getCurFrame();
    }
    
    /**
     * 
     * @param g 
     */
    public void draw(Graphics g) {
        g.drawImage(curAnimation.getCurFrame(), getMiddleX(this.gridX), getMiddleY(this.gridY), null);
    }
}