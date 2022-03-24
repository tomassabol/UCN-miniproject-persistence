package view;

import java.sql.SQLException;

public class MainMenu {

    /**
     * Fields for class MainMenu
     */

    public MainMenu() {}

    public void run() throws SQLException {
        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Quit", 0);
        menu.addOption("Order menu", 1);
        menu.addOption("Customer menu", 2);
        menu.addOption("Product menu", 3);
        menu.addOption("Supplier menu", 4);
        menu.addOption("Storage menu", 5);
        menu.addOption("Stock menu", 6);

        while(conTinUe) {
            int choice;
            choice = menu.input("Main menu - Choose option:", false);
            switch(choice) {
                case 1: {
                    OrderMenu orderMenu = new OrderMenu();
                    orderMenu.run();
                    break;
                }
                case 2: {
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.run();
                    break;
                }
                case 3: {
                	ProductMenu productMenu = new ProductMenu();
                	productMenu.run();
                    break;
                }
                case 4: {
                	SupplierMenu supplierMenu = new SupplierMenu();
                	supplierMenu.run();
                    break;
                }
                case 5: {
                	StorageMenu storageMenu = new StorageMenu();
                	storageMenu.run();
                    break;
                }
                case 6: {
                	StockMenu stockMenu = new StockMenu();
                	stockMenu.run();
                }
                case 0: {
                    System.out.println("Goodbye!");
                    conTinUe = false;
                    break;
                }
                default: break;
            }
        }

    }
}
