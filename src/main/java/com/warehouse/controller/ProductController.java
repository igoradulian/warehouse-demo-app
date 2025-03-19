package com.warehouse.controller;

import com.warehouse.model.dto.ProductDTO;
import com.warehouse.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author igoradulyan on 12/13/24
 * @project IntelliJ IDEA
 */

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/register-product")
    public String addProduct(Model model){

        model.addAttribute("product", new ProductDTO());
        return "add-product";
    }


    @PostMapping("/products-process")
    public String processProduct(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "add-product";
        }
        productService.addProduct(productDTO);
        return "redirect:/products";
    }

    @PostMapping("/products-update")
    public String updateProduct(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "add-product";
        }
        productService.updateProduct(productDTO);

        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model){

        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/{productId}/update")
    public String updateProduct(@PathVariable ("productId") String productId, Model model){
        ProductDTO productDTO = productService.getProductById(productId);
        model.addAttribute("product", productDTO);
        return "update-product";
    }

    @GetMapping("/products/{productId}/delete")
    public String deleteProduct(@PathVariable ("productId") String productId){
        productService.deleteProduct(productId);
        return "redirect:/products";
    }
}
