package model;

public class CustomerType {
    
	 /**
     * Fields for class CustomerType
     */
    private int id;
    private String name;
    private int discount;

    /**
     * Constructor for class CustomerType
     * @param id
     * @param name
     * @param discount
     */
    public CustomerType(int id, String name, int discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    /**
     * Constructor for class CustomerType
     * @param name
     * @param discount
     */
    public CustomerType(String name, int discount) {
        this.name = name;
        this.discount = discount;
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

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String toString() {
		return "CustomerType [id=" + id + ", name=" + name + ", discount=" + discount + "]";
	}

}
