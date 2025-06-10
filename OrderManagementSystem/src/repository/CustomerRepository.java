package repository;

import model.Customer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();
    private final String filePath = "customers.json";
    private final Gson gson = new Gson();

    public CustomerRepository() {
        load();
    }

    public void add(Customer customer) {
        customers.add(customer);
        save();
    }

    public List<Customer> getAll() {
        return customers;
    }

    public Customer findById(String id) {
        for (Customer c : customers) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    public Customer findByEmail(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) return c;
        }
        return null;
    }

    public Customer findByPhone(String phone) {
        for (Customer c : customers) {
            if (c.getPhone().equals(phone)) return c;
        }
        return null;
    }

    public void delete(String id) {
        customers.removeIf(c -> c.getId().equals(id));
        save();
    }

    public void save() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(customers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Customer>>(){}.getType();
            List<Customer> loaded = gson.fromJson(reader, listType);
            if (loaded != null) customers = loaded;
        } catch (IOException e) {
            // File may not exist on first run; that's OK
        }
    }
}