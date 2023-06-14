package kaito.neuefische.bestellsystemspring.shop;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepo {
    private Product product1 = new Product("1", "Brot");
    private Product product2 = new Product("2", "Milch");
    private Product product3 = new Product("3", "Eier");
    private Product product4 = new Product("4", "Käse");
    private Product product5 = new Product("5", "Wurst");
    private Map<String, Product> products;

    ProductRepo() {
        this.products = new HashMap<>();
        products.put(product1.getProductId(), product1);
        products.put(product2.getProductId(), product2);
        products.put(product3.getProductId(), product3);
        products.put(product4.getProductId(), product4);
        products.put(product5.getProductId(), product5);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    Map<String, Product> listProducts() {
        System.out.println("--- ALL PRODUCTS: ---");
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            System.out.println(products.get(entry.getKey()));
        }
        return products;
    }

    @Override
    public String toString() {
        return "ProductRepo{" +
                "product1=" + product1 +
                ", product2=" + product2 +
                ", product3=" + product3 +
                ", product4=" + product4 +
                ", product5=" + product5 +
                ", products=" + products +
                '}';
    }
}
