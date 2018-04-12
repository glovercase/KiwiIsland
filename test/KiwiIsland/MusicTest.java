/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class MusicTest {
    
    public MusicTest() {
    }

    /**
     * Test of run method, of class Music.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Music music = new Music();
        music.run();
    }

    /**
     * Test of stop method, of class Music.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        Music music = new Music();
        music.stop();
    }
    
}
