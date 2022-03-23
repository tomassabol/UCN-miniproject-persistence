package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Supplier;

public interface SupplierDBIF {
    public List<Supplier> findAll() throws SQLException;
    public Supplier findSupplierById(int id) throws SQLException;
    public void createSupplier(Supplier supplier) throws SQLException;
    public void updateSupplier(Supplier supplier) throws SQLException;
    public void deleteSupplier(Supplier supplier) throws SQLException;
}
