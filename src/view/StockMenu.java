package view;

import java.sql.SQLException;

import controller.StorageLineController;
import model.Product;
import model.Storage;
import model.StorageLine;

public class StockMenu {

	/**
	 * Fields for class StockMenu
	 */
	private StorageLineController storageLineCtrl;
	private ProductMenu productMenu;
	private StorageMenu storageMenu;
	private Input input;
	
	/**
	 * Constructor for class StockMenu
	 * @throws SQLException
	 */
	public StockMenu() throws SQLException {
		storageLineCtrl = new StorageLineController();
		productMenu = new ProductMenu();
		storageMenu = new StorageMenu();
		input = new Input();
	}
	
	/**
	 * Prints stock menu
	 * @throws SQLException
	 */
	public void run() throws SQLException {
		boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Back to main menu", 0);
        menu.addOption("Create storage line", 1);
        menu.addOption("List all storage lines", 2);
        menu.addOption("View storage line", 3);
        menu.addOption("Add to stock", 4);
        menu.addOption("Delete storage line", 5);
        System.out.println();

        while(conTinUe) {
            int choice;
            choice = menu.input("Stock menu - Choose option:", false);
            switch(choice) {
                case 1: {
                	createStorage();
                    break;
                }
                case 2: {
                	listAllStorageLines();
                    break;
                }
                case 3: {
                	findStorageLineByProductId();
                    break;
                }
                case 4: {
                	addToStock();
                    break;
                }
                case 5: {
                	deleteStorageLine();
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
	 * Creates storage
	 * @throws SQLException
	 */
	public void createStorage() throws SQLException {
    	clearLines();
    	
    	Product product = productMenu.findProductById();
    	int quantity = input.integerInput("Enter product quantity: ");
    	Storage storage = storageMenu.findStorageById();
    	
    	storageLineCtrl.createStorageLine(product, quantity, storage);
    }
	
	/**
	 * Lists all storage lines from the database
	 * @throws SQLException
	 */
	public void listAllStorageLines() throws SQLException {
		clearLines();
    	
    	for(StorageLine storageLine : storageLineCtrl.findAll()) {
    		System.out.println(storageLine.toString());
    	}
	}
	
	/**
	 * Finds storage line with given id 
	 * @return
	 * @throws SQLException
	 */
	public StorageLine findStorageLineByProductId() throws SQLException {
		listAllStorageLines();
    	int id = input.integerInput("Enter storage line id: ");
    	StorageLine storageLine = storageLineCtrl.findStorageLineById(id);
    	System.out.println(storageLine.toString());
    	return storageLine;
	}
	
	/**
	 * Adds given number of items to stock
	 * @throws SQLException
	 */
	public void addToStock() throws SQLException {
		StorageLine storageLine = findStorageLineByProductId();
		int quantity = input.integerInput("How many do you want to add to the stock: ");
		
		storageLineCtrl.addToStock(storageLine, quantity);
	}

	/**
	 * Removes given number from stock
	 * @param storageLine The storage line to remove quantity
	 * @param quantity The quantity to remove
	 * @throws SQLException
	 */
	public void removeFromStock(StorageLine storageLine, int quantity) throws SQLException {
		storageLineCtrl.removeFromStock(storageLine, quantity);
	}
	
	/**
	 * Deletes storage line
	 * @throws SQLException
	 */
	public void deleteStorageLine() throws SQLException {
		StorageLine storageLine = findStorageLineByProductId();
		storageLineCtrl.deleteStorageLine(storageLine);
	}
	
	/**
	 * Prints some lines for visibility
	 */
	public void clearLines() {
    	for(int i = 0; i<10; i++) {
    		System.out.println();
    	}
    }

}
