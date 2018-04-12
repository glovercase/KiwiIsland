package KiwiIsland;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class Food extends Item {
    // --DATAFIELDS
    // gridX and gridY correspond to indices in the world grid.
    private int gridX, gridY;
    
    // worldX and worldY correspond to absolute x and y positions on the frame.
    private int worldX, worldY;
   
    private SpriteSheet spriteSheet;
    private BufferedImage foodImage;
    
    // Energy of food
    private double energy;
    
    /**
     * 
     * @param gridX
     * @param gridY
     * @param name
     * @param description
     * @param weight
     * @param size
     * @param energy 
     */
    public Food(int gridX, int gridY, String name, String description, double weight, double size, double energy) {
        super(name, description, weight, size);   
        try {
            this.spriteSheet = new SpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/food.png")), 64, 64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        foodImage = spriteSheet.getImage(0, 0);
        this.gridX = gridX;
        this.gridY = gridY;
        this.energy = energy;   
    }
    
    /**
     * 
     */
    public void update() {}
    
     /**
     * Gets the energy of the food.
     * @return the energy of the food
     */
    public double getEnergy() {
        return this.energy;
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
     * @param i
     * @return 
     */
    public int getMiddle(int i) {
        int middle = Game.TILE_SIZE / 2 - 64 / 2;
        return middle + i*Game.TILE_SIZE;
    }
    
    /**
     * 
     * @param g 
     */
    public void draw(Graphics g) {
        g.drawImage(foodImage, getMiddle(gridX), getMiddle(gridY), null);
    }

}
