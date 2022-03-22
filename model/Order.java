package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class Order {
    private int id;
    private Date date;
    private BigDecimal totalPrice;
    private Customer customer;
    private ArrayList<OrderLine> orderLines;

    public Order(int id, Date date, BigDecimal totalPrice, Customer customer) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return this.orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public String toString() {
		return "Order [id=" + id + ", date=" + date + ", total=" + totalPrice + ", customer=" + customer + "]";
	}

}
