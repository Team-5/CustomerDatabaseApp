package viewui;

import java.util.Scanner;
import model.Customer;
import model.ICustomerDAO;
import model.datastore.mysql.CustomerDAO;
import util.Validator;
//import model.datastore.file.CustomerDAO;

/**
 * CustomerApp is the starting point for running this console-oriented
 * menu-driven customer management program. This program demonstrates two
 * solutions. The first is file based and the second is MySQL based.
 *
 * @author Lisa Caswell and Jason Whiting
 * @version 2016-10-18
 */
public class CustomerApp {

    ICustomerDAO cstList = new CustomerDAO();
    Scanner sc = new Scanner(System.in);

    public CustomerApp() {
        menuLoop();
    }

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
            choice = Validator.getLine(sc, "Number of choice: ", "^[0-7]$");

            switch (choice) {
                case "1":
                    System.out.println("\n");
                    System.out.println(cstList.toString());
                    break;
                case "2":
                    System.out.println("\n");
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
                    System.out.println("\n");
                    id = Validator.getInt(sc, "Customer ID to Retrieve: ");
                    System.out.println(cstList.retrieveCustomerById(id));
                    break;
                case "4":
                    System.out.println("\n");
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
                    System.out.println("\n");
                    id = Validator.getInt(sc, "Customer ID to Delete: ");
                    System.out.println(cstList.retrieveCustomerById(id));
                    String ok = Validator.getLine(sc, "Delete this Customer? (y/n): ", "^[yYnN]$");
                    if (ok.equalsIgnoreCase("Y")) {
                        cstList.deleteCustomer(id);
                    }
                    break;
                case "6":
                    System.out.println("\n");
                    System.out.println("Total profit of all purchases: $" + cstList.showTotalProfits());
                    break;
                case "7":
                    System.out.println("\n");
                    System.out.println(cstList.customerAgeRange());
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
