package database;

import java.sql.*;

import java.util.*;

import controller.OrderController;
import database.interfaces.*;
import model.Invoice;
import model.Order;

public class InvoiceDB implements InvoiceDBIF{
	
	/**
	 * Prepared statements for class InvoiceDB
	 */
	private static final String FIND_ALL = "SELECT Id, OrderId, [Date], Price FROM Invoices";
	private static final String FIND_INVOICE_BY_ID = "SELECT Id, OrderId, [Date], Price FROM Invoices WHERE Id=?";
	private static final String CREATE_INVOICE = "INSERT INTO Invoices (OrderId, [Date], Price) values(?, ?, ?)";
	
	/**
     * Fields for class InvioiceDB
     */
	private PreparedStatement findAll;
	private PreparedStatement findInvoiceById;
	private PreparedStatement createInvoice;
	
	/**
     * Constructor for class InvoiceDB
     * @throws SQLException
     */
	public InvoiceDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
		findInvoiceById = DBConnection.getInstance().getConnection().prepareStatement(FIND_INVOICE_BY_ID);
		createInvoice  = DBConnection.getInstance().getConnection().prepareStatement(CREATE_INVOICE);
	}
	
	/**
     * Lists all invoices in the database
     * @return list of invoices
     * @throws SQLException
     */
	@Override
	public List<Invoice> findAll() throws SQLException {
		ResultSet rs;
        rs = findAll.executeQuery();
        List<Invoice> invoices = buildObjects(rs);
        return invoices;
	}
	
	/**
     * Finds invoice by id
     * @param id The id of the invoice
     * @return invoice with given id
     * @throws SQLException
     */
	@Override
	public Invoice findInvoiceById(int id) throws SQLException {
		Invoice invoice = null;
        ResultSet rs;
		findInvoiceById.setInt(1, id);
        rs = findInvoiceById.executeQuery();
        rs.next();
        invoice = buildObject(rs);
        return invoice;
	}
	
	/**
     * Creates new invoice and adds it to the database
     * @param invoice The invoice to be added to the database
     * @throws SQLException
     */
	@Override
	public void createInvoice(Invoice invoice) throws SQLException {
		createInvoice.setInt(1, invoice.getOrder().getId());
		createInvoice.setDate(2, invoice.getPaymentDate());
		createInvoice.setBigDecimal(3, invoice.getPrice());
		createInvoice.execute();
	}
	
	/**
     * Builds the object based on the info from the database
     * @param rs Result set 
     * @return built invoice object with information from the rs
     * @throws SQLException
     */
	private Invoice buildObject(ResultSet rs) throws SQLException {
		OrderController orderCtrl = new OrderController();
		Order order = orderCtrl.findOrderById(rs.getInt("OrderId")); 
		
        Invoice invoice = new Invoice(rs.getInt("Id"),order, rs.getDate("[Date]"), rs.getBigDecimal("Price"));
        return invoice;
    }
	
	/**
     * Builds objects based on the info from the database
     * @param rs Result set
     * @return list of built invoice objects
     * @throws SQLException
     */
    private List<Invoice> buildObjects(ResultSet rs) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        while(rs.next()) {
            invoices.add(buildObject(rs));
        }
        return invoices;
    }
}
