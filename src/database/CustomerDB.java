package database;

import java.sql.*;
import java.util.*;

import database.interfaces.*;
import model.Customer;

public class CustomerDB implements CustomerDBIF {
    private static final String FIND_ALL = "SELECT Id, Name, Address, City, Phone, Email, CustomerTypeId FROM Customers";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT Id, Name, Address, City, Phone, Email, CustomerTypeId FROM Customers WHERE Id=?";
    private static final String CREATE_CUSTOMER = "INSERT INTO Customers (Id, Name, Address, City, Phone, Email, CustomerTypeId) values(?, ?, ?, ?, ?, ?, ?) ";
    private static final String UPDATE_CUSTOMER = "UPDATE Customers SET Name = ?, Address = ?, City = ?, Phone = ?, Email = ?, CustomerTypeId = ? FROM Customers WHERE Id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customers WHERE Id = ?";

    private PreparedStatement findAll;
	private PreparedStatement findCustomerById;
	private PreparedStatement createCustomer;
	private PreparedStatement updateCustomer;
	private PreparedStatement deleteCustomer;

    public CustomerDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findCustomerById = DBConnection.getInstance().getConnection().prepareStatement(FIND_CUSTOMER_BY_ID);
        createCustomer = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CUSTOMER);
        updateCustomer = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_CUSTOMER);
        deleteCustomer = DBConnection.getInstance().getConnection().prepareStatement(DELETE_CUSTOMER);
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Customer> customers = buildObjects(rs);
        return customers;
    }

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

    @Override
    public void createCustomer(Customer customer) throws SQLException {
        createCustomer.setString(1, customer.getName());
        createCustomer.setString(2, customer.getAddress());
        createCustomer.setString(3, customer.getCity());
        createCustomer.setString(4, customer.getPhoneNumber());
        createCustomer.setString(5, customer.getEmail());
        createCustomer.execute();
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        updateCustomer.setString(1, customer.getName());
        updateCustomer.setString(2, customer.getAddress());
        updateCustomer.setString(3, customer.getCity());
        updateCustomer.setString(4, customer.getPhoneNumber());
        updateCustomer.setString(5, customer.getEmail());
        updateCustomer.execute();
    }

    @Override
    public void deleteCustomer(Customer customer) throws SQLException {
        deleteCustomer.setInt(1, customer.getId());
        deleteCustomer.execute();
    }

    private Customer buildObject(ResultSet rs) throws SQLException {
        Customer customer = new Customer(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"), rs.getString("City"), rs.getString("Phone"), rs.getString("Email"));
        return customer;
    }
    private List<Customer> buildObjects(ResultSet rs) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while(rs.next()) {
            customers.add(buildObject(rs));
        }
        return customers;
    }
    
}
