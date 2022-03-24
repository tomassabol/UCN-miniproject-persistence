package database;

import java.util.*;

import controller.*;

import java.sql.*;

import database.interfaces.ProductDBIF;
import model.*;

public class ProductDB implements ProductDBIF {

    private static final String FIND_ALL = "SELECT Id, Name, Price, SupplierId FROM Products";
    private static final String FIND_PRODUCT_BY_ID = "SELECT Id, Name, Price, SupplierId FROM Products WHERE Id = ?";
    private static final String CREATE_PRODUCT = "INSERT INTO Products (Name, Price, SupplierId) values(?, ?, ?) ";
    private static final String UPDATE_PRODUCT = "UPDATE Products SET Name = ?, Price = ?, SupplierId = ? FROM Products WHERE Id = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM Products WHERE Id = ?";

    private PreparedStatement findAll;
	private PreparedStatement findProductById;
	private PreparedStatement createProduct;
	private PreparedStatement updateProduct;
	private PreparedStatement deleteProduct;

    public ProductDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
	    findProductById = DBConnection.getInstance().getConnection().prepareStatement(FIND_PRODUCT_BY_ID);
	    createProduct = DBConnection.getInstance().getConnection().prepareStatement(CREATE_PRODUCT);
	    updateProduct = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_PRODUCT);
	    deleteProduct = DBConnection.getInstance().getConnection().prepareStatement(DELETE_PRODUCT);
    }

    @Override
    public List<Product> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Product> products = buildObjects(rs);
        return products;
    }

    @Override
    public Product findProductById(int id) throws SQLException {
        Product product = null;
        ResultSet rs;
        findProductById.setInt(1, id);
        rs = findProductById.executeQuery();
        rs.next();
        product = buildObject(rs);
        return product;
    }

    @Override
    public void createProduct(Product product) throws SQLException {
        createProduct.setString(1, product.getName());
        createProduct.setBigDecimal(2, product.getPrice());
        createProduct.setInt(3, product.getSupplier().getId());
        createProduct.execute();        
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        updateProduct.setString(1, product.getName());
        updateProduct.setBigDecimal(2, product.getPrice());
        updateProduct.setInt(3, product.getSupplier().getId());
        updateProduct.execute();
    }

    @Override
    public void deleteProduct(Product product) throws SQLException {
        deleteProduct.setInt(1, product.getId());
        deleteProduct.execute();
    }

    private Product buildObject(ResultSet rs) throws SQLException {
        SupplierController supplierCtrl = new SupplierController();
        Supplier supplier = supplierCtrl.findSupplierById(rs.getInt("SupplierId"));

        Product product = new Product(rs.getInt("Id"), rs.getString("Name"), rs.getBigDecimal("Price"), supplier);
        return product;
    }

    private List<Product> buildObjects(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while(rs.next()) {
            products.add(buildObject(rs));
        }
        return products;
    }
    
}
