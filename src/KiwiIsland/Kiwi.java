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
public class Kiwi extends Fauna {
    
    // gridX and gridY correspond to indices in the world grid.
    private int gridX, gridY;
    
    // worldX and worldY correspond to absolute x and y positions on the frame.
    private int worldX, worldY;
    
    private Animation curAnimation;
    private BufferedImage spriteSheet;
    
    // Has the kiwi been logged by the user?
    private boolean counted;
    
    /**
     *
     * @param gridX
     * @param gridY
     */
    public Kiwi(int gridX, int gridY, String name, String description) {
        super(name, description);
        try {
            this.spriteSheet = ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/Kiwi.png"));    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.gridX = gridX;
        this.gridY = gridY;
        
        this.curAnimation  = new Animation(new SpriteSheet(spriteSheet, 64, 64).getRow(0, 8), Animation.DEFAULT_UPDATE_TIME, false);
        this.curAnimation.start();
        this.curAnimation.stop();
    }
    
    /**
     * 
     */
    public void update() {
        
    }
    
    /**
     * Find the middle of a tile on the ground.
     * 
     * @param i
     * @return 
     */
    public int getMiddle(int i) {
        int middle = Game.TILE_SIZE / 2 - curAnimation.getCurFrame().getWidth() / 2;
        return middle + i*Game.TILE_SIZE;
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
        // Has the Kiwi already been counted?
        if (this.counted) {
            return;
        }
        
        g.drawImage(curAnimation.getCurFrame(), getMiddle(gridX), getMiddle(gridY), null);
    }
}
    