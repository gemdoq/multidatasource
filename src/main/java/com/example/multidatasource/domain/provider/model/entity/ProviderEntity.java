package com.example.multidatasource.domain.provider.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_provider")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProviderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Long productId; // ProductEntity의 ID를 참조

	public ProviderEntity(String name, Long productId) {
		this.name = name;
		this.productId = productId;
	}

	public static ProviderEntity create(String name, Long productId) {
		return new ProviderEntity(name, productId);
	}

	public void update(String name, Long productId) {
		this.name = name;
		this.productId = productId;
	}
}
