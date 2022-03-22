package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Supplier;

public interface SupplierDBIF {
    public List<Supplier> findAll() throws SQLException;
    public Supplier findStorageById(int id) throws SQLException;
    public void createStorage(Supplier supplier) throws SQLException;
    public void updateStorage(Supplier supplier) throws SQLException;
    public void deleteStorage(Supplier supplier) throws SQLException;
}
