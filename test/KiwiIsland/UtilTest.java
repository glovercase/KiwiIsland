/* 
* To change this license header, choose License Headers in Project Properties. 
* To change this template file, choose Tools | Templates 
* and open the template in the editor. 
*/ 
package KiwiIsland; 
  
import java.awt.image.BufferedImage; 
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
public class UtilTest { 
     
    public UtilTest() { 
    } 
  
    /** 
     * Test of loadImage method, of class Util. 
     */ 
    @Test 
    public void testLoadImage() { 
        System.out.println("loadImage"); 
        String file = ""; 
        BufferedImage expResult = Util.loadImage(file); 
        BufferedImage result = Util.loadImage(file); 
        assertEquals(expResult, result); 
         
    } 
     
} 
 