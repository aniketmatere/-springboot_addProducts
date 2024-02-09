package com.restdemo.services;

import com.restdemo.dao.ProductStorage;
import com.restdemo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductStorage productStorage;

    public List<Product> getProducts(){
        return productStorage.getAllProducts();
    }

    public boolean addProduct(Product product){
       return productStorage.addProduct(product);
    }

    public boolean updateProduct(int id, Product product){
        return productStorage.updateProduct(id, product);
    }

    public boolean updateSingleProduct(int id, Product product){
        return productStorage.updateSingleProduct(id, product);
    }

    public boolean deleteProduct(int id){
        return productStorage.deleteProduct(id);
    }


}
