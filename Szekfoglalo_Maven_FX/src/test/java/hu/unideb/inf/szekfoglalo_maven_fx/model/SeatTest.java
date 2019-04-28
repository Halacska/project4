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
public class SeatTest {
    
    public SeatTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of toString method, of class Seat.
     */
    @Test
    public void testToString() {
        Seat instance = new Seat(0,0);
        String expResult = "1-1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
