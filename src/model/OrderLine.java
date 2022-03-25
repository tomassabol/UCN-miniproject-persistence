package model;

import java.math.BigDecimal;

public class OrderLine {
	
	 /**
     * Fields for class OrderLine
     */
    private Product product;
    private int quantity;
    private BigDecimal calculatedPrice;

    /**
     * Constructor for class OrderLine
     * @param product
     * @param quantity
     */
    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.calculatedPrice = BigDecimal.valueOf(0);
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

    public BigDecimal calculateOrderLinePrice() {
        BigDecimal result = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        // set calculated price
        setCalculatedPrice(result);
        return result;
    }

    public String toString() {
		return "OrderLine [" + "product=" + product + ", quantity=" + quantity + ", pric=" + calculatedPrice + "]";
	}

}
