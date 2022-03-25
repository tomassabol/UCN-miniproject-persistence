package database;

import database.interfaces.*;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorageDB implements StorageDBIF {
	
	/**
	 * Prepared statements for class StorageDB
	 */
    private static final String FIND_ALL = "SELECT Id, Name, Address FROM Storages";
    private static final String FIND_STORAGE_BY_ID = "SELECT Id, Name, Address FROM Storages WHERE Id=?";
    private static final String CREATE_STORAGE = "INSERT INTO Storages (Name, Address) values(?, ?) ";
    private static final String UPDATE_STORAGE = "UPDATE Storages SET Name = ?, Address = ? FROM Storages WHERE Id = ?";
    private static final String DELETE_STORAGE = "DELETE FROM Storages WHERE Id = ?";

    /**
     * Fields for class StorageDB
     */
    private PreparedStatement findAll;
	private PreparedStatement findStorageById;
	private PreparedStatement createStorage;
	private PreparedStatement updateStorage;
	private PreparedStatement deleteStorage;

	/**
     * Constructor for class StorageDB
     * @throws SQLException
     */
    public StorageDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findStorageById = DBConnection.getInstance().getConnection().prepareStatement(FIND_STORAGE_BY_ID);
        createStorage = DBConnection.getInstance().getConnection().prepareStatement(CREATE_STORAGE);
        updateStorage = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_STORAGE);
        deleteStorage = DBConnection.getInstance().getConnection().prepareStatement(DELETE_STORAGE);
    }
    
    /**
     * Lists all storages in the database
     * @return list of storages
     * @throws SQLException
     */
    @Override
    public List<Storage> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Storage> storages =  buildObjects(rs);
        return storages;
    }

    /**
     * Finds storage by id
     * @param id The id of the storage
     * @return storage with given id
     * @throws SQLException
     */
    @Override
    public Storage findStorageById(int id) throws SQLException {
        Storage storage = null;
        ResultSet rs;
        findStorageById.setInt(1, id);
        rs = findStorageById.executeQuery();
        rs.next();
        storage = buildObject(rs);
        return storage;
    }

    /**
     * Creates new storage and adds it to the database
     * @param storage The storage to be added to the database
     * @throws SQLException
     */
    @Override
    public void createStorage(Storage storage) throws SQLException {
        createStorage.setString(1, storage.getName());
        createStorage.setString(2, storage.getAddress());
        createStorage.execute();
    }

    /**
     * Updates the information of the storage
     * @param storage The storage to be updated
     * @throws SQLException
     */
    @Override
    public void updateStorage(Storage storage) throws SQLException {
        updateStorage.setString(1, storage.getName());
        updateStorage.setString(2, storage.getAddress());
        updateStorage.setInt(3, storage.getId());
        updateStorage.execute();
    }

    /**
     * Deletes a storage form the database
     * @param storage The storage to be deleted
     */
    @Override
    public void deleteStorage(Storage storage) throws SQLException {
        deleteStorage.setInt(1, storage.getId());
        deleteStorage.execute();
    }

    /**
     * Builds the object based on the info from the database
     * @param rs Result set 
     * @return built sotrage object with information from the rs
     * @throws SQLException
     */
    private Storage buildObject(ResultSet rs) throws SQLException {
        Storage storage = new Storage(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"));
        return storage;
    }

    /**
     * Builds objects based on the info from the database
     * @param rs Result set
     * @return list of built storage objects
     * @throws SQLException
     */
    private List<Storage> buildObjects(ResultSet rs) throws SQLException {
        List<Storage> storages = new ArrayList<>();
        while(rs.next()) {
            storages.add(buildObject(rs));
        }
        return storages;
    }
}
