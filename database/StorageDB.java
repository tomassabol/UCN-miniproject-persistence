package database;

import database.interfaces.*;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorageDB implements StorageDBIF {
    private static final String FIND_ALL = "SELECT Id, Name, Address FROM Storages";
    private static final String FIND_STORAGE_BY_ID = "SELECT Id, Name, Address FROM Storages WHERE Id=?";
    private static final String CREATE_STORAGE = "INSERT INTO Storages (Id, Name, Address) values(?, ?, ?) ";
    private static final String UPDATE_STORAGE = "UPDATE Storages SET Name = ?, Address = ? FROM Storages WHERE Id = ?";
    private static final String DELETE_STORAGE = "DELETE FROM Storages WHERE Id = ?";

    private PreparedStatement findAll;
	private PreparedStatement findStorageById;
	private PreparedStatement createStorage;
	private PreparedStatement updateStorage;
	private PreparedStatement deleteStorage;

    public StorageDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findStorageById = DBConnection.getInstance().getConnection().prepareStatement(FIND_STORAGE_BY_ID);
        createStorage = DBConnection.getInstance().getConnection().prepareStatement(CREATE_STORAGE);
        updateStorage = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_STORAGE);
        deleteStorage = DBConnection.getInstance().getConnection().prepareStatement(DELETE_STORAGE);
    }
    
    @Override
    public List<Storage> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Storage> storages =  buildObjects(rs);
        return storages;
    }

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

    @Override
    public void createStorage(Storage storage) throws SQLException {
        createStorage.setString(1, storage.getName());
        createStorage.setString(2, storage.getAddress());
        createStorage.execute();
    }

    @Override
    public void updateStorage(Storage storage) throws SQLException {
        updateStorage.setString(1, storage.getName());
        updateStorage.setString(2, storage.getAddress());
        updateStorage.execute();
    }

    @Override
    public void deleteStorage(Storage storage) throws SQLException {
        deleteStorage.setInt(1, storage.getId());
        deleteStorage.execute();
    }

    private Storage buildObject(ResultSet rs) throws SQLException {
        Storage storage = new Storage(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"));
        return storage;
    }

    private List<Storage> buildObjects(ResultSet rs) throws SQLException {
        List<Storage> storages = new ArrayList<>();
        while(rs.next()) {
            storages.add(buildObject(rs));
        }
        return storages;
    }
}
