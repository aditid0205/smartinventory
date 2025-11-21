package io.github.aditid0205.smartinventory.exception;

import io.github.aditid0205.smartinventory.controller.ProductController;
import io.github.aditid0205.smartinventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService productService;

    @Test
    void shouldReturn404whenProductNotFound() throws Exception {
        when(productService.getProductById(99L))
        .thenThrow(new ProductNotFoundException("Product Not Found with id: 99"));

        mockMvc.perform(get("/api/products/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errormessage").value("Product Not Found with id: 99"))
                .andExpect(jsonPath("$.status").value("404"));

        verify(productService,times(1)).getProductById(99L);
    }


}
