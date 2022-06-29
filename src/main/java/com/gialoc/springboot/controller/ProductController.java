package com.gialoc.springboot.controller;

import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.model.Product;
import com.gialoc.springboot.model.ResponseObject;
import com.gialoc.springboot.repository.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // get products
    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return this.productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    // get products by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }

    // get products by categoryid
    @GetMapping("/v1/products/{categoryid}")
    List<Product> findByCategoryid(@PathVariable int categoryid) {
        return productRepository.findByCategoryid(categoryid);
    }

    // get products by brandid
    @GetMapping("/v2/products/{brandid}")
    List<Product> findByBrandid(@PathVariable int brandid) {
        return productRepository.findByBrandid(brandid);
    }

    // save products
    @PostMapping("/products")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        newProduct.setCreated_on_utc(new Date());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "Post Product successfully", productRepository.save(newProduct)));
    }

    // update products
    @PutMapping("/products/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updateProduct = productRepository.findById(id).map(oldProduct -> {
            oldProduct.setName(product.getName());
            oldProduct.setImage(product.getImage());
            oldProduct.setShort_description(product.getShort_description());
            oldProduct.setTrademark(product.getTrademark());
            oldProduct.setCate(product.getCate());
            oldProduct.setOrigin(product.getOrigin());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setDiscount(product.getDiscount());
            oldProduct.setInsurance(product.getInsurance());
            oldProduct.setUpdated_on_utc(new Date());
            return productRepository.save(oldProduct);
        }).orElseGet(() -> {
            product.setId(id);
            return productRepository.save(product);
        });

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok", "Update Product successfully", updateProduct));
    }

    // delete products
    @DeleteMapping("products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

        this.productRepository.delete(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @GetMapping("/search")
    public List<Product> findAll(@RequestParam Optional<String> name) {
        return productRepository.findByName(name.orElse(null));
    }
}
