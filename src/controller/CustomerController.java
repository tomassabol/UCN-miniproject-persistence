package controller;

import java.sql.SQLException;
import java.util.List;

import database.CustomerDB;
import database.interfaces.CustomerDBIF;
import model.Customer;
import model.CustomerType;

public class CustomerController {
    
    /**
     * Fields for class CustomerController
     */
    private CustomerDBIF customerDBIF;

    /**
     * Constructor for class CustomerController
     * @throws SQLException
     */
    public CustomerController() throws SQLException {
        customerDBIF = new CustomerDB();
    }

    /**
     * Lists all the customers in the database
     * @return All the customers in the database
     * @throws SQLException
     */
    public List<Customer> findAll() throws SQLException {
        return customerDBIF.findAll();
    }

    /**
     * Finds a customer by id
     * @param id The id of the customer
     * @return The customer with the id
     * @throws SQLException
     */
    public Customer findCustomerById(int id) throws SQLException {
        return customerDBIF.findCustomerById(id);
    }

    /**
     * Creates a new customer and adds it to the database
     * @param name The name of the new Customer
     * @param address The address of the new Customer
     * @param city The city that the new Customer lives in
     * @param phoneNumber The phone number of the new Customer
     * @param email The email of the new Customer
     * @throws SQLException
     */
    public void createCustomer(String name, String address, String city, String phoneNumber, String email, CustomerType customerType) throws SQLException {
        Customer customer = new Customer(name, address, city, phoneNumber, email, customerType);
        customerDBIF.createCustomer(customer);
    }

    /**
     * Deletes a customer from the database
     * @param customer The customer that will be deleted from the database
     * @throws SQLException
     */
    public void deleteCustomer(Customer customer) throws SQLException {
        customerDBIF.deleteCustomer(customer);
    }

    /**
     * Updates the information of a customer
     * @param customer The customer whose information will be updated
     * @param name The new name of the customer
     * @param address The new address of the customer
     * @param city The new city of the customer
     * @param phoneNumber The new phone number of the customer
     * @param email The new email of the customer
     * @throws SQLException
     */
    public void updateCustomer(Customer customer, String name, String address, String city, String phoneNumber, String email, CustomerType customerType) throws SQLException {
        customer.setName(name);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setCustomerType(customerType);
        customerDBIF.updateCustomer(customer);
    }
}
