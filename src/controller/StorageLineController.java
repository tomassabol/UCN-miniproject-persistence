package controller;

import java.util.*;
import java.sql.SQLException;

import database.*;
import database.interfaces.*;
import model.*;

public class StorageLineController {

	private StorageLineDBIF storageLineDBIF;
	
	public StorageLineController() throws SQLException {
	storageLineDBIF = new StorageLineDB();
	
}

	public List<StorageLine> findAll() throws SQLException {
	return storageLineDBIF.findAll();
	}
	
	public StorageLine findStorageLinebyId(int id) throws SQLException{
		return storageLineDBIF.findById(id);
	}
	
	public StorageLine createStorageLine(int id, Product product, int quantity, Storage storage) throws SQLException{
		StorageLine storageLine = new StorageLine(id,product,quantity,storage);
		storageLineDBIF.createStorageLine(storageLine);
		
		return storageLine;
		
	}
	
	public void updateStorageLine(StorageLine storageLine,Product product,int quantity,Storage storage) throws SQLException{
		storageLine.setProduct(product);
		storageLine.setQuantity(quantity);
		storageLine.setStorage(storage);
		storageLineDBIF.updateStorageLine(storageLine);
	}
	
	public void deleteStorageLine(StorageLine storageLine) throws SQLException {
		storageLineDBIF.deleteStorageLine(storageLine);
	}

	public void addToStock(StorageLine storageLine, int quantity) throws SQLException {
		// increment quantity
		int result = storageLine.getQuantity() + quantity;
		storageLine.setQuantity(result);

		// update DB
		storageLineDBIF.updateStorageLine(storageLine);
	}

	public boolean removeFromStock(StorageLine storageLine, int quantity) throws SQLException {
		boolean removed = false;
		// subtract quantity
		int result = storageLine.getQuantity() - quantity;
		// if result is valid (>=0), uodate storageline
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
