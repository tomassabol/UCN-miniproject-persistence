package view;

import java.math.BigDecimal;
import java.sql.SQLException;

import controller.ProductController;
import model.Product;
import model.Supplier;




public class ProductMenu {
    
	/**
	 * Fields for class ProductMenu
	 */
    private ProductController productCtrl;
    private Input input;
    private SupplierMenu supplierMenu;

    /**
     * Constructor for class ProductMenu
     * @throws SQLException
     */
    public ProductMenu() throws SQLException {
        productCtrl = new ProductController();
        supplierMenu = new SupplierMenu();
        input = new Input();
    }

    /**
     * Prints out the menu and asks for a user input
     * @throws SQLException
     */
    public void run() throws SQLException {
        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Back to main menu: ", 0);
        menu.addOption("Add product: ", 1);
        menu.addOption("List all products: ", 2);
        menu.addOption("View product: ", 3);
        menu.addOption("Update product: ", 4);
        menu.addOption("Delete product: ", 5);
        System.out.println();

        while(conTinUe) {
            int choice;
            choice = menu.input("Product menu - Choose option:", false);
            switch(choice) {
                case 1: {
                	createProduct();
                    break;
                }
                case 2: {
                	listAllProducts();
                    break;
                }
                case 3: {
                	findProductById();
                    break;
                }
                case 4: {
                	updateProduct();
                    break;
                }
                case 5: {
                	deleteProduct();
                    break;
                }
                case 0: {
                	conTinUe = false;
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
    
    /**
     * Creates product
     * @throws SQLException
     */
    public void createProduct() throws SQLException{
    	clearLines();
    	String name = input.stringInput("Enter the product name: ");
    	BigDecimal price = input.bigDecimalInput("Enter the price of the product: ");
    	Supplier supplier = supplierMenu.findSupplierById();
    	
    	productCtrl.createProduct(name, price, supplier);
    }
    
    /**
     * Lists all products from the database
     * @throws SQLException
     */
    public void listAllProducts() throws SQLException{
    	clearLines();
    	
    	for(Product product : productCtrl.findAll()) {
    		System.out.print(product.toString());
    	}
    }
    
    /**
     * Finds product with given id
     * @return product with given id
     * @throws SQLException
     */
    public Product findProductById() throws SQLException{
    	listAllProducts();
    	int id = input.integerInput("Enter product`s id:");
    	Product product = productCtrl.findProductById(id);
    	System.out.println(product.toString());
    	return product;
    }
    
    /**
     * Updates information about product 
     * @throws SQLException
     */
    public void updateProduct() throws SQLException{
    	listAllProducts();
    	//Supplier supplier = supplierMenu.findSupplierById();
    	Product product = findProductById();
    	String name = input.stringInput("Enter the product name: ");
    	BigDecimal price = input.bigDecimalInput("Enter the price of the product: ");
    	Supplier supplier = supplierMenu.findSupplierById();
    	
    	
    	productCtrl.updateProduct(product, name, price, supplier);
    	
    }
    
    /**
     * Deletes product
     * @throws SQLException
     */
    public void deleteProduct() throws SQLException{
    	Product product = findProductById();
    	productCtrl.deleteProduct(product);
    }
    
    /**
     * Prints some lines for better visibility
     */
    public void clearLines() {
    	for(int i = 0; i<10; i++) {
    		System.out.println();
    	}
    }
    
}

