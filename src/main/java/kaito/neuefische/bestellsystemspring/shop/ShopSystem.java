package kaito.neuefische.bestellsystemspring.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ShopSystem {

    ProductRepo productRepo;
    OrderRepo orderRepo;

    @Autowired
    public ShopSystem(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Product getProduct(String productId) {
        System.out.println(productRepo.getProduct(productId));
        return productRepo.getProduct(productId);
    }

    public Map<String, Product> listProducts() {
        return productRepo.listProducts();
    }

    public void addOrder(String orderId, List<String> orderData) {
       //  String orderId = UUID.randomUUID().toString().substring(0, 4);
        Order newOrder = new Order(orderId);
        for (String productId : orderData) {
            newOrder.order.put(productId, productRepo.getProduct(productId));
        }
        orderRepo.addOrder(newOrder);
    }

    public void deleteOrder(String orderId){
        orderRepo.deleteOrder(orderId);
    }

    public Order getOrder(String orderId) {
        System.out.println(orderRepo.getOrder(orderId));
        return orderRepo.getOrder(orderId);
    }

    public Map<String, Order> listOrders() {
        return orderRepo.listOrders();
    }
}
