/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.view;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projekt.model.Model;
import projekt.model.Seat;

/**
 *
 * @author ilyes
 */
public class FXMLProjektControllerTest {
    
    public FXMLProjektControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of startController method, of class FXMLProjektController.
     */
    @Test
    public void testStartController() {
        System.out.println("startController");
        FXMLProjektController instance = new FXMLProjektController();
        instance.startController();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class FXMLProjektController.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        Model model = null;
        FXMLProjektController instance = new FXMLProjektController();
        instance.setModel(model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class FXMLProjektController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        FXMLProjektController instance = new FXMLProjektController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Registration method, of class FXMLProjektController.
     */
    @Test
    public void testRegistration() {
        System.out.println("Registration");
        FXMLProjektController instance = new FXMLProjektController();
        instance.Registration();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Login method, of class FXMLProjektController.
     */
    @Test
    public void testLogin() {
        System.out.println("Login");
        FXMLProjektController instance = new FXMLProjektController();
        instance.Login();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Logout method, of class FXMLProjektController.
     */
    @Test
    public void testLogout() {
        System.out.println("Logout");
        FXMLProjektController instance = new FXMLProjektController();
        instance.Logout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of button0 method, of class FXMLProjektController.
     */
    @Test
    public void testButton0() {
        System.out.println("button0");
        FXMLProjektController instance = new FXMLProjektController();
        instance.button0();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of button1 method, of class FXMLProjektController.
     */
    @Test
    public void testButton1() {
        System.out.println("button1");
        FXMLProjektController instance = new FXMLProjektController();
        instance.button1();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of button2 method, of class FXMLProjektController.
     */
    @Test
    public void testButton2() {
        System.out.println("button2");
        FXMLProjektController instance = new FXMLProjektController();
        instance.button2();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ret method, of class FXMLProjektController.
     */
    @Test
    public void testRet() {
        System.out.println("ret");
        FXMLProjektController instance = new FXMLProjektController();
        instance.ret();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadMovie method, of class FXMLProjektController.
     */
    @Test
    public void testLoadMovie() {
        System.out.println("loadMovie");
        FXMLProjektController instance = new FXMLProjektController();
        instance.loadMovie();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bookButtonPushed method, of class FXMLProjektController.
     */
    @Test
    public void testBookButtonPushed() {
        System.out.println("bookButtonPushed");
        FXMLProjektController instance = new FXMLProjektController();
        instance.bookButtonPushed();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteButtonPushed method, of class FXMLProjektController.
     */
    @Test
    public void testDeleteButtonPushed() {
        System.out.println("deleteButtonPushed");
        FXMLProjektController instance = new FXMLProjektController();
        instance.deleteButtonPushed();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Booking method, of class FXMLProjektController.
     */
    @Test
    public void testBooking() {
        System.out.println("Booking");
        HashSet<Seat> seats = null;
        FXMLProjektController instance = new FXMLProjektController();
        instance.Booking(seats);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteBooking method, of class FXMLProjektController.
     */
    @Test
    public void testDeleteBooking() {
        System.out.println("deleteBooking");
        HashSet<Seat> seats = null;
        FXMLProjektController instance = new FXMLProjektController();
        instance.deleteBooking(seats);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alert method, of class FXMLProjektController.
     */
    @Test
    public void testAlert() {
        System.out.println("alert");
        String s = "";
        FXMLProjektController instance = new FXMLProjektController();
        instance.alert(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
