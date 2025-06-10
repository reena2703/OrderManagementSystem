package service;

import model.Customer;
import model.Order;
import model.OrderItem;
import model.Product;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(String orderId, Customer customer, List<OrderItem> items) {
        double totalAmount = 0.0;
        for (OrderItem item : items) {
            double subtotal = item.getProduct().getPrice() * item.getQuantity();
            item.setSubtotal(subtotal);
            totalAmount += subtotal;
        }
        Order order = new Order(orderId, customer, items, new Date(), totalAmount);
        orderRepository.add(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }

    public List<Order> findOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(String orderId) {
        orderRepository.delete(orderId);
    }

    // Find orders by customer email or phone
    public List<Order> findOrdersByCustomerEmailOrPhone(String email, String phone) {
        List<Order> result = new ArrayList<>();
        for (Order order : orderRepository.getAll()) {
            Customer customer = order.getCustomer();
            if ((email != null && customer.getEmail().equalsIgnoreCase(email)) ||
                    (phone != null && customer.getPhone().equals(phone))) {
                result.add(order);
            }
        }
        return result;
    }
}
