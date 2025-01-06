package com.example.multidatasource.domain.provider.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderUpdateRequest {
	private String name;
	private Long productId;
}
