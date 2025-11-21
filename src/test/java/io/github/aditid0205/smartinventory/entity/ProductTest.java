package io.github.aditid0205.smartinventory.entity;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class ProductTest {
    @Test
   public void shouldCreateProductWithValidData(){
        //Arranging the data
        String name = "Laptop";
        String brand = "DELL";
        BigDecimal price = BigDecimal.valueOf(250.00);
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

    @Test
    public void shouldNotAllowNullOrEmptyName(){
        //Arrange
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Product product = new Product(null,"DELL",BigDecimal.valueOf(250.00),"LAP-001");

        //Act
        Set< ConstraintViolation<Product> > violations =  validator.validate(product);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v->v.getPropertyPath().toString().equals("name")));

        Product productWithEmptyName = new Product("","DELL",BigDecimal.valueOf(250.00),"LAP-001");
        Set<ConstraintViolation<Product>> violations2 = validator.validate(productWithEmptyName);

        assertFalse(violations2.isEmpty());
        assertTrue(violations2.stream().anyMatch(v->v.getPropertyPath().toString().equals("name")));



    }
}
