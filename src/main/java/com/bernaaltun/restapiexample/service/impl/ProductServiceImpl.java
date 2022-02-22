package com.bernaaltun.restapiexample.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernaaltun.restapiexample.model.Product;
import com.bernaaltun.restapiexample.repository.ProductRepository;
import com.bernaaltun.restapiexample.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public Optional<Product> getProduct(Long productId) {
		if(productId != null)
			return Optional.of(productRepository.findById(productId)).orElse(null);
		else 
			return Optional.empty();
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		Optional<Product> model = getProduct(product.getProductId());
		if(model.isPresent()) {
			model.get().setProductName(product.getProductName());
			model.get().setCategory(product.getCategory());
			model.get().setProductNumber(product.getProductNumber());
		}
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}
}
