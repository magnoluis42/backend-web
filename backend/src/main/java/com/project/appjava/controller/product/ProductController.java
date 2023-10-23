package com.project.appjava.controller.product;

import com.project.appjava.dtos.product.*;
import com.project.appjava.entity.product.Product;
import com.project.appjava.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<Product> registerProduct(@RequestBody ProductRegisterDTO productRegisterDTO){
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(productRegisterDTO, ProductDTO.class);
        productService.registerProduct(productDTO);
        return new ResponseEntity<>(modelMapper.map(productDTO, Product.class), HttpStatus.CREATED);

    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> listProducts(){
        List<ProductDTO> productDTOS = productService.listProducts();
        return ResponseEntity.ok().body(productDTOS);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable(value = "id") Long id,
                                                            @RequestBody ProductIdDTO productIdDTO){
        var modelMapper = new ModelMapper();
        ProductUpdateDTO updateProduct = modelMapper.map(productIdDTO, ProductUpdateDTO.class);
        productService.updateProduct(id, updateProduct);
        return new ResponseEntity<>(modelMapper.map(updateProduct, ProductResponseDTO.class), HttpStatus.OK);
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable Long id){
        productService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
