package database.interfaces;

import java.sql.*;
import java.util.*;

import model.StorageLine;

public interface StorageLineDBIF {
    public List<StorageLine> findAll() throws SQLException;
    public StorageLine findById(int id) throws SQLException;
    public void createOrderLine(StorageLine storageLine) throws SQLException;
    public void updateOrderLine(StorageLine storageLine) throws SQLException;
    public void deleteOrderLine(StorageLine storageLine) throws SQLException;
}
