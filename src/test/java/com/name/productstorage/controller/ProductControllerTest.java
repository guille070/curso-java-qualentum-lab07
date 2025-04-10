package com.name.productstorage.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.name.productstorage.model.Product;
import com.name.productstorage.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void test_getAllProducts() throws Exception {

        List<Product> productTestList = new ArrayList<>();
        Product product = new Product();

        product.setId(1);
        product.setName("Product name");
        product.setDescription("Product description");
        product.setPrice(10.00);
        productTestList.add(product);

        when(productService.getAllProducts()).thenReturn(productTestList);

        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/products"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product name"));
        
    }

    @Test
    void test_getProductById() throws Exception {

        Product product = new Product();

        product.setId(1);
        product.setName("Product name");
        product.setDescription("Product description");
        product.setPrice(10.00);

        when(productService.getProductById(1)).thenReturn(Optional.of(product));

        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/products/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product name"));
        
    }
}
