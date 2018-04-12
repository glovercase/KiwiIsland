package KiwiIsland;

import java.awt.Graphics;
import java.awt.Image;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author suraaz
 */
public class TileTest {
    
    public TileTest() {
    }

    /**
     * Test of getBackground method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testGetBackground() {
        System.out.println("getBackground");
        Tile instance = null;
        Image expResult = null;
        Image result = instance.getBackground();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTerrainName method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testGetTerrainName() {
        System.out.println("getTerrainName");
        Tile instance = null;
        String expResult = "";
        String result = instance.getTerrainName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTerrainDifficulty method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testGetTerrainDifficulty() {
        System.out.println("getTerrainDifficulty");
        Tile instance = null;
        double expResult = 0.0;
        double result = instance.getTerrainDifficulty();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of addObject method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testAddObject() {
        System.out.println("addObject");
        GameObject go = null;
        Tile instance = null;
        instance.addObject(go);
    }

    /**
     * Test of clearObject method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testClearObject() {
        System.out.println("clearObject");
        Tile instance = null;
        instance.clearObject();
    }

    /**
     * Test of getObject method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testGetObject() {
        System.out.println("getObject");
        Tile instance = null;
        GameObject expResult = null;
        GameObject result = instance.getObject();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testUpdate() {
        System.out.println("update");
        Tile instance = null;
        instance.update();
    }

    /**
     * Test of draw method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testDraw() {
        System.out.println("draw");
        Graphics g = null;
        int x = 0;
        int y = 0;
        Tile instance = null;
        instance.draw(g, x, y);
    }

    /**
     * Test of getVisited method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testGetVisited() {
        System.out.println("getVisited");
        Tile instance = null;
        boolean expResult = false;
        boolean result = instance.getVisited();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVisited method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testSetVisited() {
        System.out.println("setVisited");
        boolean visited = false;
        Tile instance = null;
        instance.setVisited(visited);
    }

    /**
     * Test of getVisible method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testGetVisible() {
        System.out.println("getVisible");
        Tile instance = null;
        boolean expResult = false;
        boolean result = instance.getVisible();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVisible method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testSetVisible() {
        System.out.println("setVisible");
        boolean visible = false;
        Tile instance = null;
        instance.setVisible(visible);
    }

    /**
     * Test of getDeadly method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testGetDeadly() {
        System.out.println("getDeadly");
        Tile instance = null;
        boolean expResult = false;
        boolean result = instance.getDeadly();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDeadly method, of class Tile.
     */
    @Test (expected = NullPointerException.class)
    public void testSetDeadly() {
        System.out.println("setDeadly");
        boolean deadly = false;
        Tile instance = null;
        instance.setDeadly(deadly);
    }
    
}
