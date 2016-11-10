package model;

import java.util.List;

/**
 * The "ICustomerDAO" is the interface for the Employee Data Access Object. The
 * interface defines the methods that will be used in all DAO implementations
 * for this application. This program has both a file and database DAO
 * implementation. However, the application code does not care which is used as
 * everything is designed through this interface.
 *
 * @authors Lucas Johns and Jason Whiting
 * @version 2016-11-10
 */
public interface ICustomerDAO {

    void createCustomer(Customer customer);

    Customer retrieveCustomerById(int id);

    List<Customer> retrieveAllCustomers();

    void updateCustomer(Customer updatedCustomer);

    void deleteCustomer(int id);

    void deleteCustomer(Customer customer);

    List getAgeGroup(int minAge, int maxAge);

    double showTotalProfits();

    double showAvgProfits();

    String customerAgeRange();

    double showTotalPurchasedItems();

    @Override
    String toString();
}
