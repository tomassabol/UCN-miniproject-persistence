package view;

import java.sql.SQLException;

import controller.CustomerController;
import model.Customer;

public class CustomerMenu {
    
	/**
	 * Fields for class CustomerMenu
	 */
    private CustomerController customerCtrl;
    private Input input;

    /**
     * Constructor for class CustomerMenu
     * @throws SQLException
     */
    public CustomerMenu() throws SQLException {
        customerCtrl = new CustomerController();
        input = new Input();
    }

    /**
     * Prints out the menu and asks for a user input
     * @throws SQLException
     */
    public void run() throws SQLException {
        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Back to main menu", 0);
        menu.addOption("Add customer", 1);
        menu.addOption("List all customers", 2);
        menu.addOption("View customer", 3);
        menu.addOption("Update customer", 4);
        menu.addOption("Delete customer", 5);
        //menu.addOption("Customer type menu", 6);
        System.out.println();

        while(conTinUe) {
            int choice;
            choice = menu.input("Customer menu - Choose option:", false);
            switch(choice) {
                case 1: {
                	createCustomer();
                    break;
                }
                case 2: {
                	listAllCustomer();
                    break;
                }
                case 3: {
                	findCustomerById();
                    break;
                }
                case 4: {
                	updateCustomer();
                    break;
                }
                case 5: {
                	deleteCustomer();
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
    
    public void createCustomer() throws SQLException {
    	clearLines();
    	
    	String name = input.stringInput("Enter customer's name: ");
    	String address = input.stringInput("Enter customer's address: ");
    	String city = input.stringInput("Enter customer's city: ");
    	String phoneNumber = input.stringInput("Enter customer's phone number: ");
    	String email = input.stringInput("Enter customer's email: ");
    	
    	customerCtrl.createCustomer(name, address, city, phoneNumber, email);
    }
    
    public void listAllCustomer() throws SQLException {
    	clearLines();
    	
    	for(Customer customer : customerCtrl.findAll()) {
    		System.out.println(customer.toString());
    	}
    	
    }
    
    public Customer findCustomerById() throws SQLException {
    	listAllCustomer();
    	int id = input.integerInput("Enter customer's id: ");
    	Customer customer = customerCtrl.findCustomerById(id);
    	System.out.println(customer.toString());
    	return customer;
    }
    
    public void updateCustomer() throws SQLException {
    	listAllCustomer();
    	int id = input.integerInput("Enter customer's id: ");
    	Customer customer = customerCtrl.findCustomerById(id);
    	
    	String name = input.stringInput("Enter customer's name: ");
    	String address = input.stringInput("Enter customer's address: ");
    	String city = input.stringInput("Enter customer's city: ");
    	String phoneNumber = input.stringInput("Enter customer's phone number: ");
    	String email = input.stringInput("Enter customer's email: ");
    	
    	customerCtrl.updateCustomer(customer, name, address, city, phoneNumber, email);
    }
    
    public void deleteCustomer() throws SQLException {
    	Customer customer = findCustomerById();
    	customerCtrl.deleteCustomer(customer);
    }
    
    public void clearLines() {
    	for(int i = 0; i<10; i++) {
    		System.out.println();
    	}
    }
}
