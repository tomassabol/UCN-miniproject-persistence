package database;

import java.sql.*;
import java.util.*;

import controller.*;
import database.interfaces.StorageLineDBIF;
import model.*;

public class StorageLineDB implements StorageLineDBIF {
	
	/**
	 * Prepared statements for class StorageLineDB
	 */
    private static final String FIND_ALL = "SELECT Id, ProductId, Quantity, StorageId FROM StorageLines";
    private static final String FIND_STORAGELINE_BY_ID = "SELECT Id, ProductId, Quantity, StorageId FROM StorageLines WHERE ProductId=?";
    private static final String CREATE_STORAGELINE = "INSERT INTO StorageLines (ProductId, Quantity, StorageId) values(?, ?, ?) ";
    private static final String UPDATE_STORAGELINE = "UPDATE StorageLines SET Quantity = ? FROM StorageLines WHERE ProductId = ?";
    private static final String DELETE_STORAGELINE = "DELETE FROM StorageLines WHERE Id = ?";

    /**
     * Fields for class StorageLineDB
     */
    private PreparedStatement findAll;
	private PreparedStatement findById;
	private PreparedStatement createStorageLine;
	private PreparedStatement updateStorageLine;
	private PreparedStatement deleteStorageLine;

	/**
     * Constructor for class StorageLineDB
     * @throws SQLException
     */
    public StorageLineDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
	    findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_STORAGELINE_BY_ID);
	    createStorageLine = DBConnection.getInstance().getConnection().prepareStatement(CREATE_STORAGELINE);
	    updateStorageLine = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_STORAGELINE);
	    deleteStorageLine = DBConnection.getInstance().getConnection().prepareStatement(DELETE_STORAGELINE);
    }

    /**
     * Lists all storage lines in the database
     * @return list of storage lines
     * @throws SQLException
     */
    @Override
    public List<StorageLine> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<StorageLine> storageLines = buildObjects(rs);
        return storageLines;
    }
    
    /**
     * Finds storage line by id
     * @param id The id of the storage line
     * @return storage line with given id
     * @throws SQLException
     */
    @Override
    public StorageLine findByProductId(int id) throws SQLException {
        StorageLine storageLine = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        storageLine = buildObject(rs);
        return storageLine;
    }
    
    /**
     * Creates new storage line and adds it to the database
     * @param sotrageLine The storage line to be added to the database
     * @throws SQLException
     */
    @Override
    public void createStorageLine(StorageLine storageLine) throws SQLException {
        createStorageLine.setInt(1, storageLine.getProduct().getId());
        createStorageLine.setInt(2, storageLine.getQuantity());
        createStorageLine.setInt(3, storageLine.getStorage().getId());
        createStorageLine.execute();
    }
    
    /**
     * Updates the information of the storage line
     * @param storageLine The storage line to be updated
     * @throws SQLException
     */
    @Override
    public void updateStorageLine(StorageLine storageLine) throws SQLException {
        updateStorageLine.setInt(1, storageLine.getProduct().getId());
        updateStorageLine.setInt(2, storageLine.getQuantity());
        updateStorageLine.setInt(3, storageLine.getStorage().getId());
        updateStorageLine.execute();
    }
    
    /**
     * Deletes a storage line form the database
     * @param storageLine The storage line to be deleted
     */
    @Override
    public void deleteStorageLine(StorageLine storageLine) throws SQLException {
        deleteStorageLine.setInt(1, storageLine.getId());
        deleteStorageLine.execute();
    }

    /**
     * Builds the object based on the info from the database
     * @param rs Result set 
     * @return built storageLine object with information from the rs
     * @throws SQLException
     */
    private StorageLine buildObject(ResultSet rs) throws SQLException {
        ProductController productCtrl = new ProductController();
        Product product = productCtrl.findProductById(rs.getInt("ProductId"));

        StorageController storageCtrl = new StorageController();
        Storage storage = storageCtrl.findStorageById(rs.getInt("StorageId"));

        StorageLine storageLine = new StorageLine(rs.getInt("Id"), product, rs.getInt("Quantity"), storage);

        return storageLine;
    }

    /**
     * Builds objects based on the info from the database
     * @param rs Result set
     * @return list of built storageLine objects
     * @throws SQLException
     */
    private List<StorageLine> buildObjects(ResultSet rs) throws SQLException {
        List<StorageLine> storageLines = new ArrayList<>();
        while(rs.next()) {
            storageLines.add(buildObject(rs));
        }
        return storageLines;
    }
}
