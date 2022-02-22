package com.bernaaltun.restapiexample.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -260526804315911461L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)
	private Long productId;

	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "category")
	private String category;	

	@Column(name = "product_number")
	private String productNumber;
	
	public Product() {
		
	}
	
	public Product(Long productId, String productName, String category, String productNumber) {
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.productNumber = productNumber;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", productNumber=" + productNumber + "]";
	}
}
