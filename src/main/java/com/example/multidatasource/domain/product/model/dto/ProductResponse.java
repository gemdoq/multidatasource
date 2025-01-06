package com.example.multidatasource.domain.product.model.dto;

import com.example.multidatasource.domain.product.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
	private Long id;
	private String name;
	private String price;
	private String amount;

	public static ProductResponse toDto(ProductEntity productEntity) {
		return ProductResponse.builder()
				.id(productEntity.getId())
				.name(productEntity.getName())
				.price(productEntity.getPrice())
				.amount(productEntity.getAmount())
				.build();
	}
}
