package database;

import java.sql.*;
import java.util.*;

import database.interfaces.CustomerTypeDBIF;
import model.CustomerType;

public class CustomerTypeDB implements CustomerTypeDBIF {
	
	private static final String FIND_ALL = "SELECT Id, Name, Discount FROM CustomerTypes ";
	private static final String FIND_CUSTOMERTYPE_BY_ID = "SELECT [Id],[Name],[Discount] FROM CustomerTypes WHERE Id=?";
	private static final String CREATE_CUSTOMERTYPE = "INSERT INTO CustomerTypes (Name, Discount) values(?, ?) ";
	private static final String UPDATE_CUSTOMERTYPE = "UPDATE CustomerTypes SET Name = ?, Discount = ? FROM CustomerTypes WHERE Id = ?";
	private static final String DELETE_CUSTOMERTYPE = "DELETE FROM CustomerTypes WHERE Id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findCustomerTypeById;
	private PreparedStatement createCustomerType;
	private PreparedStatement updateCustomerType;
	private PreparedStatement deleteCustomerType;
	
	public CustomerTypeDB() throws SQLException{
		
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
		findCustomerTypeById =DBConnection.getInstance().getConnection().prepareStatement(FIND_CUSTOMERTYPE_BY_ID);
		createCustomerType = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CUSTOMERTYPE);
		updateCustomerType = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_CUSTOMERTYPE);
		deleteCustomerType = DBConnection.getInstance().getConnection().prepareStatement(DELETE_CUSTOMERTYPE);
	}
	
	@Override
	public List<CustomerType> findAll() throws SQLException {
	ResultSet rs;
	rs = findAll.executeQuery();
	List<CustomerType> customerType = buildObjects(rs);
	return customerType;
	}
	
	@Override
	public CustomerType findCustomerTypeById(int id) throws SQLException {
        CustomerType customerType = null;
        ResultSet rs;
        findCustomerTypeById.setInt(1, id);
        rs = findCustomerTypeById.executeQuery();
        rs.next();
        customerType = buildObject(rs);
        return customerType;
    }
	
	@Override
	public void createCustomerType(CustomerType customerType) throws SQLException {
		createCustomerType.setString(1, customerType.getName());
		createCustomerType.setInt(2, customerType.getDiscount());
		createCustomerType.execute();
	}
	
	@Override
	public void updateCustomerType(CustomerType customerType) throws SQLException {
		updateCustomerType.setString(1, customerType.getName());
		updateCustomerType.setInt(2, customerType.getDiscount());
		updateCustomerType.setInt(3, customerType.getId());
		updateCustomerType.execute();
	}
	
	@Override
	public void deleteCustomerType(CustomerType customerType) throws SQLException {
		deleteCustomerType.setInt(1, customerType.getId());
		deleteCustomerType.execute();
	}
	
	private CustomerType buildObject(ResultSet rs) throws SQLException {
		CustomerType customerType = new CustomerType(rs.getInt("Id"), rs.getString("Name"), rs.getInt("Discount"));
		return customerType;
	}
	
	private List<CustomerType> buildObjects(ResultSet rs) throws SQLException {
		List<CustomerType> customerType = new ArrayList<>();
		while(rs.next()) {
			customerType.add(buildObject(rs));
		}
		return customerType;
	}

}
