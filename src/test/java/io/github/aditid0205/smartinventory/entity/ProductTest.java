package io.github.aditid0205.smartinventory.entity;


import io.github.aditid0205.smartinventory.Entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {
    @Test
   public void shouldCreateProductWithValidData(){
        //Arranging the data
        String name = "Laptop";
        String brand = "DELL";
        Double price = 250.00;
        String sku = "LAP-001";

        //Act
        Product product = new Product(name,brand,price,sku);

        //Assert
        assertNotNull(product);
        assertEquals(name, product.getName());
        assertEquals(brand, product.getBrand());
        assertEquals(price, product.getPrice());
        assertEquals(sku, product.getSku());
    }
}
