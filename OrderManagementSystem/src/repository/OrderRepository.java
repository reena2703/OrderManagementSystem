package repository;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import model.Order;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class OrderRepository {
    private List<Order> orders = new ArrayList<>();
    private final String filePath = "orders.json";
    private final Gson gson = new Gson();

    public OrderRepository() {
        load();
    }

    public void add(Order order) {
        orders.add(order);
        save();
    }

    public List<Order> getAll() {
        return orders;
    }

    public Order findById(String id) {
        for (Order o : orders) {
            if (o.getId().equals(id)) return o;
        }
        return null;
    }

    public List<Order> findByCustomerId(String customerId) {
        List<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getId().equals(customerId)) {
                result.add(o);
            }
        }
        return result;
    }

    public void delete(String id) {
        orders.removeIf(o -> o.getId().equals(id));
        save();
    }

    public void deleteByCustomerId(String customerId) {
        orders.removeIf(o -> o.getCustomer().getId().equals(customerId));
        save();
    }

    public void save() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(orders, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Order>>(){}.getType();
            List<Order> loaded = gson.fromJson(reader, listType);
            if (loaded != null) orders = loaded;
        } catch (IOException e) {
            // File may not exist on first run; that's OK
        }
    }
}