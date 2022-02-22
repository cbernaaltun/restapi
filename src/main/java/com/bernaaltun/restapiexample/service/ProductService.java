package com.bernaaltun.restapiexample.service;

import java.util.List;
import java.util.Optional;

import com.bernaaltun.restapiexample.model.Product;

public interface ProductService {
	Optional<Product> getProduct(Long productId);
	List<Product> getAllProduct();
	Product saveProduct(Product product);
	void deleteProduct(Long productId);
}
