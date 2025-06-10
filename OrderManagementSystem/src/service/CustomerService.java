package service;

import model.Customer;
import repository.CustomerRepository;
import repository.OrderRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public void addCustomer(Customer customer) {
        customerRepository.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    public Customer findById(String id) {
        return customerRepository.findById(id);
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    public void deleteCustomer(String customerId) {
        // Delete all orders for this customer
        orderRepository.deleteByCustomerId(customerId);
        // Delete the customer
        customerRepository.delete(customerId);
    }
}