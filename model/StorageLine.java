package model;

public class StorageLine {
    private int id;
    private Product product;
    private int quantity;
    private Storage storage;

    public StorageLine(int id, Product product, int quantity, Storage storage) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.storage = storage;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String toString() {
		return "StorageLine [id=" + id + ", Product=" + product + ", Quantity=" + quantity +  ", Storage=" + storage + "]";
	}

}
