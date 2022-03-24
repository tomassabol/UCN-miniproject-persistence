package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import database.ProductDB;
import database.interfaces.ProductDBIF;
import model.Product;
import model.Supplier;

public class ProductController {
	
	/**
	 * Fields for class ProductController
	 */
	private ProductDBIF productDBIF;
	private SupplierController supplierCtrl;
	
	/**
	 * Constructor for class ProductController
	 * @throws SQLException 
	 */
	public ProductController() throws SQLException {
		productDBIF = new ProductDB();
		supplierCtrl = new SupplierController();
	}
	
	/**
	 * Lists all the Products in the database
	 * @return all the products in the database
	 * @throws SQLException
	 */
	public List<Product> findAll() throws SQLException {
		return productDBIF.findAll();
	}
	
	/**
	 * Returns a product by its id
	 * @param id The id of a product
	 * @return the product with the id parameter
	 * @throws SQLException
	 */
	public Product findProductById(int id) throws SQLException {
		return productDBIF.findProductById(id);
	}
	
	/**
	 * Creates a product and adds it to the database
	 * @param name The name of the new product
	 * @param price The price of the new product
	 * @param supplier The supplier who supplies the new product
	 * @return The newly created product
	 * @throws SQLException
	 */
	public Product createProduct(String name, BigDecimal price, Supplier supplier) throws SQLException {
		Product product = new Product(name, price, supplier);
		productDBIF.createProduct(product);
		return product;
	}
	
	/**
	 * Deletes a product from the database
	 * @param product The product that will be deleted
	 * @throws SQLException
	 */
	public void deleteProduct(Product product) throws SQLException {
		productDBIF.deleteProduct(product);
	}
	
	/**
	 * Updates a products attributes
	 * @param product The product that will be updated
	 * @param name The new name of the product
	 * @param price The new price of the product
	 * @param supplier The new supplier of the product
	 * @throws SQLException
	 */
	public void updateProduct(Product product, String name, BigDecimal price, Supplier supplier) throws SQLException {
		product.setName(name);
		product.setPrice(price);
		product.setSupplier(supplier);
		productDBIF.updateProduct(product);
	}
	
	public Supplier findSupplierById(int id) throws SQLException {
		return supplierCtrl.findSupplierById(id);
	}
	
}
