/*
 * Ilyés Antal
 */
package hu.unideb.inf.szekfoglalo_maven_fx.model;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilyes
 */
public class ModelTest {
    
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getUsers method, of class Model.
     */
    @Test
    public void testGetUsers() {
        Model instance = new Model();
        ArrayList expResult = new ArrayList<>();
        ArrayList result = instance.getUsers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getShows method, of class Model.
     */
    @Test
    public void testGetShows() {
        Model instance = new Model();
        ArrayList expResult = new ArrayList<>();
        ArrayList result = instance.getShows();
        assertEquals(expResult, result);
    }

    /**
     * Test of getShow method, of class Model.
     */
    @Test
    public void testGetShow() {
        String name = "Valami";
        Model instance = new Model();
        Show expResult = new Show(name, 1, 1);
        instance.getShows().add(expResult);
        Show result = instance.getShow(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class Model.
     */
    @Test
    public void testGetUser() {
        String name = "Valaki";
        Model instance = new Model();
        User expResult = new User(name, "jelszo");
        instance.getUsers().add(expResult);
        User result = instance.getUser(name);
        assertEquals(expResult, result);
    }
    
}
