package controller;

import java.util.*;
import java.sql.SQLException;

import database.*;
import database.interfaces.*;
import model.*;

public class StorageLineController {

	/**
     * Fields for class StorageLineController
     */
	private StorageLineDBIF storageLineDBIF;
	
	/**
     * Constructor for class StorageLineController
     * @throws SQLException
     */
	public StorageLineController() throws SQLException {
		storageLineDBIF = new StorageLineDB();
	}

	/**
     * Lists all the storage lines in the database
     * @return All the storage lines in the database
     * @throws SQLException
     */
	public List<StorageLine> findAll() throws SQLException {
		return storageLineDBIF.findAll();
	}
	
	/**
     * Finds a storage line by id
     * @param id The id of the storage line
     * @return The storage line with the id
     * @throws SQLException
     */
	public StorageLine findStorageLineById(int id) throws SQLException{
		return storageLineDBIF.findById(id);
	}
	
	/**
     * Creates a new storage line and adds it to the database
     * @param product The product in the storage line
     * @param quantity The quantity of the product in the storage line
     * @param storage The location of the product from storage line
     * @throws SQLException
     */
	public StorageLine createStorageLine(Product product, int quantity, Storage storage) throws SQLException{
		StorageLine storageLine = new StorageLine(product, quantity, storage);
		storageLineDBIF.createStorageLine(storageLine);
		
		return storageLine;
	}
	
	/*
	public void updateStorageLine(StorageLine storageLine,Product product,int quantity,Storage storage) throws SQLException{
		storageLine.setProduct(product);
		storageLine.setQuantity(quantity);
		storageLine.setStorage(storage);
		storageLineDBIF.updateStorageLine(storageLine);
	}
	*/
	
	 /**
     * Deletes a storage line from the database
     * @param storageLine The storage line that will be deleted from the database
     * @throws SQLException
     */
	public void deleteStorageLine(StorageLine storageLine) throws SQLException {
		storageLineDBIF.deleteStorageLine(storageLine);
	}

	/**
	 * Adds quantity to the existing storage line
	 * @param storageLine The storage line to add the quantity
	 * @param quantity The quantity of the product to add to storage line
	 * @throws SQLException
	 */
	public void addToStock(StorageLine storageLine, int quantity) throws SQLException {
		// increment quantity
		int result = storageLine.getQuantity() + quantity;
		storageLine.setQuantity(result);

		// update DB
		storageLineDBIF.updateStorageLineQuantity(storageLine);
	}

	/**
	 * Removes given quantity from stock
	 * @param storageLine The storage line to remove quantity from
	 * @param quantity The quantity to remove from the storage line
	 * @return true if removed successfully
	 * @throws SQLException
	 */
	public boolean removeFromStock(StorageLine storageLine, int quantity) throws SQLException {
		boolean removed = false;
		// subtract quantity
		int result = storageLine.getQuantity() - quantity;
		// if result is valid (>=0), update storageLine
		if (result >= 0) {
			storageLine.setQuantity(result);
			removed = true;
			// update DB
			StorageLineDBIF storageLineDBIF = new StorageLineDB();
			storageLineDBIF.updateStorageLine(storageLine);
		}

		return removed;
	}
	
}
