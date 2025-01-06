package com.example.multidatasource.domain.productdetail.service;

import com.example.multidatasource.domain.productdetail.model.dto.ProductdetailResponse;
import com.example.multidatasource.domain.productdetail.model.entity.ProductdetailEntity;
import com.example.multidatasource.domain.productdetail.repository.ProductdetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductdetailService {

	private final ProductdetailRepository productdetailRepository;

	public ProductdetailService(ProductdetailRepository productdetailRepository) {
		this.productdetailRepository = productdetailRepository;
	}

	public ProductdetailResponse createProductdetail(Long productId, String details) {
		ProductdetailEntity savedProductdetailEntity = productdetailRepository.save(ProductdetailEntity.create(productId, details));
		ProductdetailResponse res = ProductdetailResponse.toDto(savedProductdetailEntity);
		return res;
	}

	public ProductdetailResponse getProductdetailByProductId(Long productId) {
		ProductdetailEntity foundProductdetailEntity = productdetailRepository.findByProductId(productId)
				.orElseThrow(() -> new EntityNotFoundException("해당 제품id의 제품상세정보를 찾을 수 없습니다: " + productId));
		ProductdetailResponse res = ProductdetailResponse.toDto(foundProductdetailEntity);
		return res;
	}

	public ProductdetailResponse updateProductdetail(Long id, String details) {
		ProductdetailEntity foundProductdetailEntity = productdetailRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("해당 제품id의 제품상세정보를 찾을 수 없습니다: " + id));
		foundProductdetailEntity.update(details);
		ProductdetailEntity updatedProductdetailEntity = productdetailRepository.save(foundProductdetailEntity);
		ProductdetailResponse res = ProductdetailResponse.toDto(updatedProductdetailEntity);
		return res;
	}

	public void deleteProductdetail(Long id) {
		productdetailRepository.deleteById(id);
	}
}
