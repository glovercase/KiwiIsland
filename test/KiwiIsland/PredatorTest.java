/* 
* To change this license header, choose License Headers in Project Properties. 
* To change this template file, choose Tools | Templates 
* and open the template in the editor. 
*/ 
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
public class PredatorTest { 
     
    public PredatorTest() { 
    } 
  
    /** 
     * Test of update method, of class Predator. 
     */ 
    @Test 
    public void testUpdate() { 
        System.out.println("update"); 
        Predator predator = new Predator(5,5,"possum","angry predator"); 
        predator.update(); 
    } 
  
    /** 
     * Test of getMiddleX method, of class Predator. 
     */ 
    @Test 
    public void testGetMiddleX() { 
        System.out.println("getMiddleX"); 
        int x = 5; 
        Predator predator = new Predator(5,5,"possum","angry predator"); 
        int expResult = 320; 
        int result = predator.getMiddleX(x); 
        assertEquals(expResult, result); 
        
    } 
  
    /** 
     * Test of getMiddleY method, of class Predator. 
     */ 
    @Test 
    public void testGetMiddleY() { 
        System.out.println("getMiddleY"); 
        int y = 5; 
        Predator predator = new Predator(5,5,"possum","angry predator"); 
        int expResult = 320; 
        int result = predator.getMiddleY(y); 
        assertEquals(expResult, result); 
    } 
  
    /** 
     * Test of getWorldX method, of class Predator. 
     */ 
    @Test 
    public void testGetWorldX() { 
        System.out.println("getWorldX"); 
        Predator predator = new Predator(5,5,"possum","angry predator");
        int expResult = 0; 
        int result = predator.getWorldX(); 
        assertEquals(expResult, result); 
    } 
  
    /** 
     * Test of getWorldY method, of class Predator. 
     */ 
    @Test 
    public void testGetWorldY() { 
        System.out.println("getWorldY"); 
        Predator predator = new Predator(5,5,"possum","angry predator");
        int expResult = 0; 
        int result = predator.getWorldY(); 
        assertEquals(expResult, result); 
    } 
  
     /**  
     * Test of getGridX method, of class Predator.  
     */  
    @Test  
    public void testGetGridX() {  
        System.out.println("getGridX");  
        Predator predator = new Predator(5,5,"possum","angry predator");  
        assertEquals(predator.getGridX(),5);  
    }  
  
    /**  
     * Test of getGridY method, of class Predator.  
     */  
    @Test  
    public void testGetGridY() {  
        System.out.println("getGridY");  
        Predator predator = new Predator(5,5,"possum","angry predator");  
        assertEquals(predator.getGridY(),5);  
    }  
  
     
} 
 