package database;

import java.sql.*;
import java.util.*;

import controller.*;
import database.interfaces.StorageLineDBIF;
import model.*;

public class StorageLineDB implements StorageLineDBIF {
    private static final String FIND_ALL = "SELECT Id, ProductId, Quantity, StorageId FROM StorageLines";
    private static final String FIND_ORDERLINE_BY_ID = "SELECT Id, ProductId, Quantity, StorageId FROM StorageLines WHERE ProductId=?";
    private static final String CREATE_ORDERLINE = "INSERT INTO StorageLines (Id, ProductId, Quantity, StorageId) values(?, ?, ?, ?) ";
    private static final String UPDATE_ORDERLINE = "UPDATE StorageLines SET Quantity = ? FROM StorageLines WHERE ProductId = ?";
    private static final String DELETE_ORDERLINE = "DELETE FROM StorageLines WHERE Id = ?";

    private PreparedStatement findAll;
	private PreparedStatement findById;
	private PreparedStatement createStorageLine;
	private PreparedStatement updateStorageLine;
	private PreparedStatement deleteStorageLine;

    public StorageLineDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
	    findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_ORDERLINE_BY_ID);
	    createStorageLine = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDERLINE);
	    updateStorageLine = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ORDERLINE);
	    deleteStorageLine = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDERLINE);
    }

    @Override
    public List<StorageLine> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<StorageLine> storageLines = buildObjects(rs);
        return storageLines;
    }
    @Override
    public StorageLine findById(int id) throws SQLException {
        StorageLine storageLine = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        storageLine = buildObject(rs);
        return storageLine;
    }
    @Override
    public void createOrderLine(StorageLine storageLine) throws SQLException {
        createStorageLine.setInt(1, storageLine.getProduct().getId());
        createStorageLine.setInt(2, storageLine.getQuantity());
        createStorageLine.setInt(3, storageLine.getStorage().getId());
        createStorageLine.execute();
    }
    @Override
    public void updateOrderLine(StorageLine storageLine) throws SQLException {
        updateStorageLine.setInt(1, storageLine.getProduct().getId());
        updateStorageLine.setInt(2, storageLine.getQuantity());
        updateStorageLine.setInt(3, storageLine.getStorage().getId());
        updateStorageLine.execute();
    }
    @Override
    public void deleteOrderLine(StorageLine storageLine) throws SQLException {
        deleteStorageLine.setInt(1, storageLine.getId());
        deleteStorageLine.execute();
    }

    private StorageLine buildObject(ResultSet rs) throws SQLException {
        ProductController productCtrl = new ProductController();
        Product product = productCtrl.findProductById(rs.getInt("ProductId"));

        StorageController storageCtrl = new StorageController();
        Storage storage = storageCtrl.findStorageById(rs.getInt("StorageId"));

        StorageLine storageLine = new StorageLine(rs.getInt("Id"), product, rs.getInt("Quantity"), storage);

        return storageLine;
    }

    private List<StorageLine> buildObjects(ResultSet rs) throws SQLException {
        List<StorageLine> storageLines = new ArrayList<>();
        while(rs.next()) {
            storageLines.add(buildObject(rs));
        }
        return storageLines;
    }
}
