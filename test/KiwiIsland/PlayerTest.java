/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KiwiIsland;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
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
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    /**
     * Test of update method, of class Player.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        player.update();
    }

    /**
     * Test of getCurrentTile method, of class Player.
     */
    @Test
    public void testGetCurrentTile() {
        System.out.println("getCurrentTile");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        Tile expResult = null;
        Tile result = player.getCurrentTile();
        assertEquals(expResult, result);
    }

    /**
     * Test of eachStep method, of class Player.
     */
    @Test (expected = NullPointerException.class)
    public void testEachStep() {
        System.out.println("eachStep");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        player.eachStep();
    }

    /**
     * Test of getMiddleX method, of class Player.
     */
    @Test (expected = NullPointerException.class)
    public void testGetMiddle() {
        System.out.println("getMiddle");
        int i = 5;
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        int expResult = 320;
        int result = player.getMiddle(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of up method, of class Player.
     */
    @Test
    public void testUp() {
        System.out.println("up");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        player.up();
    }

    /**
     * Test of down method, of class Player.
     */
    @Test
    public void testDown() {
        System.out.println("down");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        player.down();
    }

    /**
     * Test of left method, of class Player.
     */
    @Test
    public void testLeft() {
        System.out.println("left");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        player.left();
    }

    /**
     * Test of right method, of class Player.
     */
    @Test
    public void testRight() {
        System.out.println("right");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        player.right();
    }

     /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        String expResult = "Lisa";
        String result = player.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAlive method, of class Player.
     */
    @Test
    public void testIsAlive() {
        System.out.println("isAlive");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        boolean expResult = false;
        boolean result = player.isAlive();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxStamina method, of class Player.
     */
    @Test
    public void testGetMaxStamina() {
        System.out.println("getMaxStamina");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        double expResult = 25.0;
        double result = player.getMaxStamina();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getStaminaLevel method, of class Player.
     */
    @Test
    public void testGetStaminaLevel() {
        System.out.println("getStaminaLevel");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        double expResult = 25.0;
        double result = player.getStaminaLevel();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getStaminaNeededToMove method, of class Player.
     */
    @Test (expected = NullPointerException.class)
    public void testGetStaminaNeededToMove() {
        System.out.println("getStaminaNeededToMove");
        Terrain terrain = null;
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        double expResult = 1.0;
        double result = player.getStaminaNeededToMove(terrain);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of hasStamina method, of class Player.
     */
    @Test
    public void testHasStamina() {
        System.out.println("hasStamina");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        boolean expResult = true;
        boolean result = instance.hasStamina();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCurrentBackpackSize method, of class Player.
     */
    @Test (expected = NullPointerException.class)
    public void testGetCurrentBackpackSize() {
        System.out.println("getCurrentBackpackSize");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        int expResult =0;
        int result = player.getCurrentBackpackSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaximumBackpackSize method, of class Player.
     */
    @Test
    public void testGetMaximumBackpackSize() {
        System.out.println("getMaximumBackpackSize");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        int expResult = 20;
        int result = instance.getMaximumBackpackSize();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCurrentBackpackWeight method, of class Player.
     */ 
    @Test (expected = NullPointerException.class)
    public void testGetCurrentBackpackWeight() {
        System.out.println("getCurrentBackpackWeight");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        int expResult = 0;
        int result = player.getCurrentBackpackWeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaximumBackpackWeight method, of class Player.
     */
    @Test
    public void testGetMaximumBackpackWeight() {
        System.out.println("getMaximumBackpackWeight");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        double expResult = 15.0;
        double result = instance.getMaximumBackpackWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of hasItem method, of class Player.
     */
    @Test(expected = NullPointerException.class)
    public void testHasItem() {
        System.out.println("hasItem");
        Item item = null;
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        boolean expResult = false;
        boolean result = player.hasItem("Food");
        assertEquals(expResult, result);
    }

    /**
     * Test of getWorldX method, of class Player.
     */
    @Test
    public void testGetWorldX() {
        System.out.println("getWorldX");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        int expResult = 0;
        int result = instance.getWorldX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWorldY method, of class Player.
     */
    @Test
    public void testGetWorldY() {
        System.out.println("getWorldY");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        int expResult = 0;
        int result = instance.getWorldY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGridX method, of class Player.
     */
    @Test
    public void testGetGridX() {
        System.out.println("getGridX");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        int expResult = 0;
        int result = instance.getGridX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGridY method, of class Player.
     */
    @Test
    public void testGetGridY() {
        System.out.println("getGridY");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        int expResult = 0;
        int result = instance.getGridY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessage method, of class Player.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        String expResult = null;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of kill method, of class Player.
     */
    @Test
    public void testKill() {
        System.out.println("kill");
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        instance.kill();
    }


    /**
     * Test of reduceStamina method, of class Player.
     */
    @Test
    public void testReduceStamina() {
        System.out.println("reduceStamina");
        double reduction = 0.0;
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        instance.reduceStamina(reduction);
    }

    /**
     * Test of increaseStamina method, of class Player.
     */
    @Test
    public void testIncreaseStamina() {
        System.out.println("increaseStamina");
        double increase = 0.0;
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        instance.increaseStamina(increase);
    }

    /**
     * Test of collect method, of class Player.
     */
    @Test
    public void testCollect() {
        System.out.println("collect");
        Item item = null;
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        boolean expResult = false;
        boolean result = instance.collect(item);
        assertEquals(expResult, result);
    }

    /**
     * Test of drop method, of class Player.
     */
    @Test (expected = NullPointerException.class)
    public void testDrop() {
        System.out.println("drop");
        Item item = null;
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        boolean expResult = false;
        boolean result = player.drop("Food");
        assertEquals(expResult, result);
    }

    /**
     * Test of updating method, of class Player.
     */
    @Test
    public void testUpdating() {
        System.out.println("updating");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        boolean expResult = false;
        boolean result = player.updating();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImg method, of class Player.
     */
    @Test (expected = NullPointerException.class)
    public void testGetImg() {
        System.out.println("getImg");
        Player player = new Player("Lisa",25.0, 15.0, 20.0);
        Image expResult = null;
        Image result = player.getImg();
        assertEquals(expResult, result);
    }

     /**
     * Test of draw method, of class Player.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics g = null;
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        instance.draw(g);
    }

    /**
     * Test of setMessage method, of class Player.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "";
        Player instance = new Player("Lisa",25.0, 15.0, 20.0);
        instance.setMessage(message);
    }

}    

