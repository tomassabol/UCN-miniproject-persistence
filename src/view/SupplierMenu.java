package view;

import java.sql.SQLException;

import controller.SupplierController;
import model.Supplier;

public class SupplierMenu {
    
	/**
	 * Fields for class SupplierMenu
	 */
    private SupplierController supplierCtrl;
    private Input input;

    /**
     * Constructor for class SupplierMenu
     * @throws SQLException
     */
    public SupplierMenu() throws SQLException {
        supplierCtrl = new SupplierController();
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
        menu.addOption("Add supplier: ", 1);
        menu.addOption("List all suppliers: ", 2);
        menu.addOption("View supplier: ", 3);
        menu.addOption("Update supplier: ", 4);
        menu.addOption("Delete supplier: ", 5);
        System.out.println();
        
        while(conTinUe) {
        	int choice;
        	choice = menu.input("Supplier menu - Choose an option: ",false);
        	switch(choice) {
        	case 1: {
        		createSupplier();
        		break;
        	}
        	case 2: {
        		listAllSuppliers();
        		break;
        	}
        	case 3: {
        		findSupplierById();
        		break;
        	}
        	case 4: {
        		updateSupplier();
        		break;
        	}
        	case 5: {
        		deleteSupplier();
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
    
    public void createSupplier() throws SQLException {
    	clearLines();
    	
    	String name = input.stringInput("Add supplier`s name: ");
    	String address = input.stringInput("Add supplier`s address: ");
    	String country = input.stringInput("Add supplier`s country: ");
    	String phone = input.stringInput("Add supplier`s phone number: ");
    	String email = input.stringInput("Add supplier`s email: ");
    	
    	supplierCtrl.createSupplier(name, address, country, phone, email);
    }
    
    public void listAllSuppliers() throws SQLException {
    	clearLines();
    	
    	for(Supplier supplier : supplierCtrl.findAll()) {
    		System.out.println(supplier.toString());
    	}
    }
    
    public Supplier findSupplierById() throws SQLException {
    	listAllSuppliers();
    	int id = input.integerInput("Enter supplier`s ID: ");
    	Supplier supplier = supplierCtrl.findSupplierById(id);
    	System.out.println(supplier.toString());
    	return supplier;
    }
    
    public void updateSupplier() throws SQLException {
    	listAllSuppliers();
    	int id = input.integerInput("Enter supplier`s ID: ");
    	Supplier supplier = supplierCtrl.findSupplierById(id);
    	
    	String name = input.stringInput("Add supplier`s name: ");
    	String address = input.stringInput("Add supplier`s address: ");
    	String country = input.stringInput("Add supplier`s country: ");
    	String phone = input.stringInput("Add supplier`s phone number: ");
    	String email = input.stringInput("Add supplier`s email: ");
    	
    	supplierCtrl.updateSupplier(supplier,name, address, country, phone, email);
    	}
    
    public void deleteSupplier() throws SQLException {
    	Supplier supplier = findSupplierById();
    	supplierCtrl.deleteSupplier(supplier);
    }
    
    public void clearLines() {
    	for(int i = 0; i<10; i++) {
    		System.out.println();
    	}
    }
}