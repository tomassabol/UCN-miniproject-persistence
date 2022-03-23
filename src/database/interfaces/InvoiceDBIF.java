package database.interfaces;

import java.sql.SQLException;

import java.util.List;

import model.Invoice;

public interface InvoiceDBIF {
	public List<Invoice> findAll() throws SQLException;
	public Invoice findInvoiceById() throws SQLException;
	public void createInvoice(Invoice invoice) throws SQLException;
}
