package kaito.neuefische.bestellsystemspring.shop;

import java.util.HashMap;
import java.util.Map;

class Order {

    String orderId;

    Map<String, Product> order;

    public Order(String orderId) {
        this.orderId = orderId;
        this.order = new HashMap<>();
    }

    @Override
    public String toString() {
        return "orderId: '" + orderId + '\'' +
                ", order: " + order;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Map<String, Product> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Product> order) {
        this.order = order;
    }
}
