package viewui;

import java.util.List;
import java.util.Scanner;
import model.Customer;
import model.ICustomerDAO;
import model.datastore.mysql.CustomerDAO;
import util.Validator;
//import model.datastore.file.CustomerDAO;

/**
 * The "CustomerApp" class is the starting point for running this
 * console-oriented, menu-driven, customer management program. This program
 * demonstrates two solutions. The first is file based and the second is MySQL
 * based.
 *
 * @authors Lisa Caswell, Jason Whiting, Willie Scott, Jeremy Wiles, and Lucas
 * Johns
 * @version 2016-11-30
 */
public class CustomerApp {

    ICustomerDAO cstList = new CustomerDAO();
    Scanner sc = new Scanner(System.in);

    /**
     * Calls the "menuLoop" method which contains most of the class's code.
     */
    public CustomerApp() {
        menuLoop();
    }

    /**
     * Displays the console commands to interact with the database. This will
     * have options for all the various menu items for this project. The regular
     * expression allows for numbers of one or more digits. Anything else will
     * recieve an input error.
     */
    private void menuLoop() {

        int id, age;
        double price;
        String firstName, lastName, state, purchase;

        String choice = "1";

        while (!choice.equals("0")) {

            System.out.println("\nCustomer App");
            System.out.println("0 = Quit");
            System.out.println("1 = List All Customers");
            System.out.println("2 = Create New Customer");
            System.out.println("3 = Retrieve Customer");
            System.out.println("4 = Update Customer");
            System.out.println("5 = Delete Customer");
            System.out.println("6 = Show Total Profits");
            System.out.println("7 = Show Age Range");
            System.out.println("8 = Show Number of Items Purchased");
            System.out.println("9 = Show Customer Age Group");
            System.out.println("10 = Show Average Profits\n");
            choice = Validator.getLine(sc, "Number of choice: ", "^\\d+$");

            switch (choice) {

                case "1":
                    System.out.println();
                    System.out.println(cstList.toString());
                    break;

                case "2":
                    System.out.println();
                    id = Validator.getInt(sc, "New Customer ID: ");
                    firstName = Validator.getLine(sc, "New Customer First Name: ");
                    lastName = Validator.getLine(sc, "New Customer Last Name: ");
                    age = Validator.getInt(sc, "New Customer Age: ");
                    state = Validator.getLine(sc, "New Customer State: ");
                    purchase = Validator.getLine(sc, "New Customer Purchase: ");
                    price = Validator.getDouble(sc, "New Customer Payment Amount: ");
                    cstList.createCustomer(new Customer(id, firstName, lastName, age, state, purchase, price));
                    break;

                case "3":
                    System.out.println();
                    id = Validator.getInt(sc, "Customer ID to Retrieve: ");
                    System.out.println(cstList.retrieveCustomerById(id));
                    break;

                case "4":
                    System.out.println();
                    id = Validator.getInt(sc, "Customer ID to Update: ");
                    firstName = Validator.getLine(sc, "Customer First Name: ");
                    lastName = Validator.getLine(sc, "Customer Last Name: ");
                    age = Validator.getInt(sc, "Customer Age: ");
                    state = Validator.getLine(sc, "Customer State: ");
                    purchase = Validator.getLine(sc, "Customer Purchase: ");
                    price = Validator.getDouble(sc, "Customer Price: ");
                    cstList.updateCustomer(new Customer(id, firstName, lastName, age, state, purchase, price));
                    break;

                case "5":
                    System.out.println();
                    id = Validator.getInt(sc, "Customer ID to Delete: ");
                    System.out.println(cstList.retrieveCustomerById(id));
                    String ok = Validator.getLine(sc, "Delete this Customer? (y/n): ", "^[yYnN]$");
                    if (ok.equalsIgnoreCase("Y")) {
                        cstList.deleteCustomer(id);
                    }
                    break;

                case "6":
                    System.out.println();
                    System.out.println("Total profit of all purchases: $" + cstList.showTotalProfits());
                    break;

                case "7":
                    System.out.println();
                    System.out.println(cstList.customerAgeRange());
                    break;

                case "8":
                    System.out.println();
                    String item = Validator.getLine(sc, "Item to Search: ");
                    System.out.println("\nTotal number of " + item + "'s purchased: " + cstList.showTotalPurchasedItems(item));
                    break;

                case "9":
                    System.out.println();
                    int min = Validator.getInt(sc, "Minimum Age: ");
                    int max = Validator.getInt(sc, "Maximum Age: ");
                    List<Customer> customers = cstList.getAgeGroup(min, max);
                    for (Customer c : customers) {
                        System.out.println(c);
                    }
                    System.out.println(String.format(("\nCustomers in the age range %d to %d: %d"), min, max, customers.size()));
                    break;

                case "10":
                    System.out.println();
                    System.out.println("Average profits of all purchases: $" + cstList.showAvgProfits());
                    break;
            }
        }
    }

    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        new CustomerApp();
    }
}
