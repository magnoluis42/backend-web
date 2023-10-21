package com.project.appjava.service.product;

import com.project.appjava.dtos.product.ProductDTO;
import com.project.appjava.dtos.user.UserDTO;
import com.project.appjava.entity.product.Product;
import com.project.appjava.entity.user.User;
import com.project.appjava.repository.product.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ProductDTO> findAll(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Product product: products){
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTOS.add(productDTO);
        }

        return productDTOS;


    }
}
