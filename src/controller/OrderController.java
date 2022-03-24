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
    private Order order;

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

    public void createOrder(Date date, Customer customer) throws SQLException {
        Order order = new Order(date, customer);
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
        Invoice invoice = new Invoice(order.getId(), order, order.getDate(), order.getTotalPrice());
        invoiceDBIF.createInvoice(invoice);
        return invoice;
    }

    public Order startOrder(Customer customer) {
        Date date = new Date(System.currentTimeMillis());
        order = new Order(date, customer);
        return order;
    }

    public BigDecimal calculateTotal(Order order) {
        BigDecimal result = BigDecimal.valueOf(0);
        // get all orderline of the order
        List<OrderLine> orderLines = order.getOrderLines();
        for (OrderLine orderLine : orderLines) {
            // increment price of every orderline
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
}
