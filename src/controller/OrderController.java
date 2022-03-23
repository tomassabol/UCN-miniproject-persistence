package controller;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import database.*;
import database.interfaces.*;
import model.*;

public class OrderController {
    
    private OrderDBIF orderDBIF;
    private InvoiceDBIF invoiceDBIF;

    public OrderController() throws SQLException {
        orderDBIF = new OrderDb();
        invoiceDBIF = new InvoiceDB();
    }

    public List<Order> findAll() throws SQLException {
        return orderDBIF.findAll();
    }

    public Order findOrderById(int id) throws SQLException {
        return orderDBIF.findOrderById(id);
    }

    public void createOrder(int id, Date date, BigDecimal totalPrice, Customer customer) throws SQLException {
        Order order = new Order(id, date, totalPrice, customer);
        orderDBIF.createOrder(order);
    }

    public void updateOrder(Order order, Date date, BigDecimal totalPrice, Customer customer) throws SQLException {
        order.setDate(date);
        order.setTotalPrice(totalPrice);
        order.setCustomer(customer);
        orderDBIF.updateOrder(order);
    }

    public void deleteOrder(Order order) throws SQLException {
        orderDBIF.deleteOrder(order);
    }

    public Invoice generateInvoice(Order order) throws SQLException {
        Invoice invoice = new Invoice(order.getId(), order, order.getDate(), order.getTotalPrice()); // figure out invoice id
        invoiceDBIF.createInvoice(invoice);
        return invoice;
    }
}
