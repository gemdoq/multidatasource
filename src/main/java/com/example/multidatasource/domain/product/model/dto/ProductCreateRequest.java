package com.example.multidatasource.domain.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {
	private String name;
	private String price;
	private String amount;
}
