## Mini Order Management System (Java + JSON)

## Overview

This is a Java-based mini e-commerce order management system that simulates a MongoDB-style backend using JSON files for data storage. The application supports CRUD operations for customers, products, and orders, and demonstrates document embedding, indexing logic, and query-like filtering—all without a real database.

## ************ ✅ Features ************

1.Add, list, and delete customers

2.Add, list, update, and delete products

3.Create orders with embedded customer and item details

4.List all orders with customer and item details

5.Find orders by customer email or phone

6.Calculate and store total amount during order creation

7.Update a product’s price (future orders use new price)

8.Delete a customer and all associated orders

9.Data stored as JSON documents (Gson)


## ************ 🧰 Technologies Used ************

- Java (OOP + Collections)
- Gson Library (for JSON serialization/deserialization)
- In-memory data structures (List, HashMap)
- IntelliJ IDEA (Recommended IDE)


## Assumptions

Customer and product IDs are generated as UUIDs.

Data is persisted in JSON files in the project root directory.

Deleting a customer also deletes all their orders.

Product price updates only affect future orders.

No authentication or user roles are implemented.


## ************ 📁 Project structure ************

OrderManagementSystem/

├── src/

│ ├── libs/

│   └── gson-2.8.9.jar

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

Explanation:

libs/ — Contains external libraries (like Gson JAR).

src/ — All your Java source code.

model/ — Entity classes (Customer, Product, Order, OrderItem).

repository/ — Data access and JSON persistence.

service/ — Business logic.

Main.java — Entry point with CLI.

customers.json, products.json, orders.json — Data files generated at runtime.

README.md — Project documentation.

## ************ ▶️How to Run ************

### Requirements:

Java 8 or higher
Gson library (already included in libs/)
IntelliJ IDEA or any Java IDE

### Setup:

Clone or unzip the project.
Open the project in IntelliJ.
Ensure libs/gson-2.x.x.jar is added to your project dependencies (see instructions above if needed).

### Run:

Run Main.java.
Use the menu to interact with the system.

## 🧪Sample Input/Output

--- Mini Order Management System ---
1. Add Customer
2. Add Product
3. Add Order
4. List All Orders
5. Find Orders by Customer Email/Phone
6. Update Product Price
7. Delete Customer and Their Orders
0. Exit


### option: 1
Enter customer name: Alice

Enter customer email: alice@example.com

Enter customer phone: 1234567890

Customer added with ID: 123e4567-e89b-12d3-a456-426614174000

### option: 2
Enter product name: Laptop

Enter product price: 1200

Product added with ID: 223e4567-e89b-12d3-a456-426614174001

###  option: 3
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
- 
  Total: $1200.0
