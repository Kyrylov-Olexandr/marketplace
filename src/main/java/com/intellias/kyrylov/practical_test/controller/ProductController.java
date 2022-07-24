package com.intellias.kyrylov.practical_test.controller;

import com.intellias.kyrylov.practical_test.dto.ProductDTO;
import com.intellias.kyrylov.practical_test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO savedProductDTO = productService.saveProduct(productDTO);
            return ResponseEntity.ok(savedProductDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOList = productService.findAllProducts();
        return ResponseEntity.ok(productDTOList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        boolean deleted = productService.deleteProductById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
