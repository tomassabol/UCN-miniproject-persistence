package view;

import java.sql.SQLException;

import controller.CustomerController;

public class CustomerMenu {
    
    private CustomerController customerCtrl;
    private Input input;

    public CustomerMenu() throws SQLException {
        customerCtrl = new CustomerController();
        input = new Input();
    }

    public void run() throws SQLException {
        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Add customer", 1);
        menu.addOption("List all customers", 2);
        menu.addOption("View customer", 3);
        menu.addOption("Update customer", 4);
        menu.addOption("Delete customer", 5);
        menu.addOption("Back to main menu", 0);
        System.out.println();

        while(conTinUe) {
            int choice;
            choice = menu.input("Customer menu - Choose option:", false);
            switch(choice) {
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {

                    break;
                }
                case 5: {

                    break;
                }
                case 0: {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.run();
                    break;
                }
                default: {

                    break;
                }
            }
        }
    }
}
