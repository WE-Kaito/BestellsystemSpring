package kaito.neuefische.bestellsystemspring.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestShopController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ShopSystem shopSystem;


    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DirtiesContext
    void test_getAllProducts() throws Exception {
        String expectedMap = objectMapper.writeValueAsString(Map.of(
                "1", new Product("1", "Brot"),
                "2", new Product("2", "Milch"),
                "3", new Product("3", "Eier")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shop/products"))
                .andExpect(MockMvcResultMatchers.content().json(expectedMap));

    }

    @Test
    @DirtiesContext
    void test_getProduct() throws Exception {
        String expectedProduct = """
                {
                "productId": "1",
                "name": "Brot"
        }""";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shop/products/{productId}", "1"))
                .andExpect(MockMvcResultMatchers.content().json(expectedProduct));
    }

    @Test
    @DirtiesContext
    void test_noOrders_getAllOrders() throws Exception {
        String expectedMap = objectMapper.writeValueAsString(new HashMap<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shop/orders"))
                .andExpect(MockMvcResultMatchers.content().json(expectedMap));
    }

    @Test
    @DirtiesContext
    void test__1Order_AllOrders() throws Exception {
        String expectedOrders = objectMapper.writeValueAsString(Map.of(
                "1", new Order("1")
        ));

        shopSystem.addOrder("1", new ArrayList<>(List.of("1", "2", "3")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shop/orders"))
                .andExpect(MockMvcResultMatchers.content().json(expectedOrders));
    }

    @Test
    @DirtiesContext
    void test_getOrderById() throws Exception {
        String expectedOrder = """
                {
                    "orderId": "123",
                    "order": {
                        "1": {
                            "productId": "1",
                            "name": "Brot"
                        },
                        "2": {
                            "productId": "2",
                            "name": "Milch"
                        },
                        "3": {
                            "productId": "3",
                            "name": "Eier"
                        }
                    }
                }""";

        shopSystem.addOrder("123", new ArrayList<>(List.of("1", "2", "3")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shop/orders/{orderId}", "123"))
                .andExpect(MockMvcResultMatchers.content().json(expectedOrder));
    }

    @Test
    @DirtiesContext
    void test_addOrder() throws Exception {
        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/orders/{orderId}", "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1,2,3]")
                );
        mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/orders/{orderId}", "12341")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[2,2,2]")
        );

        //Then

        Assertions.assertEquals(2, shopSystem.listOrders().size());
    }

    @Test
    @DirtiesContext
    void test_deleteOrder() throws Exception {
        // Given
        shopSystem.addOrder("123", new ArrayList<>(List.of("1", "2", "3")));

        // When
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/shop/orders/{orderId}", "123"));

        // Then
        Assertions.assertEquals(0, shopSystem.listOrders().size());
    }


}
