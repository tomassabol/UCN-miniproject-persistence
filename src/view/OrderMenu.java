package view;

import java.sql.SQLException;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
//import controller.StorageLineController;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;
//import model.StorageLine;

public class OrderMenu {
    private OrderController orderCtrl;
    private CustomerController customerCtrl;
    private ProductController productCtrl;
    //private StorageLineController storageLineCtrl;
    private Input input;

    public OrderMenu() throws SQLException {
        orderCtrl = new OrderController();
        customerCtrl = new CustomerController();
        productCtrl = new ProductController();
        //storageLineCtrl = new StorageLineController();
        input = new Input();
    }

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

    public void createOrder() throws SQLException {
        clearLines();

        listAllCustomers();
        
        int customerId = input.integerInput("Enter Customer ID: ");
        Customer customer = customerCtrl.findCustomerById(customerId);
        
        Order order = orderCtrl.startOrder(customer);


        boolean conTinUe = true;
        ListChoice<Integer> menu = new ListChoice<>();
        menu.addOption("Quit", 0);
        menu.addOption("Add product", 1);
        menu.addOption("Finish Order", 2);


        System.out.println();

        while(conTinUe) {
            int choice = menu.input("Choose Option", false);
            switch(choice) {
                case 1: {
                    listAllProducts();

                    int productId = input.integerInput("Enter Product ID: ");
                    Product product = productCtrl.findProductById(productId);

                    int quantity = input.integerInput("Enter Quantity: ");
                    


                    OrderLine orderLine = new OrderLine(productId, product, quantity);
                    order.addOrderLine(orderLine);

                    //StorageLine storageLine = storageLineCtrl.findStorageLinebyId(productId); // TODO: crete storage line when creating product. Storageline and product will have same ID
                    //storageLineCtrl.removeFromStock(storageLine, quantity);
                    break;
                }
                case 2: {
                    orderCtrl.finishOrder(order);
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
    
    public void listAllOrders() throws SQLException {
        clearLines();

        for (Order order : orderCtrl.findAll()) {
            System.out.println(order.toString());
        }
    }

    public Order findOrderById() throws SQLException {
        listAllOrders();
        int id = input.integerInput("Enter order ID: ");
        Order order = orderCtrl.findOrderById(id);
        System.out.println(order.toString());
        return order;
    }

    public void deleteOrder() throws SQLException {
        Order order = findOrderById();
        orderCtrl.deleteOrder(order);
    }

    public void genereateInvoice() throws SQLException {
        listAllOrders();
        Order order = findOrderById();
        orderCtrl.generateInvoice(order);
    }

    public void clearLines() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public void listAllCustomers() throws SQLException {
        for (Customer customer : customerCtrl.findAll()) {
            System.out.println(customer.toString());
        }
    }

    public void listAllProducts() throws SQLException {
        for (Product product : productCtrl.findAll()) {
            System.out.println(product.toString());
        }
    }
}
