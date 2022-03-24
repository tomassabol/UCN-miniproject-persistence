	package controller;

import java.sql.SQLException;
import java.util.List;

import database.StorageDB;
import database.interfaces.StorageDBIF;
import model.Storage;


public class StorageController {
    
    private StorageDBIF StorageDBIF;

    /**
     * The constuctor for the class 
     * @throws SQLException
     */

    public StorageController() throws SQLException {
        StorageDBIF = new StorageDB();
    }

    /**
     * 
     * @return Returns all the storages 
     * @throws SQLException
     */

    public List<Storage> findAll() throws SQLException {
        return StorageDBIF.findAll();
    }

    /**
     * Returns a storage by id
     * @param id The id of the storage 
     * @return Returns storage
     * @throws SQLException
     */
    public Storage findStorageById(int id) throws SQLException {
        return StorageDBIF.findStorageById(id);
    }

    /**
     * Creates a new storage
     * @param name The name of the new storage
     * @param address The address of the new storage
     * @throws SQLException
     */

    public void createStorage(String name, String address) throws SQLException {
        Storage stor = new Storage(name, address);
        StorageDBIF.createStorage(stor);
    }

    /**
     * Deletes a storage 
     * @param storage The storage that needs to be deleted
     * @throws SQLException
     */

    public void deleteStorage(Storage storage) throws SQLException {
        StorageDBIF.deleteStorage(storage);
    }

      /**
     * Updates the information of a storage
     * @param customer The storage whose information will be updated
     * @param name The new name of the storage
     * @param address The new address of the storage
     * @throws SQLException
     */
    public void updateStorage(Storage storage, String name, String address) throws SQLException {
        storage.setName(name);
        storage.setAddress(address);
        StorageDBIF.updateStorage(storage);
    }
}


    

