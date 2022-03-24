package database.interfaces;

import model.Order;
import java.sql.SQLException;
import java.util.List;

public interface OrderDBIF {
    public List<Order> findAll() throws SQLException;
    public Order findOrderById(int id) throws SQLException;
    public void createOrder(Order order) throws SQLException;
    // public void updateOrder(Order order) throws SQLException;
    public void deleteOrder(Order order) throws SQLException;    
}
