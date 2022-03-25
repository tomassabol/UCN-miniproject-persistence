package database;

import java.sql.*;

import java.util.*;

import controller.OrderController;
import database.interfaces.*;
import model.Invoice;
import model.Order;

public class InvoiceDB implements InvoiceDBIF{
	private static final String FIND_ALL = "SELECT Id, OrderId, [Date], Price FROM Invoices";
	private static final String FIND_INVOICE_BY_ID = "SELECT Id, OrderId, [Date], Price FROM Invoices WHERE Id=?";
	private static final String CREATE_INVOICE = "INSERT INTO Invoices (Id, OrderId, [Date], Price) values(?, ?, ?, ?)";
	
	private PreparedStatement findAll;
	private PreparedStatement findInvoiceById;
	private PreparedStatement createInvoice;
	
	public InvoiceDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
		findInvoiceById = DBConnection.getInstance().getConnection().prepareStatement(FIND_INVOICE_BY_ID);
		createInvoice  = DBConnection.getInstance().getConnection().prepareStatement(CREATE_INVOICE);
	}
	
	@Override
	public List<Invoice> findAll() throws SQLException {
		ResultSet rs;
        rs = findAll.executeQuery();
        List<Invoice> invoices = buildObjects(rs);
        return invoices;
	}
	
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
	
	@Override
	public void createInvoice(Invoice invoice) throws SQLException {
		createInvoice.setInt(1, invoice.getOrder().getId());
		createInvoice.setDate(2, invoice.getPaymentDate());
		createInvoice.setBigDecimal(3, invoice.getPrice());
		createInvoice.execute();
	}
	
	private Invoice buildObject(ResultSet rs) throws SQLException {
		OrderController orderCtrl = new OrderController();
		Order order = orderCtrl.findOrderById(rs.getInt("OrderId")); 
		
        Invoice invoice = new Invoice(rs.getInt("Id"),order, rs.getDate("[Date]"), rs.getBigDecimal("Price"));
        return invoice;
    }
	
    private List<Invoice> buildObjects(ResultSet rs) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        while(rs.next()) {
            invoices.add(buildObject(rs));
        }
        return invoices;
    }
}
