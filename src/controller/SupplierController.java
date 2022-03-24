package controller;

import java.sql.SQLException;
import java.util.List;

import database.interfaces.SupplierDBIF;
import model.Supplier;

public class SupplierController {

	private SupplierDBIF supplierDBIF;
	
	/**
	 * Lists all the Suppliers in the database
	 * @return all the suppliers in the database
	 * @throws SQLException
	 */
	public List<Supplier> findAll() throws SQLException {
		return supplierDBIF.findAll();
	}
	
	/**
	 * Returns a supplier by its id
	 * @param id The id of a supplier
	 * @return the supplier with the id parameter
	 * @throws SQLException
	 */
	public Supplier findSupplierById(int id) throws SQLException {
		return supplierDBIF.findSupplierById(id);
	}
	
	/**
	 * Creates a supplier and adds it to the database
	 * @param name The name of the new supplier
	 * @param address The address of the new supplier
	 * @param country The country of the new supplier
	 * @param phone The phone of the new supplier
	 * @param email The email of the new supplier
	 * @return The newly created supplier
	 * @throws SQLException
	 */
	public Supplier createSupplier(String name, String address, String country, String phone, String email) throws SQLException {
		Supplier supplier = new  Supplier(name, address, country, phone, email);
		supplierDBIF.createSupplier(supplier);
		return supplier;
	}
	
	/**
	 * Deletes a supplier from the database
	 * @param supplier The supplier that will be deleted
	 * @throws SQLException
	 */
	public void deleteSupplier(Supplier supplier) throws SQLException{
		supplierDBIF.deleteSupplier(supplier);
	}
	
	/**
	 * Updates a supplier attributes
	 * @param supplier The supplier that will be updated
	 * @param name The new name of the supplier
	 * @param adress The new address of the supplier
	 * @param country The new country of the supplier
	 * @param phone The new phone of the supplier
	 * @param email The new email of the supplier
	 * @throws SQLException
	 */
	public void updateSupplier(Supplier supplier, String name, String address, String country, String phone, String email) throws SQLException {
		supplier.setName(name);
		supplier.setAddress(address);
		supplier.setCountry(country);
		supplier.setPhone(phone);
		supplier.setEmail(email);
		supplierDBIF.updateSupplier(supplier);
	}
}
