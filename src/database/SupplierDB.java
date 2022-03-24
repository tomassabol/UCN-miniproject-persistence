package database;

import java.sql.*;
import java.util.*;

import database.interfaces.*;
import model.Supplier;

public class SupplierDB implements SupplierDBIF {

    private static final String FIND_ALL = "SELECT Id, Name, Address, Country, Phone, Email FROM Suppliers";
    private static final String FIND_SUPPLIER_BY_ID = "SELECT Id, Name, Address, Country, Phone, Email FROM Suppliers WHERE Id=?";
    private static final String CREATE_SUPPLIER = "INSERT INTO Suppliers (Name, Address, Country, Phone, Email) values(?, ?, ?, ?, ?) ";
    private static final String UPDATE_SUPPLIER = "UPDATE Suppliers SET Name = ?, Address = ?, Country = ?, Phone = ?, Email = ? FROM Suppliers WHERE Id = ?";
    private static final String DELETE_SUPPLIER = "DELETE FROM Suppliers WHERE Id = ?";

    private PreparedStatement findAll;
	private PreparedStatement findSupplierById;
	private PreparedStatement createSupplier;
	private PreparedStatement updateSupplier;
	private PreparedStatement deleteSupplier;

    public SupplierDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findSupplierById = DBConnection.getInstance().getConnection().prepareStatement(FIND_SUPPLIER_BY_ID);
        createSupplier = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SUPPLIER);
        updateSupplier = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_SUPPLIER);
        deleteSupplier = DBConnection.getInstance().getConnection().prepareStatement(DELETE_SUPPLIER);
    }

    @Override
    public List<Supplier> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Supplier> suppliers = buildObjects(rs);
        return suppliers;
    }

    @Override
    public Supplier findSupplierById(int id) throws SQLException {
        Supplier supplier = null;
        ResultSet rs;
        findSupplierById.setInt(1, id);
        rs = findSupplierById.executeQuery();
        rs.next();
        supplier = buildObject(rs);
        return supplier;
    }

    @Override
    public void createSupplier(Supplier supplier) throws SQLException {
        int id;
        createSupplier.setString(1, supplier.getName());
        createSupplier.setString(2, supplier.getAddress());
        createSupplier.setString(3, supplier.getCountry());
        createSupplier.setString(4, supplier.getPhone());
        createSupplier.setString(5, supplier.getEmail());
        id = DBConnection.getInstance().executeInsertWithIdentity(createSupplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        int id;
        updateSupplier.setString(1, supplier.getName());
        updateSupplier.setString(2, supplier.getAddress());
        updateSupplier.setString(3, supplier.getCountry());
        updateSupplier.setString(4, supplier.getPhone());
        updateSupplier.setString(5, supplier.getEmail());
        id = DBConnection.getInstance().executeUpdate(updateSupplier);
    }

    @Override
    public void deleteSupplier(Supplier supplier) throws SQLException {
        deleteSupplier.setInt(1, supplier.getId());
        deleteSupplier.execute();
    }

    private Supplier buildObject(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"), rs.getString("Country"), rs.getString("Phone"), rs.getString("Email"));
        return supplier;
    }

    private List<Supplier> buildObjects(ResultSet rs) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        while(rs.next()) {
            suppliers.add(buildObject(rs));
        }
        return suppliers;
    }
    
}
