package com.ecommerce.project.service;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public String deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND,"product id not found"));
        productRepository.delete(product);

        return "Product with ProductId: \" + productId +\n" +
                "                \" deleted successfully!!";
    }

    @Override
    public Product updateProduct(Product product, Long productId) {
        Product existingproduct =productRepository.findById(productId)
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND,"product is not found"));
        product.setProductId(productId);

        return productRepository.save(product);
    }
}
