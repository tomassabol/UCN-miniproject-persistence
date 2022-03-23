package database.interfaces;

import java.sql.*;
import java.util.*;
import model.*;

public interface CustomerTypeDBIF{
	public List<CustomerType> findAll() throws SQLException;
	public CustomerType findCustomerTypeById(int id) throws SQLException;
	public void createCustomerType(CustomerType customerType) throws SQLException;
	public void updateCustomerType(CustomerType customerType) throws SQLException;
	public void deleteCustomerType(CustomerType customerType) throws SQLException;
}
