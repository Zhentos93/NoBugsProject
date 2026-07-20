package complex_tasks.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {
    private final Map<String, List<Product>> goods = new HashMap<>();
    private boolean isInventoryOpen = true;

    public void setInventoryOpen(boolean inventoryOpen) {
        isInventoryOpen = inventoryOpen;
    }

    public void addProduct(Product product) {
        if (!isInventoryOpen) {
            System.out.println("На данный момент добавление товара технически невозможно");
        } else {
            if (product == null) {
                throw new IllegalArgumentException("Product cannot be null");
            }

            if (product.getName() == null) {
                throw new IllegalArgumentException("Product name cannot be null");
            }

            if (product.getCategory() == null) {
                throw new IllegalArgumentException("Product category cannot be null");
            }

            if (product.getPrice() < 0) {
                throw new IllegalArgumentException("Product price cannot be negative");
            }

            String category = product.getCategory();

            if (!goods.containsKey(category)) {
                goods.put(category, new ArrayList<>());
            }

            List<Product> products = goods.get(category);
            products.add(product);
        }
    }

    public Product getProductByCategory(String category) throws OutOfStockException {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if (!goods.containsKey(category)) {
            throw new OutOfStockException("Category '" + category + "' not found");
        }

        List<Product> products = goods.get(category);

        if (products.isEmpty()) {
            throw new OutOfStockException("No products found in category '" + category + "'.");
        }

        Product product = products.getFirst();
        products.removeFirst();

        return product;
    }

    public List<Product> filterByCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        return goods.values()
                .stream()
                .flatMap(products -> products.stream())
                .filter(product -> product.getCategory().equals(category))
                .toList();
    }

    public List<Product> filterByPrice(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Min price cannot be greater than max price");
        }

        return goods.values()
                .stream()
                .flatMap(products -> products.stream())
                .filter(product -> product.getPrice() >= minPrice)
                .filter(product -> product.getPrice() <= maxPrice)
                .toList();
    }
}
