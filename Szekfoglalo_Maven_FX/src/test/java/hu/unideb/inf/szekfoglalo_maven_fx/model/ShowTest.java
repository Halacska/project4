/*
 * Ilyés Antal
 */
package hu.unideb.inf.szekfoglalo_maven_fx.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilyes
 */
public class ShowTest {
    
    public ShowTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isBooked method, of class Show.
     */
    @Test
    public void testIsBooked() {
        Seat s = new Seat(0, 0);
        Show instance = new Show("Alma", 10, 10);
        boolean expResult_1 = false;
        boolean result_1 = instance.isBooked(s);
        assertEquals(expResult_1, result_1);
        instance.room[0][0] = true;
        boolean expResult_2 = true;
        boolean result_2 = instance.isBooked(s);
        assertEquals(expResult_2, result_2);        
    }

    /**
     * Test of getRoom method, of class Show.
     */
    @Test
    public void testGetRoom() {
        Show instance = new Show("Alma", 10, 10);
        boolean[][] expResult = new boolean [10][10];
        boolean[][] result = instance.getRoom();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of printRoom method, of class Show.
     */
    @Test
    public void testPrintRoom() {
        Show instance = new Show("Alma", 2, 3);
        String expResult = "000\n000\n";
        String result = instance.printRoom();
        assertEquals(expResult, result);
        instance.room[0][0] = true;
        expResult = "+00\n000\n";
        result = instance.printRoom();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Show.
     */
    @Test
    public void testToString() {
        Show instance = new Show("Alma", 10, 10);
        String expResult = "Alma\n";
        expResult += instance.printRoom();
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
