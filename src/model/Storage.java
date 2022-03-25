package model;

public class Storage {
	
	 /**
     * Fields for class Storage
     */
    private int id;
    private String name;
    private String address;

    /**
     * Constructor for class Storage
     * @param id
     * @param name
     * @param address
     */
    public Storage(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    /**
     * Constructor for class Storage
     * @param name
     * @param address
     */
    public Storage(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
		return "Storage [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
