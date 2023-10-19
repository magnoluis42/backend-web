package com.project.appjava.service.product;

import com.project.appjava.dtos.product.ProductDTO;
import com.project.appjava.entity.product.Product;
import com.project.appjava.repository.product.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;
    public ProductDTO register(ProductDTO productDTO){
        var modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDTO, Product.class);
        var newProduct = productRepository.save(product);
        return productDTO;
    }
}
