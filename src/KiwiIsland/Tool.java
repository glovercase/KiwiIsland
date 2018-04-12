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
public class Tool extends Item {
    
    // gridX and gridY correspond to indices in the world grid.
    private int gridX, gridY;
    
    // worldX and worldY correspond to absolute x and y positions on the frame.
    private int worldX, worldY;
    
    private Animation curAnimation;
    private BufferedImage spriteSheet;
    
    // Is the tool broken?
    private boolean broken;
    
    /**
     * 
     * @param gridX
     * @param gridY
     * @param name
     * @param description
     * @param weight
     * @param size 
     */
    public Tool(int gridX, int gridY, String name, String description, double weight, double size) {
        super(name, description, weight, size);
        
        try {
            this.spriteSheet = ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/"+name+".png"));    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.gridX = gridX;
        this.gridY = gridY;
        
        this.curAnimation  = new Animation(new SpriteSheet(spriteSheet, 64, 64).getRow(0, 8), Animation.DEFAULT_UPDATE_TIME, false);
        this.curAnimation.start();
    }
    
    /**
     * 
     */
    public void update() {
        
    }
    
    /**
     * Find the middle of X tile on the grid.
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
        g.drawImage(curAnimation.getCurFrame(), getMiddle(gridX), getMiddle(gridY), null);
    }
    
    /**
     * Break the tool
     */
    public void setBroken()
    {
        broken = true;
    }
    
    /**
     * Fix the tool
     */
    public void fix()
    {
        broken = false;
    }
    
    /**
     * Is tool broken
     * @return true if broken
     */
    public boolean isBroken()
    {
        return this.broken;
    }
    
    /**
    * Check if this tool is a predator trap
    * @return true if trap
     */
    public boolean isTrap()
    {
      String name = this.getName();
      return name.equalsIgnoreCase("Trap");
    }
 
    /**
    * Check if this tool is a screwdriver
    * @return true if screwdriver
     */    
    public boolean isScrewdriver() {
       String name = this.getName();
      return name.equalsIgnoreCase("Tool");
    }
    
}
    