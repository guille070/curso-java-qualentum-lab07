package com.name.productstorage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.name.productstorage.model.Product;
import com.name.productstorage.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    void test_getAllProducts() {
        List<Product> productTestList = new ArrayList<>();
        Product product = new Product();

        product.setId(1);
        product.setName("Product name");
        product.setDescription("Product description");
        product.setPrice(10.00);
        productTestList.add(product);

        when(productRepository.findAll()).thenReturn(productTestList);

        List<Product> productList = productService.getAllProducts();
        
        assertEquals(productList, productTestList);
    }

    @Test
    void test_getProductById() {
        Product product = new Product();

        product.setId(1);
        product.setName("Product name");
        product.setDescription("Product description");
        product.setPrice(10.00);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product productById = productService.getProductById(1).get();
        
        assertEquals(productById, product);
    }
}
