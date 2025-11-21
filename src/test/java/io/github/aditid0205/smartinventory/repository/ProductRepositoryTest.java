package io.github.aditid0205.smartinventory.repository;

import io.github.aditid0205.smartinventory.Entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository ;

    @Test
    public void shouldSaveProduct(){

        Product product = new Product("Laptop", "DELL", BigDecimal.valueOf(250.00), "LAP-001");

        Product saved = productRepository.save(product);

        assertNotNull(saved);
        assertNotNull(saved.getProductId());
        assertEquals(saved.getName(), product.getName());
    }

    @Test
    public void shouldFindProductById(){
        Product product = new Product("Mouse", "Logitech", BigDecimal.valueOf(25.00), "MOU-001");
        Product saved = productRepository.save(product);

        Optional<Product> found = productRepository.findById(saved.getProductId());

        assertTrue(found.isPresent());
        assertEquals(saved.getProductId(), found.get().getProductId());
        assertEquals(saved.getName(), found.get().getName());

    }

    @Test
    public void shouldDeleteProduct(){
        Product product = new Product("Headset", "Sony", BigDecimal.valueOf(150.00), "HEAD-001");
        Product saved = productRepository.save(product);

        productRepository.deleteById(saved.getProductId());

        assertFalse(productRepository.findById(saved.getProductId()).isPresent());

    }

    @Test
    public void shouldFindProductBySku(){
        productRepository.save(new Product("Tablet", "Apple", BigDecimal.valueOf(500.00), "TAB-001"));

       Optional<Product> found = productRepository.findBySku("TAB-001");
       assertTrue(found.isPresent());
        assertEquals("Tablet", found.get().getName());
   }
}
