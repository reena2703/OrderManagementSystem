# Mini Order Management System (Java + JSON)

## Overview

This project is a comprehensive e-commerce order management system built in Java, with JSON-based persistence. It demonstrates enterprise-level software design and is structured for maintainability and clarity. All core business data is stored in JSON files, simulating a MongoDB-style backend.

---

## Purpose and Scope

- **Complete CRUD:** Customers, products, and orders can be fully created, read, updated, and deleted.
- **NoSQL-style Embedding:** Orders embed full customer and item details, maintaining data consistency and historical price integrity.
- **Layered Architecture:** Clear separation of concerns between UI, business logic, data access, and persistence.
- **Enterprise Patterns:** Implements complex business operations (cascading deletes, cross-entity queries) while using simple file-based persistence.
- **Data Integrity:** Ensures consistency and reliability through design and document embedding.

---

## System Architecture

This system uses a classic layered architecture:
 

![Screenshot (7)](https://github.com/user-attachments/assets/95b35dd2-fd1e-45c3-8f2a-edf9328b2ea8)

- **Presentation Layer:** Command-Line Interface (CLI) for user interaction.
- **Business Logic Layer:** Services coordinate operations, enforce business rules, and handle cross-entity logic.
- **Repository/Data Access Layer:** Encapsulates JSON persistence; each entity has its own repository.
- **Persistence Layer:** Data is stored in JSON files with in-memory caching to optimize read/write operations.

**Technologies Used:**

| Component     | Technology               | Purpose                       |
|---------------|--------------------------|-------------------------------|
| Language      | Java 8+                  | Core application development  |
| JSON Library  | Gson 2.8.9               | Serialization/deserialization |
| Data Storage  | JSON files               | File-based persistence        |
| Collections   | Java Collections         | In-memory data management     |
| UI            | Command-Line Interface   | User interaction              |

---

## Core Features

### Customer Management
- **Add Customer:** Create customer records (UUID-based IDs).
- **Delete Customer:** Remove customer with cascading delete of orders.

### Product Management
- **Add Product:** Add catalog entries with price.
- **Update Product Price:** Change price for future orders (historical orders preserve original price).

### Order Processing
- **Create Order:** Multi-step process with customer lookup, product selection, and total calculation.
- **List All Orders:** Display complete order history, with embedded customer and item data.
- **Find Orders by Customer:** Query with customer email or phone.

### Data Integrity
- **Document Embedding:** Orders store full customer and product details at time of creation.
- **Historical Price Preservation:** Orders retain price-at-purchase for historical accuracy.
- **Cascading Deletes:** Customer deletion removes all their orders.

---

## Key Design Decisions

- **Document Embedding:** Orders store embedded details (not just IDs), optimizing for reads and historical accuracy.
- **JSON Persistence:** Each entity is persisted in its own JSON file (`customers.json`, `products.json`, `orders.json`).
- **Service Coordination:** Operations like order creation are coordinated across multiple services (CustomerService, ProductService, OrderService).
- **Single Entry Point:** All operations are accessible via a menu-driven CLI in the `Main` class.

---

## Project Structure

```
OrderManagementSystem/
├── src/
│   ├── libs/
│   │   └── gson-2.8.9.jar
│   ├── model/
│   │   ├── Customer.java
│   │   ├── Product.java
│   │   ├── OrderItem.java
│   │   └── Order.java
│   ├── repository/
│   │   ├── CustomerRepository.java
│   │   ├── ProductRepository.java
│   │   └── OrderRepository.java
│   ├── service/
│   │   ├── CustomerService.java
│   │   ├── ProductService.java
│   │   └── OrderService.java
│   └── Main.java
├── customers.json
├── products.json
├── orders.json
└── README.md
```

---

## ▶ How to Run

### Requirements

- Java 8 or higher
- Gson library (included in `libs/`)
- IntelliJ IDEA or any Java IDE

### Setup

1. Clone or unzip the project.
2. Open the project in your IDE.
3. Add `libs/gson-2.8.9.jar` to your dependencies.

### Run

1. Run `Main.java`.
2. Use the CLI menu to perform system operations.

---

 
##   Menu Options & Service Integration

| Menu Option             | Service Integration            | Primary Function          |
|-------------------------|--------------------------------|---------------------------|
| 1. Add Customer         | CustomerService                | Customer registration     |
| 2. Add Product          | ProductService                 | Catalog management        |
| 3. Add Order            | OrderService + coordination    | Order processing          |
| 4. List All Orders      | OrderService                   | Order reporting           |
| 5. Find Orders          | OrderService + CustomerService | Customer order lookup     |
| 6. Update Product Price | ProductService                 | Price management          |
| 7. Delete Customer      | CustomerService + OrderService | Cascading deletion        |
| 0. Exit                 | System termination             | Application shutdown      |

```
##  Sample Input/Output

### option: 1
Enter customer name: Alice
Enter customer email: alice@example.com
Enter customer phone: 1234567890
Customer added with ID: 123e4567-e89b-12d3-a456-426614174000

### option: 2
Enter product name: Laptop
Enter product price: 1200
Product added with ID: 223e4567-e89b-12d3-a456-426614174001

### option: 3
Enter customer email for order: alice@example.com
Available products:
223e4567-e89b-12d3-a456-426614174001: Laptop ($1200.0)
Enter product ID to add to order (or 'done' to finish): 223e4567-e89b-12d3-a456-426614174001
Enter quantity: 1
Enter product ID to add to order (or 'done' to finish): done
Order placed with ID: 323e4567-e89b-12d3-a456-426614174002

### option: 4
Order ID: 323e4567-e89b-12d3-a456-426614174002
Customer: Alice (alice@example.com)
Order Date: Sat Jun 08 20:00:00 IST 2024
Items:
- Laptop x1 ($1200.0) = $1200.0
Total: $1200.0
```

---
