package model;

import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private Customer customer; // Embedded document
    private List<OrderItem> items;
    private Date orderDate;
    private double totalAmount;

    public Order() {}

    public Order(String id, Customer customer, List<OrderItem> items, Date orderDate, double totalAmount) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", items=" + items +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

