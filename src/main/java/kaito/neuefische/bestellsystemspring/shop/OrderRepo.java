package kaito.neuefische.bestellsystemspring.shop;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepo {

    private Map<String, Order> orders;

    OrderRepo() {
        this.orders = new HashMap<>();
    }

    Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    Map<String, Order> listOrders() {
        System.out.println("--- ALL ORDERS: ---");
        for (Map.Entry<String, Order> entry : orders.entrySet()) {
            System.out.println(entry.getValue());
        }
        return orders;
    }

    void deleteOrder(String orderId){
        orders.remove(orderId);
    }

    void addOrder(Order order) {
        orders.put(order.orderId, order);
    }

}
