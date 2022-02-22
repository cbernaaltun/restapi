package com.bernaaltun.restapiexample.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bernaaltun.restapiexample.model.Product;
import com.bernaaltun.restapiexample.model.ProductListResponse;
import com.bernaaltun.restapiexample.model.ProductResponse;
import com.bernaaltun.restapiexample.repository.ProductRepository;
import com.bernaaltun.restapiexample.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(value = "/save",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> saveProduct(
			@RequestBody Product product,
			HttpServletRequest request
	) 
	{
		Product model = productService.saveProduct(product);
		HttpHeaders header = new HttpHeaders();
		header.set(header.LOCATION, request.getRequestURL().append("/").append(model.getProductId()).toString());
		return new ResponseEntity<>(model, header, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{productId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
	public ResponseEntity<Product> updateProduct(
			@RequestBody Product product,
			HttpServletRequest request
	) {
		Product model = productService.saveProduct(product);
		HttpHeaders header = new HttpHeaders();
		header.set(header.LOCATION, request.getRequestURL().append("/").append(model.getProductId()).toString());
		return new ResponseEntity<>(model, header, HttpStatus.CREATED);
    }
	  
	@GetMapping(value = "/{productId}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable long productId){
		Optional<Product> model = productService.getProduct(productId);
		ProductResponse response = new ProductResponse();
		if(model.isPresent()) {
			response.setMessage("Aradığınız ürün başarıyla döndürülmüştür.");
			response.setProduct(model.get());
			return ResponseEntity.ok().body(response);
		}
		response.setMessage("Aradığınız kriterlerde bir ürün bulunamamıştır!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@DeleteMapping ("/{productId}")	
	public ResponseEntity<Void> deleteProduct(@PathVariable long productId){
		productService.deleteProduct(productId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("")
	public ResponseEntity<ProductListResponse> getAllProduct() {
	    List<Product> products = productService.getAllProduct();
	    ProductListResponse response = new ProductListResponse();
	    int count = products.size();
	    if(count == 0) {
			response.setMessage("Veritabanında kayıtlı ürün bulunmamaktadır!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	    response.setMessage(String.format("Veritabanınızda kayıtlı %d adet ürün listelenmiştir.", count));
	    response.setProducts(products);
	    return ResponseEntity.ok().body(response);
	}
}
