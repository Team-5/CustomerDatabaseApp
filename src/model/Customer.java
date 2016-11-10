package model;

/**
 * The "Customer" class represents a single customer. This customer will enter
 * their personal information voluntarily so that the business can gather
 * demographics of their customers. For example, which age groups are buying
 * which products? Where are the majority of their customers located? What are
 * people buying most?
 *
 * @authors Lisa Caswell and Jason Whiting
 * @version 2016-11-10
 */
public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String state;
    private String purchase;
    private double price;

    /**
     * The empty constructor. Sets all variabels to the empty set or 0.
     */
    public Customer() {
        id = 0;
        firstName = "";
        lastName = "";
        age = 0;
        state = "";
        purchase = "";
        price = 0;
    }

    /**
     * constructor that sets all variables equal to the customer object
     * variables that are passed in.
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param age
     * @param state
     * @param purchase
     * @param price
     */
    public Customer(int id, String firstName, String lastName, int age, String state, String purchase, double price) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.state = state;
        this.purchase = purchase;
        this.price = price;
    }

    /**
     * Allows the user to retrieve the ID of a customer object.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Allows the user to set the ID of a customer object.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Allows the user to retrieve the first name of a customer object.
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Allows the user to set the first name of a customer object.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Allows the user to retrieve the last name of a customer object.
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Allows the user to set the last name of a customer object.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Allows the user to retrieve the age of a customer object.
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * Allows the user to set the age of a customer object.
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Allows the user to retrieve the home state of a customer object.
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Allows the user to set the home state of a customer object.
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Allows the user to retrieve the purchase item of a customer object.
     *
     * @return
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * Allows the user to set the purchase item of a customer object.
     *
     * @param purchase
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    /**
     * Allows the user to retrieve the purchase price of a customer object.
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Allows the user to set the purchase price of a customer object.
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%5d : %s, %s, %3d, %s, %s, %7.2f", this.getId(), this.getFirstName(),
                this.getLastName(), this.getAge(), this.getState(), this.getPurchase(), this.getPrice());
    }
}
