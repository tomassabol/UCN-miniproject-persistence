package controller;

import java.sql.SQLException;
import java.util.List;

import database.CustomerTypeDB;
import database.interfaces.CustomerTypeDBIF;
import model.CustomerType;

public class CustomerTypeController {
    
    /**
     * Fields for class CustomerTypeController
     */
    private CustomerTypeDBIF customerTypeDBIF;

    /**
     * Constructor for class CustomerTypeController
     * @throws SQLException
     */
    public CustomerTypeController() throws SQLException {
       customerTypeDBIF = new CustomerTypeDB(); 
    }

    /**
     * Lists all the customer types in the database
     * @return All the CustomerTypes
     * @throws SQLException
     */
    public List<CustomerType> findAll() throws SQLException {
        return customerTypeDBIF.findAll();
    }

    /**
     * Finds a customer type by id
     * @param id The id of the CustomerType
     * @return The CustomerType with the id parameter
     * @throws SQLException
     */
    public CustomerType findCustomerTypeById(int id) throws SQLException {
        return customerTypeDBIF.findCustomerTypeById(id);
    }

    /**
     * Creates a new CustomerType and adds it to the database
     * @param id The id of the new CustomerType
     * @param name The name of the new CustomerType
     * @param discount The discount that the new CustomerType will have
     * @throws SQLException
     */
    public void createCustomertype(int id, String name, int discount) throws SQLException {
        CustomerType customerType = new CustomerType(id, name, discount);
        customerTypeDBIF.createCustomerType(customerType);
    }

    /**
     * Deletes a CustomerType from the database
     * @param customerType The Customertype that gets deleted
     * @throws SQLException
     */
    public void deleteCustomerType(CustomerType customerType) throws SQLException {
        customerTypeDBIF.deleteCustomerType(customerType);
    }

    /**
     * Updates a CustomerTypes information
     * @param customerType
     * @param name
     * @param discount
     * @throws SQLException
     */
    public void updateCustomerType(CustomerType customerType, String name, int discount) throws SQLException {
        customerType.setName(name);
        customerType.setDiscount(discount);
        customerTypeDBIF.updateCustomerType(customerType);
    }
}
