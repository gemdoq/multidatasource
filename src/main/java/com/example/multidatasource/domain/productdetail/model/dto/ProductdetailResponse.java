package com.example.multidatasource.domain.productdetail.model.dto;

import com.example.multidatasource.domain.productdetail.model.entity.ProductdetailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductdetailResponse {
	private Long id;
	private Long productId;
	private String details;

	public static ProductdetailResponse toDto(ProductdetailEntity productdetailEntity) {
		return ProductdetailResponse.builder()
				.id(productdetailEntity.getId())
				.productId(productdetailEntity.getProductId())
				.details(productdetailEntity.getDetails())
				.build();
	}
}
