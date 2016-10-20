package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.ICustomerDAO;

/**
 * CustomerDAO (Data Access Object) handles all the interactions with the data
 * store. This version uses a MySQL database to store the data. It is multi-user
 * safe.
 *
 * @author Willie Scott, Jeremy Wiles, and Jason Whiting
 * @version 2016-10-20
 */
public class CustomerDAO implements ICustomerDAO {

    protected final static boolean DEBUG = true;

    @Override
    public void createCustomer(Customer customer) {
        final String QUERY = "insert into customer "
                + "(id, firstName, lastName, age, homeState, purchase, price) "
                + "VALUES (null, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(QUERY);) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setInt(3, customer.getAge());
            stmt.setString(4, customer.getState());
            stmt.setString(5, customer.getPurchase());
            stmt.setDouble(6, customer.getPrice());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("createCustomer SQLException: " + ex.getMessage());
        }
    }

    @Override
    public Customer retrieveCustomerById(int id) {

        final String QUERY = "select id, firstName, lastName, age, homeState, purchase, price "
                + "from customer where id = " + id;
        Customer cst = null;

        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(QUERY)) {

            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            ResultSet rs = stmt.executeQuery(QUERY);

            if (rs.next()) {
                cst = new Customer(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("homeState"),
                        rs.getString("purchase"),
                        rs.getDouble("price"));
            }
        } catch (SQLException ex) {
            System.out.println("retrieveCustomerById SQLException: " + ex.getMessage());
        }
        return cst;
    }

    @Override
    public List<Customer> retrieveAllCustomers() {

        final List<Customer> myList = new ArrayList<>();
        final String QUERY = "select id, firstName, lastName, age, homeState, purchase, price "
                + "from customer";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                myList.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("homeState"),
                        rs.getString("purchase"),
                        rs.getDouble("price")));
            }
        } catch (SQLException ex) {
            System.out.println("retrieveAllCustomers SQLException: " + ex.getMessage());
        }
        return myList;
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {

        final String QUERY = "update customer set firstName=?, lastName=?, "
                + "age=?, homeState=?, purchase=?, price=? where id=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(QUERY);) {
            stmt.setString(1, updatedCustomer.getFirstName());
            stmt.setString(2, updatedCustomer.getLastName());
            stmt.setInt(3, updatedCustomer.getAge());
            stmt.setString(4, updatedCustomer.getState());
            stmt.setString(5, updatedCustomer.getPurchase());
            stmt.setDouble(6, updatedCustomer.getPrice());
            stmt.setInt(7, updatedCustomer.getId());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("updateCustomer SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void deleteCustomer(int id) {

        final String QUERY = "delete from customer where id = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setInt(1, id);
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("deleteRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void deleteCustomer(Customer customer) {

        final String QUERY = "delete from customer where id = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setInt(1, customer.getId());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("deleteRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Customer customer : retrieveAllCustomers()) {
            sb.append(customer.toString()).append("\n");
        }
        return sb.toString();
    }
}
