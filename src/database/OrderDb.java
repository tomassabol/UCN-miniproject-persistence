package database;

import java.util.*;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import java.sql.*;
import database.interfaces.OrderDBIF;
import model.*;

public class OrderDb implements OrderDBIF{

    private static final String FIND_ALL = "SELECT Id, Date, TotalPrice, Customerid FROM Orders";
    private static final String FIND_ORDER_BY_ID = "SELECT Id, Date, TotalPrice, Customerid FROM Orders WHERE Id=?";
    private static final String CREATE_ORDER = "INSERT INTO Orders (Id, Date, TotalPrice, Customerid) values(?, ?, ?, ?) ";
    private static final String UPDATE_ORDER = "UPDATE Orders SET Date = ?, TotalPrice = ?, Customerid = ? FROM Orders WHERE ID = ?"; 
    private static final String DELETE_ORDER = "DELETE FROM Orders WHERE Id = ?";

    private PreparedStatement findAll;
	private PreparedStatement findOrderById;
	private PreparedStatement createOrder;
	private PreparedStatement updateOrder;
	private PreparedStatement deleteOrder;

    OrderDb()throws SQLException{
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
	    findOrderById = DBConnection.getInstance().getConnection().prepareStatement(FIND_ORDER_BY_ID);
	    createOrder = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER);
	    updateOrder = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ORDER);
	    deleteOrder = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDER);
    }

    @Override
    public List<Order> findAll() throws SQLException{
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Order> orders = buildObjects(rs);
        return orders;
    }

    @Override
    public Order findOrderById(int id) throws SQLException{
        Order order = null;
        ResultSet rs;
        findOrderById.setInt(1, id);
        rs = findOrderById.executeQuery();
        rs.next();
        order = buildObject(rs);
        return order;
    }

    @Override
    public void createOrder(Order order) throws SQLException{
        createOrder.setDate(1, order.getDate());
        createOrder.setBigDecimal(2, order.getTotalPrice());
        createOrder.setInt(3, order.getCustomer().getId());
        createOrder.execute();
    }

    @Override
    public void updateOrder(Order order) throws SQLException{
        updateOrder.setDate(1, order.getDate());
        updateOrder.setBigDecimal(2, order.getTotalPrice());
        updateOrder.setInt(3, order.getCustomer().getId());
        updateOrder.execute();
    }

    @Override
    public void deleteOrder(Order order) throws SQLException{
        deleteOrder.setInt(1, order.getId());
        deleteOrder.execute();
    }
     
    private Order buildObject(ResultSet rs) throws SQLException {
        CustomerController custCont = new CustomerController();
        Customer customer = custCont.findCustomerById(rs.getInt("Id"));

        Order order = new Order(rs.getInt("Id"), rs.getDate("Date"), rs.getBigDecimal("Totalprice"), customer);
    }

    private List<Order> buildObjects(ResultSet rs) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while(rs.next()) {
            orders.add(buildObject(rs));
        }
        return orders;
    }
}
