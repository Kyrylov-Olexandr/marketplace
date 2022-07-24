package com.intellias.kyrylov.practical_test.service;

import com.intellias.kyrylov.practical_test.dto.ProductDTO;

import java.util.List;

public interface ProductService {

     ProductDTO saveProduct(ProductDTO productDTO);

     List<ProductDTO> findAllProducts();

     boolean deleteProductById(Long id);
}
