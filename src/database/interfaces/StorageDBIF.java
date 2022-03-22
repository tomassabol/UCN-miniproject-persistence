package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Storage;

public interface StorageDBIF {
    public List<Storage> findAll() throws SQLException;
    public Storage findStorageById(int id) throws SQLException;
    public void createStorage(Storage storage) throws SQLException;
    public void updateStorage(Storage storage) throws SQLException;
    public void deleteStorage(Storage storage) throws SQLException;
}
