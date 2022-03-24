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
                	findStorageLineById();
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
	
	public void createStorage() throws SQLException {
    	clearLines();
    	
    	Product product = productMenu.findProductById();
    	int quantity = input.integerInput("Enter product quantity: ");
    	Storage storage = storageMenu.findStorageById();
    	
    	storageLineCtrl.createStorageLine(product, quantity, storage);
    }
	
	public void listAllStorageLines() throws SQLException {
		clearLines();
    	
    	for(StorageLine storageLine : storageLineCtrl.findAll()) {
    		System.out.println(storageLine.toString());
    	}
	}
	
	public StorageLine findStorageLineById() throws SQLException {
		listAllStorageLines();
    	int id = input.integerInput("Enter storage line id: ");
    	StorageLine storageLine = storageLineCtrl.findStorageLineById(id);
    	System.out.println(storageLine.toString());
    	return storageLine;
	}
	
	public void addToStock() throws SQLException {
		StorageLine storageLine = findStorageLineById();
		int quantity = input.integerInput("How many do you want to add to the stock: ");
		
		storageLineCtrl.addToStock(storageLine, quantity);
	}
	
	public void deleteStorageLine() throws SQLException {
		StorageLine storageLine = findStorageLineById();
		storageLineCtrl.deleteStorageLine(storageLine);
	}
	
	public void clearLines() {
    	for(int i = 0; i<10; i++) {
    		System.out.println();
    	}
    }

}
