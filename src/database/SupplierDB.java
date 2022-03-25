package database;

import java.sql.*;
import java.util.*;

import database.interfaces.*;
import model.Supplier;

public class SupplierDB implements SupplierDBIF {

	/**
	 * Prepared statements for class SupplierDB
	 */
    private static final String FIND_ALL = "SELECT Id, Name, Address, Country, Phone, Email FROM Suppliers";
    private static final String FIND_SUPPLIER_BY_ID = "SELECT Id, Name, Address, Country, Phone, Email FROM Suppliers WHERE Id=?";
    private static final String CREATE_SUPPLIER = "INSERT INTO Suppliers (Name, Address, Country, Phone, Email) values(?, ?, ?, ?, ?) ";
    private static final String UPDATE_SUPPLIER = "UPDATE Suppliers SET Name = ?, Address = ?, Country = ?, Phone = ?, Email = ? FROM Suppliers WHERE Id = ?";
    private static final String DELETE_SUPPLIER = "DELETE FROM Suppliers WHERE Id = ?";

    /**
     * Fields for class SupplierDB
     */
    private PreparedStatement findAll;
	private PreparedStatement findSupplierById;
	private PreparedStatement createSupplier;
	private PreparedStatement updateSupplier;
	private PreparedStatement deleteSupplier;

	/**
     * Constructor for class SupplierDB
     * @throws SQLException
     */
    public SupplierDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findSupplierById = DBConnection.getInstance().getConnection().prepareStatement(FIND_SUPPLIER_BY_ID);
        createSupplier = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SUPPLIER);
        updateSupplier = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_SUPPLIER);
        deleteSupplier = DBConnection.getInstance().getConnection().prepareStatement(DELETE_SUPPLIER);
    }

    /**
     * Lists all suppliers in the database
     * @return list of suppliers
     * @throws SQLException
     */
    @Override
    public List<Supplier> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Supplier> suppliers = buildObjects(rs);
        return suppliers;
    }

    /**
     * Finds supplier by id
     * @param id The id of the supplier
     * @return supplier with given id
     * @throws SQLException
     */
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

    /**
     * Creates new supplier and adds it to the database
     * @param supplier The supplier to be added to the database
     * @throws SQLException
     */
    @Override
    public void createSupplier(Supplier supplier) throws SQLException {
        createSupplier.setString(1, supplier.getName());
        createSupplier.setString(2, supplier.getAddress());
        createSupplier.setString(3, supplier.getCountry());
        createSupplier.setString(4, supplier.getPhone());
        createSupplier.setString(5, supplier.getEmail());
        createSupplier.execute();
    }

    /**
     * Updates the information of the supplier
     * @param supplier The supplier to be updated
     * @throws SQLException
     */
    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        updateSupplier.setString(1, supplier.getName());
        updateSupplier.setString(2, supplier.getAddress());
        updateSupplier.setString(3, supplier.getCountry());
        updateSupplier.setString(4, supplier.getPhone());
        updateSupplier.setString(5, supplier.getEmail());
        updateSupplier.setInt(6, supplier.getId());
        updateSupplier.execute();
    }

    /**
     * Deletes a supplier form the database
     * @param supplier The supplier to be deleted
     */
    @Override
    public void deleteSupplier(Supplier supplier) throws SQLException {
        deleteSupplier.setInt(1, supplier.getId());
        deleteSupplier.execute();
    }

    /**
     * Builds the object based on the info from the database
     * @param rs Result set 
     * @return built supplier object with information from the rs
     * @throws SQLException
     */
    private Supplier buildObject(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier(rs.getInt("Id"), rs.getString("Name"), rs.getString("Address"), rs.getString("Country"), rs.getString("Phone"), rs.getString("Email"));
        return supplier;
    }

    /**
     * Builds objects based on the info from the database
     * @param rs Result set
     * @return list of built supplier objects
     * @throws SQLException
     */
    private List<Supplier> buildObjects(ResultSet rs) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        while(rs.next()) {
            suppliers.add(buildObject(rs));
        }
        return suppliers;
    }
    
}
