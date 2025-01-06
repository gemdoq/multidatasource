package com.example.multidatasource.domain.product.service;

import com.example.multidatasource.domain.product.model.dto.ProductResponse;
import com.example.multidatasource.domain.product.model.entity.ProductEntity;
import com.example.multidatasource.domain.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductResponse createProduct(String name, String price, String amount) {
		ProductEntity savedProductEntity = productRepository.save(ProductEntity.create(name, price, amount));
		ProductResponse res = ProductResponse.toDto(savedProductEntity);
		return res;
	}

	public ProductResponse getProduct(Long id) {
		ProductEntity foundProductEntity = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("해당 id의 상품을 찾을 수 없습니다: " + id));
		ProductResponse res = ProductResponse.toDto(foundProductEntity);
		return res;
	}

	public ProductResponse updateProduct(Long id, String name, String price, String amount) {
		ProductEntity foundProductEntity = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("해당 id의 상품을 찾을 수 없습니다: " + id));
		foundProductEntity.update(name, price, amount);
		ProductEntity updatedProductEntity = productRepository.save(foundProductEntity);
		ProductResponse res = ProductResponse.toDto(updatedProductEntity);
		return res;
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
