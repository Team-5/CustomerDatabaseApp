package model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import model.Customer;
import model.ICustomerDAO;

/**
 * CustomerDAO (Data Access Object) handles all the interactions with the data
 * store. This version uses a file to store the data. It is not multi-user safe.
 *
 * @author Jason Whiting
 * @version 2016-10-20
 */
public class CustomerDAO implements ICustomerDAO {

    protected String fileName = null;
    protected final List<Customer> myList;

    public CustomerDAO() {

        Properties props = new Properties();

        try {
            props.load(new FileInputStream("res/file/db.properties"));
            this.fileName = props.getProperty("DB_FILENAME");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        this.myList = new ArrayList<>();

        try {
            Files.createFile(Paths.get(fileName));
        } catch (FileAlreadyExistsException fae) {
            ;
        } catch (IOException ioe) {
            System.out.println("Create file error with " + ioe.getMessage());
        }

        readList();
    }

    @Override
    public void createCustomer(Customer customer) {
        myList.add(customer);
        writeList();
    }

    @Override
    public Customer retrieveCustomerById(int id) {
        for (Customer customer : myList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> retrieveAllCustomers() {
        return myList;
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {
        for (Customer customer : myList) {
            if (customer.getId() == updatedCustomer.getId()) {
                customer.setFirstName(updatedCustomer.getFirstName());
                customer.setLastName(updatedCustomer.getLastName());
                customer.setAge(updatedCustomer.getAge());
                customer.setState(updatedCustomer.getState());
                customer.setPurchase(updatedCustomer.getPurchase());
                customer.setPrice(updatedCustomer.getPrice());
                break;
            }
        }
        writeList();
    }

    @Override
    public void deleteCustomer(int id) {
        for (Customer customer : myList) {
            if (customer.getId() == id) {
                myList.remove(customer);
                break;
            }
        }
        writeList();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        myList.remove(customer);
        writeList();
    }

    private void readList() {

        Path path = Paths.get(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName = data[2];
                int age = Integer.parseInt(data[3]);
                String state = data[4];
                String purchase = data[5];
                double price = Double.parseDouble(data[6]);
                Customer customer = new Customer(id, firstName, lastName, age, state, purchase, price);
                myList.add(customer);
            }
        } catch (IOException ioe) {
            System.out.println("Read file error with " + ioe.getMessage());
        }
    }

    private void writeList() {

        Path path = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Customer customer : myList) {
                writer.write(String.format("%d,%s,%s,%d,%s,%s,%d",
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getAge(),
                        customer.getState(),
                        customer.getPurchase(),
                        customer.getPrice()));
            }
        } catch (IOException ioe) {
            System.out.println("Write file error with " + ioe.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Customer customer : myList) {
            sb.append(String.format("%5d : %s, %s, %3d, %s, %s, %5.2",
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getAge(),
                    customer.getState(),
                    customer.getPurchase(),
                    customer.getPrice()));
        }
        return sb.toString();
    }

    @Override
    public List getAgeGroup(int minAge, int maxAge) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double showTotalProfits() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public double showAvgProfits() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String customerAgeRange() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public double showTotalPurchasedItems(String item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
