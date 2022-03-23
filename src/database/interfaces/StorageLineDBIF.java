package database.interfaces;

import java.sql.*;
import java.util.*;

import model.StorageLine;

public interface StorageLineDBIF {
    public List<StorageLine> findAll() throws SQLException;
    public StorageLine findById(int id) throws SQLException;
    public void createStorageLine(StorageLine storageLine) throws SQLException;
    public void updateStorageLine(StorageLine storageLine) throws SQLException;
    public void deleteStorageLine(StorageLine storageLine) throws SQLException;
}
