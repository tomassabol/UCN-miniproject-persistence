package view;

import java.math.BigDecimal;
import java.sql.SQLException;

import controller.ProductController;
import model.Product;
import model.Supplier;
import controller.SupplierController;



public class ProductMenu {
    
	/**
	 * Fields for class ProductMenu
	 */
    private ProductController productCtrl;
    private Input input;
    private SupplierController supplierCtrl;
    private SupplierMenu supplierMenu;

    /**
     * Constructor for class ProductMenu
     * @throws SQLException
     */
    public ProductMenu() throws SQLException {
        productCtrl = new ProductController();
        input = new Input();
        supplierCtrl = new SupplierController();
    }

    /**
     * Prints out the menu and asks for a user input
     * @throws SQLException
     */
    public void run() throws SQLException {
        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Back to main menu", 0);
        menu.addOption("Add product", 1);
        menu.addOption("List all products", 2);
        menu.addOption("View product", 3);
        menu.addOption("Update product", 4);
        menu.addOption("Delete product", 5);
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
    
    public void createProduct() throws SQLException{
    	clearLines();
    	String name = input.stringInput("Enter the product name");
    	BigDecimal price = input.bigDecimalInput("Enter the price of the product");
    	int inStock = input.integerInput("Check the availability of the product");
    	//Supplier supplier = supplierMenu.findSupplierById();
    	int id = input.integerInput("Enter supplier`s ID");
    	
    	productCtrl.createProduct(name, price, inStock,id);
    	
    	
    	
    }
    
    public void listAllProducts() throws SQLException{
    	clearLines();
    	
    	for(Product product : productCtrl.findAll()) {
    		System.out.print(product.toString());
    	}
    }
    
    public Product findProductById() throws SQLException{
    	listAllProducts();
    	int id = input.integerInput("Enter product`s id:");
    	Product product = productCtrl.findProductById(id);
    	System.out.println(product.toString());
    	return product;
    }
    
    public void updateProduct() throws SQLException{
    	listAllProducts();
    	//Supplier supplier = supplierMenu.findSupplierById();
    	Product product = findProductById();
    	String name = input.stringInput("Enter the product name");
    	BigDecimal price = input.bigDecimalInput("Enter the price of the product");
    	int inStock = input.integerInput("Check the availability of the product");
    	
    	
    	productCtrl.updateProduct(product,name,price,inStock);
    	
    }
    
    public void deleteProduct() throws SQLException{
    	Product product = findProductById();
    	productCtrl.deleteProduct(product);
    }
    
    public void clearLines() {
    	for(int i = 0; i<10; i++) {
    		System.out.println();
    	}
    }
    
}

