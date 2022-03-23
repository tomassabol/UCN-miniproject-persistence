package database.interfaces;

import java.sql.SQLException;

import java.util.List;

import model.Customer;

public interface CustomerDBIF {
    public List<Customer> findAll() throws SQLException;
    public Customer findCustomerById() throws SQLException;
    public void createCustomer(Customer customer) throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(Customer customer) throws SQLException;
}
