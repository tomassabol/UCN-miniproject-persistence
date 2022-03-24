package model;

import java.math.BigDecimal;

public class Product{

    /**
     * Fields for class Product
     */
    private int id;
    private String name;
    private BigDecimal price;
    private int inStock;
    private Supplier supplier;

    /**
     * Constructor for class Product
     * @param id
     * @param name
     * @param price
     * @param inStock
     * @param supplier
     */
    public Product(int id, String name, BigDecimal price, int inStock, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.inStock = inStock;
        this.supplier = supplier;
    }

    /**
     * Constructor for class Product
     * @param name
     * @param price
     * @param inStock
     * @param supplier
     */
    public Product(String name, BigDecimal price, int inStock, Supplier supplier) {
        this.name = name;
        this.inStock = inStock;
        this.supplier = supplier;
    }
    
    //GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String toString() {
        return "Order [id=" + id + ", name=" + name + ", price=" + price + ", inStock=" + inStock + ", supplier=" + supplier + "]";
    }
    
}