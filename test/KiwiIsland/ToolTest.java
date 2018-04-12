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
public class ToolTest {
    
    public ToolTest() {
    }

    /**
     * Test of update method, of class Tool.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        tool.update();
    }

    /**
     * Test of getMiddleX method, of class Tool.
     */
    @Test
    public void testGetMiddle() {
        System.out.println("getMiddleX");
        int i = 5;
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        int expResult = 320;
        int result = tool.getMiddle(i);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getGridX method, of class Tool.
     */
    @Test
    public void testGetGridX() {
        System.out.println("getGridX");
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        int expResult = 5;
        int result = tool.getGridX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGridY method, of class Tool.
     */
    @Test
    public void testGetGridY() {
        System.out.println("getGridY");
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        int expResult = 5;
        int result = tool.getGridY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImg method, of class Tool.
     */
    @Test
    public void testGetImg() {
        System.out.println("getImg");
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        Image expResult = tool.getImg();
        Image result = tool.getImg();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of draw method, of class Tool.
     */
    @Test (expected = NullPointerException.class) 
    public void testDraw() {
        System.out.println("draw");
        Graphics g = null;
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        tool.draw(g);
    }

    /**
     * Test of setBroken method, of class Tool.
     */
    @Test
    public void testSetBroken() {
        System.out.println("setBroken");
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        tool.setBroken();
    }

    /**
     * Test of fix method, of class Tool.
     */
    @Test (expected = NullPointerException.class) 
    public void testFix() {
        System.out.println("fix");
        Tool instance = null;
        instance.fix();
    }

    /**
     * Test of isBroken method, of class Tool.
     */
    @Test 
    public void testIsBroken() {
        System.out.println("isBroken");
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        boolean expResult = false;
        boolean result = tool.isBroken();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of isTrap method, of class Tool.
     */
    @Test 
    public void testIsTrap() {
        System.out.println("isTrap");
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        boolean expResult = true;
        boolean result = tool.isTrap();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isScrewdriver method, of class Tool.
     */
    @Test 
    public void testIsScrewdriver() {
        System.out.println("isScrewdriver");
        Tool tool = new Tool(5, 5, "trap", "trap a predator", 10.0, 20.0);
        boolean expResult = false;
        boolean result = tool.isScrewdriver();
        assertEquals(expResult, result);
       
    }
    
}
