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
 */
public class NativeTest {
    
    public NativeTest() {
    }

    /**
     * Test of getName method, of class Native.
     */
    @Test (expected = NullPointerException.class)
    public void testGetName() {
        System.out.println("getName");
        Fauna instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Native.
     */
    @Test (expected = NullPointerException.class)
    public void testGetDescription() {
        System.out.println("getDescription");
        Fauna instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
    
}
