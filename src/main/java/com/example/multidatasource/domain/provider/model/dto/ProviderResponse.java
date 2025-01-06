package com.example.multidatasource.domain.provider.model.dto;

import com.example.multidatasource.domain.provider.model.entity.ProviderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderResponse {
	private Long id;
	private String name;
	private Long productId;

	public static ProviderResponse toDto(ProviderEntity providerEntity) {
		return ProviderResponse.builder()
				.id(providerEntity.getId())
				.name(providerEntity.getName())
				.productId(providerEntity.getProductId())
				.build();
	}
}
