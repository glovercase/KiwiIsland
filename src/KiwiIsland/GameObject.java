package KiwiIsland;

import java.awt.Graphics;

/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public abstract class GameObject {
    // DATA FIELDS
    boolean counted;
    
    //private final String description;
    
    abstract public void draw(Graphics g);
    abstract public int getGridX();
    abstract public int getGridY();
}
