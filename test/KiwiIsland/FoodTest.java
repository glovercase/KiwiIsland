package KiwiIsland;

import java.awt.Graphics;
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
public class FoodTest {
    
    public FoodTest() {
    }

    /**
     * Test of update method, of class Food.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Food food = new Food( 5, 5, "fruitandnuts", "energy bar", 10.0, 20.0, 15.0);
        food.update();
    }

    /**
     * Test of getEnergy method, of class Food.
     */
    @Test
    public void testGetEnergy() {
        System.out.println("getEnergy");
        Food food = new Food( 5, 5, "fruitandnuts", "energy bar", 10.0, 20.0, 15.0);
        double expResult = 15.0;
        double result = food.getEnergy();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getGridX method, of class Food.
     */
    @Test
    public void testGetGridX() {
        System.out.println("getGridX");
        Food food = new Food( 5, 5, "fruitandnuts", "energy bar", 10.0, 20.0, 15.0);
        int expResult = 5;
        int result = food.getGridX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGridY method, of class Food.
     */
    @Test
    public void testGetGridY() {
        System.out.println("getGridY");
        Food food = new Food( 5, 5, "fruitandnuts", "energy bar", 10.0, 20.0, 15.0);
        int expResult = 5;
        int result = food.getGridY();
        assertEquals(expResult, result);
    }

    /**
     * Test of draw method, of class Food.
     */
    @Test (expected = NullPointerException.class)
    public void testDraw() {
        System.out.println("draw");
        Graphics g = null;
        Food food = new Food( 5, 5, "fruitandnuts", "energy bar", 10.0, 20.0, 15.0);
        food.draw(g);
    }
    
}
