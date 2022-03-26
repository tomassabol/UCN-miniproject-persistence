package database;

import java.util.*;

import controller.CustomerController;

import java.sql.*;
import database.interfaces.OrderDBIF;
import model.*;

public class OrderDb implements OrderDBIF {

	/**
	 * Prepared statements for class OrderDB
	 */
    private static final String FIND_ALL = "SELECT Id, Date, CustomerId FROM Orders";
    private static final String FIND_ORDER_BY_ID = "SELECT Id, Date, Customerid FROM Orders WHERE Id=?";
    private static final String CREATE_ORDER = "INSERT INTO Orders (Date, CustomerId) values(?, ?) ";
    private static final String DELETE_ORDER = "DELETE FROM Orders WHERE Id = ?";
    private static final String CREATE_ORDERLINE = "INSERT INTO OrderProducts (ProductId, Quantity, OrderId) values (?, ?, ?) ";

    /**
     * Fields for class OrderDB
     */
    private PreparedStatement findAll;
	private PreparedStatement findOrderById;
	private PreparedStatement createOrder;
	private PreparedStatement deleteOrder;
    private PreparedStatement createOrderLine;

    /**
     * Constructor for class OrderDB
     * @throws SQLException
     */
    public OrderDb() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
	    findOrderById = DBConnection.getInstance().getConnection().prepareStatement(FIND_ORDER_BY_ID);
	    createOrder = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER);
	    deleteOrder = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDER);
        createOrderLine = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDERLINE);
    }

    /**
     * Lists all orders in the database
     * @return list of orders
     * @throws SQLException
     */
    @Override
    public List<Order> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Order> orders = buildObjects(rs);
        return orders;
    }

    /**
     * Finds order by id
     * @param id The id of the order
     * @return order with given id
     * @throws SQLException
     */
    @Override
    public Order findOrderById(int id) throws SQLException {
        Order order = null;
        ResultSet rs;
        findOrderById.setInt(1, id);
        rs = findOrderById.executeQuery();
        rs.next();
        order = buildObject(rs);
        return order;
    }

    /**
     * Creates new order and adds it to the database
     * @param order The order to be added to the database
     * @throws SQLException
     */
    @Override
    public void createOrder(Order order) throws SQLException {
        createOrder.setDate(1, order.getDate());
        createOrder.setInt(2, order.getCustomer().getId());
        createOrder.execute();
    }

    /**
     * Creates new order line and adds it to the database
     * @param order The order to be added to the orderLine
     * @param orderLine The order line to be added to the database
     * @throws SQLException
     */
    @Override
    public void createOrderLine(Order order, OrderLine orderLine) throws SQLException {
        createOrderLine.setInt(1, orderLine.getProduct().getId());
        createOrderLine.setInt(2, orderLine.getQuantity());
        createOrderLine.setInt(3, order.getId());
        createOrderLine.execute();
    }

    /*
    @Override
    public void updateOrder(Order order) throws SQLException {
        int id;
        updateOrder.setDate(1, order.getDate());
        updateOrder.setBigDecimal(2, order.getTotalPrice());
        updateOrder.setInt(3, order.getCustomer().getId());
        id = DBConnection.getInstance().executeUpdate(updateOrder);
    }
    */

    /**
     * Deletes an order form the database
     * @param order The order to be deleted
     */
    @Override
    public void deleteOrder(Order order) throws SQLException {
        deleteOrder.setInt(1, order.getId());
        deleteOrder.execute();
    }
     
    /**
     * Builds the object based on the info from the database
     * @param rs Result set 
     * @return built order object with information from the rs
     * @throws SQLException
     */
    private Order buildObject(ResultSet rs) throws SQLException {
        CustomerController custCont = new CustomerController();
        Customer customer = custCont.findCustomerById(rs.getInt("CustomerId"));

        Order order = new Order(rs.getInt("Id"), rs.getDate("Date"), customer);
        return order;
    }

    /**
     * Builds objects based on the info from the database
     * @param rs Result set
     * @return list of built order objects
     * @throws SQLException
     */
    private List<Order> buildObjects(ResultSet rs) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while(rs.next()) {
            orders.add(buildObject(rs));
        }
        return orders;
    }
}
