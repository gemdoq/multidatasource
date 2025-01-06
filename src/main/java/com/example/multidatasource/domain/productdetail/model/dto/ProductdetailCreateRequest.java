package com.example.multidatasource.domain.productdetail.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductdetailCreateRequest {
	private Long productId;
	private String details;
}
