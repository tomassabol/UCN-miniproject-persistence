package view;

import java.sql.SQLException;

import controller.CustomerController;
import controller.CustomerTypeController;
import model.Customer;
import model.CustomerType;

public class CustomerMenu {
    
	/**
	 * Fields for class CustomerMenu
	 */
    private CustomerController customerCtrl;
    private CustomerTypeController customerTypeCtrl;
    private Input input;

    /**
     * Constructor for class CustomerMenu
     * @throws SQLException
     */
    public CustomerMenu() throws SQLException {
        customerCtrl = new CustomerController();
        customerTypeCtrl = new CustomerTypeController();
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
        menu.addOption("Add customer type", 6);
        menu.addOption("Add list all types", 7);
        menu.addOption("View customer type", 8);
        menu.addOption("Update customer type", 9);
        menu.addOption("Delete customer type", 10);
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
                case 6: {
                	createCustomerType();
                    break;
                }
                case 7: {
                	listAllCustomerTypes();;
                    break;
                }
                case 8: {
                	findCustomerTypeById();
                    break;
                }
                case 9: {
                	updateCustomerType();
                    break;
                }
                case 10: {
                	deleteCustomerType();
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

        CustomerType customerType = findCustomerTypeById();
    	
    	customerCtrl.createCustomer(name, address, city, phoneNumber, email, customerType);
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
    	Customer customer = findCustomerById();
    	
    	String name = input.stringInput("Enter customer's name: ");
    	String address = input.stringInput("Enter customer's address: ");
    	String city = input.stringInput("Enter customer's city: ");
    	String phoneNumber = input.stringInput("Enter customer's phone number: ");
    	String email = input.stringInput("Enter customer's email: ");

        CustomerType customerType = findCustomerTypeById();
    	
    	customerCtrl.updateCustomer(customer, name, address, city, phoneNumber, email, customerType);
    }
    
    public void deleteCustomer() throws SQLException {
    	Customer customer = findCustomerById();
    	customerCtrl.deleteCustomer(customer);
    }


    // <------- CustomerType ------->
    public void createCustomerType() throws SQLException {
        clearLines();
        String name = input.stringInput("Enter Customer Type Name: ");
        int discount = input.integerInput("Enter Customer Type Discount: ");
        customerTypeCtrl.createCustomertype(name, discount);
    }

    public void listAllCustomerTypes() throws SQLException {
        for (CustomerType customerType : customerTypeCtrl.findAll()) {
            System.out.println(customerType.toString());
        }
    }

    public CustomerType findCustomerTypeById() throws SQLException {
        listAllCustomerTypes();
        int id = input.integerInput("Enter ID: ");
        CustomerType customerType = customerTypeCtrl.findCustomerTypeById(id);
        System.out.println(customerType.toString());
        return customerType;
    }

    public void updateCustomerType() throws SQLException {
        listAllCustomerTypes();
        CustomerType customerType = findCustomerTypeById();

        String name = input.stringInput("Enter new Customer Type name: ");
        int discount = input.integerInput("Enter new Customer Type discount: ");
        customerTypeCtrl.updateCustomerType(customerType, name, discount);
    }

    public void deleteCustomerType() {}
    
    public void clearLines() {
    	for(int i = 0; i<10; i++) {
    		System.out.println();
    	}
    }
}
