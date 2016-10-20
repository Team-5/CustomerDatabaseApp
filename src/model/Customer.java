package model;

/**
 * The Customer class represents a single customer. This customer will enter
 * their personal information voluntarily so that the business can gather
 * demographics of their customers. For example, which age groups are buying
 * which products? Where are the majority of theri customers located? What are
 * people buying most?
 *
 * @author Lisa Caswell and Jason Whiting
 * @version 2016-10-20
 */
public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String state;
    private String purchase;
    private double price;

    public Customer() {
        id = 0;
        firstName = "";
        lastName = "";
        age = 0;
        state = "";
        purchase = "";
        price = 0;
    }

    public Customer(int id, String firstName, String lastName, int age, String state, String purchase, double price) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.state = state;
        this.purchase = purchase;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String state) {
        this.purchase = purchase;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%5d : %s, %s, %3d, %s, %s, %7.2f", this.getId(), this.getFirstName(),
                this.getLastName(), this.getAge(), this.getState(), this.getPurchase(), this.getPrice());
    }
}
