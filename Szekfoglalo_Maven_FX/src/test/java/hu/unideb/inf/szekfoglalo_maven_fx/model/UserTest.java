/*
 * Ilyés Antal
 */
package hu.unideb.inf.szekfoglalo_maven_fx.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilyes
 */
public class UserTest {
    
    Map<String, ArrayList<Seat>> booking = new HashMap<>();
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {    
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of hasSeat method, of class User.
     */
    @Test
    public void testHasSeat() {
        Show show = new Show("Alma", 10, 10);
        Seat s = new Seat(0,0);
        User instance = new User("Anti", "jelszo");
        boolean expResult = false;
        boolean result = instance.hasSeat(show, s);
        assertEquals(expResult, result);
        
        instance.addBooking(show, s);
        result = instance.hasSeat(show, s);
        expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of addBooking method, of class User.
     */
    @Test
    public void testAddBooking() {
        Show show = new Show("Alma", 10, 10);
        Seat s = new Seat(0, 0);
        User instance = new User("Anti", "jelszo");
        instance.addBooking(show, s);
        boolean expResult = true;
        boolean result = instance.hasSeat(show, s);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteBooking method, of class User.
     */
    @Test
    public void testDeleteBooking() {
        Show show = new Show("Alma", 10, 10);
        show.room[0][0] = true;
        Seat s = new Seat(0, 0);
        User instance = new User("Anti", "jelszo");
        instance.getBooking().put(show.name, new ArrayList<Seat>());
        booking.put(show.name, new ArrayList<>());
        instance.deleteBooking(show, s);
        boolean result = booking.get(show.name).contains(s);
        boolean expResult = false;
        assertEquals(expResult, result);
        expResult = false;
        result = instance.hasSeat(show, s);
        assertEquals(expResult, result);
    }

    /**
     * Test of isCorrectPassword method, of class User.
     */
    @Test
    public void testIsCorrectPassword() {
        String other = "jelszo";
        User instance = new User("Anti", "jelszo");
        boolean expResult = true;
        boolean result = instance.isCorrectPassword(other);
        assertEquals(expResult, result);
        instance = new User("Valaki", "rossz");
        expResult = false;
        result = instance.isCorrectPassword(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of printBooking method, of class User.
     */
    @Test
    public void testPrintBooking() {
        User instance = new User("Anti", "jelszo");
        String expResult = "There is no booking.";
        String result = instance.printBooking();
        assertEquals(expResult, result);
        Show show = new Show("Alma", 10, 10);
        Seat s = new Seat(0, 0);
        instance.addBooking(show, s);
        expResult = "Alma: 1-1 \n";
        result = instance.printBooking();
        System.out.println(result);
        System.out.println(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        User instance = new User("Anti", "jelszo");
        String expResult = "Username: Anti, password: jelszo\nThere is no booking.";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        User instance = new User("Anti", "jelszo");
        String expResult = "Anti";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        User instance = new User("Anti", "jelszo");
        String expResult = "jelszo";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBooking method, of class User.
     */
    @Test
    public void testGetBooking() {
        User instance = new User("Anti", "jelszo");
        HashMap<String, ArrayList<Seat>> expResult = (HashMap<String, ArrayList<Seat>>) booking;
        HashMap<String, ArrayList<Seat>> result = instance.getBooking();
        assertEquals(expResult, result);
    }
    
}
