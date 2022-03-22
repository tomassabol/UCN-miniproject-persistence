package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Invoice {
    private int id;
    private Order order;
    private Date paymentDate;
    private BigDecimal price;

    public Invoice(int id, Order order, Date date, BigDecimal price) {
        this.id = id;
        this.order = order;
        this.paymentDate = date;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString() {
		return "Invoice [id=" + id + ", order=" + order + ", payment date=" + paymentDate + ", price=" + price + "]";
	}

}
