package kaito.neuefische.bestellsystemspring.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    ShopSystem shopSystem;

    @Autowired
    public ShopController(ShopSystem shopSystem) {
        this.shopSystem = shopSystem;
    }

    @GetMapping("/products")
    public Map<String, Product> listProducts() {
       return shopSystem.listProducts();
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable String productId){
        return shopSystem.getProduct(productId);
    }

    @GetMapping("/orders")
    public Map<String, Order> listOrders(){
        return shopSystem.listOrders();
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable String orderId){
        return shopSystem.getOrder(orderId);
    }

    @PostMapping("/orders/{orderId}")
    public void addOrder(@PathVariable String orderId, @RequestBody List<Integer> productIdArr){
        List<String> orderData = new ArrayList<>();
        for (int productId : productIdArr) {
            orderData.add(String.valueOf(productId));
        }
        shopSystem.addOrder(orderId, orderData);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrder(@PathVariable String orderId){
        shopSystem.deleteOrder(orderId);
    }

}
