package com.restdemo.controllers;

import com.restdemo.models.Product;
import com.restdemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> allProducts = productService.getProducts();
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(allProducts, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        ResponseEntity<String> responseEntity;
        if(productService.addProduct(product))
            responseEntity = new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
        else
            responseEntity = new ResponseEntity<>("Product addition failed", HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @PutMapping(value = "/product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@PathVariable(name = "id") int id,
                                                @RequestBody Product product){
        ResponseEntity<String> responseEntity;
        if(productService.updateProduct(id, product))
            responseEntity = new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        else
            responseEntity = new ResponseEntity<>("Product updation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @PatchMapping(value = "/product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateparticularField(@PathVariable(name = "id") int id,
                                             @RequestBody Product product){
        ResponseEntity<String> responseEntity;
        if(productService.updateSingleProduct(id, product))
            responseEntity = new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        else
            responseEntity = new ResponseEntity<>("Product updation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @DeleteMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id){
        ResponseEntity<String> responseEntity;
        if(productService.deleteProduct(id))
            responseEntity = new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        else
            responseEntity = new ResponseEntity<>("Product deletion failed", HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
