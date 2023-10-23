package com.project.appjava.service.product;

import com.project.appjava.dtos.product.ProductDTO;
import com.project.appjava.dtos.product.ProductUpdateDTO;
import com.project.appjava.entity.product.Product;
import com.project.appjava.repository.product.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public ProductDTO registerProduct(ProductDTO productDTO){
        var modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
        return productDTO;
    }
    public List<ProductDTO> listProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Product product: products){
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
    public ProductUpdateDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO){
        productUpdateDTO.setId(id);
        var modelMapper = new ModelMapper();
        Product product = modelMapper.map(productUpdateDTO, Product.class);
        productRepository.save(product);
        return productUpdateDTO;
    }

    public void removeProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new EntityNotFoundException("NÃO EXISTE");
        }
        productRepository.deleteById(id);
    }
}
