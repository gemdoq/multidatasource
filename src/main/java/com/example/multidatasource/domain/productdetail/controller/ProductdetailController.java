package com.example.multidatasource.domain.productdetail.controller;

import com.example.multidatasource.domain.productdetail.model.dto.ProductdetailCreateRequest;
import com.example.multidatasource.domain.productdetail.model.dto.ProductdetailResponse;
import com.example.multidatasource.domain.productdetail.model.dto.ProductdetailUpdateRequest;
import com.example.multidatasource.domain.productdetail.service.ProductdetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productdetail")
public class ProductdetailController {

	private final ProductdetailService productdetailService;

	public ProductdetailController(ProductdetailService productdetailService) {
		this.productdetailService = productdetailService;
	}

	@PostMapping
	public ResponseEntity<ProductdetailResponse> createProductdetail(@RequestBody ProductdetailCreateRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productdetailService.createProductdetail(req.getProductId(), req.getDetails()));
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductdetailResponse> getProductdetailByProductId(@PathVariable Long productId) {
		return ResponseEntity.ok(productdetailService.getProductdetailByProductId(productId));
	}

	@PutMapping("/product/{productId}")
	public ResponseEntity<ProductdetailResponse> updateProductdetail(@PathVariable Long productId, @RequestBody ProductdetailUpdateRequest req) {
		return ResponseEntity.ok(productdetailService.updateProductdetail(productId, req.getDetails()));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductdetail(@PathVariable Long id) {
		productdetailService.deleteProductdetail(id);
		return ResponseEntity.noContent().build();
	}
}
