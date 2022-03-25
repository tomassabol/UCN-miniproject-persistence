package view;

import java.math.BigDecimal;
import java.sql.SQLException;

import controller.OrderController;
//import controller.StorageLineController;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;
//import model.StorageLine;
import model.StorageLine;

public class OrderMenu {
	
	/**
	 * Fields for class OrderMenu
	 */
    private OrderController orderCtrl;
    
    private CustomerMenu customerMenu;
    private ProductMenu productMenu;
    private StockMenu stockMenu;

    private Input input;

    /**
     * Constructor for class Order Menu
     * @throws SQLException
     */
    public OrderMenu() throws SQLException {
        orderCtrl = new OrderController();

        customerMenu = new CustomerMenu();
        productMenu = new ProductMenu();
        stockMenu = new StockMenu();

        input = new Input();
    }

    /**
     * Prints order menu
     * @throws SQLException
     */
    public void run() throws SQLException {
        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Back to main menu", 0);
        menu.addOption("Create Order", 1);
        menu.addOption("List all Orders", 2);
        menu.addOption("View Order", 3);
        menu.addOption("Delete Order", 4);
        menu.addOption("Generate Invoice", 5);

        System.out.println();

        while(conTinUe) {
            int choice = menu.input("Order Menu - Choose option", false);
            switch(choice) {
                case 1: {
                    createOrder();
                    break;
                }
                case 2: {
                    listAllOrders();
                    break;
                }
                case 3: {
                    findOrderById();
                    break;
                }
                case 4: {
                    deleteOrder();
                    break;
                }
                case 5: {
                    genereateInvoice();
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
     * Creates order
     * @throws SQLException
     */
    public void createOrder() throws SQLException {
        clearLines();
        
        Customer customer = customerMenu.findCustomerById();
        
        Order order = orderCtrl.startOrder(customer);


        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Go Back to Order Menu", 0);
        menu.addOption("Add product", 1);
        menu.addOption("Finish Order", 2);


        System.out.println();

        while(conTinUe) {
            int choice = menu.input("Choose Option", false);
            switch(choice) {
                case 1: {
                    // get product by id
                    Product product = productMenu.findProductById();
                    // get storage line for product
                    StorageLine storageLine = stockMenu.findStorageLineByProductId();
                    System.out.println("Available quantity: " + storageLine.getQuantity());
                    // get desired quantity
                    int quantity = input.integerInput("Enter Quantity: ");
                    // create order line object
                    OrderLine orderLine = new OrderLine(product, quantity);

                    if (storageLine.getQuantity() >= quantity) {
                        orderLine.calculateOrderLinePrice();
                        order.addOrderLine(orderLine);
                        stockMenu.removeFromStock(storageLine, quantity);
                        orderCtrl.createOrderLine(order, orderLine);
                    }


                    break;
                }
                case 2: {
                    orderCtrl.calculateTotal(order);
                    if (order.getTotalPrice() == BigDecimal.valueOf(0)) {
                        System.out.println("Empty order will not be created");
                        break;
                    } else {
                        orderCtrl.finishOrder(order);
                        conTinUe = false;
                        break;
                    }
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
     * Lists all orders from the database
     * @throws SQLException
     */
    public void listAllOrders() throws SQLException {
        clearLines();

        for (Order order : orderCtrl.findAll()) {
            System.out.println(order.toString());
        }
    }

    /**
     * Finds order with given id 
     * @return
     * @throws SQLException
     */
    public Order findOrderById() throws SQLException {
        listAllOrders();
        int id = input.integerInput("Enter order ID: ");
        Order order = orderCtrl.findOrderById(id);
        System.out.println(order.toString());
        return order;
    }

    /**
     * Deletes order
     * @throws SQLException
     */
    public void deleteOrder() throws SQLException {
        Order order = findOrderById();
        orderCtrl.deleteOrder(order);
    }

    /**
     * Generates invoice for order
     * @throws SQLException
     */
    public void genereateInvoice() throws SQLException {
        listAllOrders();
        Order order = findOrderById();
        orderCtrl.generateInvoice(order);
    }

    /**
     * Prints some lines for visibility
     */
    public void clearLines() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

}
