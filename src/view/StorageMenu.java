package view;

import java.sql.SQLException;

import controller.StorageController;
import model.Storage;

public class StorageMenu {
	
	/**
	 * Fields for class StorageMenu
	 */
	private StorageController storageCtrl;
	private Input input;
	
	/**
	 * Constructor for class StorageMenu
	 * @throws SQLException
	 */
	public StorageMenu() throws SQLException {
		storageCtrl = new StorageController();
		input = new Input();
	}
	
	/**
	 * Prints storage menu
	 * @throws SQLException
	 */
	public void run() throws SQLException {
        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Back to main menu", 0);
        menu.addOption("Add storage", 1);
        menu.addOption("List all storages", 2);
        menu.addOption("View storage", 3);
        menu.addOption("Update storage", 4);
        menu.addOption("Delete storage", 5);
        System.out.println();

        while(conTinUe) {
            int choice;
            choice = menu.input("Storage menu - Choose option:", false);
            switch(choice) {
                case 1: {
                	createStorage();
                    break;
                }
                case 2: {
                	listAllStorage();
                    break;
                }
                case 3: {
                	findStorageById();
                    break;
                }
                case 4: {
                	updateStorage();
                    break;
                }
                case 5: {
                	deleteStorage();
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
	private void createStorage() throws SQLException {
		clearLines();
		
		String name = input.stringInput("Enter name of the storage: ");
		String address = input.stringInput("Enter storage's address: ");
		
		storageCtrl.createStorage(name, address);
	}
	
	/**
	 * Lists all storage in the database
	 * @throws SQLException
	 */
	private void listAllStorage() throws SQLException {
		clearLines();
		
		for(Storage storage : storageCtrl.findAll()) {
			System.out.println(storage.toString());
		}
	}
	
	/**
	 * Finds storage with given id
	 * @return storage with given id
	 * @throws SQLException
	 */
	public Storage findStorageById() throws SQLException {
		listAllStorage();
    	int id = input.integerInput("Enter storage's id: ");
    	Storage storage= storageCtrl.findStorageById(id);
    	System.out.println(storage.toString());
    	return storage;
	}
	
	/**
	 * Updates information about storage
	 * @throws SQLException
	 */
	private void updateStorage() throws SQLException {
		listAllStorage();
		Storage storage = findStorageById();
		
		String name = input.stringInput("Enter storage's name: ");
		String address = input.stringInput("Enter storage's address: ");
		
		storageCtrl.updateStorage(storage, name, address);
	}
	
	/**
	 * Deletes storage
	 * @throws SQLException
	 */
	private void deleteStorage() throws SQLException {
		Storage storage = findStorageById();
		storageCtrl.deleteStorage(storage);
	}
	
	/**
	 * Prints some clear line for better visibility
	 */
	 public void clearLines() {
	    for(int i = 0; i<10; i++) {
	    	System.out.println();
	    }
	 }
	
	
}
