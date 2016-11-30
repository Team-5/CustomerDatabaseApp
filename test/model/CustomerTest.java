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
     * Test of the "Customer" class setFirstName method.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String name = "";
        instance.setFirstName(name);
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
     * Test of the "Customer" class setLastName method.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String name = "";
        instance.setLastName(name);
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
     * Test of the "Customer" class setAge method.
     */
    @Test
    public void testSetAge() {
        System.out.println("setAge");
        int age = 0;
        instance.setAge(age);
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
     * Test of the "Customer" class setState method.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        String state = "";
        instance.setState(state);
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
     * Test of the "Customer" class setPurchase method.
     */
    @Test
    public void testSetPurchase() {
        System.out.println("setPurchase");
        String purchase = "";
        instance.setPurchase(purchase);
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
    
    /**
     * Test of the "Customer" class setPrice method.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 0.0;
        instance.setPrice(price);
    }
}
