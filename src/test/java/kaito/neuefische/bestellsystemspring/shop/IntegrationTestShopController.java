package kaito.neuefische.bestellsystemspring.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
}
