package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @authors Lucas Johns and Jason Whiting
 * @version 2016-11-10
 */
public class CustomerTest {
    
    Customer instance = new Customer(1, "Jason", "Whiting", 19, "Pennsylvania", "Playstation 4", 249.99);
    
    /**
     * Test of getId method, of class Customer.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Customer.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        instance.setId(id);
    }

    /**
     * Test of getFirstName method, of class Customer.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String expResult = "Jason";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastName method, of class Customer.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        String expResult = "Whiting";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAge method, of class Customer.
     */
    @Test
    public void testGetAge() {
        System.out.println("getAge");
        int expResult = 19;
        int result = instance.getAge();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Customer.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        String expResult = "Pennsylvania";
        String result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPurchase method, of class Customer.
     */
    @Test
    public void testGetPurchase() {
        System.out.println("getPurchase");
        String expResult = "Playstation 4";
        String result = instance.getPurchase();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Customer.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        
        double expResult = 249.99;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    } 
}
