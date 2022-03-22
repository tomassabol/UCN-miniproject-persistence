package model;

import java.math.BigDecimal;

public class Product{

    /**
     * Fields for class Product
     */
    private int id;
    private String name;
    private BigDecimal price;
    private String countryOfOrigin;
    private int inStock;
    private Supplier supplier;

    /**
     * Constructor for class Product
     * @param id
     * @param name
     * @param price
     * @param countryOfOrigin
     * @param inStock
     * @param supplier
     */
    private Product(int id, String name, BigDecimal price, String countryOfOrigin, int inStock, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
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

    public String getCountryOfOrigin() {
        return countryOfOrigin;
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

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String toString() {
        return "Order [id=" + id + ", name=" + name + ", price=" + price + ", country of origin=" + countryOfOrigin + ", inStock=" + inStock + ", supplier=" + supplier + "]";
    }
    
}