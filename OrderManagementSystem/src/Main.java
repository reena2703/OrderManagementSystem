import model.*;
import repository.*;
import service.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instantiate repositories
        CustomerRepository customerRepo = new CustomerRepository();
        ProductRepository productRepo = new ProductRepository();
        OrderRepository orderRepo = new OrderRepository();

        // Instantiate services
        CustomerService customerService = new CustomerService(customerRepo, orderRepo);
        ProductService productService = new ProductService(productRepo);
        OrderService orderService = new OrderService(orderRepo);

        while (true) {
            System.out.println("\n--- Mini Order Management System ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Add Order");
            System.out.println("4. List All Orders");
            System.out.println("5. Find Orders by Customer Email/Phone");
            System.out.println("6. Update Product Price");
            System.out.println("7. Delete Customer and Their Orders");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Add Customer
                    System.out.print("Enter customer name: ");
                    String cname = scanner.nextLine();
                    System.out.print("Enter customer email: ");
                    String cemail = scanner.nextLine();
                    System.out.print("Enter customer phone: ");
                    String cphone = scanner.nextLine();
                    String cid = UUID.randomUUID().toString();
                    Customer customer = new Customer(cid, cname, cemail, cphone);
                    customerService.addCustomer(customer);
                    System.out.println("Customer added with ID: " + cid);
                    break;
                case 2:
                    // Add Product
                    System.out.print("Enter product name: ");
                    String pname = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double pprice = Double.parseDouble(scanner.nextLine());
                    String pid = UUID.randomUUID().toString();
                    Product product = new Product(pid, pname, pprice);
                    productService.addProduct(product);
                    System.out.println("Product added with ID: " + pid);
                    break;
                case 3:
                    // Add Order
                    System.out.print("Enter customer email for order: ");
                    String orderEmail = scanner.nextLine();
                    Customer orderCustomer = customerService.findByEmail(orderEmail);
                    if (orderCustomer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }
                    List<Product> allProducts = productService.getAllProducts();
                    if (allProducts.isEmpty()) {
                        System.out.println("No products available. Add products first.");
                        break;
                    }
                    List<OrderItem> items = new ArrayList<>();
                    while (true) {
                        System.out.println("Available products:");
                        for (Product p : allProducts) {
                            System.out.println(p.getId() + ": " + p.getName() + " ($" + p.getPrice() + ")");
                        }
                        System.out.print("Enter product ID to add to order (or 'done' to finish): ");
                        String prodId = scanner.nextLine();
                        if (prodId.equalsIgnoreCase("done")) break;
                        Product selected = productService.findById(prodId);
                        if (selected == null) {
                            System.out.println("Product not found.");
                            continue;
                        }
                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        items.add(new OrderItem(selected, qty, 0)); // subtotal will be set in service
                    }
                    if (items.isEmpty()) {
                        System.out.println("No items added to order.");
                        break;
                    }
                    String orderId = UUID.randomUUID().toString();
                    orderService.addOrder(orderId, orderCustomer, items);
                    System.out.println("Order placed with ID: " + orderId);
                    break;
                case 4:
                    // List All Orders
                    List<Order> orders = orderService.getAllOrders();
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        for (Order o : orders) {
                            System.out.println("\nOrder ID: " + o.getId());
                            System.out.println("Customer: " + o.getCustomer().getName() + " (" + o.getCustomer().getEmail() + ")");
                            System.out.println("Order Date: " + o.getOrderDate());
                            System.out.println("Items:");
                            for (OrderItem item : o.getItems()) {
                                System.out.println("  - " + item.getProduct().getName() + " x" + item.getQuantity() + " ($" + item.getProduct().getPrice() + ") = $" + item.getSubtotal());
                            }
                            System.out.println("Total: $" + o.getTotalAmount());
                        }
                    }
                    break;
                case 5:
                    // Find Orders by Customer Email/Phone
                    System.out.print("Enter customer email (or leave blank): ");
                    String searchEmail = scanner.nextLine();
                    System.out.print("Enter customer phone (or leave blank): ");
                    String searchPhone = scanner.nextLine();
                    if (searchEmail.isEmpty()) searchEmail = null;
                    if (searchPhone.isEmpty()) searchPhone = null;
                    List<Order> foundOrders = orderService.findOrdersByCustomerEmailOrPhone(searchEmail, searchPhone);
                    if (foundOrders.isEmpty()) {
                        System.out.println("No orders found for given criteria.");
                    } else {
                        for (Order o : foundOrders) {
                            System.out.println("\nOrder ID: " + o.getId());
                            System.out.println("Customer: " + o.getCustomer().getName() + " (" + o.getCustomer().getEmail() + ")");
                            System.out.println("Order Date: " + o.getOrderDate());
                            System.out.println("Items:");
                            for (OrderItem item : o.getItems()) {
                                System.out.println("  - " + item.getProduct().getName() + " x" + item.getQuantity() + " ($" + item.getProduct().getPrice() + ") = $" + item.getSubtotal());
                            }
                            System.out.println("Total: $" + o.getTotalAmount());
                        }
                    }
                    break;
                case 6:
                    // Update Product Price
                    List<Product> products = productService.getAllProducts();
                    if (products.isEmpty()) {
                        System.out.println("No products available.");
                        break;
                    }
                    System.out.println("Available products:");
                    for (Product p : products) {
                        System.out.println(p.getId() + ": " + p.getName() + " ($" + p.getPrice() + ")");
                    }
                    System.out.print("Enter product ID to update: ");
                    String upId = scanner.nextLine();
                    Product upProduct = productService.findById(upId);
                    if (upProduct == null) {
                        System.out.println("Product not found.");
                        break;
                    }
                    System.out.print("Enter new price: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());
                    productService.updateProductPrice(upId, newPrice);
                    System.out.println("Product price updated.");
                    break;
                case 7:
                    // Delete Customer and Their Orders
                    System.out.print("Enter customer email to delete: ");
                    String delEmail = scanner.nextLine();
                    Customer delCustomer = customerService.findByEmail(delEmail);
                    if (delCustomer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }
                    customerService.deleteCustomer(delCustomer.getId());
                    System.out.println("Customer and their orders deleted.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
