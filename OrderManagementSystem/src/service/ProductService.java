package service;

import model.Product;
import repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.add(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id);
    }

    public void updateProductPrice(String productId, double newPrice) {
        Product product = productRepository.findById(productId);
        if (product != null) {
            product.setPrice(newPrice);
            productRepository.update(product);
        }
    }

    public void deleteProduct(String productId) {
        productRepository.delete(productId);
    }
}