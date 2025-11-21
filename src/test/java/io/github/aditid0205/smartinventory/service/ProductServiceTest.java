package io.github.aditid0205.smartinventory.service;

import io.github.aditid0205.smartinventory.entity.Product;
import io.github.aditid0205.smartinventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    private Product testProduct;
    @BeforeEach
    void setUp() {
        testProduct = new Product("Laptop", "DELL",BigDecimal.valueOf(250.00),"LAP-001");
    }

    @Test
    public void shouldCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        Product created = productService.createProduct(testProduct);

        assertNotNull(created);
        assertEquals("Laptop", created.getName());
        verify(productRepository,times(1)).save(testProduct);

    }
}
