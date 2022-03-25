package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class Order {
	
	 /**
     * Fields for class Order
     */
    private int id;
    private Date date;
    private BigDecimal totalPrice;
    private Customer customer;
    private ArrayList<OrderLine> orderLines;

    /**
     * Constructor for class Order
     * @param id
     * @param date
     * @param customer
     */
    public Order(int id, Date date, Customer customer) {
        this.id = id;
        this.date = date;
        this.totalPrice = BigDecimal.valueOf(0);
        this.customer = customer;
        this.orderLines = new ArrayList<>();
    }

    /**
     * Constructor for class Order
     * @param date
     * @param customer
     */
    public Order(Date date, Customer customer) {
        this.date = date;
        this.totalPrice = BigDecimal.valueOf(0);
        this.customer = customer;
        this.orderLines = new ArrayList<>();
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

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public String toString() {
		return "Order [id=" + id + ", date=" + date + ", total=" + totalPrice + ", customer=" + customer.getName() + "]";
	}

}
