package controller;

import java.util.*;
import java.math.BigDecimal;
import java.sql.SQLException;

import database.StorageLineDB;
import database.interfaces.ProductDBIF;
import database.interfaces.StorageLineDBIF;
import model.StorageLine;
import model.Product;
import model.Storage;

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
	
}
