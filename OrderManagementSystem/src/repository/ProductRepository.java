package repository;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import model.Product;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    private final String filePath = "products.json";
    private final Gson gson = new Gson();

    public ProductRepository() {
        load();
    }

    public void add(Product product) {
        products.add(product);
        save();
    }

    public List<Product> getAll() {
        return products;
    }

    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    public void update(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(updatedProduct.getId())) {
                products.set(i, updatedProduct);
                save();
                return;
            }
        }
    }

    public void delete(String id) {
        products.removeIf(p -> p.getId().equals(id));
        save();
    }

    public void save() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
            List<Product> loaded = gson.fromJson(reader, listType);
            if (loaded != null) products = loaded;
        } catch (IOException e) {
            // File may not exist on first run; that's OK
        }
    }
}
