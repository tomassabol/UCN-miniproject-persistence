package database;

import java.sql.*;
import java.util.*;

import controller.CustomerTypeController;
import database.interfaces.*;
import model.Customer;
import model.CustomerType;

public class CustomerDB implements CustomerDBIF {
	
	/**
	 * Prepared statements for class CustomerDB
	 */
    private static final String FIND_ALL = "SELECT [Id],[Name],[Address],[City],[Phone],[Email],[CustomerTypeId] FROM Customers";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT [Id],[Name],[Address],[City],[Phone],[Email] FROM Customers WHERE Id=?";
    private static final String CREATE_CUSTOMER = "INSERT INTO Customers (Name, Address, City, Phone, Email) values(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER = "UPDATE Customers SET Name = ?, Address = ?, City = ?, Phone = ?, Email = ? FROM Customers WHERE Id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customers WHERE Id = ?";

    /**
     * Fields for class CustomerDB
     */
    private PreparedStatement findAll;
	private PreparedStatement findCustomerById;
	private PreparedStatement createCustomer;
	private PreparedStatement updateCustomer;
	private PreparedStatement deleteCustomer;

	 /**
     * Constructor for class CustomerDB
     * @throws SQLException
     */
    public CustomerDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findCustomerById = DBConnection.getInstance().getConnection().prepareStatement(FIND_CUSTOMER_BY_ID);
        createCustomer = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CUSTOMER);
        updateCustomer = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_CUSTOMER);
        deleteCustomer = DBConnection.getInstance().getConnection().prepareStatement(DELETE_CUSTOMER);
    }

    /**
     * Lists all customers in the database
     * @return list of customers
     * @throws SQLException
     */
    @Override
    public List<Customer> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Customer> customers = buildObjects(rs);
        return customers;
    }

    /**
     * Finds customer by id
     * @param id The id of the customer
     * @return customer with given id
     * @throws SQLException
     */
    @Override
    public Customer findCustomerById(int id) throws SQLException {
        Customer customer = null;
        ResultSet rs;
        findCustomerById.setInt(1, id);
        rs = findCustomerById.executeQuery();
        rs.next();
        customer = buildObject(rs);
        return customer;
    }

    /**
     * Creates new customer and adds it to the database
     * @param customer The customer to be added to the database
     * @throws SQLException
     */
    @Override
    public void createCustomer(Customer customer) throws SQLException {
        createCustomer.setString(1, customer.getName());
        createCustomer.setString(2, customer.getAddress());
        createCustomer.setString(3, customer.getCity());
        createCustomer.setString(4, customer.getPhoneNumber());
        createCustomer.setString(5, customer.getEmail());
        createCustomer.setInt(6, customer.getCustomerType().getId());
        createCustomer.execute();
    }

    /**
     * Updates the information of the customer
     * @param customer The customer to be updated
     * @throws SQLException
     */
    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        updateCustomer.setString(1, customer.getName());
        updateCustomer.setString(2, customer.getAddress());
        updateCustomer.setString(3, customer.getCity());
        updateCustomer.setString(4, customer.getPhoneNumber());
        updateCustomer.setString(5, customer.getEmail());
        updateCustomer.setInt(6, customer.getCustomerType().getId());
        updateCustomer.execute();
    }

    /**
     * Deletes a customer form the database
     * @param customer The customer to be deleted
     */
    @Override
    public void deleteCustomer(Customer customer) throws SQLException {
        deleteCustomer.setInt(1, customer.getId());
        deleteCustomer.execute();
    }

    /**
     * Builds the object based on the info from the database
     * @param rs Result set 
     * @return built customer object with information from the rs
     * @throws SQLException
     */
    private Customer buildObject(ResultSet rs) throws SQLException {
        // build customer type object
        CustomerTypeController customerTypeCtrl = new CustomerTypeController();
        CustomerType customerType;
        customerType = customerTypeCtrl.findCustomerTypeById(rs.getInt("CustomerTypeId"));

        // build customer object
        Customer customer = new Customer(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"), rs.getString("City"), rs.getString("Phone"), rs.getString("Email"), customerType);
        return customer;
    }
    
    /**
     * Builds objects based on the info from the database
     * @param rs Result set
     * @return list of built customer objects
     * @throws SQLException
     */
    private List<Customer> buildObjects(ResultSet rs) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while(rs.next()) {
            customers.add(buildObject(rs));
        }
        return customers;
    }
    
}
