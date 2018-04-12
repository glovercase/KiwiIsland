package KiwiIsland; 
 
import org.junit.After; 
import org.junit.AfterClass; 
import org.junit.Before; 
import org.junit.BeforeClass; 
import org.junit.Test; 
import static org.junit.Assert.*; 
public class TerrainTest { 
    
    /** 
    * 
    * @author suraaz 
    */ 
    public TerrainTest() { 
    } 
 
    /** 
     * Test of getDifficulty method, of class Terrain. 
     */ 
    @Test 
    public void testGetDifficulty() { 
        System.out.println("getDifficulty"); 
         
        System.out.println("Sand"); 
        assertEquals(Terrain.SAND.getDifficulty(),1.0,1.0); 
         
        System.out.println("Grass"); 
        assertEquals(Terrain.GRASS.getDifficulty(),2.0,2.0); 
         
        System.out.println("Ice"); 
        assertEquals(Terrain.ICE.getDifficulty(),2.5,2.5); 
         
        System.out.println("Water"); 
        assertEquals(Terrain.WATER.getDifficulty(),4.0,4.0); 
         
    } 
 
    /** 
     * Test of getStringRepresentation method, of class Terrain. 
     */ 
    @Test 
    public void testGetStringRepresentation() { 
        System.out.println("getStringRepresentation"); 
        System.out.println("Sand"); 
        assertEquals(Terrain.SAND.getStringRepresentation(),"."); 
         
        System.out.println("Grass"); 
        assertEquals(Terrain.GRASS.getStringRepresentation(),"*"); 
         
        System.out.println("Ice"); 
        assertEquals(Terrain.ICE.getStringRepresentation(),"#"); 
         
        System.out.println("Water"); 
        assertEquals(Terrain.WATER.getStringRepresentation(),"~"); 
 
    } 
     
} 