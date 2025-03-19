package com.warehouse.service;

import com.warehouse.exceptions.ProductAlreadyExistException;
import com.warehouse.exceptions.ProductNotFoundException;
import com.warehouse.model.dto.ProductDTO;
import com.warehouse.model.entity.Product;
import com.warehouse.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author igoradulyan on 12/13/24
 * @project IntelliJ IDEA
 */
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        if(productRepository.findProductByName(productDTO.getName()) != null) {
            throw new ProductAlreadyExistException("Product already exists");
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Product product = modelMapper.map(productDTO, Product.class);
        product.setProductId(UUID.randomUUID().toString());

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findProductByProductId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setActive(productDTO.isActive());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findProductByProductId(productId);
        productRepository.delete(product);
    }

    @Override
    public ProductDTO getProductById(String productId) {
        if(productRepository.findProductByProductId(productId) == null) {
            throw new ProductNotFoundException("Product not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper.map(productRepository.findProductByProductId(productId), ProductDTO.class);
    }

    @Override
    public Product getProductByName(String name) {
        if(productRepository.findProductByName(name) == null) {
            throw new ProductNotFoundException("Product not found");
        }
        return productRepository.findProductByName(name);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List <ProductDTO> productDTOList = new ArrayList<>();
        for(Product product : productRepository.findAll()) {
           productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }
}
