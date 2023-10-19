package com.project.appjava.controller.product;

import com.project.appjava.dtos.product.ProductDTO;
import com.project.appjava.dtos.product.ProductRegisterDTO;
import com.project.appjava.entity.product.Product;
import com.project.appjava.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<Product> register(@RequestBody ProductRegisterDTO productRegisterDTO){
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(productRegisterDTO, ProductDTO.class);
        var newProduct = productService.register(productDTO);
        return new ResponseEntity<>(modelMapper.map(productDTO, Product.class), HttpStatus.CREATED);

    }

    @GetMapping("/find")
    public String find(){
        return "<h1>Rota aberta</h1>";
    }
}
