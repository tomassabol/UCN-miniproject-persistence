package model;

import java.math.BigDecimal;

public class OrderLine {
    private int id;
    private Product product;
    private int quantity;
    private BigDecimal calculatedPrice;


    public OrderLine(int id, Product product, int quantity, BigDecimal calculatedPrice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.calculatedPrice = calculatedPrice;
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

    public BigDecimal getCalculatedPrice() {
        return this.calculatedPrice;
    }

    public void setCalculatedPrice(BigDecimal calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public String toString() {
		return "OrderLine [id=" + id + ", product=" + product + ", quantity=" + quantity + ", pric=" + calculatedPrice + "]";
	}

}
