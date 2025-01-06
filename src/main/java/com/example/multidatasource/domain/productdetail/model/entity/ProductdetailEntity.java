package com.example.multidatasource.domain.productdetail.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_productdetail")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductdetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long productId; // ProductEntity의 ID를 참조
	private String details;

	public ProductdetailEntity(Long productId, String details) {
		this.productId = productId;
		this.details = details;
	}

	public static ProductdetailEntity create(Long productId, String details) {
		return new ProductdetailEntity(productId, details);
	}

	public void update(String details) {
		this.details = details;
	}
}
