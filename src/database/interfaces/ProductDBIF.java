package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Product;

public interface ProductDBIF {
    public List<Product> findAll() throws SQLException;
    public Product findProductById(int id) throws SQLException;
    public void createProduct(Product product) throws SQLException;
    public void updateProduct(Product product) throws SQLException;
    public void deleteProduct(Product product) throws SQLException;
}
