package controller;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import database.*;
import database.interfaces.*;
import model.*;

public class OrderController {
    
	/**
     * Fields for class OrderController
     */
    private OrderDBIF orderDBIF;
    private InvoiceDBIF invoiceDBIF;
    private Order order;

    /**
     * Constructor for class OrderController
     * @throws SQLException
     */
    public OrderController() throws SQLException {
        orderDBIF = new OrderDb();
        invoiceDBIF = new InvoiceDB();
    }

    /**
     * Lists all the orders in the database
     * @return All the orders in the database
     * @throws SQLException
     */
    public List<Order> findAll() throws SQLException {
        return orderDBIF.findAll();
    }

    /**
     * Finds an order by id
     * @param id The id of the order
     * @return The order with the id
     * @throws SQLException
     */
    public Order findOrderById(int id) throws SQLException {
        return orderDBIF.findOrderById(id);
    }

    /**
     * Creates a new order and adds it to the database
     * @param date The date when the order was created
     * @param customer The customer that placed the order
     * @throws SQLException
     */
    public void createOrder(Date date, Customer customer) throws SQLException {
        Order order = new Order(date, customer);
        orderDBIF.createOrder(order);
    }

    /*public void updateOrder(Order order, Date date, BigDecimal totalPrice, Customer customer) throws SQLException {
        order.setDate(date);
        order.setTotalPrice(totalPrice);
        order.setCustomer(customer);
        orderDBIF.updateOrder(order);
    }*/

    /**
     * Deletes an order from the database
     * @param order The order that will be deleted from the database
     * @throws SQLException
     */
    public void deleteOrder(Order order) throws SQLException {
        orderDBIF.deleteOrder(order);
    }

    /**
     * Creates an invoice for given order
     * @param order The order for the invoice
     * @return The created invoice 
     * @throws SQLException
     */
    public Invoice generateInvoice(Order order) throws SQLException {
        Invoice invoice = new Invoice(order.getId(), order, order.getDate(), order.getTotalPrice());
        invoiceDBIF.createInvoice(invoice);
        return invoice;
    }

    /**
     * Starts the order with given customer and the date
     * @param customer The customer of the order
     * @return New order
     */
    public Order startOrder(Customer customer) {
        Date date = new Date(System.currentTimeMillis());
        order = new Order(date, customer);
        return order;
    }

    /**
     * Calculates the total price of the given order
     * @param order The order to calculate the total price
     * @return total price of the order
     */
    public BigDecimal calculateTotal(Order order) {
        BigDecimal result = BigDecimal.valueOf(0);
        // get all orderLine of the order
        List<OrderLine> orderLines = order.getOrderLines();
        for (OrderLine orderLine : orderLines) {
            // increment price of every orderLine
            result.add(orderLine.getCalculatedPrice());
        }
        // set order total
        order.setTotalPrice(result);
        return result;
    }

    // same as createOrder()
    // does not create new order object, but gets it from constructor
    // forward order to orderDBIF
    public void finishOrder(Order order) throws SQLException {
        orderDBIF.createOrder(order);
    }

    public void createOrderLine(Order order, OrderLine orderLine) throws SQLException {
        orderDBIF.createOrderLine(order, orderLine);
    }
}
