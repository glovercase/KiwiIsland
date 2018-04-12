/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class HazardTest {
    
    public HazardTest() {
    }

   /**
     * Test of draw method, of class Hazard.
     */
    @Test (expected = NullPointerException.class)
    public void testDraw() {
        System.out.println("draw");
        Graphics g = null;
        Hazard hazard = new Hazard(5, 5, "Lisa", " description", 15.0);
        hazard.draw(g); 
    }


  /**
     * Test of getGridX method, of class Hazard.
     */
    @Test
    public void testGetGridX() {
        System.out.println("getGridX");
        Hazard hazard = new Hazard(5, 5, "Lisa", " description", 15.0);
        int expResult = 5;
        int result = hazard.getGridX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGridY method, of class Hazard.
     */
    @Test
    public void testGetGridY() {
        System.out.println("getGridY");
        Hazard hazard = new Hazard(5, 5, "Lisa", " description", 15.0);
        int expResult = 5;
        int result = hazard.getGridY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMiddle method, of class Hazard.
     */
    @Test
    public void testGetMiddle() {
        System.out.println("getMiddle");
        int i = 5;
        Hazard hazard = new Hazard(5, 5, "Lisa", " description", 15.0);
        int expResult = 320;
        int result = hazard.getMiddle(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of getImpact method, of class Hazard.
     */
    @Test
    public void testGetImpact() {
        System.out.println("getImpact");
        Hazard hazard = new Hazard(5, 5, "Lisa", " description", 15.0);
        double expResult = 15.0;
        double result = hazard.getImpact();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of isFatal method, of class Hazard.
     */
    @Test
    public void testIsFatal() {
        System.out.println("isFatal");
        Hazard hazard = new Hazard(5, 5, "Lisa", " description", 15.0);
        boolean expResult = false;
        boolean result = hazard.isFatal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Hazard.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Hazard hazard = new Hazard(5, 5, "Lisa", " description", 15.0);
        String expResult = "Lisa";
        String result = hazard.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Hazard.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Hazard hazard = new Hazard(5, 5, "Lisa", "description", 15.0);
        String expResult = "description";
        String result = hazard.getDescription();
        assertEquals(expResult, result);
    } 
}
