package io.github.aditid0205.smartinventory.controller;

import io.github.aditid0205.smartinventory.entity.Product;
import io.github.aditid0205.smartinventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product("Laptop","DELL", BigDecimal.valueOf(250.00),"LAP-001");
        testProduct.setProductId(1L);
    }

    @Test
    void shouldCreateProduct() throws Exception {

        when(productService.createProduct(any(Product.class))).thenReturn(testProduct);

        String productJson = """
                {
                 "name": "Laptop",
                 "brand": "DELL",
                 "price": 250.00,
                 "sku": "LAP-001"
                }
                """;
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Laptop"));

        verify(productService, times(1)).createProduct(any(Product.class));

    }

    @Test
    void shouldGetProductById() throws Exception {

        when(productService.getProductById(1L)).thenReturn(testProduct);
        mockMvc.perform(get("/api/products/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));

        verify(productService, times(1)).getProductById(1L);

    }

    @Test
    void shouldGetAllProducts() throws Exception {
        Product product2 = new Product("iPad", "APPLE", BigDecimal.valueOf(350.00), "iPad-001");
        when(productService.getAllProducts()).thenReturn(Arrays.asList(testProduct, product2));
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[1].name").value("iPad"));

        verify(productService, times(1)).getAllProducts();


    }
}
