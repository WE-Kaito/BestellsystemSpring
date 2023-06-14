package kaito.neuefische.bestellsystemspring.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShopSystemTest {

    ProductRepo productRepo = mock(ProductRepo.class);
    OrderRepo orderRepo = mock(OrderRepo.class);
    ShopSystem shopSystem = new ShopSystem(productRepo, orderRepo);

    @Test
    void listProducts() {
        //GIVEN
        Map<String, Product> expected = new HashMap<>(Map.of("1", new Product("1", "Brot"), "2", new Product("2", "Milch")));
        //WHEN
        when(productRepo.listProducts()).thenReturn(expected);
        //THEN
        assertEquals(expected, shopSystem.listProducts());
        verify(productRepo).listProducts();
    }

    @Test
    void listOrders() {
        //GIVEN
        Map<String, Order> expected = new HashMap<>(Map.of("1", new Order("1")));
        //WHEN
        when(orderRepo.listOrders()).thenReturn(expected);
        //THEN
        assertEquals(expected, shopSystem.listOrders());
        verify(orderRepo).listOrders();
    }

    @Test
    void getProduct() {
        // GIVEN
        Product expected = new Product("1", "Brot");
        Product actual = shopSystem.getProduct("1");

        // WHEN
        when(productRepo.getProduct("1")).thenReturn(expected);

        // THEN
        assertEquals(expected, actual);
        verify(productRepo).getProduct("1");
    }

}