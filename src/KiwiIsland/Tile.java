package KiwiIsland;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class Tile {
    // Background image (terrain tile) drawn on the screen.
    private BufferedImage background;
    
    // Possible GameObject's include: Kiwi, Tools, Predator's etc.
    private GameObject object;
    
    // Has the player visited this square?
    private boolean visited;
    
    private Terrain terrain;
    
    // Is the tile covered by fog?
    private boolean visible;
    
    private boolean deadly;
    
    /**
     * 
     * @param background
     * @param terrain 
     */
    public Tile(BufferedImage background, Terrain terrain) {
        this.background = background;
        this.terrain = terrain;
    }
    
    /**
     * 
     * @return 
     */
    public Image getBackground() {
        return this.background;
    }
    
    /**
     * 
     * @return 
     */
    public String getTerrainName(){
        return this.terrain.getStringRepresentation();
    }
    
    /**
     * 
     * @return 
     */
    public double getTerrainDifficulty(){
        return this.terrain.getDifficulty();
    }
    
    /**
     * 
     * @param go 
     */
    public void addObject(GameObject go) {
        this.object = go;
    }
    
    /**
     * 
     */
    public void clearObject() {
        this.object = null;
    }
    
    /**
     * 
     * @return 
     */
    public GameObject getObject() {
    	return this.object;
    }
    
    /**
     * 
     */
    public void update() {}
    
    /**
     * 
     * @param g
     * @param x
     * @param y 
     */
    public void draw(Graphics g, int x, int y) {
        if (this.visible) {            
            g.drawImage(background, x, y, null);
        } else {
            // If the tile is not currently visible then it is covered in fog.
            // To simulate fog, we darken the tile by 75%.
            
            // Convert the original background image to a simple RGB image.
            BufferedImage darkImage = new BufferedImage(background.getWidth(null), background.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D gg = darkImage.createGraphics();
            gg.drawImage(background, 0, 0, null);
            gg.dispose();

            // Darken the image.
            RescaleOp op = new RescaleOp(0.25f, 0, null);
            g.drawImage(op.filter(darkImage, null), x, y, null);
        }
    }
    
    /**
     * 
     * @return 
     */
    public boolean getVisited() {
        return this.visited;
    }
    
    /**
     * 
     * @param visited 
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getVisible() {
        return this.visible;
    }
    
    /**
     * 
     * @param visible 
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getDeadly() {
        return this.deadly;
    }
    
    /**
     * 
     * @param deadly 
     */
    public void setDeadly(boolean deadly) {
        this.deadly = deadly;
    }
}
