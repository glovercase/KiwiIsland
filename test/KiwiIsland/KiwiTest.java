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
public class KiwiTest { 
     
    public KiwiTest() { 
    } 
 
    /** 
     * Test of getGridX method, of class Kiwi. 
     */ 
    @Test 
    public void testGetGridX() { 
        System.out.println("getGridX"); 
        Kiwi kiwi = new Kiwi(5,5,"rock","hot"); 
        assertEquals(kiwi.getGridX(),5); 
    } 
    /** 
     * Test of getGridY method, of class Kiwi. 
     */ 
    @Test 
    public void testGetGridY() { 
        System.out.println("getGridY"); 
        Kiwi kiwi = new Kiwi(5,5,"rock","hot"); 
        assertEquals(kiwi.getGridY(),5); 
    } 
 
/** 
     * Test of getMiddle method, of class Kiwi. 
     */ 
    @Test 
    public void testGetMiddle() { 
        System.out.println("getMiddleX"); 
        int i = 5; 
        Kiwi kiwi = new Kiwi(5,5,"rock","hot"); 
        int expResult = 320; 
        int result = kiwi.getMiddle(i); 
        assertEquals(expResult, result); 
        
    } 

    /**
     * Test of update method, of class Kiwi.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Kiwi kiwi = new Kiwi(5,5,"rock","hot");
        kiwi.update();
        
    }


    /**
     * Test of draw method, of class Kiwi.
     */
    @Test (expected = NullPointerException.class)
    public void testDraw() {
        System.out.println("draw");
        Graphics g = null;
        Kiwi kiwi = new Kiwi(5,5,"rock","hot");
        kiwi.draw(g);
    }
     
} 