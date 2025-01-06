package com.example.multidatasource.domain.product.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String price;
	private String amount;

	public ProductEntity(String name, String price, String amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public static ProductEntity create(String name, String price, String amount) {
		return new ProductEntity(name, price, amount);
	}

	public void update(String name, String price, String amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
}
