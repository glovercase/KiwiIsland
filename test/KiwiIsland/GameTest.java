 package KiwiIsland; 
  
import org.junit.After; 
import org.junit.AfterClass; 
import org.junit.Before; 
import org.junit.BeforeClass; 
import org.junit.Test; 
import static org.junit.Assert.*; 
  
/** 
* 
* @author suraaz 
* 
*
*/ 
public class GameTest { 
     
    public GameTest() { 
    } 
     
    /** 
     * Test of main method, of class Game. 
     */ 
    @Test 
    public void testMain() { 
        System.out.println("main"); 
        String[] args = null; 
       
    }

    /**
     * Test of getGameState method, of class Game.
     */
    @Test (expected = NullPointerException.class)
    public void testGetGameState() {
        System.out.println("getGameState");
        Game instance = null;
        GameState expResult = null;
        GameState result = instance.getGameState();
        assertEquals(expResult, result);
    }
    
        
} 
 