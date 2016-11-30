package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The JUnit tests for the "CustomerDatabaseApp" program.
 * Currently only consists of the basic JUnit tests.
 * 
 * @authors Lucas Johns and Jason Whiting
 * @version 2016-11-30
 */
public class CustomerTest {
    
    Customer instance = new Customer(1, "Jason", "Whiting", 19, "Pennsylvania", "Playstation 4", 249.99);
    
    /**
     * Test of the "Customer" class getId method.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of the "Customer" class setId method.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        instance.setId(id);
    }

    /**
     * Test of the "Customer" class getFirstName method.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String expResult = "Jason";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of the "Customer" class getLastName method.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        String expResult = "Whiting";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of the "Customer" class getAge method.
     */
    @Test
    public void testGetAge() {
        System.out.println("getAge");
        int expResult = 19;
        int result = instance.getAge();
        assertEquals(expResult, result);
    }

    /**
     *Test of the "Customer" class getState method.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        String expResult = "Pennsylvania";
        String result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of the "Customer" class getPurchase method.
     */
    @Test
    public void testGetPurchase() {
        System.out.println("getPurchase");
        String expResult = "Playstation 4";
        String result = instance.getPurchase();
        assertEquals(expResult, result);
    }

    /**
     * Test of the "Customer" class getPrice method.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        
        double expResult = 249.99;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    } 
}
