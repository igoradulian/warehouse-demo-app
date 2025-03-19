package com.warehouse.service;

import com.warehouse.model.dto.ProductDTO;
import com.warehouse.model.entity.Product;

import java.util.List;

/**
 * @author igoradulyan on 12/13/24
 * @project IntelliJ IDEA
 */
public interface ProductService {

    public Product addProduct(ProductDTO product);
    public void updateProduct(ProductDTO product);
    public void deleteProduct(String productId);
    public ProductDTO getProductById(String productId);
    public List<ProductDTO> getAllProducts();
    public List<Product> getProductsByCategory(String category);
    public Product getProductByName(String name);
}
