package com.intellias.kyrylov.practical_test.service.impl;

import com.intellias.kyrylov.practical_test.dto.ProductDTO;
import com.intellias.kyrylov.practical_test.dto.UserDTO;
import com.intellias.kyrylov.practical_test.model.Product;
import com.intellias.kyrylov.practical_test.model.User;
import com.intellias.kyrylov.practical_test.repository.ProductRepository;
import com.intellias.kyrylov.practical_test.service.ProductService;
import com.intellias.kyrylov.practical_test.util.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService, Mapper<Product, ProductDTO> {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return convertAllToDTO(productRepository.findAll());
    }

    @Override
    public boolean deleteProductById(Long id) {
        try {
            Product product = productRepository.findById(id).orElseThrow();
            productRepository.delete(product);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    @Override
    public List<ProductDTO> convertAllToDTO(Collection<Product> products) {
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
    }

    @Override
    public List<Product> convertAllToEntity(Collection<ProductDTO> dtos) {
        return dtos.stream().map(productDTO -> modelMapper.map(productDTO, Product.class)).toList();
    }
}
