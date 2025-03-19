package com.warehouse.service;

import com.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.model.dto.ProductDTO;
import com.warehouse.model.entity.Product;
import com.warehouse.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author igoradulyan on 12/13/24
 * @project IntelliJ IDEA
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    ProductDTO productDTO;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @BeforeAll
    public void setUp() {
        productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setProductId("1");
        productDTO.setDescription("Test Description");
        productDTO.setPrice("100");
        productDTO.setQuantity("10");
    }

    @Test
    @Order(1)
    public void addProductTest() {
      Product productActual = productService.addProduct(productDTO);
      productDTO.setProductId(productActual.getProductId());
      Assertions.assertEquals(productDTO.getName(), productActual.getName());
      Assertions.assertTrue(productRepository.count() > 0);
    }

    @Test
    @Order(2)
    public void getProductByNameTest() {
        Product product = productService.getProductByName("Test Product");
        Assertions.assertEquals(productDTO.getName(), product.getName());
    }

    @Test
    @Order(3)
    public void getProductByNameFailTest() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductByName("Test Product 2");
        });
    }

    @Test
    @Order(4)
    void updateProduct() {
       productDTO.setName("Updated Product");
       productService.updateProduct(productDTO);
       Product product = productService.getProductByName(productDTO.getName());
       Assertions.assertEquals(productDTO.getName(), product.getName());
    }

    @Test
    @Order(5)
    void getProductById() {
        ProductDTO product = productService.getProductById(productDTO.getProductId());
        Assertions.assertEquals(productDTO.getName(), product.getName());
    }

    @Test
    @Order(6)
    void getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        Assertions.assertFalse(products.isEmpty());
    }

    @Test
    void deleteProduct() {
    }

    @AfterAll
    public void tearDown() {
        productRepository.deleteAll();
    }
}
