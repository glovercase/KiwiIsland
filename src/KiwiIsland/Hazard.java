package KiwiIsland;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class Hazard extends GameObject{
    
    //DATA FIELDS
    private final double impact;
    private final double FATAL_IMPACT = 1.0;

    private int gridX, gridY;
    
    // worldX and worldY correspond to absolute x and y positions on the frame.
    private int worldX, worldY;
    private String name, description;
   
    private SpriteSheet spriteSheet;
    private BufferedImage hazardImage;
    
    /**
     * 
     * @param gridX
     * @param gridY
     * @param name
     * @param description
     * @param impact 
     */
    public Hazard(int gridX, int gridY, String name, String description, double impact){
         
        try {
            this.spriteSheet = new SpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/terrain.png")), 64, 64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        hazardImage = spriteSheet.getImage(5, 21);
        this.gridX = gridX;
        this.gridY = gridY;
        this.name = name;
        this.description = description;
        this.impact = impact;
    }
    
    /**
     * 
     * @param g 
     */
    @Override
    public void draw(Graphics g) {
         g.drawImage(hazardImage, getMiddle(gridX), getMiddle(gridY), null);
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getGridX(){
        return this.gridX;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getGridY() {
        return this.gridY;
    }
    
    /**
     * Fine the middle of Y of a tile on the grid
     * 
     * @param i
     * @return 
     */
    public int getMiddle(int i) {
        int middle = Game.TILE_SIZE / 2 - 64 / 2;
        return middle + i*Game.TILE_SIZE;
    }
    
     /**
     * What is impact of hazard
     * @return impact
     */
    public double getImpact() {
        return this.impact;
    }
    
    /**
     * Checks if fatal impact
     * @return true if fatal
     */
    public boolean isFatal()
    {
        return impact == FATAL_IMPACT;
    }
    
    /**
     * Gets the hazard's name.
     * 
     * @return the name of the occupant
     */
    public String getName()
    {
        return this.name;
    } 

   /**
    * Gets the description for the Hazard.
    * 
    * @return the description
    */
    public String getDescription() {
        return this.description;
    }
}
