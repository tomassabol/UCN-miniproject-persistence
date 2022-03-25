package tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.*;
import database.DBConnection;
import model.*;

public class TestCreateOrder {
    
    OrderController orderCtrl;
    StorageLineController storageLineCtrl;
    CustomerController customerCtrl;
    ProductController productCtrl;


    @Before
    public void setUp() throws SQLException {
        orderCtrl = new OrderController();
        storageLineCtrl = new StorageLineController();
        customerCtrl = new CustomerController();
        productCtrl = new ProductController();
    }

    @Test
    public void testCreateOrder() throws SQLException {
        // Arrange
        Customer customer = customerCtrl.findCustomerById(1);
        Product product = productCtrl.findProductById(1);
        OrderLine orderLine = new OrderLine(product, 1);

        // Act
        Order order = orderCtrl.startOrder(customer);
        order.addOrderLine(orderLine);
        orderLine.calculateOrderLinePrice();
        orderCtrl.calculateTotal(order);
        orderCtrl.finishOrder(order);

        // Assert
        assertNotNull(orderCtrl.findOrderById(1));
    }

    @After
    public void cleanUp() {
        DBConnection.disconnect();
    }
}
